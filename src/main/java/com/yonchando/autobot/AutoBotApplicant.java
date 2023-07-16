package com.yonchando.autobot;

import com.yonchando.autobot.commands.BotCommandMenu;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.description.SetMyDescription;
import org.telegram.telegrambots.meta.api.methods.menubutton.GetChatMenuButton;
import org.telegram.telegrambots.meta.api.objects.menubutton.MenuButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class AutoBotApplicant {

    public static void main(String[] args) {
        try {

            Bot bot = new Bot();

            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);

            setMyDescription(bot);
            setMyCommand(bot);

        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getChatMenuButton(AbsSender absSender) throws TelegramApiException{
        GetChatMenuButton chatMenuButton = GetChatMenuButton.builder().build();
        MenuButton menuButton = absSender.execute(chatMenuButton);
        System.out.println(menuButton.toString());
    }

    public static void setMyDescription(AbsSender absSender) throws TelegramApiException {
        String description = """
                Hi, I'm Auto Bot.\s
                I'm here to help you.\s\s
                                             
                This is some command you can start with.\s
                                    
                /start - Start command to get info list.\s
                /mention_all - Mention all members in group chat.\s
                /ignore_me - You don't get mention.\s
                /in - You're back in mention.\s
                /help - see what bot can do.\s
                """;
        SetMyDescription setMyDescription = SetMyDescription.builder().description(description).build();

        absSender.execute(setMyDescription);

    }

    public static void setMyCommand(AbsSender absSender) throws TelegramApiException {
        SetMyCommands setMyCommands = SetMyCommands.builder()
                .commands(new BotCommandMenu().getMenu())
                .build();

         absSender.execute(setMyCommands);
    }


}
