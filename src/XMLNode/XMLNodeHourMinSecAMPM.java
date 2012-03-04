package XMLNode;

import event.Event;

/**
 * XML Node object for "AMPMTime" tag
 * Time contains hour, minutes, seconds using AM/PM time format
 * @author Andrew, Tianyu
 */
public class XMLNodeHourMinSecAMPM extends XMLNode {
   
    
    public void updateEvent(Event event, String tagInfo) 
    {
        int[] timeInfo = parseGeneralAMPMTimeFormat(tagInfo);
        
        if (!event.isStartTimeDefault()) setDateHourMinuteSecond(event.getEndDate(), timeInfo);
        else setDateHourMinuteSecond(event.getStartDate(), timeInfo);
    }

    

    public static int[] parseGeneralAMPMTimeFormat(String hourMinuteSecond) {

        int[] timeInfo = new int[SIZE_OF_TIME_ARRAY];
        boolean afterNoon = false;
        if (AFTERNOON_CHARACTER_REGEX.matcher(hourMinuteSecond).find())
            afterNoon = true;

        if (hourMinuteSecond.contains(COLON_CHARACTER))
            timeInfo = parseHourMinuteAMPMTimeFormat(hourMinuteSecond,
                    timeInfo, afterNoon);

        else
            timeInfo = parseOnlyHourAMPMTimeFormat(hourMinuteSecond, timeInfo,
                    afterNoon);

        timeInfo[SECONDS_INDEX] = DEFAULT_TIME;

        return timeInfo;
    }
    

    public static int[] parseHourMinuteAMPMTimeFormat(String hourMinuteSecond,
            int[] timeInfo, boolean afterNoon) {

        String[] hourAndMinuteArray = hourMinuteSecond.split(COLON_CHARACTER);

        int hour = Integer.parseInt(hourAndMinuteArray[HOUR_INDEX]);

        if (afterNoon && hour < AFTERNOON_HOUR)
            hour += AFTERNOON_HOUR;
        timeInfo[HOUR_INDEX] = hour;

        int minute = Integer.parseInt(hourAndMinuteArray[MINUTE_INDEX]
                .replaceAll(AMPM_REGEX, EMPTY_STRING).trim());
        timeInfo[MINUTE_INDEX] = minute;

        return timeInfo;
    }
    

    public static int[] parseOnlyHourAMPMTimeFormat(String hourMinuteSecond,
            int[] timeInfo, boolean afterNoon) {

        timeInfo[HOUR_INDEX] = Integer.parseInt(hourMinuteSecond.replaceAll(
                AMPM_REGEX, EMPTY_STRING));
        if (afterNoon && timeInfo[HOUR_INDEX] < AFTERNOON_HOUR)
            timeInfo[HOUR_INDEX] += AFTERNOON_HOUR;
        timeInfo[MINUTE_INDEX] = DEFAULT_TIME;

        return timeInfo;
    }

}
