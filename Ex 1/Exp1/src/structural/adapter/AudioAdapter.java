package structural.adapter;

public class AudioAdapter implements MediaPlayer {
    private final LegacyAudioLib audioLib = new LegacyAudioLib();

    @Override
    public void play(MediaContent content) {
        if (!"mp3".equals(content.getType())) {
            throw new IllegalArgumentException("AudioAdapter only supports mp3");
        }
        audioLib.playMp3(content.getFileName());
    }
}
