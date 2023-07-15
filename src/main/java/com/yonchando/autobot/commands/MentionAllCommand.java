package com.yonchando.autobot.commands;

import com.yonchando.autobot.interfaces.BotInterface;
import com.yonchando.autobot.model.User;
import com.yonchando.autobot.services.UserService;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public class MentionAllCommand implements BotInterface {

    private final UserService userService = new UserService();

    @Override
    public String run(Message message) {
        if (message.getChat().isSuperGroupChat() || message.getChat().isGroupChat()) {
            List<User> users = userService.getList(message);

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
}
