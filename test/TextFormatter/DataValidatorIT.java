package TextFormatter;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Scott Dykstra
 * @version 2/19/2015
 *
 */
public class DataValidatorIT {

    public DataValidatorIT() {
    }

    @Test
    public void numberFormatVaidateTest() {
        DataValidator temp = new DataValidator();
        assertTrue(temp.numberFormatVaidate(123112.560));
        assertTrue(temp.numberFormatVaidate(56));
        assertTrue(temp.currencyFormatVaidate(00000000));
        assertTrue(temp.numberFormatVaidate((float) 12.56));
        assertTrue(temp.numberFormatVaidate("431.00"));
        assertTrue(temp.currencyFormatVaidate(" 431.00"));
        assertFalse(temp.numberFormatVaidate(""));
        assertFalse(temp.numberFormatVaidate("t431t"));
        assertFalse(temp.currencyFormatVaidate("*12@431"));
        assertFalse(temp.currencyFormatVaidate("Tom Jones"));
        assertFalse(temp.numberFormatVaidate("$431.00"));
        assertFalse(temp.currencyFormatVaidate("12.4.31"));
        assertFalse(temp.numberFormatVaidate(null));
    }

    @Test
    public void currencyFormatVaidateTest() {
        DataValidator temp = new DataValidator();
        assertTrue(temp.currencyFormatVaidate(123112.560));
        assertTrue(temp.currencyFormatVaidate(00000000));
        assertTrue(temp.currencyFormatVaidate(56));
        assertTrue(temp.currencyFormatVaidate((float) 12.56));
        assertTrue(temp.currencyFormatVaidate("431.00"));
        assertTrue(temp.currencyFormatVaidate("$431.00"));
        assertTrue(temp.currencyFormatVaidate(" $431.00"));
        assertTrue(temp.currencyFormatVaidate(" $4,310.00"));
        assertFalse(temp.currencyFormatVaidate(""));
        assertFalse(temp.currencyFormatVaidate(" "));
        assertFalse(temp.currencyFormatVaidate("t431t"));
        assertFalse(temp.currencyFormatVaidate("*12@431t"));
        assertFalse(temp.currencyFormatVaidate("Tom Jones"));
        assertFalse(temp.currencyFormatVaidate(123112.562));
        assertFalse(temp.currencyFormatVaidate(null));
        assertFalse(temp.currencyFormatVaidate(" $12.4.31"));
        assertFalse(temp.currencyFormatVaidate("$12$4.31"));
        assertFalse(temp.currencyFormatVaidate("$12 4.31"));
    }

    @Test
    public void SSNumberFormatVaidateTest() {
        DataValidator temp = new DataValidator();
        assertTrue(temp.SSNumberFormatVaidate(123457890));
        assertTrue(temp.SSNumberFormatVaidate("123457890"));
        assertTrue(temp.SSNumberFormatVaidate("123 45 7890"));
        assertTrue(temp.SSNumberFormatVaidate("12 3457 890"));
        assertTrue(temp.SSNumberFormatVaidate("123-45-7890"));
        assertFalse(temp.numberFormatVaidate(""));
        assertFalse(temp.numberFormatVaidate(" "));
        assertFalse(temp.SSNumberFormatVaidate("55,444,333,222,111.0001"));
        assertFalse(temp.SSNumberFormatVaidate("22,111"));
        assertFalse(temp.SSNumberFormatVaidate("12345678901"));
        assertFalse(temp.SSNumberFormatVaidate("1234B7890"));
        assertFalse(temp.SSNumberFormatVaidate("1234/78?0"));
        assertFalse(temp.SSNumberFormatVaidate("ABCDEFGHI"));
        assertFalse(temp.SSNumberFormatVaidate(null));
    }

