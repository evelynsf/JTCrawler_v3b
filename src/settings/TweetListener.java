package settings;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

/**
 * Listener for twitter stream
 * @author Evelyn
 *
 */
public class TweetListener implements StatusListener {
	

	private TimeStamp runtime;
	private Tweet tweet;
	private File file;
	private int colected;
	private int stored;
	private ConfigurationFile results;
	
	
    public TweetListener() throws IOException{
    	
    	runtime = new TimeStamp(); //catch the current daytime
    	this.setColected(0);
    	this.setStored(0);
    	setResults(new ConfigurationFile("output/" + runtime.getTimeFormat() + ".txt"));
    }
    
	public void onStatus(Status status) {
		
		tweet = new Tweet(status.getText(),status.getUser().getName(), status.getUser().getScreenName(), 
				status.getCreatedAt().toString(), status.getUser().getLocation());
		
		//System.out.println(tweet.toString());
		
		try {
			
	          file = new File("tweets.txt");
	          PrintWriter writer = new PrintWriter(new FileWriter(file, true)); 
	          writer.append(tweet.toString() + "\n");
	          stored++;
	          writer.close();
	          
	        
		} catch ( IOException e ) {
	           e.printStackTrace();
	    }
		this.setColected(this.getColected() + 1);
		try {
			results.writeProperties(getColected(), stored);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		/*		
			System.out.println("[" + 
			//new GregorianCalendar().getTime().toString() 
			time.getTimeFormat()
			+ "] " 
	        		+ status.getUser().getName() + " : " + status.getText());
	    */
    }
	
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
    	System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
    }
    
    public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
    	System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
    }
    
    public void onException(Exception ex) {
        ex.printStackTrace();
    }
    
	@Override
	public void onScrubGeo(long userID, long upToStatusID) {
		System.out.println("Got scrub_geo event userId:" + userID + " upToStatusId:" + upToStatusID);
		
	}
	@Override
	public void onStallWarning(StallWarning stallWarning) {
		System.out.println("Got stall warning:" + stallWarning);
		
	}

	public ConfigurationFile getResults() {
		return results;
	}

	public void setResults(ConfigurationFile results) {
		this.results = results;
	}

	public int getStored() {
		return stored;
	}

	public void setStored(int stored) {
		this.stored = stored;
	}

	public int getColected() {
		return colected;
	}

	public void setColected(int colected) {
		this.colected = colected;
	}

	public TimeStamp getRuntime() {
		return runtime;
	}

	public void setRuntime(TimeStamp runtime) {
		this.runtime = runtime;
	}

}