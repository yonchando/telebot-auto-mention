package com.yonchando.autobot.commands;

import com.yonchando.autobot.interfaces.BotInterface;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

public class HelpCommand implements BotInterface {
    @Override
    public String send(Message message) {
        return """
                Here what Bot can do\s\s
                                
                This is command lists\s
                /start - Help you to start bot.\s
                /mention_all - Mention all member.\s
                /ignore_me - You don't get mention.\s
                /in - You are back in mentions list.\s
                /help - see what bot can do.

                """;
    }

    @Override
    public BotCommand getBotCommand() {
        BotCommand botCommand = new BotCommand();
        botCommand.setCommand("/help");
        botCommand.setDescription("see what bot can do");

        return botCommand;

    }

    @Override
    public String parseMode() {
        return null;
    }
}
