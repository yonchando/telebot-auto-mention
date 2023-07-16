package com.yonchando.autobot.commands;

import com.yonchando.autobot.interfaces.BotInterface;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.Arrays;
import java.util.Random;

public class LuckyDrawCommand implements BotInterface {
    public String send(Message message) {
        boolean isStartLuckyDraw = false;

        if (isStartLuckyDraw) {
            String text = message.getText();

            String[] strings = text.split("\n");
            System.out.println(Arrays.toString(strings));

            Random random = new Random();
            int index = random.nextInt(strings.length);

            return strings[index];
        } else {
            return """
                    Send me your lists for lucky draw with this format:\s

                    Lucky 1\s
                    Lucky 2\s
                    Lucky 3""";
        }
    }

    @Override
    public BotCommand getBotCommand() {
        return null;
    }

    @Override
    public String parseMode() {
        return null;
    }
}
