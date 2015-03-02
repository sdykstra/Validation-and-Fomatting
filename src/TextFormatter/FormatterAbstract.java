
package TextFormatter;

/**
 *
 * @author Scott Dysktra
 * @version 2-28-2015
 */
public class FormatterAbstract {
    DataValidator vald = new DataValidator();

    public String timeFormat(String value) {
        String time = null;
        if (vald.timeFormatVaidate(value)) {
            if (value.contains(":")) {
                return value;
            }
            int len = value.length();
            if (value.length() > 4) {
                time = value.substring(0, len - 4) + ":" + value.substring(len - 4, len - 2) + ":" + value.substring(len - 2, len);
            } else {
                time = value.substring(0, len - 2) + ":" + value.substring(len - 2, len);
            }
        }
        return time;
    }

    public String SSNFormat(Object object) {
        String ssn = null;
        if (vald.SSNumberFormatVaidate(object)) {
            if (null != object.getClass().getSimpleName()) {
                switch (object.getClass().getSimpleName()) {
                    case "Integer":
                        {
                            String str = object + "";
                            ssn = str.substring(0, 3) + "-" + str.substring(3, 5) + "-" + str.substring(5, str.length());
                            break;
                        }
                    case "String":
                        {
                            String str = (String) object;
                            if (str.contains("-")) {
                                str = str.replaceAll("-", "");
                            }
                            str = str.replaceAll(" ", "");
                            ssn = str.substring(0, 3) + "-" + str.substring(3, 5) + "-" + str.substring(5, str.length());
                            break;
                        }
                }
            }
        }
        return ssn;
    } //end SSNFormat

    public String phoneNumberFormat(Object object) {
        String phoneNumber = null;
        if (object != null) {
            if (vald.phoneNumberFormatVaidate(object)) {
                if (null != object.getClass().getSimpleName()) {
                    switch (object.getClass().getSimpleName()) {
                        case "Integer":
                            {
                                String str = object + "";
                                if (str.length() == 10) {
                                    phoneNumber = str.substring(0, 3) + "-" + str.substring(3, 6) + "-" + str.substring(6, str.length());
                                } else {
                                    phoneNumber = str.substring(0, 1) + "-" + str.substring(1, 4) + "-" + str.substring(4, 7) + "-" + str.substring(7, str.length());
                                }
                                break;
                            }
                        case "String":
                            {
                                String str = (String) object;
                                str = str.replaceAll(" ", "");
                                if (str.contains("-")) {
                                    str = str.replaceAll("-", "");
                                }
                                if (str.length() == 10) {
                                    phoneNumber = str.substring(0, 3) + "-" + str.substring(3, 6) + "-" + str.substring(6, str.length());
                                } else {
                                    phoneNumber = str.substring(0, 1) + "-" + str.substring(1, 4) + "-" + str.substring(4, 7) + "-" + str.substring(7, str.length());
                                }
                                break;
                            }
                    }
                }
            }
        }
        return phoneNumber;
    } //end phoneNumberFormat

    public double currencyFormat(Object object) {
        double money = 0;
        if (object != null) {
            if (vald.currencyFormatVaidate(object)) {
                if ("String".equals(object.getClass().getSimpleName())) {
                    money = Double.parseDouble(object + "");
                } else if ("Integer".equals(object.getClass().getSimpleName())) {
                    money = (int) object + 0.0;
                } else {
                    money = (double) object;
                }
            }
        }
        return money;
    } //end currencyFormat

    public int accountNumberFormat(Object object) {
        int account = 0;
        if (object != null) {
            if (vald.accountNumberFormatVaidate(object)) {
                if ("String".equals(object.getClass().getSimpleName())) {
                    account = Integer.parseInt(object + "");
                } else if ("Integer".equals(object.getClass().getSimpleName())) {
                    account = (int) object;
                }
            }
        }
        return account;
    } //end accountNumberFormat

    public String EmailAddressFormat(Object object) {
        String emailAddress = "";
        if (vald.EmailAddressFormatVaidate(object)) {
            emailAddress = (String) object;
        }
        return emailAddress;
    } //end EmailAddressFormat

    public String StringValuesFormat(Object object) {
        String stringValue = null;
        if (object != null) {
            if (vald.StringValueFormatVaidate(object)) {
                stringValue = (String) object;
                stringValue = stringValue.trim();
            }
        }
        return stringValue;
    } //end StringValuesFormat
    
}
