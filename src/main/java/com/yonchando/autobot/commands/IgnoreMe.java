package com.yonchando.autobot.commands;

import com.yonchando.autobot.interfaces.BotInterface;
import com.yonchando.autobot.services.UserService;
import org.telegram.telegrambots.meta.api.objects.Message;

public class IgnoreMe implements BotInterface {

    private final UserService userService = new UserService();

    @Override
    public String run(Message message) {
        if (userService.ignoreMe(message.getFrom().getId(), message.getChatId()))
            return "You are not include in mention.";

        return "You already not include in mention.";
    }
}
