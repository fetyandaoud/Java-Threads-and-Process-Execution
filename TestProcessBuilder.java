import java.io.*;
import java.util.*;

public class TestProcessBuilder {
    static List<String> errorLog=new ArrayList<>();  // Logg för felaktiga kommandon

    static void createProcess(String command) throws java.io.IOException {
        // Kontrollera om kommandot är 'showerrlog'
        if (command.toLowerCase().equals("showerrlog")) {
            System.out.println("Error Log:");
            if (errorLog.isEmpty()) {
                System.out.println("No errors logged.");
            } else {
                for (String errorCommand : errorLog) {
                    System.out.println(errorCommand);
                }
            }
            return;
        }
        CommandRunner commandRunner=new CommandRunner(command);
        Thread thread=new Thread(commandRunner);
        thread.start();
    }
    public static void main(String[] args) throws java.io.IOException {
        String commandLine;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n***** Welcome to the Java Command Shell *****");
        System.out.println("If you want to exit the shell, type END and press RETURN.\n");

        while (true) {
            System.out.print("jsh>");
            commandLine=scanner.nextLine();

            if (commandLine.equals("")) {
                continue;
            }
            if (commandLine.toLowerCase().equals("end")) {
                System.out.println("\n***** Command Shell Terminated. See you next time. BYE for now. *****\n");
                scanner.close();
                System.exit(0);
            }

            createProcess(commandLine);
        }
    }
}
