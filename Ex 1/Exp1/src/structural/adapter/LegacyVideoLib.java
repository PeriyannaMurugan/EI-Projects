package structural.adapter;

public class LegacyVideoLib {
    public void decodeAndPlayMp4(String filename) {
        System.out.println("[VideoLib] Decoding and playing video: " + filename);
    }
}
