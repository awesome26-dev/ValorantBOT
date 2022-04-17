package de.virusexe.valorant.event;

import org.javacord.api.entity.user.User;
import org.javacord.api.event.server.member.ServerMemberJoinEvent;
import org.javacord.api.listener.server.member.ServerMemberJoinListener;

public class MemberJoin implements ServerMemberJoinListener {

    /*
     * Send to {user} a private message to verify
     */

    @Override
    public void onServerMemberJoin(ServerMemberJoinEvent serverMemberJoinEvent) {
        User user = serverMemberJoinEvent.getUser();

        user.openPrivateChannel();
        user.sendMessage("Um dich zu Verfizieren schreibe in https://discord.com/channels/963096290982518837/963945161656782889 -> !verify <Nametag #Tag> rein.");

        if (serverMemberJoinEvent.getApi().getServerById("963096290982518837").isPresent()) {
            if (serverMemberJoinEvent.getServer().getRoleById("963868618997780562").isEmpty()) return;
            user.addRole(serverMemberJoinEvent.getServer().getRoleById("963868618997780562").get());
        }
    }
}
