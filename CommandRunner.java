import java.io.*;
import java.util.*;
public class CommandRunner implements Runnable {
    private String command;
    public CommandRunner(String command) {
        this.command = command;
    }
    public void run() {
        List<String> input=Arrays.asList(command.split(" "));
        ProcessBuilder processBuilder=new ProcessBuilder(input);
        BufferedReader bufferReader=null;
        try {
            Process proc=processBuilder.start();
            InputStream inputStream=proc.getInputStream();
            InputStreamReader isr=new InputStreamReader(inputStream);
            bufferReader=new BufferedReader(isr);
            String line;
            while ((line=bufferReader.readLine()) !=null) {
                System.out.println(line);
            }
            bufferReader.close();
        } catch (java.io.IOException ioe) {
            System.err.println("Error executing command: " + command);
            TestProcessBuilder.errorLog.add(command);
        } finally {
            if (bufferReader !=null) {
                try {
                    bufferReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
