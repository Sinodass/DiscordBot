package me.seasickproductions;

import me.seasickproductions.listener.DiscordEventListener;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class MineAcademyBot {

    protected static MineAcademyBot selfBot;
    private ShardManager shardManager = null;

    public MineAcademyBot(String token){
        try {
            shardManager = buildShardManager(token);
        } catch (LoginException e) {
            System.out.println("Failed to start bot! Please check the console for any errors.");
            System.exit(0);
        }
    }

    private ShardManager buildShardManager(String token) throws LoginException {
        DefaultShardManagerBuilder builder =
                DefaultShardManagerBuilder.createDefault(token).addEventListeners(new DiscordEventListener(this));

        return builder.build();
    }

    public ShardManager getShardManager() {
        return shardManager;
    }
}
