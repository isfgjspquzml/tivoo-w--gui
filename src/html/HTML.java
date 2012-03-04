package html;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import event.Event;
import exceptions.HTMLException;

/**
 * The main class for HTML output part
 * 
 * @author Donghe
 */
public class HTML implements HTMLConstants {

	private static String myHtmlFolderPath;
	private static String myCalName;

	private ArrayList<Event> myFilteredEvents;
	private ArrayList<Event> myBackupEvents;

	public HTML(ArrayList<Event> filteredEvents) {
		myFilteredEvents = filteredEvents;
		myBackupEvents = copyEvents();
	}

	/**
	 * input the name of the calendar and the folder path generate every
	 * specific event html generate event list html generate event list with
	 * conflict events html generate event calendar html
	 * 
	 * @throws Exception
	 */
	public void writeHTML() throws Exception {
		try {
			readCalNameAndFolderPath();
			
			EventCollection specificEvents = new EventCollectionSeparate(
					myFilteredEvents, myBackupEvents);
			specificEvents.writeHTMLFiles();

			EventCollection summaryList = new EventCollectionList(
					myFilteredEvents, myBackupEvents);
			summaryList.writeHTMLFiles();

			sortEventsByTime();

			EventCollection summaryListConflict = new EventCollectionListConflict(
					myFilteredEvents, myBackupEvents);
			summaryListConflict.writeHTMLFiles();

			EventCollection summaryCalendar = new EventCollectionCalendar(
					myFilteredEvents, myBackupEvents);
			summaryCalendar.writeHTMLFiles();

		} catch (HTMLException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(IO_EXCEPTION);
		}
	}

	/**
	 * @param myFilteredEvents
	 * @return copy the filteredEvents because we need the firse order
	 */
	public ArrayList<Event> copyEvents() {
		ArrayList<Event> sortedEvents = new ArrayList<Event>();
		for (Event e : myFilteredEvents) {
			sortedEvents.add(e);
		}
		return sortedEvents;
	}

	/**
	 * get the folder path and the Calendar name from the player
	 */
	public void readCalNameAndFolderPath() throws Exception {
		myCalName = getUserInfo(INPUT_NAME);
		myHtmlFolderPath = getUserInfo(INPUT_PATH);
		if (!(new File(myHtmlFolderPath)).isDirectory()) {
			throw new HTMLException(NOT_FOLDER);
		}
	}

	/**
	 * read the player's input
	 * 
	 * @param display
	 *            the words that is displayed to player
	 * @return the player's input
	 * @throws IOException
	 */
	public String getUserInfo(String display) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		System.out.println(display);
		return reader.readLine();
	}

	/**
	 * sort this event list by time
	 */
	
	public void sortEventsByTime() {
		Collections.sort(myFilteredEvents);
	}
	
	
	public static String getHTMLFolderPath () {
	    return myHtmlFolderPath;
	}
	
	public static void setHTMLFolderPath(String htmlFolderPath) {
	    myHtmlFolderPath = htmlFolderPath;
	}
	
	public static String getCalName () {
        return myCalName;
    }
	
	public static void setCalName (String calName) {
	    myCalName = calName;
	}
}