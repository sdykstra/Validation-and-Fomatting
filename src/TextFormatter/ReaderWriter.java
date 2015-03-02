package TextFormatter;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Scott Dykstra
 * @version 03/01/2013
 */
public class ReaderWriter extends ReaderWriterAbstract {


    public ReaderWriter() {
        tokens = new ArrayList();

    }//end ReaderWriter

    public ReaderWriter(String value) {
        if (value != null) {
            tokens = new ArrayList();
            tokenizer(value, null);
        }
    }//end ReaderWriter

    public ReaderWriter(String value, String delim) {
        if (value != null) {
            tokens = new ArrayList();
            tokenizer(value, delim);
        }
    }//end ReaderWriter

    public ReaderWriter(Scanner value) {
        if (value != null) {
            tokens = new ArrayList();
            String string = "";
            while (value.hasNext()) {
                string += value.next() + " ";
            }
            tokenizer(string, " ");
        }
    }//end ReaderWriter

    public ReaderWriter(Scanner value, String delim) {
        if (value != null) {
            tokens = new ArrayList();
            String string = "";
            while (value.hasNext()) {
                string += value.next() + delim;
            }
            tokenizer(string, delim);
        }
    }//end ReaderWriter

}//end ReaderWriter
