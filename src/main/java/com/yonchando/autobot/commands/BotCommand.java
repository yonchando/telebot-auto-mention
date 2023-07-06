package com.yonchando.autobot.commands;

import com.yonchando.autobot.model.User;
import com.yonchando.autobot.services.UserService;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.sql.SQLException;
import java.util.List;

public class BotCommand {

    private final UserService userService = new UserService();

    public String start() {
        System.out.println("Start Command");

        return "Hi, I'm Auto Mention Bot. \n" +
                "I'm here to help you to mention all member in group except user who ignore this group. \n" +
                "This is some command you can use. \n" +
                "/mention_all - Mention all member \n" +
                "/ignore_me - You don't get mention when someone is call command /all \n" +
                "/tov_na - Tov na is menu that you give me to choose one for you. \n " +
                "/c_ey - C ey is menu that you give me to choose one for you.";
    }

    public String mentionAll(Update update) {

        if (update.getMessage().getChat().isSuperGroupChat()) {
            List<User> users = userService.getList(update);

            System.out.println("Mention all users");
            StringBuilder text = new StringBuilder();

            if (!users.isEmpty()) {
                for (User user : users) {
                    text.append("@").append(user.getUsername()).append(", ");
                }
            } else
                text.append("No user active.");

            return text.toString();

        } else {
            return "Sorry, I'm not in group chat and mention only to user has been active chat one.";
        }

    }

    public String tovNa(String text) {
        return "You don't know what to choose. Need my help to choose for your, Okay! Sent me all your favorite lists\n" +
                "Here is the format \n\n" +
                "item 1\n" +
                "item 2\n" +
                "item 3\n";
    }

    public String ignoreMe() {
        userService.ignoreMe();
        return "I will not include you in mentions";
    }
}
