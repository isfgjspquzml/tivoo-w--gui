package html;

/**
 * This class defines the month and year displayed in summaries
 * 
 * @author Donghe
 */

public class CalendarMonth implements HTMLConstants {
    private int myMonth;
    private int myYear;

    /**
     * constructor
     * 
     * @param month
     *            update myMonth
     * @param year
     *            update myYear
     */
    public CalendarMonth(int month, int year) {
        myMonth = month;
        myYear = year;
    }

    /**
     * the format that is displayed in summaries
     */
    public String toString() {
        return myYear + STRING_SPACE + (myMonth + INT_ONE);
    }

    public int getMonth() {
        return myMonth;
    }

    public int getYear() {
        return myYear;
    }

}