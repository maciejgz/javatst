package pl.mg.javatst.shellscript;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class ShellScriptTool {

    public static void main(String[] args) {
        try {
            String javaHome = System.getProperty("java.home");
            String javaBin = javaHome +
                    File.separator + "bin" +
                    File.separator + "java";
            List<String> command = new LinkedList<String>();
            command.add(javaBin);
            command.add("-version");
            ProcessBuilder versionBuilder = new ProcessBuilder(command);
            Process process = versionBuilder.inheritIO().start();
            process.waitFor();
            printResults(process);



            ProcessBuilder processBuilder = new ProcessBuilder("java.exe", "-version");
            Process processJava = processBuilder.start();
            processJava.waitFor();
            printResults(processJava);

            Process processPing = Runtime.getRuntime().exec("ping www.onet.pl");
            printResults(processPing);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printResults(Process process) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void creatingNewProcess() throws IOException {
        ProcessBuilder builder = new ProcessBuilder("notepad.exe");
        Process process = builder.start();
    }

}
