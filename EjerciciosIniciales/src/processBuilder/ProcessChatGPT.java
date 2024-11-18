package processBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProcessChatGPT {

    public static void main(String[] args) {
        try {
            // Ejecuta cmd en una ventana visible
            Process process = Runtime.getRuntime().exec("cmd /c start cmd");
            
            // Leer la salida del proceso
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
