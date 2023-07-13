package com.yonchando.autobot.commands;

import com.yonchando.autobot.interfaces.BotInterface;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Arrays;
import java.util.Random;

public class LuckyDraw implements BotInterface {
    public String run(Message message) {
        Boolean isStartLuckyDraw = false;

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
}
