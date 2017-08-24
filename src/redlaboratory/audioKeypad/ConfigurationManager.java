package redlaboratory.audioKeypad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ConfigurationManager {
	
	private File configurationFile = null;
	
	public ConfigurationManager() {
		configurationFile = new File("data.config");
		
		try {
			if (!configurationFile.exists()) configurationFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void save() throws IOException {
		OutputStream os = new FileOutputStream(configurationFile);
		
		for (int i = 0; i < (Main.width * Main.height); i++) {
			byte gain = (byte) Main.getMasterGain(i);
			if (gain != -1) {
				os.write(new byte[] {(byte) i, gain});
			} else {
				os.write(new byte[] {-1, -1});
			}
		}
		
		os.flush();
		os.close();
	}
	
	public void load() throws IOException {
		InputStream is = new FileInputStream(configurationFile);
		
		byte[] buffer = new byte[Main.width * Main.height * 2];
		is.read(buffer);
		
		for (int i = 0; i < Main.width * Main.height; i++) {
			int index = buffer[i * 2];
			int gain = buffer[i * 2 + 1];
			
			if (index != -1 && gain != -1) Main.setMasterGain(index, gain);
		}
		
		is.close();
	}
	
}
