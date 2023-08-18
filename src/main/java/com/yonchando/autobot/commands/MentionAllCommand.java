package com.yonchando.autobot.commands;

import com.yonchando.autobot.interfaces.BotInterface;
import com.yonchando.autobot.model.User;
import com.yonchando.autobot.services.UserService;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

public class MentionAllCommand implements BotInterface {

    private final UserService userService = new UserService();

    @Override
    public String send(Message message) {
        if (message.getChat().isSuperGroupChat() || message.getChat().isGroupChat()) {
            List<User> users = userService.getList(message);

            StringBuilder text = new StringBuilder();

            if (!users.isEmpty()) {
                for (User user: users){
                    text.append("<a href=\"tg://user?id=")
                        .append(user.getUserId())
                        .append("\">")
                        .append(user.getFirstName())
                        .append(user.getLastName() != null ? " " + user.getLastName() : "")
                        .append(" ")
                        .append("</a>");
                }
            } else
                text.append("No user active.");

            return text.toString();
        } else {
            return "Sorry, I'm not in group chat and mention only to user has been active chat one\\.";
        }
    }

    @Override
    public BotCommand getBotCommand() {
        BotCommand botCommand = new BotCommand();
        botCommand.setCommand("/mention_all");
        botCommand.setDescription("To mention all your friend active in group chat.");

        return botCommand;
    }

    @Override
    public String parseMode() {
        return ParseMode.HTML;
    }
}
