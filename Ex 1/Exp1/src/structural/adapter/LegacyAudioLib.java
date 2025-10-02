package structural.adapter;

public class LegacyAudioLib {
    public void playMp3(String path) {
        System.out.println("[AudioLib] Playing audio file: " + path);
    }
}
