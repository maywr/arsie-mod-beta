package xyz.maywr.arsie.impl;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import xyz.maywr.arsie.Arsie;

import java.util.Random;

import static xyz.maywr.arsie.Arsie.mc;
import static xyz.maywr.arsie.Arsie.NAME;
import static xyz.maywr.arsie.Arsie.VERSION;

public class RPCThread extends Thread {

    DiscordEventHandlers eventHandlers = new DiscordEventHandlers();
    private static final DiscordRichPresence discordRichPresence = new DiscordRichPresence();
    private static final DiscordRPC discordRPC = DiscordRPC.INSTANCE;
    long timeStamp = Arsie.timeFromRun / 1000L;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            discordRichPresence.startTimestamp = timeStamp;
            String discordID = "942948223742509076";
            discordRPC.Discord_Initialize(discordID, eventHandlers, true, null);
            discordRichPresence.largeImageKey = "arsie";
            discordRichPresence.largeImageText = "arsie mod";
            discordRichPresence.details = "$NAME $VERSION | ${mc.player.getName()}";
            discordRichPresence.state = mc.isIntegratedServerRunning() ? "синглплеер" : (mc.getCurrentServerData() != null ? mc.getCurrentServerData().serverIP.toLowerCase() : "в меню");

            discordRPC.Discord_UpdatePresence(discordRichPresence);
        }
    }

        public void stopRPC(){
            discordRPC.Discord_Shutdown();
            discordRPC.Discord_ClearPresence();
            this.stop();
        }
}
