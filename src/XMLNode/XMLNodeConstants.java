package XMLNode;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Interface implemented by XMLNode, stores constants used in XMLNode classes
 * @author Andrew, Tianyu
 */
public interface XMLNodeConstants {

    static final int[] DEFAULT_START_HOUR_MIN_SECOND = {0,0,0};
    static final int[] DEFAULT_END_HOUR_MIN_SECOND = {23,59,59};
    static final int HOUR_INDEX = 0;
    static final int MINUTE_INDEX = 1;
    static final int SECONDS_INDEX = 2;
    static final int AFTERNOON_HOUR = 12;
    static final int DEFAULT_TIME = 0;
    static final int SIZE_OF_TIME_ARRAY = 3;
    
    static final String SLASH_CHARACTER = "/";
    static final String COLON_CHARACTER = ":";
    static final String DASH_CHARACTER = "-";
    static final String COMMA_CHARACTER = ",";
    static final String AFTERNOON_CHARACTER = "p";

    static final int DATE_FOUR_DIGIT_YEAR_OFFSET = -1900;
    static final int DATE_TWO_DIGIT_YEAR_OFFSET = 100;
    static final int TWO_DIGIT_YEAR_FORMAT = 2;
    static final int DATE_MONTH_OFFSET = -1;    
    
    static final int YEAR_INDEX = 0;
    static final int MONTH_INDEX = 1;
    static final int DAY_INDEX = 2;
    
    
    

    static final Pattern MONTH_DAY_YEAR_SLASH_REGEX = 
            Pattern.compile("(\\d){1,2}/{1}(\\d){1,2}/{1}(\\d){2,4}");
    
    static final Pattern YEAR_MONTH_DAY_DASH_REGEX = 
            Pattern.compile("(\\d){4}-{1}(\\d){2}-{1}(\\d){2}");
    
    static final Pattern VERBOSE_MONTH_DAY_YEAR_REGEX =
            Pattern.compile("(Sun|Mon|Tue|Wed|Thu|Fri|Sat){1}( ){1}(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec){1}( ){1}(\\d){1,2},{1}( ){1}(\\d){4}");
    
    
    static final Pattern[] myYearMonthDayPatterns = {
        MONTH_DAY_YEAR_SLASH_REGEX,
        YEAR_MONTH_DAY_DASH_REGEX,
        VERBOSE_MONTH_DAY_YEAR_REGEX
    };
    
    static final List<Pattern> myYearMonDayPatternsList = Arrays.asList(myYearMonthDayPatterns);

    static final XMLNode[] myYearMonDayNodeFormats = {
        new XMLNodeMonthDayYearSlash(),
        new XMLNodeYearMonthDayDash(),
        new XMLNodeVerboseYearMonthDay()
    };
    
    
    
    
    static final Pattern MILITARY_TIME_REGEX = 
            Pattern.compile("(\\d){2}:{1}(\\d){2}(:{1}(\\d){2})?");
    
    static final Pattern AMPM_TIME_REGEX = 
            Pattern.compile("(\\d){1,2}(:{1}(\\d){2})*(\\s)?(((A|a)|(P|p))(M|m)){1}");

    static final Pattern VERBOSE_HOUR_MINUTE_SECOND_REGEX = 
            Pattern.compile("(\\d){1,2}(:{1}(\\d){2})*( )?(((A|a)|(P|p))(M|m)){1}( to ){1}(\\d){1,2}(:{1}(\\d){2})*( )?(((A|a)|(P|p))(M|m)){1}");
    
    static final Pattern[] myHourMinSecPatterns = {
        MILITARY_TIME_REGEX,
        AMPM_TIME_REGEX,
        VERBOSE_HOUR_MINUTE_SECOND_REGEX
    };

    static final List<Pattern> myHourMinSecPatternsList = Arrays.asList(myHourMinSecPatterns);

    static final XMLNode[] myHourMinSecNodeFormats = {
        new XMLNodeHour24MinSecond(),
        new XMLNodeHourMinSecAMPM(),
        new XMLNodeVerboseHourMinuteSecond()
    };

    
    
    
    static final Pattern DATE_REGEX = Pattern.compile("(\\d){1,2},{1}");

    static final Pattern FOUR_DIGIT_YEAR_REGEX = Pattern.compile("(\\d){4}");

    static final Pattern THREE_LETTER_DAY_REGEX = Pattern
            .compile("Sun|Mon|Tue|Wed|Thu|Fri|Sat");

    static final Pattern THREE_LETTER_MONTH_REGEX = Pattern
            .compile("Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec");
                    
    static final Pattern AFTERNOON_CHARACTER_REGEX =
            Pattern.compile(AFTERNOON_CHARACTER, Pattern.CASE_INSENSITIVE);
      
    static final String AMPM_REGEX = "((A|a)|(P|p))(M|m)";
    static final String EMPTY_STRING = "";

    
    
    static final String THREE_LETTER_JANUARY = "Jan";
    static final String THREE_LETTER_FEBRUARY = "Feb";
    static final String THREE_LETTER_MARCH = "Mar";
    static final String THREE_LETTER_APRIL = "Apr";
    static final String THREE_LETTER_MAY = "May";
    static final String THREE_LETTER_JUNE = "Jun";
    static final String THREE_LETTER_JULY = "Jul";
    static final String THREE_LETTER_AUGUST = "Aug";
    static final String THREE_LETTER_SEPTEMBER = "Sep";
    static final String THREE_LETTER_OCTOBER = "Oct";
    static final String THREE_LETTER_NOVEMBER = "Nov";
    static final String THREE_LETTER_DECEMBER = "Dec";

    static final String[] myMonths = { 
        THREE_LETTER_JANUARY,
        THREE_LETTER_FEBRUARY,
        THREE_LETTER_MARCH,
        THREE_LETTER_APRIL,
        THREE_LETTER_MAY,
        THREE_LETTER_JUNE,
        THREE_LETTER_JULY,
        THREE_LETTER_AUGUST,
        THREE_LETTER_SEPTEMBER,
        THREE_LETTER_OCTOBER,
        THREE_LETTER_NOVEMBER,
        THREE_LETTER_DECEMBER
        };

    static final List<String> myMonthsList = Arrays.asList(myMonths);

    
    
}
