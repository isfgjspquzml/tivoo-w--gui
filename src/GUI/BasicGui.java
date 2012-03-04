package GUI;

import html.EventCollection;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;

import exceptions.FilterException;

import main.Main;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/** 
	This class demonstrates the basics of setting up a Java Swing GUI uisng the
	BorderLayout. You should be able to use this program to drop in other
	components when building a GUI 
 */
public class BasicGui{

	// Initialize all swing objects.
	private JFrame f = new JFrame("Basic GUI"); //create Frame
	private String input;
	private String duke = "";
	private String actor = "";
	//	private String keyword;
	private ArrayList<String>  keywordfilter = new ArrayList<String>();
	private ArrayList<Date>  date = new ArrayList<Date>();
	private boolean parsecheck;
	private boolean htmlcheck;
	private boolean datecheck;

	// Menu
	private JMenuBar mb = new JMenuBar(); // Menubar
	private JMenu mnuFile = new JMenu("File"); // File Entry on Menu bar
	private JMenuItem mnuItemQuit = new JMenuItem("Quit"); // Quit sub item
	private JMenu mnuHelp = new JMenu("Help"); // Help Menu entry
	private JMenuItem mnuItemAbout = new JMenuItem("About"); // About Entry
	private JMenuItem mnuItemLoad = new JMenuItem("Load..."); //Load
	private JMenuItem mnuItemFilter = new JMenuItem("Filter"); //Filter
	private JMenuItem mnuItemHTML = new JMenuItem("Write HTML"); //Filter
	private JMenuItem mnuItemHTMLPreview = new JMenuItem("Preview HTML"); //Filter

	/** Constructor for the GUI */
	public BasicGui(){
		// Set menubar
		parsecheck = false;
		f.setJMenuBar(mb);

		//Build Menus
		mnuFile.add(mnuItemQuit);  // Create Quit line
		mnuFile.add(mnuItemLoad);	// Create Load
		mnuFile.add(mnuItemFilter);
		mnuFile.add(mnuItemHTML);
		mnuFile.add(mnuItemHTMLPreview);
		mnuHelp.add(mnuItemAbout); // Create About line
		mb.add(mnuFile);        // Add Menu items to form
		mb.add(mnuHelp);

		// Allows the Swing App to be closed
		f.addWindowListener(new ListenCloseWdw());

		//Add Menu listener
		mnuItemQuit.addActionListener(new ListenMenuQuit());
		mnuItemLoad.addActionListener(new ListenMenuLoad());
		mnuItemFilter.addActionListener(new ListenMenuFilter());
		mnuItemHTML.addActionListener(new ListenMenuHTML());
		mnuItemHTMLPreview.addActionListener(new ListenMenuHTMLPreview());
	}

	public class ListenMenuHTMLPreview implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(parsecheck) {
				htmlcheck = true;
				Main.generateHTML();
				parsecheck = false;

				if(htmlcheck) {				
					for(File f:EventCollection.topreview) {      
						try {
							if(f.toURI().toString().contains("Summary")) {
								HTMLExample foo = new HTMLExample(f.toURI().toString());
							}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
				else{
					System.out.println("To pre");
				}
			}
			else{
				System.out.println("Load file first");
			}
		}
	}

