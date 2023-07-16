package com.yonchando.autobot.commands;

import com.yonchando.autobot.interfaces.BotInterface;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

public class StartCommand implements BotInterface {

    @Override
    public String send(Message message) {
        return """
                 Hi, I'm Auto Mention Bot.\s
                 I'm here to help you to mention all member in group except user who ignore this group.\s
                 This is some command you can use.\s
                 /start - Help you to start bot.\s
                 /mention_all - Mention all member.\s
                 /ignore_me - You don't get mention.\s
                 /in - You are back in mentions list.\s
                 /help - see what bot can do.
                """;
    }

    @Override
    public BotCommand getBotCommand(){
        BotCommand botCommand = new BotCommand();
        botCommand.setCommand("/start");
        botCommand.setDescription("Help you to start bot.");

        return botCommand;
    }

    @Override
    public String parseMode() {
        return null;
    }
}