    @Test
    public void accountNumberFormatVaidateTest() {
        DataValidator temp = new DataValidator();
        assertTrue(temp.accountNumberFormatVaidate(123457890));
        assertTrue(temp.accountNumberFormatVaidate(123));
        assertTrue(temp.accountNumberFormatVaidate("123457890"));
        assertTrue(temp.accountNumberFormatVaidate("123 45 7890"));
        assertTrue(temp.accountNumberFormatVaidate("12 3457 890"));
        assertTrue(temp.accountNumberFormatVaidate("123-45-7890"));
        assertTrue(temp.accountNumberFormatVaidate("12345678901234567891"));
        assertTrue(temp.accountNumberFormatVaidate("1234 5678 9012 3456 7891"));
        assertFalse(temp.accountNumberFormatVaidate(""));
        assertFalse(temp.accountNumberFormatVaidate(" "));
        assertFalse(temp.accountNumberFormatVaidate("1234B7890"));
        assertFalse(temp.accountNumberFormatVaidate("1234,B7890"));
        assertFalse(temp.accountNumberFormatVaidate("1234/78?0"));
        assertFalse(temp.accountNumberFormatVaidate("ABCDEFGHI"));
        assertFalse(temp.accountNumberFormatVaidate(null));
    }

    @Test
    public void floatingPointFormatVaidateTest() {
        DataValidator temp = new DataValidator();
        assertTrue(temp.floatingPointFormatVaidate(.5f));
        assertTrue(temp.floatingPointFormatVaidate(5f));
        assertTrue(temp.floatingPointFormatVaidate(123112.560f));
        assertTrue(temp.floatingPointFormatVaidate(00000000));
        assertTrue(temp.floatingPointFormatVaidate(56));
        assertTrue(temp.floatingPointFormatVaidate((float) 12.56));
        assertTrue(temp.floatingPointFormatVaidate("431.00"));
        assertTrue(temp.floatingPointFormatVaidate("431.12314"));
        assertTrue(temp.floatingPointFormatVaidate(" 431.00"));
        assertTrue(temp.floatingPointFormatVaidate("55,444,333,222,111.0001"));
        assertTrue(temp.floatingPointFormatVaidate("22,111"));
        assertFalse(temp.floatingPointFormatVaidate(""));
        assertFalse(temp.floatingPointFormatVaidate("t431t"));
        assertFalse(temp.floatingPointFormatVaidate("*12@431t"));
        assertFalse(temp.floatingPointFormatVaidate("Tom Jones"));
        assertFalse(temp.floatingPointFormatVaidate(null));
        assertFalse(temp.floatingPointFormatVaidate(" %12.4.31"));
        assertFalse(temp.floatingPointFormatVaidate("$12&4.31"));
        assertFalse(temp.floatingPointFormatVaidate("$12 4.31"));
        assertFalse(temp.floatingPointFormatVaidate("55,444,33,,222,111.0001"));
        assertFalse(temp.floatingPointFormatVaidate("111.,001"));
    }

    @Test
    public void commaFormatCheckerTest() {
        DataValidator temp = new DataValidator();
        assertTrue(temp.commaFormatCheck("1"));
        assertTrue(temp.commaFormatCheck("123457890"));
        assertTrue(temp.commaFormatCheck("55,444,333,222,111.0001"));
        assertTrue(temp.commaFormatCheck("22,111"));
        assertTrue(temp.commaFormatCheck("111.0001"));
        assertTrue(temp.commaFormatCheck(""));
        assertTrue(temp.commaFormatCheck("$12&4.31"));
        assertTrue(temp.commaFormatCheck("$12 4.31"));
        assertFalse(temp.commaFormatCheck(null));
        assertFalse(temp.commaFormatCheck(" %12,4.31"));
        assertFalse(temp.commaFormatCheck("22,,11"));
        assertFalse(temp.commaFormatCheck("55,444,33,,222,111.0001"));
        assertFalse(temp.commaFormatCheck("111.,001"));
    }

