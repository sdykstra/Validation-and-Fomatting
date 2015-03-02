
package TextFormatter;

/**
 *
 * @author Scott Dykstra
 * @version 02/29/2013
 */

public class DataValidatorAbstract {

    public DataValidatorAbstract() {
    }

    public boolean numberFormatVaidate(Object value) {
        if (null != value) {
            String type = value.getClass().getSimpleName();
            if ("Integer".equals(type) || "Double".equals(type) || "Float".equals(type)) {
                return true;
            } else if ("String".equals(type)) {
                String str = (String) value;
                str = str.trim();
                if (str.length() > 0) {
                    int countP = 0;
                    for (int i = 0; i < str.length(); i++) {
                        int charValue = str.codePointAt(i);
                        if (charValue == 46) {
                            countP++;
                        }
                        if (charValue != 46 && (charValue < 48 || charValue > 57) || countP >= 2) {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    } //end numberFormatVaidate

    public boolean currencyFormatVaidate(Object value) {
        //make sure it does not have more than two decimal places
        if (null != value) {
            String type = value.getClass().getSimpleName();
            if (null != type) {
                switch (type) {
                    case "Integer":
                        return true;
                    case "Double":
                        String dub = value + "";
                        int dublength = dub.length() - 3;
                        if (".".equals("" + dub.charAt(dublength))) {
                            return true;
                        }
                        return false;
                    case "Float":
                        String flo = value + "";
                        int flolength = flo.length() - 3;
                        if (".".equals("" + flo.charAt(flolength))) {
                            return true;
                        }
                        return false;
                    case "String":
                        String str = (String) value;
                        str = str.trim();
                        if (commaFormatCheck(str) && str.length() > 0) {
                            str = str.replace(",", "");
                            int countP = 0;
                            for (int i = 0; i < str.length(); i++) {
                                int charValue = str.codePointAt(i);
                                if (charValue == 46) {
                                    countP++;
                                }
                                if (charValue != 46 && i != 0 && (charValue < 48 || charValue > 57) || countP >= 2) {
                                    return false;
                                } else if (charValue != 46 && charValue != 36 && (charValue < 48 || charValue > 57) || countP >= 2) {
                                    return false;
                                }
                            }
                            return true;
                        }
                    default:
                        return false;
                }
            }
        }
        return false;
    } //end currencyFormatVaidate

    public boolean commaFormatCheck(String value) {
        if (null != value) {
            if (value.length() > 0) {
                String subTop;
                if (value.contains(".")) {
                    subTop = value.substring(0, value.indexOf("."));
                    String subBottom = value.substring(value.indexOf("."), value.length());
                    if (subBottom.contains(",")) {
                        return false;
                    }
                } else {
                    subTop = value;
                }
                if (!subTop.contains(",")) {
                    return true;
                } else {
                    if (subTop.length() <= 3) {
                        return false;
                    } else {
                        String subBottom = subTop.substring(subTop.length() - 4, subTop.length());
                        if (subBottom.charAt(0) == ',') {
                            for (int i = 1; i < 4; i++) {
                                if (subBottom.charAt(i) == ',') {
                                    return false;
                                }
                            }
                            return commaFormatCheck(subTop.substring(0, subTop.length() - 4));
                        } else {
                            return false;
                        }
                    }
                }
            } else {
                return true;
            }
        }
        return false;
    } //end commaFormatCheck

    public boolean floatingPointFormatVaidate(Object value) {
        if (null != value) {
            String type = value.getClass().getSimpleName();
            if (null != type) {
                switch (type) {
                    case "Integer":
                    case "Double":
                    case "Float":
                        return true;
                    case "String":
                        String str = (String) value;
                        if (commaFormatCheck(str)) {
                            str = str.replaceAll(",", "");
                            str = str.trim();
                            if (str.length() > 0) {
                                int countP = 0;
                                for (int i = 0; i < str.length(); i++) {
                                    int charValue = str.codePointAt(i);
                                    if (charValue == 46) {
                                        countP++;
                                    }
                                    if (charValue != 46 && i != 0 && (charValue < 48 || charValue > 57) || countP >= 2) {
                                        return false;
                                    } else if (charValue != 46 && (charValue < 48 || charValue > 57) || countP >= 2) {
                                        return false;
                                    }
                                }
                                return true;
                            }
                        }
                    default:
                        return false;
                }
            }
        }
        return false;
    } //end floatingPointFormatVaidate

    public boolean SSNumberFormatVaidate(Object value) {
        if (null != value) {
            if ("Integer".equals(value.getClass().getSimpleName())) {
                if (String.valueOf(value).length() == 9) {
                    return true;
                }
            } else if ("String".equals(value.getClass().getSimpleName())) {
                String str = (String) value;
                if (!str.contains(",")) {
                    if (str.length() > 0) {
                        str = str.replaceAll("\\s+", "");
                        str = str.replaceAll("-", "");
                        for (int i = 0; i < str.length(); i++) {
                            int charValue = str.codePointAt(i);
                            if (charValue < 48 || charValue > 57) {
                                return false;
                            }
                        }
                        if (str.length() != 9) {
                            return false;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    } //end SSNnumberFormatVaidate

    public boolean accountNumberFormatVaidate(Object value) {
        // need to look into this, numbers only? certain length?
        //credit vs bank account numbers?
        if (null != value) {
            if ("Integer".equals(value.getClass().getSimpleName())) {
                int length = String.valueOf(value).length();
                if (length > 2 && length < 21) {
                    return true;
                }
            } else if ("String".equals(value.getClass().getSimpleName())) {
                String str = (String) value;
                str = str.trim();
                if (!str.contains(",")) {
                    if (str.length() > 0) {
                        str = str.replaceAll("\\s+", "");
                        str = str.replaceAll("-", "");
                        for (int i = 0; i < str.length(); i++) {
                            int charValue = str.codePointAt(i);
                            if (charValue < 48 || charValue > 57) {
                                return false;
                            }
                        }
                        int length = str.length();
                        if (length > 2 && length < 21) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    } //end accountNumberFormatVaidate

    public boolean StringValueFormatVaidate(Object value) {
        // this is for string values only being names, cities, states
        if (null != value) {
            if ("String".equals(value.getClass().getSimpleName())) {
                String str = (String) value;
                if (!str.contains(" ") && str.length() > 0) {
                    int countC = 0;
                    int countD = 0;
                    for (int i = 0; i < str.length(); i++) {
                        if ((str.codePointAt(i) >= 65 && str.codePointAt(i) <= 90 || str.codePointAt(i) >= 97 && str.codePointAt(i) <= 122 || str.codePointAt(i) == 45 || str.codePointAt(i) == 39) && countD < 2 && countC < 2) {
                            if (str.codePointAt(i) == 39) {
                                countC++;
                            } else if (str.codePointAt(i) == 45) {
                                countD++;
                            }
                        } else {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    } //end StringValueFormatVaidate

    public boolean timeFormatVaidate(Object value) {
        String str = null;
        if (value != null) {
            if ("String".equals(value.getClass().getSimpleName())) {
                str = (String) value;
                str = str.toLowerCase();
                int length = str.length();
                if (length >= 3 && length <= 13) {
                    if (str.contains("m")) {
                        str = str.replace(" ", "");
                        if (str.contains(".")) {
                            if (str.charAt(str.length() - 1) == '.' && str.charAt(str.length() - 3) == '.') {
                                str = str.replace(".", "");
                            } else {
                                return false;
                            }
                        }
                        if (str.charAt(str.length() - 1) == 'm') {
                            if (str.charAt(str.length() - 2) == 'a' || str.charAt(str.length() - 2) == 'p') {
                                str = str.substring(0, str.length() - 2);
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                        length = str.length();
                    }
                    if (str.contains(":") && length > 3 && length < 9) {
                        if (str.charAt(str.length() - 3) == ':' && length < 7) {
                            str = str.replace(":", "");
                        } else if (str.charAt(str.length() - 3) == ':' && str.charAt(str.length() - 6) == ':' && length > 6 && length < 9) {
                            str = str.replace(":", "");
                        } else if (str.contains(":")) {
                            return false;
                        }
                        for (int i = 0; i < str.length(); i++) {
                            int charValue = str.codePointAt(i);
                            if (charValue < 48 || charValue > 57) {
                                return false;
                            }
                            if (Integer.parseInt(str.charAt(i) + "", 10) > 6 && i < str.length() - 1) {
                                return false;
                            }
                        }
                        return true;
                    } else if (str.length() < 7) {
                        for (int i = 0; i < str.length(); i++) {
                            int charValue = str.codePointAt(i);
                            if (charValue < 48 || charValue > 57) {
                                return false;
                            }
                        }
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    } //end timeFormatVaidate

    public boolean phoneNumberFormatVaidate(Object value) {
        //us only numbers for the us don't think there 5 digit numbers any more
        if (null != value) {
            if ("Integer".equals(value.getClass().getSimpleName())) {
                if (String.valueOf(value).length() == 9) {
                    return true;
                } else if (String.valueOf(value).length() == 10) {
                    if (String.valueOf(value).charAt(0) == '1') {
                        return true;
                    }
                }
            } else if ("String".equals(value.getClass().getSimpleName())) {
                String str = (String) value;
                str = str.replace(" ", "");
                if (!str.contains(",")) {
                    if (str.length() > 0 && str.length() <= 14) {
                        str = str.replaceAll("-", "");
                        for (int i = 0; i < str.length(); i++) {
                            int charValue = str.codePointAt(i);
                            if (charValue < 48 || charValue > 57) {
                                return false;
                            }
                        }
                        if (str.length() == 8 && str.charAt(0) != '1') {
                            return false;
                        } else if (str.length() == 11 && str.charAt(0) != '1') {
                            return false;
                        }
                        if (str.length() <= 11 && str.length() >= 7) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    } //end phoneNumberFormatVaidate

    public boolean EmailAddressFormatVaidate(Object value) {
        if (value != null) {
            if ("String".equals(value.getClass().getSimpleName())) {
                String str = (String) value;
                str = str.toLowerCase();
                if (str.contains("@") && str.contains(".")) {
                    int at = str.lastIndexOf("@");
                    int dot = str.lastIndexOf(".");
                    if (dot - at >= 2) {
                        if (dot < (str.length() - 1)) {
                            str = str.substring(0, at);
                            int firstDot = 0;
                            int quot = 0;
                            int count = 0;
                            for (int i = 0; i < str.length(); i++) {
                                if (str.codePointAt(i) >= 97 && str.codePointAt(i) <= 122 || str.codePointAt(i) >= 48 && str.codePointAt(i) <= 57 || str.codePointAt(i) == 45 || str.codePointAt(i) == 95 || str.codePointAt(i) == 34 || str.codePointAt(i) == 46) {
                                    if (str.codePointAt(i) == 46) {
                                        if (firstDot == 0) {
                                            firstDot = i;
                                        } else {
                                            if (i - firstDot > 1) {
                                                firstDot = i;
                                            } else {
                                                return false;
                                            }
                                        }
                                    }
                                    if (str.codePointAt(i) == 34) {
                                        quot = i;
                                        if (quot - firstDot == 1 && count == 0) {
                                            count++;
                                        } else if (str.charAt(i + 1) == '.' && count == 1) {
                                            count = 0;
                                        } else {
                                            return false;
                                        }
                                    }
                                } else {
                                    return false;
                                }
                                //  System.out.println(str);
                            }
                            if (count == 0) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    } //end EmailFormatVaidate

    public boolean dateFormatVaidate(Object value) {
    return false;}//end dateFormatVaidate
    
}
