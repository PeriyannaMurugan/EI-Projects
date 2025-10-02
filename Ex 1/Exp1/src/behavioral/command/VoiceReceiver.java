package behavioral.command;

/**
 * VoiceReceiver - Receiver in Command Pattern
 * Performs actual operations for voice assistant
 */
public class VoiceReceiver {
    private boolean lightsOn = false;
    private int temperature = 22;
    private int volume = 50;
    private boolean musicPlaying = false;
    private String currentSong = "";
    
    /**
     * Turn on the lights
     */
    public void turnOnLights() {
        lightsOn = true;
        System.out.println("✓ Lights turned ON");
    }
    
    /**
     * Turn off the lights
     */
    public void turnOffLights() {
        lightsOn = false;
        System.out.println("✓ Lights turned OFF");
    }
    
    /**
     * Set temperature with validation
     */
    public void setTemperature(int temp) {
        if (temp >= 16 && temp <= 30) {
            this.temperature = temp;
            System.out.println("✓ Temperature set to " + temp + "°C");
        } else {
            System.out.println("✗ Invalid temperature. Range: 16-30°C");
        }
    }
    
    /**
     * Play music with specified song
     */
    public void playMusic(String song) {
        this.currentSong = song;
        this.musicPlaying = true;
        System.out.println("♪ Now playing: " + song);
    }
    
    /**
     * Stop music playback
     */
    public void stopMusic() {
        if (musicPlaying) {
            this.musicPlaying = false;
            System.out.println("⏹ Music stopped");
            this.currentSong = "";
        } else {
            System.out.println("✗ No music is currently playing");
        }
    }
    
    /**
     * Set volume with validation
     */
    public void setVolume(int vol) {
        if (vol >= 0 && vol <= 100) {
            this.volume = vol;
            System.out.println("🔊 Volume set to " + vol + "%");
        } else {
            System.out.println("✗ Invalid volume. Range: 0-100%");
        }
    }
    
    /**
     * Display current system status
     */
    public void showStatus() {
        System.out.println("\n=== Voice Assistant Status ===");
        System.out.println("💡 Lights: " + (lightsOn ? "ON" : "OFF"));
        System.out.println("🌡️ Temperature: " + temperature + "°C");
        System.out.println("🔊 Volume: " + volume + "%");
        System.out.println("♪ Music: " + (musicPlaying ? "Playing - " + currentSong : "Stopped"));
        System.out.println("===============================\n");
    }
    
    // Getters for testing purposes
    public boolean isLightsOn() { return lightsOn; }
    public int getTemperature() { return temperature; }
    public int getVolume() { return volume; }
    public boolean isMusicPlaying() { return musicPlaying; }
    public String getCurrentSong() { return currentSong; }
}
