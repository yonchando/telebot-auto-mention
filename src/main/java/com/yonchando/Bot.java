package com.yonchando;

import com.yonchando.database.DBConnect;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@EqualsAndHashCode(callSuper = true)
@Data
public class Bot extends TelegramLongPollingBot {

    private Long userId;
    private Long chatId;

    private boolean screaming = false;

    private final DBConnect dbConnect;

    @Override
    public void onUpdateReceived(Update update) {
        var msg = update.getMessage();

        if(msg != null){
            var text = msg.getText();
            chatId = msg.getChatId();

            if (msg.isCommand()) {
                if (text.equals("/start")) {
                    start();
                }
            }
        }
    }

    @Override
    public String getBotToken() {
        return System.getenv("BOT_TOKEN");
    }

    @Override
    public String getBotUsername() {
        return "auto_mention_bot";
    }

    private void start() {
        String text = "Hi, I'm Auto Mention Bot. \n" +
                "I'm here to help you to mention all member in group except user who ignore this group. \n" +
                "This is some command you can use. \n" +
                "/all - Mention all member \n" +
                "/ignore_me - ";
        sendText(text);
    }

    public void sendText(String what) {
        SendMessage cm = SendMessage.builder()
                                    .chatId(chatId.toString())
                                    .text(what)
                                    .build();
        try {
            execute(cm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
