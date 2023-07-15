package com.yonchando.autobot;

import com.yonchando.autobot.commands.*;
import com.yonchando.autobot.interfaces.BotInterface;
import com.yonchando.autobot.services.UserService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;
import java.util.HashMap;

@EqualsAndHashCode(callSuper = true)
@Data
public class Bot extends TelegramLongPollingBot {

    private final UserService userService = new UserService();
    private final LuckyDrawCommand command = new LuckyDrawCommand();

    private boolean isStartLuckyDraw = false;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            Message message = update.getMessage();

            Long chatId = message.getChatId();

            userService.saveIfNotExist(
                    message.getFrom(),
                    message
            );

            String username = getBotUsername();

            String text = message.getText();

            HashMap<String, BotInterface> commandList = new HashMap<>();

            commandList.put("/start", new StartCommand());
            commandList.put("/start@" + username, new StartCommand());

            commandList.put("/mention_all", new MentionAllCommand());
            commandList.put("/mention_all@" + username, new MentionAllCommand());

            commandList.put("/ignore_me", new IgnoreMeCommand());
            commandList.put("/ignore_me@" + username, new IgnoreMeCommand());

            commandList.put("/in", new InCommand());
            commandList.put("/in@" + username, new InCommand());

            if (message.isCommand()) {
                if (commandList.get(text) == null)
                    sendText(chatId, "The command is not found!");
                else {
                    sendText(chatId, commandList.get(text).run(message));
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
        return System.getenv("BOT_USERNAME");
    }

    public void sendText(Long chatId, String what) {
        SendMessage cm = SendMessage.builder().chatId(chatId.toString()).text(what).build();
        try {
            execute(cm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
