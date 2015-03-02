package TextFormatter;

/**
 *
 * @author Scott
 * @version 2/28/2015
 */
public class Formatter extends FormatterAbstract {


    public Float floatingPointFormat(Object object) {
        Float floats = (Float)0.0f;
        if (object != null) {
            if (vald.floatingPointFormatVaidate(object)) {
                if ("String".equals(object.getClass().getSimpleName())) {
                    floats = Float.parseFloat(object + "");
                } else if ("Integer".equals(object.getClass().getSimpleName())) {
                    int t = (int) object;
                    floats = t + .0f;
                } else {
                    floats = Float.parseFloat(object + "");
                }
            }
        }
        return floats;
    }//end floatingPointFormat

}//end Formatter
