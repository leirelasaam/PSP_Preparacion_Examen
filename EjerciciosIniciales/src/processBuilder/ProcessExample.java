package processBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessExample {

	public static void main(String[] args) {
		ProcessBuilder pb = new ProcessBuilder("CMD.exe");
		
		try {
			Process p = pb.start();
			System.out.println("Parte 1");
			
			
			InputStream is = p.getInputStream();

			int c;
			while((c = is.read()) != -1) {
				System.out.print((char) c);
			}
			is.close();
			
			System.out.println("Parte 2");
			InputStream er = p.getInputStream();
			BufferedReader brer = new BufferedReader(new InputStreamReader(er));
			
			String line = null;
			while((line = brer.readLine()) != null) {
				System.out.println(line);
			}
			er.close();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
