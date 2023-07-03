package com.yonchando.commands;

import lombok.NonNull;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class StartCommand extends BotCommand {
    public static final String LOGTAG = "STARTCOMMAND";

    public StartCommand() {
        super("start","With this command you can start the Bot");
    }

    public void execute(AbsSender absSender, User user, Chat chat, String[] strings){

    }
}
