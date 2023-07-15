package com.yonchando.autobot.commands;

import com.yonchando.autobot.interfaces.BotInterface;
import com.yonchando.autobot.services.UserService;
import org.telegram.telegrambots.meta.api.objects.Message;


public class InCommand implements BotInterface {

    private final UserService userService = new UserService();

    @Override
    public String run(Message message) {
        boolean updated = userService.in(message.getFrom().getId(), message.getChatId());

        return updated ? "You are back in mention lists." : "You are already in mention lists.";
    }
}
