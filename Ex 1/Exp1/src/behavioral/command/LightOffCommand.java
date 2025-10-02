package behavioral.command;

/**
 * Concrete Command for turning lights OFF
 * Implements Command interface for light control
 */
public class LightOffCommand implements Command {
    private VoiceReceiver receiver;
    
    /**
     * Constructor
     * @param receiver VoiceReceiver instance to perform the operation
     */
    public LightOffCommand(VoiceReceiver receiver) {
        this.receiver = receiver;
    }
    
    /**
     * Execute the command to turn lights off
     */
    @Override
    public void execute() {
        receiver.turnOffLights();
    }
}
