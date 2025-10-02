package behavioral.command;

/**
 * Concrete Command for setting temperature
 * Implements Command interface for temperature control
 */
public class SetTemperatureCommand implements Command {
    private VoiceReceiver receiver;
    private int temperature;
    
    /**
     * Constructor
     * @param receiver VoiceReceiver instance to perform the operation
     * @param temperature Target temperature value
     */
    public SetTemperatureCommand(VoiceReceiver receiver, int temperature) {
        this.receiver = receiver;
        this.temperature = temperature;
    }
    
    /**
     * Execute the command to set temperature
     */
    @Override
    public void execute() {
        receiver.setTemperature(temperature);
    }
}
