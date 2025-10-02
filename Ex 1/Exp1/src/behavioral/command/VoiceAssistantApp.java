package behavioral.command;

import java.util.Scanner;


public class VoiceAssistantApp {
    private VoiceReceiver receiver;
    private VoiceAssistantInvoker invoker;
    private Scanner scanner;
    private boolean isRunning;
    
    public VoiceAssistantApp() {
        receiver = new VoiceReceiver();
        invoker = new VoiceAssistantInvoker();
        scanner = new Scanner(System.in);
        isRunning = false;
        setupCommands();
    }
    
    
    private void setupCommands() {
        // Light commands
        invoker.setCommand("turn on lights", new LightOnCommand(receiver));
        invoker.setCommand("turn off lights", new LightOffCommand(receiver));
        invoker.setCommand("lights on", new LightOnCommand(receiver));
        invoker.setCommand("lights off", new LightOffCommand(receiver));
        invoker.setCommand("switch on lights", new LightOnCommand(receiver));
        invoker.setCommand("switch off lights", new LightOffCommand(receiver));
        
        // Temperature commands - Multiple temperature options
        invoker.setCommand("set temperature 16", new SetTemperatureCommand(receiver, 16));
        invoker.setCommand("set temperature 18", new SetTemperatureCommand(receiver, 18));
        invoker.setCommand("set temperature 20", new SetTemperatureCommand(receiver, 20));
        invoker.setCommand("set temperature 22", new SetTemperatureCommand(receiver, 22));
        invoker.setCommand("set temperature 24", new SetTemperatureCommand(receiver, 24));
        invoker.setCommand("set temperature 26", new SetTemperatureCommand(receiver, 26));
        invoker.setCommand("set temperature 28", new SetTemperatureCommand(receiver, 28));
        invoker.setCommand("set temperature 30", new SetTemperatureCommand(receiver, 30));
        
        // Shorthand temperature commands
        invoker.setCommand("temperature 20", new SetTemperatureCommand(receiver, 20));
        invoker.setCommand("temperature 22", new SetTemperatureCommand(receiver, 22));
        invoker.setCommand("temperature 24", new SetTemperatureCommand(receiver, 24));
        invoker.setCommand("temperature 26", new SetTemperatureCommand(receiver, 26));
        
        // Music commands
        invoker.setCommand("play music", new PlayMusicCommand(receiver, "Default Playlist"));
        invoker.setCommand("play song", new PlayMusicCommand(receiver, "Favorite Song"));
        invoker.setCommand("play jazz", new PlayMusicCommand(receiver, "Jazz Collection"));
        invoker.setCommand("play rock", new PlayMusicCommand(receiver, "Rock Hits"));
        invoker.setCommand("play classical", new PlayMusicCommand(receiver, "Classical Music"));
        invoker.setCommand("stop music", new StopMusicCommand(receiver));
        invoker.setCommand("pause music", new StopMusicCommand(receiver));
        invoker.setCommand("stop song", new StopMusicCommand(receiver));
        
        // Volume commands - Multiple volume levels
        invoker.setCommand("volume 0", new SetVolumeCommand(receiver, 0));
        invoker.setCommand("volume 10", new SetVolumeCommand(receiver, 10));
        invoker.setCommand("volume 20", new SetVolumeCommand(receiver, 20));
        invoker.setCommand("volume 30", new SetVolumeCommand(receiver, 30));
        invoker.setCommand("volume 40", new SetVolumeCommand(receiver, 40));
        invoker.setCommand("volume 50", new SetVolumeCommand(receiver, 50));
        invoker.setCommand("volume 60", new SetVolumeCommand(receiver, 60));
        invoker.setCommand("volume 70", new SetVolumeCommand(receiver, 70));
        invoker.setCommand("volume 80", new SetVolumeCommand(receiver, 80));
        invoker.setCommand("volume 90", new SetVolumeCommand(receiver, 90));
        invoker.setCommand("volume 100", new SetVolumeCommand(receiver, 100));
        
        // Volume shortcuts
        invoker.setCommand("mute", new SetVolumeCommand(receiver, 0));
        invoker.setCommand("low volume", new SetVolumeCommand(receiver, 20));
        invoker.setCommand("medium volume", new SetVolumeCommand(receiver, 50));
        invoker.setCommand("high volume", new SetVolumeCommand(receiver, 80));
        invoker.setCommand("max volume", new SetVolumeCommand(receiver, 100));
        
        // Status commands
        invoker.setCommand("show status", new ShowStatusCommand(receiver));
        invoker.setCommand("status", new ShowStatusCommand(receiver));
        invoker.setCommand("check status", new ShowStatusCommand(receiver));
        invoker.setCommand("system status", new ShowStatusCommand(receiver));
        invoker.setCommand("report", new ShowStatusCommand(receiver));
    }
    
    
    public void start() {
        displayWelcome();
        isRunning = true;
        
        String input;
        while (isRunning) {
            System.out.print("🎤 Voice Command: ");
            input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                continue;
            }
            
            processInput(input);
            System.out.println(); // Add spacing between commands
        }
        
