package structural.adapter;

public class MediaContent {
    private final String fileName;
    private final String type; // e.g. "mp3", "mp4", "m3u8"

    public MediaContent(String fileName, String type) {
        if (fileName == null || type == null) throw new IllegalArgumentException("File and type required");
        this.fileName = fileName;
        this.type = type.toLowerCase();
    }

    public String getFileName() { return fileName; }
    public String getType() { return type; }
}
