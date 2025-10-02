package behavioral.command;

/**
 * Concrete Command for setting volume
 * Implements Command interface for volume control
 */
public class SetVolumeCommand implements Command {
    private VoiceReceiver receiver;
    private int volume;
    
    /**
     * Constructor
     * @param receiver VoiceReceiver instance to perform the operation
     * @param volume Target volume level (0-100)
     */
    public SetVolumeCommand(VoiceReceiver receiver, int volume) {
        this.receiver = receiver;
        this.volume = volume;
    }
    
    /**
     * Execute the command to set volume
     */
    @Override
    public void execute() {
        receiver.setVolume(volume);
    }
}
