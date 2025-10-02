package structural.adapter;

import java.util.logging.Logger;
import java.util.*;

public class Main {
    private static final Logger logger = LoggerUtil.getLogger(
            Main.class.getName(), "logs/adapter-media.log");

    public static void main(String[] args) {
        logger.info("Starting Media Player with Adapter Pattern...");

        // Register adapters
        Map<String, MediaPlayer> players = new HashMap<>();
        players.put("mp3", new AudioAdapter());
        players.put("mp4", new VideoAdapter());
        players.put("m3u8", new StreamingAdapter());

        // Sample media
        List<MediaContent> playlist = Arrays.asList(
            new MediaContent("song1.mp3", "mp3"),
            new MediaContent("movie.mp4", "mp4"),
            new MediaContent("live.m3u8", "m3u8")
        );

        // Play all
        for (MediaContent mc : playlist) {
            MediaPlayer player = players.get(mc.getType());
            if (player != null) {
                logger.info("Playing " + mc.getFileName());
                player.play(mc);
            } else {
                logger.warning("No adapter found for " + mc.getType());
            }
        }

        logger.info("Media playback finished.");
        System.out.println("=== Media Playback Completed ===");
    }
}