	public class ListenMenuHTML implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(parsecheck) {
				htmlcheck = true;
				Main.generateHTML();
				parsecheck = false;
			}
			else{
				System.out.println("Load file first");
			}
		}
	}

	public class ListenMenuFilter implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(parsecheck) {
				GUIFilter guifilter = new GUIFilter();
				guifilter.launchFrame2();
			}
			else{
				System.out.println("Load file first");
			}
		}
	}

	public class ListenMenuLoad implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			parsecheck = true;

			JFileChooser fc = new JFileChooser();

			int returnVal = fc.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				input = file.getName();
			}

			try {
				Main.parseXML();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public ArrayList<String> getkeyword() {
		ArrayList<String> replacekey = keywordfilter;
		keywordfilter.clear();
		return replacekey;
	}
	public String getduke() {
		String replaceduke = duke;
		duke = "";
		return replaceduke;
	}public String getactor() {
		String replaceactor = actor;
		actor = "";
		return replaceactor;
	}public ArrayList<Date> getdate() {
		ArrayList<Date> replacedate = date;
		date.clear();
		return replacedate;
	}public String getter() {
		return input;
	}

	public class ListenMenuQuit implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.exit(0);         
		}
	}

	public class ListenCloseWdw extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			System.exit(0);         
		}
	}

	public void launchFrame(){
		// Display Frame
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack(); //Adjusts panel to components for display
		f.setVisible(true);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Filter Part

	public class GUIFilter{

		private int index;
		private Map<Integer, String> map = new HashMap<Integer, String>();

		// Initialize all swing objects.
		private JFrame f1 = new JFrame("Selector"); //create Frame
		private JPanel pnlNorth = new JPanel(); // North quadrant 
		private JPanel pnlSouth = new JPanel(); // South quadrant
		private JPanel pnlEast = new JPanel(); // East quadrant
		private JPanel pnlWest = new JPanel(); // West quadrant
		private JPanel pnlCenter = new JPanel(); // Center quadrant

		// Buttons some there is something to put in the panels
		private JButton btnSouth = new JButton("Filter by Date");
		private JButton btnEast = new JButton("Filter by Keyword");
		private JButton btnWest = new JButton("Filter by Actor");
		private JButton btnCenter = new JButton("Click to Filter");
		private	JButton btnNorth = new JButton("Filter by Duke");

		public GUIFilter() {
			// Add Buttons
			pnlNorth.add(btnNorth);
			pnlSouth.add(btnSouth);
			pnlEast.add(btnEast);
			pnlWest.add(btnWest);
			pnlCenter.add(btnCenter);

			// Setup Main Frame
			f1.getContentPane().setLayout(new BorderLayout());
			f1.getContentPane().add(pnlNorth, BorderLayout.NORTH);
			f1.getContentPane().add(pnlSouth, BorderLayout.SOUTH);
			f1.getContentPane().add(pnlEast, BorderLayout.EAST);
			f1.getContentPane().add(pnlWest, BorderLayout.WEST);
			f1.getContentPane().add(pnlCenter, BorderLayout.CENTER);

			btnNorth.addActionListener(new btnNorthListener());
			btnSouth.addActionListener(new btnSouthListener());
			btnEast.addActionListener(new btnEastListener());
			btnWest.addActionListener(new btnWestListener());
			btnCenter.addActionListener(new btnCenterListener());
		}

		public class btnNorthListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				duke = "duke";
			}
		}
		public class btnSouthListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				ArrayList<Integer> list = new ArrayList<Integer>();
				datecheck = true;

				while(list.size()!=6) {
					String number = JOptionPane.showInputDialog(null,
							"Filter by Date (new Date(x, x, x))",
							"Enter 6 values",
							JOptionPane.QUESTION_MESSAGE);
					int i = Integer.parseInt(number);

					list.add(i);
				}
				Date date1 = new Date(list.get(1), list.get(2), list.get(3));
				Date date2 = new Date(list.get(4), list.get(4), list.get(5));

				date.add(date1);
				date.add(date2);
			}
		}
		public class btnEastListener implements ActionListener{
			public void actionPerformed(ActionEvent e){			
				String keyword = JOptionPane.showInputDialog(null,
						"Filter by KeyWord",
						"Enter Keyword",
						JOptionPane.QUESTION_MESSAGE);
				keywordfilter.add(keyword);
			}
		}
		public class btnWestListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				actor = "";
				actor = JOptionPane.showInputDialog(null,
						"Filter by Actor",
						"Enter Actor",
						JOptionPane.QUESTION_MESSAGE);
			}
		}
		public class btnCenterListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				try{
					Main.filterEventList();
				} catch (FilterException f) {
					System.out.println(f.getMessage());
				}
			}
		}

		public void launchFrame2(){
			// Display Frame
			f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f1.pack(); //Adjusts panel to components for display
			f1.setVisible(true);
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Preview

	public class HTMLExample  extends JFrame {

		JEditorPane pane;

		public HTMLExample(String url) throws IOException
		{
			pane = new JEditorPane();
			getContentPane().setLayout(new BorderLayout());
			getContentPane().add(pane, BorderLayout.CENTER);
			pane.setEditable(false);
			pane.setPreferredSize(new Dimension(800,600));
			pane.addHyperlinkListener(new LinkFollower());
			pack();
			pane.setPage(url);
			setVisible(true);
		}


		private class LinkFollower implements HyperlinkListener
		{
			public void hyperlinkUpdate (HyperlinkEvent evt)
			{
				if (evt.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
				{
					// user clicked a link, load it and show it
					try
					{
						pane.setPage((evt.getURL().toString()));
					}
					catch (Exception e)
					{
						String s = evt.getURL().toString();
						JOptionPane.showMessageDialog(HTMLExample.this,
								"loading problem for " + s + " " + e,
								"Load Problem", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}
}