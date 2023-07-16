package com.yonchando.autobot.commands;


import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.LinkedList;
import java.util.List;

public class BotCommandMenu {

    public List<BotCommand> getMenu() {

        List<BotCommand> botCommands = new LinkedList<>();

        botCommands.add(new StartCommand().getBotCommand());
        botCommands.add(new MentionAllCommand().getBotCommand());
        botCommands.add(new IgnoreMeCommand().getBotCommand());
        botCommands.add(new InCommand().getBotCommand());
        botCommands.add(new HelpCommand().getBotCommand());

        return botCommands;
    }

}
