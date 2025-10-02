package structural.adapter;

public class StreamingAdapter implements MediaPlayer {
    private final LegacyStreamingLib streamLib = new LegacyStreamingLib();

    @Override
    public void play(MediaContent content) {
        if (!"m3u8".equals(content.getType())) {
            throw new IllegalArgumentException("StreamingAdapter only supports m3u8");
        }
        streamLib.stream(content.getFileName());
    }
}
