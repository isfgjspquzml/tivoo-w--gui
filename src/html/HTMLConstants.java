package html;

/**
 * all the constants that are used in HTML parts
 * 
 * @author Donghe
 */
public interface HTMLConstants {

    static final String INPUT_NAME = "Input the Calendar Name (NO white space): ";
    static final String INPUT_PATH = "Input a folder path where you want to generate HTML: ";
    static final String NOT_FOLDER = "Folder not found";
    static final String IO_EXCEPTION = "IOException in HTML output";
    static final String HTML_LINE_BREAK = "<br />";

    static final String HTML_END = ".html";
    static final String HTML_HEAD = "html";
    static final String HTML_BODY = "body";
    static final String HTML_LINK_ATTRIBUTE = "href=";
    static final String HTML_LINK = "a";
    static final String HTML_TITLE = "title";
    static final String HTML_TABLE = "table";
    static final String HTML_TABLE_ATTRIBUTE = "border=1";
    static final String HTML_TABLE_LINE = "tr";
    static final String HTML_TABLE_HEADER = "th";
    static final String HTML_TABLE_ROW = "td";
    static final String HTML_TEXT_ITALIC = "i";
    static final String HTML_TABLE_TITLE = "div";
    static final String HTML_FONT = "font";
    static final String HTML_COLOR_RED = "color=red";

    static final String HTML_SPECIFIC_SLASH = "\\";
    static final String HTML_SPECIFIC_COLON = ":";
    static final String HTML_SPECIFIC_DESCRIPTION = "Description";
    static final String HTML_SPECIFIC_TIME = "time";
    static final String HTML_SPECIFIC_TIME_START = "startTime";
    static final String HTML_SPECIFIC_TIME_END = "endTime";
    static final String HTML_SPECIFIC_DAY = "Day";
    static final String HTML_SPECIFIC_AUTHOR = "Author";
    static final String HTML_SPECIFIC_TO = " to ";
    static final String HTML_SPECIFIC_SUMMARY_TABLE = "SummaryTable";
    static final String HTML_SPECIFIC_SUMMARY_LIST = "SummaryList";
    static final String HTML_SPECIFIC_SUMMARY_LIST_CONFLICT = "SummaryListConflict";
    static final String HTML_SPECIFIC_TABLE_BLANK = "&nbsp;";

    static final String HTML_DAY_SUN = "Sunday";
    static final String HTML_DAY_MON = "Monday";
    static final String HTML_DAY_TUE = "Tuesday";
    static final String HTML_DAY_WED = "Wednesday";
    static final String HTML_DAY_THU = "Thursday";
    static final String HTML_DAY_FRI = "Friday";
    static final String HTML_DAY_SAT = "Saturday";
    static final int HTML_DAY_NUMBER = 7;

    static final int INT_TEN = 10;
    static final int INT_ONE = 1;
    static final int INT_ZERO = 0;
    static final int YEAR_DIFF = 1900;
    static final String STRING_ZERO = "0";
    static final String STRING_BLANK = "";
    static final String STRING_SPACE = " ";
    static final int EMPTY_LIST = 0;
    static final int INDEX_OFFSET = -1;

    static final String DEPRECATION = "deprecation";

}