        shutdown();
    }
    
    
    private void displayWelcome() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("    🏠 SMART VOICE ASSISTANT - COMMAND PATTERN DEMO");
        System.out.println("═".repeat(60));
        System.out.println("Welcome to your Smart Voice Assistant!");
        System.out.println("\n📝 Available Commands:");
        System.out.println("   • Type voice commands (e.g., 'turn on lights')");
        System.out.println("   • Type 'help' to see all available commands");
        System.out.println("   • Type 'demo' to run a demonstration");
        System.out.println("   • Type 'exit' to quit the application");
        System.out.println("\n💡 Try saying: 'turn on lights', 'set temperature 24', 'play music'");
        System.out.println("═".repeat(60) + "\n");
    }
    
    /**
     * Process user input
     */
    private void processInput(String input) {
        String lowerInput = input.toLowerCase().trim();
        
        switch (lowerInput) {
            case "exit":
            case "quit":
            case "bye":
            case "goodbye":
                System.out.println("👋 Goodbye! Thank you for using Smart Voice Assistant");
                isRunning = false;
                break;
                
            case "help":
            case "commands":
                invoker.showAvailableCommands();
                break;
                
            case "demo":
                runDemo();
                break;
                
            case "count":
                System.out.println("📊 Total commands registered: " + invoker.getCommandCount());
                break;
                
            default:
                invoker.executeCommand(input);
                break;
        }
    }
    
    
    private void runDemo() {
        System.out.println("🎭 Running Voice Assistant Demo...");
        System.out.println("═".repeat(40));
        
        String[] demoCommands = {
            "show status",
            "turn on lights", 
            "set temperature 24",
            "play jazz",
            "volume 70",
            "show status",
            "turn off lights",
            "stop music",
            "show status"
        };
        
        for (String command : demoCommands) {
            System.out.println("\n🎯 Demo Command: " + command);
            invoker.executeCommand(command);
            
            // Small delay for better visualization
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        System.out.println("\n🎭 Demo completed!");
        System.out.println("═".repeat(40));
    }
    
    
    private void shutdown() {
        if (scanner != null) {
            scanner.close();
        }
        System.out.println("🔌 Voice Assistant shutting down...");
    }
    
    /**
     * Main method - Application entry point
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            VoiceAssistantApp app = new VoiceAssistantApp();
            
            // Add shutdown hook for graceful termination
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("\n\n🔄 System shutdown detected...");
                System.out.println("👋 Voice Assistant terminated gracefully");
            }));
            
            app.start();
            
        } catch (Exception e) {
            System.err.println("❌ Fatal Error: Failed to start Voice Assistant");
            System.err.println("Error details: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
