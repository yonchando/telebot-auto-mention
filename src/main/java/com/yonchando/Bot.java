package com.yonchando;

import com.yonchando.commands.BotCommand;
import com.yonchando.model.User;
import com.yonchando.services.UserService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Bot extends TelegramLongPollingBot {

    private Long userId;
    private static Long chatId;

    private final UserService userService = new UserService();
    private final BotCommand command = new BotCommand();
    private boolean screaming = false;

    @Override
    public void onUpdateReceived(Update update) {
        var msg = update.getMessage();

        if (msg != null) {
            var text = msg.getText();
            var msgUser = msg.getFrom();
            Chat chat = update.getMessage().getChat();
            chatId = chat.getId();
            if (chat.isSuperGroupChat()) {
                try {
                    User user = userService.show(msgUser.getId(), chatId);

                    if (user.getUserId() == null) {
                        user.setUserId(msgUser.getId());
                        user.setChatId(chat.getId());
                        user.setFirstName(msgUser.getFirstName());
                        user.setLastName(msgUser.getLastName());
                        user.setMessageId(msg.getMessageId());
                        user.setUsername(msgUser.getUserName());
                        userService.save(user);
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (msg.isCommand()) {
                System.out.println(text);
                switch (text) {
                    case "/start" -> sendText(command.start());
                    case "/mention_all", "/mention_all@auto_mention_bot" -> {
                        if (chat.isSuperGroupChat()) {
                            try {
                                List<User> users = userService.getList(update);
                                sendText(command.mentionAll(users));
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            sendText("Sorry, I'm not in group chat and mention only to user has been active chat one.");
                        }
                    }
                    case "/mini-game" -> sendText(command.game());
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

    public void sendText(String what) {
        SendMessage cm = SendMessage.builder().chatId(chatId.toString()).text(what).build();
        try {
            execute(cm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
