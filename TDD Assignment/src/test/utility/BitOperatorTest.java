package test.utility;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import øving.utility.Utility.BitOperator;
import øving.utility.Utility.BitString;

public class BitOperatorTest {

    @Test
    public void AND_BitStringContaining000000000000000011000011AndBitStringContaining000000000000000001001110_ShouldReturn000000000000000001000010() {
        BitString firstString = new BitString("000000000000000011000011");
        BitString secondString = new BitString("000000000000000001001110");
        assertThat(BitOperator.AND.applyTo(firstString, secondString), is(equalTo("000000000000000001000010")));
    }
    
    @Test
    public void OR_BitStringContaining000000000000000011000011AndBitStringContaining000000000000000001001110_ShouldReturn000000000000000011001111() {
        BitString firstString = new BitString("000000000000000011000011");
        BitString secondString = new BitString("000000000000000001001110");
        assertThat(BitOperator.OR.applyTo(firstString, secondString), is(equalTo("000000000000000011001111")));
    }

}