    @Test
    public void StringValueFormatVaidateTest() {
        DataValidator temp = new DataValidator();
        assertTrue(temp.StringValueFormatVaidate("a"));
        assertTrue(temp.StringValueFormatVaidate("B"));
        assertTrue(temp.StringValueFormatVaidate("Test"));
        assertTrue(temp.StringValueFormatVaidate("abcdefghijklmnopqrestvwxyz"));
        assertTrue(temp.StringValueFormatVaidate("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        assertTrue(temp.StringValueFormatVaidate("ABCDEFGHI-klmnopqrestv"));
        assertTrue(temp.StringValueFormatVaidate("ABCDEFGHI'klmnopqrestv"));
        assertTrue(temp.StringValueFormatVaidate("ABCDEFGHI'klmnopq-restv"));
        assertFalse(temp.StringValueFormatVaidate('A'));// Case for a warning
        assertFalse(temp.StringValueFormatVaidate("Test test"));
        assertFalse(temp.StringValueFormatVaidate(1234));
        assertFalse(temp.StringValueFormatVaidate(" "));
        assertFalse(temp.StringValueFormatVaidate(null));
        assertFalse(temp.StringValueFormatVaidate(12.656));
        assertFalse(temp.StringValueFormatVaidate("String-values-Dashes"));
        assertFalse(temp.StringValueFormatVaidate("String'values'n"));
        assertFalse(temp.StringValueFormatVaidate("Str-ing'value-s'b"));
        assertFalse(temp.StringValueFormatVaidate("12656"));
        assertFalse(temp.StringValueFormatVaidate("12,656,000"));
    }

    @Test
    public void timeFormatVaidateTest() {
        DataValidator temp = new DataValidator();
        //have to find out how to handle integers that start with zero going binary
        //assertTrue(temp.timeFormatVaidate(2400));
        // assertTrue(temp.timeFormatVaidate(1259));
        // assertTrue(temp.timeFormatVaidate(0200));
        // assertTrue(temp.timeFormatVaidate(0001));
        //assertTrue(temp.timeFormatVaidate(301));

        assertTrue(temp.timeFormatVaidate("11:40:34"));
        assertTrue(temp.timeFormatVaidate("11:40:34 am"));
        assertTrue(temp.timeFormatVaidate("400"));
        assertTrue(temp.timeFormatVaidate("1259"));
        assertTrue(temp.timeFormatVaidate("125934"));
        assertTrue(temp.timeFormatVaidate("2400"));
        assertTrue(temp.timeFormatVaidate("12:59"));
        assertTrue(temp.timeFormatVaidate("12:00pm"));
        assertTrue(temp.timeFormatVaidate("1:00 am"));
        assertTrue(temp.timeFormatVaidate("400"));
        assertTrue(temp.timeFormatVaidate("1259"));
        assertTrue(temp.timeFormatVaidate("2400"));
        assertTrue(temp.timeFormatVaidate("1259"));
        assertTrue(temp.timeFormatVaidate("0100"));
        assertTrue(temp.timeFormatVaidate("0001"));

        assertFalse(temp.timeFormatVaidate("12,656,000"));
        assertFalse(temp.timeFormatVaidate("125:9"));
        assertFalse(temp.timeFormatVaidate("2:400"));
        assertFalse(temp.timeFormatVaidate("12:59 45"));
        assertFalse(temp.timeFormatVaidate("1C34"));
        assertFalse(temp.timeFormatVaidate("00 01"));
        assertFalse(temp.timeFormatVaidate("19:59"));
        assertFalse(temp.timeFormatVaidate(null));

    }

    @Test
    public void phoneNumberFormatVaidateTest() {
        DataValidator temp = new DataValidator();
        assertTrue(temp.phoneNumberFormatVaidate(124347890));
        assertTrue(temp.phoneNumberFormatVaidate("234 4567"));
        assertTrue(temp.phoneNumberFormatVaidate("1-345-7890"));
        assertTrue(temp.phoneNumberFormatVaidate("12 45 7890"));
        assertTrue(temp.phoneNumberFormatVaidate("1 245 345 7890"));
        assertTrue(temp.phoneNumberFormatVaidate("1-823-495-7890"));

        assertFalse(temp.phoneNumberFormatVaidate("3-823-495-7890"));
        assertFalse(temp.phoneNumberFormatVaidate("7-495-7890"));
        assertFalse(temp.phoneNumberFormatVaidate(123));
        assertFalse(temp.phoneNumberFormatVaidate("12345678901234567891"));
        assertFalse(temp.phoneNumberFormatVaidate("1234B7890"));
        assertFalse(temp.phoneNumberFormatVaidate("1234,B7890"));
        assertFalse(temp.phoneNumberFormatVaidate("1234/78?0"));
        assertFalse(temp.phoneNumberFormatVaidate("ABCDEFGHI"));
        assertFalse(temp.phoneNumberFormatVaidate(null));
    }

    @Test
    public void EmailAddressFormatVaidateTest() {
        //Wikipedia says they are permissiable but some mail servers refuse to send email with 
        // addresses containing !#$%*/?^`{|}~ vlaue so I am excluding them from passing addresses
        // http://en.wikipedia.org/wiki/Email_address

        DataValidator temp = new DataValidator();
        assertTrue(temp.EmailAddressFormatVaidate("a@asd.vd"));
        assertTrue(temp.EmailAddressFormatVaidate("B@inv.dfa"));
        assertTrue(temp.EmailAddressFormatVaidate("Test1@msc.com"));
        assertTrue(temp.EmailAddressFormatVaidate("abcdefghijklmnopqrestvw@xyz.dte"));
        assertTrue(temp.EmailAddressFormatVaidate("ABCDEFGHIJKLMNOPQRSTUVWXYZ@xyz.dte"));
        assertTrue(temp.EmailAddressFormatVaidate("ABC12345_stv@xyz.com"));
        assertTrue(temp.EmailAddressFormatVaidate("HI-klmnop@qre.stv"));
        assertTrue(temp.EmailAddressFormatVaidate("abc.\"defghi\".xyz@example.com"));
        assertTrue(temp.EmailAddressFormatVaidate("A.p@res.tv"));
        assertFalse(temp.EmailAddressFormatVaidate("A..g@dat.com"));
        assertFalse(temp.EmailAddressFormatVaidate("Test@tes"));
        assertFalse(temp.EmailAddressFormatVaidate("abc\"defghi\"xyz@example.com"));
        assertFalse(temp.EmailAddressFormatVaidate("abc\\\"def\\\"ghi@example.com"));
        assertFalse(temp.EmailAddressFormatVaidate("12@34@dft.com"));
        assertFalse(temp.EmailAddressFormatVaidate("12$34@dft.com"));
        assertFalse(temp.EmailAddressFormatVaidate("12!34@dft.com"));
        assertFalse(temp.EmailAddressFormatVaidate("12^34@dft.com"));
        assertFalse(temp.EmailAddressFormatVaidate("12(34@dft.com"));
        assertFalse(temp.EmailAddressFormatVaidate("12+34@dft.com"));
        assertFalse(temp.EmailAddressFormatVaidate("12`34@dft.com"));
        assertFalse(temp.EmailAddressFormatVaidate("12~34@dft.com"));
        assertFalse(temp.EmailAddressFormatVaidate("12!34@dft.com"));
        assertFalse(temp.EmailAddressFormatVaidate(" "));
        assertFalse(temp.EmailAddressFormatVaidate(null));
        assertFalse(temp.EmailAddressFormatVaidate("12656"));
        assertFalse(temp.EmailAddressFormatVaidate("1@.com"));
        assertFalse(temp.EmailAddressFormatVaidate(12656));
    }

    @Test
    public void dateFormatVaidateTest() {
        DataValidator temp = new DataValidator();
        assertTrue(temp.dateFormatVaidate("12/01/1974"));
        assertTrue(temp.dateFormatVaidate("12/01/74"));
        assertFalse(temp.dateFormatVaidate(null));
    }//end dateFormatVaidateTest

}//end DataValidatorIT
