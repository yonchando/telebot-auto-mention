package com.yonchando.autobot.commands;

import com.yonchando.autobot.model.User;

import java.util.List;

public class BotCommand {
    public String start() {
        System.out.println("Start Command");

        String text = "Hi, I'm Auto Mention Bot. \n" +
                "I'm here to help you to mention all member in group except user who ignore this group. \n" +
                "This is some command you can use. \n" +
                "/all - Mention all member \n" +
                "/ignore_me - You don't get mention when someone is call command /all";
        return text;
    }

    public String mentionAll(List<User> users) {
        System.out.println("Mention all users");
        StringBuilder text = new StringBuilder();

        if (!users.isEmpty()) {

            for (User user : users) {
                text.append("@").append(user.getUsername()).append(", ");
            }
        }else
            text.append("No user active.");

        return text.toString();
    }

    public String game() {
        return null;
    }
}
