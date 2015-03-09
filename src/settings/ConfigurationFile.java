package settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * Configuration file class - object
 * Manipulates files - create, write, read
 * @author Evelyn
 *
 */
public class ConfigurationFile {
	
	private String filename;
	private File file;
	private Properties properties;
	//private int run;
	
	public ConfigurationFile(String namePath) throws IOException{
		this.setFilename(namePath);
		createFile();
		loadProperties();
	}
	
	//read number_of_runs
	// if number_of_runs == 0 
	// increment number_of_runs AND create file results1
	// else create file 
	
	private void createFile() throws IOException{
		
		this.file = new File(getFilename());
		if (!file.exists()){
			try{
				file.createNewFile();
				System.out.println("File " + file.getName() + " created successfully.");
			}
			catch(IOException ioe){
				System.out.println("File cannot be created." + ioe.getMessage());
				System.exit(0);
			}			
		}
		else{
			System.out.println("File " + file.getName() + " found.");
		}
	}
	
	private void loadProperties() throws FileNotFoundException, IOException{
		
		properties = new Properties();
		try{
			properties.load(new FileInputStream(this.file.getPath()));
			System.out.println("Loading properties from file...");
			if (properties.isEmpty()){
				System.out.println("No properties found.");
				System.out.println("Setting properties and writting in file.");
				
				//properties.put("Number_of_Runs", "0");
				//this.run = 0;
				properties.put("Colected_Tweets", ""); 
				properties.put("Stored_Tweets", "");
				
				PrintWriter ptw = new PrintWriter(new FileWriter(file), true);
				properties.store(ptw, "Properties");
				ptw.close();	
			}
			else{
				//this.run = Integer.parseInt(properties.getProperty("Number_of_Runs"));
				//this.setRun(this.run + 1);
				//properties.put("Colected_Tweets" + getRun(), ""); 
				//properties.put("Stored_Tweets" + getRun(), "");
			}
		}
		catch(NullPointerException npe){
			System.out.println("Not possible to get properties from the file " + this.file.getName());
			System.exit(0);
		}
		catch(FileNotFoundException fnfe){
			System.out.println("File not found." + fnfe.getMessage());
			System.exit(0);
		}
		
	}
	
	public void writeProperties(int colected, int stored) throws IOException{
		
		//colected = colected + Integer.parseInt(this.properties.getProperty("Colected_Tweets"));
		//String numero = "" + this.run;
	    //if (this.run == 0) numero = "";
				
		//this.properties.setProperty("Number_of_Runs", "" + this.run);
		//this.properties.setProperty("Colected_Tweets" + numero, "" + colected);
		//this.properties.setProperty("Stored_Tweets" + numero, "" + stored);
		
		this.properties.setProperty("Colected_Tweets", "" + colected);
		this.properties.setProperty("Stored_Tweets", "" + stored);
		PrintWriter ptw = new PrintWriter(new FileWriter(file), false);
		properties.store(ptw, "Properties");
		ptw.close();	
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}

