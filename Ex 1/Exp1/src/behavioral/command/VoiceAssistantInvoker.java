package behavioral.command;

import java.util.*;

/**
 * VoiceAssistantInvoker - Invoker in Command Pattern
 * Executes commands without knowing their implementation details
 */
public class VoiceAssistantInvoker {
    private Map<String, Command> commands;
    
    /**
     * Constructor - Initialize command storage
     */
    public VoiceAssistantInvoker() {
        commands = new HashMap<>();
    }
    
    /**
     * Store a command with a voice trigger
     * @param voiceTrigger The voice command string that triggers this command
     * @param command The Command object to execute
     */
    public void setCommand(String voiceTrigger, Command command) {
        commands.put(voiceTrigger.toLowerCase(), command);
    }
    
    /**
     * Execute command based on voice input
     * @param voiceInput The voice command from user
     */
    public void executeCommand(String voiceInput) {
        String trigger = voiceInput.toLowerCase().trim();
        Command command = commands.get(trigger);
        
        if (command != null) {
            System.out.println("🎯 Executing: " + voiceInput);
            command.execute();
        } else {
            System.out.println("❌ Command not recognized: " + voiceInput);
            showAvailableCommands();
        }
    }
    
    /**
     * Show all available voice commands
     */
    public void showAvailableCommands() {
        System.out.println("\n📋 Available voice commands:");
        System.out.println("═══════════════════════════════");
        
        List<String> sortedCommands = new ArrayList<>(commands.keySet());
        Collections.sort(sortedCommands);
        
        for (String trigger : sortedCommands) {
            System.out.println("🎤 \"" + trigger + "\"");
        }
        System.out.println("═══════════════════════════════\n");
    }
    
    /**
     * Get total number of registered commands
     * @return Number of commands registered
     */
    public int getCommandCount() {
        return commands.size();
    }
    
    /**
     * Check if a command is registered
     * @param voiceTrigger The voice trigger to check
     * @return true if command exists, false otherwise
     */
    public boolean hasCommand(String voiceTrigger) {
        return commands.containsKey(voiceTrigger.toLowerCase());
    }
    
    /**
     * Remove a command
     * @param voiceTrigger The voice trigger to remove
     * @return true if command was removed, false if it didn't exist
     */
    public boolean removeCommand(String voiceTrigger) {
        return commands.remove(voiceTrigger.toLowerCase()) != null;
    }
}
