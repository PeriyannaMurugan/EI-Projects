package behavioral.command;

/**
 * Concrete Command for playing music
 * Implements Command interface for music playback control
 */
public class PlayMusicCommand implements Command {
    private VoiceReceiver receiver;
    private String songName;
    
    /**
     * Constructor
     * @param receiver VoiceReceiver instance to perform the operation
     * @param songName Name of the song to play
     */
    public PlayMusicCommand(VoiceReceiver receiver, String songName) {
        this.receiver = receiver;
        this.songName = songName;
    }
    
    /**
     * Execute the command to play music
     */
    @Override
    public void execute() {
        receiver.playMusic(songName);
    }
}
