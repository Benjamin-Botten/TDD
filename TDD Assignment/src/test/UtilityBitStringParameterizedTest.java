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

@RunWith(Parameterized.class)
public class UtilityBitStringParameterizedTest {

    private Integer input;
    private String expected;

    public UtilityBitStringParameterizedTest(Integer input, String expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection bitStrings() {
        return Arrays.asList(new Object[][] { { 10, "000000000000000000001010" }, { 2, "000000000000000000000010" }, { 5, "000000000000000000000101" }, 
                { 16, "000000000000000000010000" }, { 17, "000000000000000000010001" } });
    }

    @Test
    public void parseString_IntegerInput_StringExpected() {
        assertThat(BitString.parseString(input), is(equalTo(expected)));
    }
}
