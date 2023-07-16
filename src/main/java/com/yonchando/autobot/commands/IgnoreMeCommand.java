package com.yonchando.autobot.commands;

import com.yonchando.autobot.interfaces.BotInterface;
import com.yonchando.autobot.services.UserService;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

public class IgnoreMeCommand implements BotInterface {

    private final UserService userService = new UserService();

    @Override
    public String send(Message message) {
        if (userService.ignoreMe(message.getFrom().getId(), message.getChatId()))
            return "You are not in mention.";

        return "You are already not in mention.";
    }

    @Override
    public BotCommand getBotCommand() {
        BotCommand botCommand = new BotCommand();
        botCommand.setCommand("/ignore_me");
        botCommand.setDescription("You are out from mention command.");

        return botCommand;
    }

    @Override
    public String parseMode() {
        return null;
    }
}
