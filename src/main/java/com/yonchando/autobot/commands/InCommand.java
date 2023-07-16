package com.yonchando.autobot.commands;

import com.yonchando.autobot.interfaces.BotInterface;
import com.yonchando.autobot.services.UserService;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;


public class InCommand implements BotInterface {

    private final UserService userService = new UserService();

    @Override
    public String send(Message message) {
        boolean updated = userService.in(message.getFrom().getId(), message.getChatId());

        return updated ? "You are back in mention lists." : "You are already in mention lists.";
    }

    @Override
    public BotCommand getBotCommand() {
        BotCommand botCommand = new BotCommand();
        botCommand.setCommand("/in");
        botCommand.setDescription("You back in mention command");

        return botCommand;

    }

    @Override
    public String parseMode() {
        return null;
    }
}
