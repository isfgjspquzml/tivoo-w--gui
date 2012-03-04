package html;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import event.Event;

/**
 * generate the Calendar of each month
 * 
 * @author Donghe
 */
public class EventCollectionCalendar extends EventCollection {

    private int eventIndex;
    private int dayIndex;
    private ArrayList<CalendarMonth> myAllMonthList;

    public EventCollectionCalendar(ArrayList<Event> filteredEvents,
            ArrayList<Event> backupEvents) {
        super(filteredEvents, backupEvents);
        eventIndex = INT_ZERO;
        myAllMonthList = getAllMonth();
    }

    /**
     * write calendar months
     * 
     * @param myFilteredEvents
     *            the event list
     * @throws IOException
     */
    public void writeHTMLFiles() throws IOException {
        for (CalendarMonth calMonth : myAllMonthList) {
            writeSpecificMonthCalendar(calMonth);
        }
    }

    /**
     * get the whole CalendarMonth information from the events list into a list
     */
    public ArrayList<CalendarMonth> getAllMonth() {
        ArrayList<CalendarMonth> list = new ArrayList<CalendarMonth>();
        for (Event e : myFilteredEvents) {
            int month = e.getStartDate().getMonth();
            int year = e.getStartDate().getYear() + YEAR_DIFF;
            int size = list.size();
            if (size == EMPTY_LIST
                    || list.get(size + INDEX_OFFSET).getMonth() != month
                    || list.get(size + INDEX_OFFSET).getYear() != year) {
                list.add(new CalendarMonth(month, year));
            }
        }
        return list;
    }

    /**
     * write each month of the calendar
     * 
     * @param calMonth
     *            the infomation of that month
     * @throws IOException
     */
    private void writeSpecificMonthCalendar(CalendarMonth calMonth)
            throws IOException {
        dayIndex = INT_ZERO;
        Tag html = new Tag(HTML_HEAD);
        Tag body = new Tag(HTML_BODY);
        writeNormalTag(HTML_TABLE_TITLE, calMonth.toString(), body);
        Tag table = new Tag(HTML_TABLE, HTML_TABLE_ATTRIBUTE);
        getTableHead(table);
        writeFirstWeek(table, calMonth);
        writeFollowWeeks(table, calMonth);
        body.add(table);
        html.add(body);
        String address = HTML.getHTMLFolderPath() + HTML_SPECIFIC_SLASH
                + HTML.getCalName() + "SummaryCalendar" + calMonth.toString()
                + HTML_END;
        writeHTML(html, address);
    }

    /**
     * write the first week
     * 
     * @param myFilteredEvents
     *            the event list
     * @param table
     *            where the tag should be included
     * @param calMonth
     *            the information about that month
     */
    private void writeFirstWeek(Tag table, CalendarMonth calMonth) {
        Calendar calendar = getMonthCalendar(calMonth);
        int numOfBlanks = numOfBlankDaysInMonthHead(calendar);
        Tag firstWeek = new Tag(HTML_TABLE_LINE);
        for (int i = 0; i < numOfBlanks; i++) {
            writeBlankDay(firstWeek);
        }
        for (int i = numOfBlanks; i < HTML_DAY_NUMBER; i++) {
            Tag eventThatDay = new Tag(HTML_TABLE_ROW);
            eventThatDay.add(dayIndex + HTML_LINE_BREAK);
            writeEventsInThatDay(calMonth, eventThatDay);
            firstWeek.add(eventThatDay);
            dayIndex++;
        }
        table.add(firstWeek);
    }

    /**
     * write the following weeks
     * 
     * @param myFilteredEvents
     *            the event list
     * @param table
     *            where the tag should be included
     * @param calMonth
     *            the information about that month
     */
    private void writeFollowWeeks(Tag table, CalendarMonth calMonth) {
        Calendar calendar = getMonthCalendar(calMonth);
        int numOfWeeks = numOfWeeksInMonth(calendar);
        for (int j = 1; j < numOfWeeks; j++) {
            writeFollowSpecificWeek(table, calMonth);
        }
    }

