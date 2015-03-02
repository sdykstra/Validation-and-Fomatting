package TextFormatter;


import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Scott Dysktra
 * @version 3-1-2015
 */
public class FormatterIT {

    public FormatterIT() {
    }

    /**
     * Test of SSNFormat method, of class Formatter.
     */
    @Test
    public void testSSNFormat() {

        Formatter format = new Formatter();
        assertEquals("345-12-4232", format.SSNFormat("345124232"));
        assertEquals("345-12-4232", format.SSNFormat("345 124 232"));
        assertEquals("123-34-1256", format.SSNFormat(123341256));
        assertEquals("342-13-6789", format.SSNFormat("34-2136-789"));
        assertEquals(null, format.SSNFormat(null));

    }

    /**
     * Test of timeFormat method, of class Formatter.
     */
    @Test
    public void testTimeFormat() {

        Formatter format = new Formatter();
        assertEquals("12:42:32", format.timeFormat("12:42:32"));
        assertEquals(null, format.timeFormat(""));
        assertEquals("7:89", format.timeFormat("789"));
        assertEquals(null, format.SSNFormat(null));
    }

    /**
     * Test of phoneNumberFormat method, of class Formatter.
     */
    @Test
    public void testPhoneNumberFormat() {
        Formatter format = new Formatter();
        assertEquals("333-444-4555", format.phoneNumberFormat("333 4444 555"));
        assertEquals("333-444-4555", format.phoneNumberFormat("333-4444-555"));
        assertEquals("1-333-444-4555", format.phoneNumberFormat("13334444555"));
        assertEquals(null, format.phoneNumberFormat(null));
    }

    /**
     * Test of floatingPointFormat method, of class Formatter.
     */
    @Test
    public void testFloatingPointFormat() {
        Formatter format = new Formatter();
        assertEquals((Float) 1234567.0f, format.floatingPointFormat("1234567"));
        assertNotSame((Float)3334444.555f, format.floatingPointFormat("3334444.555"));
        assertEquals((Float)445.55f, format.floatingPointFormat("445.55"));
        assertEquals((Float)0.0f, format.floatingPointFormat(null));
    }



}
