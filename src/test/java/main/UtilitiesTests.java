package main;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class of the utilities.
 * @see Utilities
 * @author SpecOp0
 */
public class UtilitiesTests {
    
    /**
     * Tests the is null or whitespace method.
     */
    @Test
    public void testIsNullOrWhitespace(){
        // expect true
        String[] stringsToTest = {
            null,
            "",
            " ",
            "   ",
            "\r\n",
            System.lineSeparator()
        };
        for(String value : stringsToTest){
            Assert.assertTrue("'" + value + "'", Utilities.isNullOrWhitespace(value));
        }
        // expect false
        String[] validStrings = {
            "   information  ",
            "a ",
            " a"
        };
        for(String value : validStrings){
            Assert.assertFalse("'" + value + "'", Utilities.isNullOrWhitespace(value));
        }
    }
}
