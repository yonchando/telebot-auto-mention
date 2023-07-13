package com.yonchando.autobot.interfaces;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface BotInterface {
    String run(Message message);
}
