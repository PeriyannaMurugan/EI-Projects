package behavioral.command;

/**
 * Concrete Command for stopping music
 * Implements Command interface for music control
 */
public class StopMusicCommand implements Command {
    private VoiceReceiver receiver;
    
    /**
     * Constructor
     * @param receiver VoiceReceiver instance to perform the operation
     */
    public StopMusicCommand(VoiceReceiver receiver) {
        this.receiver = receiver;
    }
    
    /**
     * Execute the command to stop music
     */
    @Override
    public void execute() {
        receiver.stopMusic();
    }
}
