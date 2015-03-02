package TextFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author Scott Dykstra
 * @version 2/19/2015
 *
 */
public class DataValidator extends DataValidatorAbstract {

    static HashMap<String, Integer> monthsMap = new HashMap();

    public DataValidator() {
        ArrayList<String> shortMonths = new ArrayList(Arrays.asList("",
                "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug",
                "sep", "oct", "nov", "dec"));
        ArrayList<String> longMonths = new ArrayList(Arrays.asList("",
                "january", "february", "march", "april", "may", "june", "july", "august",
                "september", "october", "november", "december"));

        for (int month = 1; month < shortMonths.size(); month++) {

            monthsMap.put(shortMonths.get(month), month);
            monthsMap.put(longMonths.get(month), month);
        }
    }

    public static int getMonthInt(String monthIn) {

        if (monthsMap.containsKey(monthIn)) {
            return monthsMap.get(monthIn);
        } else {
            return 0;
        }
    }// end getMonthInt

    public static boolean daysOfMonthCheck(int month, int day, int year) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            if (day == 31) {
                return true;
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (day == 30) {
                return true;
            }
        } else if (month == 2) {
            if (year % 4 == 0) {
                if (day == 29) {
                    return true;
                }
            } else if (year % 4 != 0) {
                if (day == 28) {
                    return true;
                }
            }
        }
        return false;
    }//end of daysOfMonthCheck

    @Override
    public boolean dateFormatVaidate(Object value) {
        int month = 0;
        int day = 0;
        int year = 0;
        if (value != null) {
            if ("String".equals(value.getClass().getSimpleName())) {
                String str = (String) value;
                str = str.toLowerCase();
                if (str.contains("/") || str.contains(".") || str.contains("-")) {
                    str = str.replace(".", "/");
                    str = str.replace("-", "/");
                    int length = str.length();
                    for (int i = 0; i < length; i++) {
                        if (str.codePointAt(i) < 47 || str.codePointAt(i) > 57) {
                            return false;
                        }
                        if (str.length() == 8) {
                        } else if (str.length() == 10) {
                            if (str.lastIndexOf("/") == str.length() - 4) {
                                year = Integer.parseInt(str.substring(str.lastIndexOf("/")));
                                month = Integer.parseInt(str.substring(0, 2));
                                day = Integer.parseInt(str.substring(str.indexOf('/'), str.lastIndexOf("/")));
                                System.out.println(year + " " + month + " " + day);
                            } else if (str.lastIndexOf("/") == str.length() - 2) {
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    } //end dateFormatVaidate

}//end class DataValidator
