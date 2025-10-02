package behavioral.command;

/**
 * Concrete Command for turning lights ON
 * Implements Command interface for light control
 */
public class LightOnCommand implements Command {
    private VoiceReceiver receiver;
    
    /**
     * Constructor
     * @param receiver VoiceReceiver instance to perform the operation
     */
    public LightOnCommand(VoiceReceiver receiver) {
        this.receiver = receiver;
    }
    
    /**
     * Execute the command to turn lights on
     */
    @Override
    public void execute() {
        receiver.turnOnLights();
    }
}
