package com.yonchando.autobot.interfaces;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

public interface BotInterface {
    String send(Message message);
    BotCommand getBotCommand();
    String parseMode();
}
