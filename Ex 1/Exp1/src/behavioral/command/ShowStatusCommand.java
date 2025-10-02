package behavioral.command;

/**
 * Concrete Command for showing system status
 * Implements Command interface for status display
 */
public class ShowStatusCommand implements Command {
    private VoiceReceiver receiver;
    
    /**
     * Constructor
     * @param receiver VoiceReceiver instance to perform the operation
     */
    public ShowStatusCommand(VoiceReceiver receiver) {
        this.receiver = receiver;
    }
    
    /**
     * Execute the command to show system status
     */
    @Override
    public void execute() {
        receiver.showStatus();
    }
}
