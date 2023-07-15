package com.yonchando.autobot.interfaces;

import org.telegram.telegrambots.meta.api.objects.Message;

import java.sql.SQLException;

public interface BotInterface {
    String run(Message message);
}
