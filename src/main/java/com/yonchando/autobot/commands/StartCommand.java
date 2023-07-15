package com.yonchando.autobot.commands;

import com.yonchando.autobot.interfaces.BotInterface;
import org.telegram.telegrambots.meta.api.objects.Message;

public class StartCommand implements BotInterface {

    @Override
    public String run(Message message) {
        return """
                 Hi, I'm Auto Mention Bot.\s
                 I'm here to help you to mention all member in group except user who ignore this group.\s
                 This is some command you can use.\s
                 /mention_all - Mention all member.\s
                 /ignore_me - You don't get mention.\s
                 /in - You are back in mentions list.\s
                """;
    }
}