    /**
     * write the specific following week
     * 
     * @param myFilteredEvents
     *            the event list
     * @param table
     *            where the tag should be included
     * @param calMonth
     *            the information about that month
     */
    private void writeFollowSpecificWeek(Tag table, CalendarMonth calMonth) {
        Calendar calendar = getMonthCalendar(calMonth);
        int numOfDays = numOfDaysInMonth(calendar);
        Tag week = new Tag(HTML_TABLE_LINE);
        for (int i = 0; i < HTML_DAY_NUMBER; i++) {
            if (dayIndex > numOfDays) {
                writeBlankDay(week);
            } else {
                Tag eventThatDay = new Tag(HTML_TABLE_ROW);
                eventThatDay.add(dayIndex + HTML_LINE_BREAK);
                if (!hasNoEvent()) {
                    writeEventsInThatDay(calMonth, eventThatDay);
                }
                week.add(eventThatDay);
                dayIndex++;
            }
        }
        table.add(week);
    }

    /**
     * write blank into the first lables
     * 
     * @param week
     *            the first week tag
     */
    public void writeBlankDay(Tag week) {
        writeNormalTag(HTML_TABLE_ROW, HTML_SPECIFIC_TABLE_BLANK, week);
    }

    /**
     * write events in that day
     * 
     * @param myFilteredEvents
     *            the event list
     * @param calMonth
     *            the information of that month
     * @param eventThatDay
     *            where the tag should be included
     */
    public void writeEventsInThatDay(CalendarMonth calMonth, Tag eventThatDay) {
        while (hasEventInThatDay(calMonth)) {
            Event e = myFilteredEvents.get(eventIndex);
            String att = HTML_LINK_ATTRIBUTE + HTML.getHTMLFolderPath()
                    + HTML_SPECIFIC_SLASH + HTML.getCalName()
                    + myBackupEvents.indexOf(e) + HTML_END;
            writeAttTag(HTML_LINK, e.getTitle() + HTML_LINE_BREAK,
                    eventThatDay, att);
            eventThatDay.add(timeDisplay(e.getStartDate()) + HTML_SPECIFIC_TO
                    + timeDisplay(e.getEndDate()) + HTML_LINE_BREAK);
            eventIndex++;
            if (hasNoEvent())
                break;
        }
    }

    /**
     * whether there is left event in this list
     * 
     * @param myFilteredEvents
     *            the event list
     * @return whether there is left event
     */
    public boolean hasNoEvent() {
        return eventIndex >= myFilteredEvents.size();
    }

    /**
     * whether the date of the event and the day are the same
     * 
     * @param myFilteredEvents
     *            the event list
     * @param calMonth
     *            the information fo that month
     * @return whether the date of the event and the day are the same
     */
    public boolean hasEventInThatDay(CalendarMonth calMonth) {
        int month = calMonth.getMonth();
        int year = calMonth.getYear();

        return (myFilteredEvents.get(eventIndex).getStartDate().getYear() + YEAR_DIFF) == year
                && myFilteredEvents.get(eventIndex).getStartDate().getMonth() == month
                && myFilteredEvents.get(eventIndex).getStartDate().getDate() == dayIndex;
    }

    /**
     * get a Calendar class
     * 
     * @param calMonth
     *            the information of that month
     * @return
     */
    public Calendar getMonthCalendar(CalendarMonth calMonth) {
        Calendar calendar = Calendar.getInstance();
        int year = calMonth.getYear();
        int month = calMonth.getMonth();
        int date = INT_ONE;
        calendar.set(year, month, date);
        return calendar;
    }

    /**
     * @param calendar
     *            the calendar
     * @return the number of days in the month
     */
    public int numOfDaysInMonth(Calendar calendar) {
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * @param calendar
     *            the calendar
     * @return the number of weeks in the month
     */
    public int numOfWeeksInMonth(Calendar calendar) {
        return calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
    }

    /**
     * @param calendar
     *            the calendar
     * @return the number of first blank lables in the first week
     */
    public int numOfBlankDaysInMonthHead(Calendar calendar) {
        return calendar.getTime().getDay();
    }

}