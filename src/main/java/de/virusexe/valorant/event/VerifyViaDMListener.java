package de.virusexe.valorant.event;

import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.net.UnknownServiceException;
import java.util.Arrays;

public class VerifyViaDMListener implements MessageCreateListener {

    private final String[] allowedMessages = new String[]{
            "abc",
            "test"
    };

    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        Message message = messageCreateEvent.getMessage();
        User user = messageCreateEvent.getMessageAuthor().asUser().get();

        if(!message.isPrivateMessage()) {
            return;
        }

        if(message.getContent().equalsIgnoreCase(".help")) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setColor(Color.green);
            embedBuilder.setTitle("Help - List");

            embedBuilder.setDescription("""
                    1. !verify <Name/Password>\s
                    2. !verify <Name#Tag>\s
                    3. !shop\s
                    4. !shop <@Username> \s
                    5. !skins \s
                    6. !skins <@Username> \s
                    7. !battle-pass <@Username> \s
                    8. !settings""");


            message.reply(embedBuilder);
            return;
        }
    }
}
