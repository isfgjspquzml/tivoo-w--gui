package main;
/**
 * @author Donghe, James, Andrew, Tianyu
 */

import html.EventCollection;
import html.HTML;

import java.io.BufferedReader;
import java.io.File;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Stack;

import event.Event;
import event.EventFilters;
import event.EventTV;
import exceptions.FilterException;
import exceptions.XMLException;

import parser.Parser;
import parser.ParserFactory;

import GUI.BasicGui;

public class Main {

	private static final String INPUT_QUESTION = "Which type of XML file would you like to parse?";
	private static final String INPUT_DUKE_CAL = "[1] Duke Calendar XML";
	private static final String INPUT_ICS_CAL = "[2] ICS (Duke Football) Calendar XML";
	private static final String INPUT_GOOGLE_CAL = "[3] Google Calendar XML";
	private static final String INPUT_CSV_CAL = "[4] CSV (NFL) Calendar XML";
	private static final String INPUT_TV_CAL = "[5] XMLTV TV Listings";
	private static final String INPUT_INDICATOR = "Input number or press RETURN to escape: ";

	private static final String DUKE_CAL_XML_FILE = "dukecal.xml";
	private static final String ICS_CAL_XML_FILE = "dukefootball.xml";
	private static final String GOOGLE_CAL_XML_FILE = "googlecal.xml";
	private static final String CSV_CAL_XML_FILE = "NFL.xml";
	private static final String TV_CAL_XML_FILE = "tvml.xml";


	private static final File[] myXMLFiles = {
		new File(DUKE_CAL_XML_FILE),
		new File(ICS_CAL_XML_FILE),
		new File(GOOGLE_CAL_XML_FILE),
		new File(CSV_CAL_XML_FILE),
		new File(TV_CAL_XML_FILE),
	};


	private static final int BEGIN_INDEX = 0;
	private static final int END_INDEX = 4;
	private static final int INDEX_OFFSET = -1;
	private static final String INDEX_ERROR_MESSAGE = "Input must be between 1 to 5!! ";
	private static final String FILE_NOT_RECOGNIZED_MESSAGE = "XML file format not recognized";

	private static ArrayList<Event> myEventList;
	private static Stack<EventTV> myEventTVList;

	private static BasicGui basicgui;

	public static void main(String[] args) 
	{    
		basicgui = new BasicGui();
		basicgui.launchFrame();

		//    	try {    
		//            parseXML();
		//            filterEventList();
		//            generateHTML();
		//             
		//        } catch (XMLException x) {
		//            System.out.println(x.getMessage());
		//        } catch (FilterException f) {
		//            System.out.println(f.getMessage());
		//        } catch (Exception e) {
		//            e.printStackTrace();
		//        }
	}


	public static void parseXML() throws Exception {

		myEventList = new ArrayList<Event>();
		myEventTVList = new Stack<EventTV>();

		//        while (true) {
		//        BufferedReader reader = new BufferedReader(new InputStreamReader(
		//                    System.in));
		//        
		//            System.out.printf("\n%s\n%s\n%s\n%s\n%s\n%s\n%s", INPUT_QUESTION,
		//                    INPUT_DUKE_CAL, INPUT_ICS_CAL, INPUT_GOOGLE_CAL,
		//                    INPUT_CSV_CAL, INPUT_TV_CAL, INPUT_INDICATOR);
		//            
		//            String inputString = reader.readLine();
		//            if (inputString.isEmpty()) return;
		//
		//            int index = Integer.parseInt(inputString) + INDEX_OFFSET;
		//            
		//            if (index < BEGIN_INDEX || index > END_INDEX) {
		//                System.out.printf("\n\n%s\n\n", INDEX_ERROR_MESSAGE); 
		//                continue;
		//            }


		String test = basicgui.getter();
		int index = 0;

		for(int i=0; i<myXMLFiles.length; i++) {

			if(myXMLFiles[i].equals(new File(test))) {
				index = index+i;
				break;
			}
		}

		File file = myXMLFiles[index];

		ParserFactory factory = new ParserFactory(file);
		factory.setXMLIndex();
		if (factory.getXMLIndex() < BEGIN_INDEX)
			throw new XMLException(FILE_NOT_RECOGNIZED_MESSAGE);

		Parser parser = new Parser();
		parser.parse(factory, myEventList);
	}

	public static void filterEventList() throws FilterException {

		String duke = basicgui.getduke();
		String actor = basicgui.getactor();
		ArrayList<String> key = basicgui.getkeyword();
		ArrayList<Date> date =  basicgui.getdate();

		if(!duke.equalsIgnoreCase("")) {
			myEventList = EventFilters.filterByTitle(myEventList, "Duke");
		}

		if(!actor.equals("")) {
			myEventList = EventFilters.filterByTitle(myEventList, actor);
			// "Duke" "Actor" etc.
		}

		if(!date.isEmpty()) {
			myEventList = EventFilters.filterByTime(myEventList, date.get(0), date.get(1));
		}

		if(!key.isEmpty()) {
			ArrayList<String> keyWordFilter = new ArrayList<String>();
			keyWordFilter.add("The");
			myEventList = EventFilters.filterByKeyWord(myEventList, keyWordFilter, true);
		}

		//Comment/uncomment out the below lines to test the various filtering functionalities
		//Note: filterByActor only works when parsing XMLTV data

		//Filters events according to whether or not they contain "Duke" in their titles
		//        myEventList = EventFilters.filterByTitle(myEventList, "Duke");


		//Filters events according to the specified dates
		//i.e. to filter events between January 1, 2011 and January 1, 2012
		//make the two Date parameters new Date(111, 0, 1) and new Date(112, 0, 1)
		//        myEventList = EventFilters.filterByTime(myEventList, new Date(),
		//                new Date());


		//Filters events according to the specified keyWords in the ArrayList
		//i.e. to filter events according to whether or not any of their information
		//contains the word "The", add "The" to the ArrayList
		//        ArrayList<String> keyWordFilter = new ArrayList<String>();
		//         keyWordFilter.add("The");
		//        myEventList = EventFilters.filterByKeyWord(myEventList, keyWordFilter, true);

		//Filters EventTVs according to their actors
		//The line below filters EventTVs by whether or not they have actors named "Drew"
		//        myEventList = EventFilters.filterByActor(myEventList, "Peter");

	}

	public static void generateHTML() {
		try {
			HTML html = new HTML(myEventList);
			html.writeHTML();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addEventTV (EventTV eventTV) {
		myEventTVList.push(eventTV);
	}

	public static boolean isEventTVListEmpty () {
		return myEventTVList.isEmpty();
	}

	public static void setEventTVAuthor (String author) {
		myEventTVList.peek().setAuthor(author);
	}

	public static ArrayList<EventTV> getEventTVList () {
		return new ArrayList<EventTV>(myEventTVList);
	}
}