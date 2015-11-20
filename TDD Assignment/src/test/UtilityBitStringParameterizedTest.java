package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.hamcrest.CoreMatchers.*;

import øving.Utility.BitString;

@RunWith (Parameterized.class)
public class UtilityBitStringParameterizedTest {

    private Integer input;
    private String expected;
    
    public UtilityBitStringParameterizedTest(Integer input, String expected) {
        this.input = input;
        this.expected = expected;
    }
    
    @Parameterized.Parameters
    public static Collection bitStrings() {
        return Arrays.asList(new Object[][] {
                {10, "1010"},
                {2, "10"},
                {5, "101"},
                {16, "10000"},
                {17, "10001"}
        });
    }

    @Test
    public void parseString_Input_Expected() {
        assertThat(BitString.parseString(input), is(equalTo(expected)));
    }
}
