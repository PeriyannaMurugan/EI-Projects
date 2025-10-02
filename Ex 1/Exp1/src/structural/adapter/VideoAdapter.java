package structural.adapter;

public class VideoAdapter implements MediaPlayer {
    private final LegacyVideoLib videoLib = new LegacyVideoLib();

    @Override
    public void play(MediaContent content) {
        if (!"mp4".equals(content.getType())) {
            throw new IllegalArgumentException("VideoAdapter only supports mp4");
        }
        videoLib.decodeAndPlayMp4(content.getFileName());
    }
}
