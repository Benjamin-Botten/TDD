package test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import øving.Utility.HexString;

public class UtilityHexStringTest {

    @Test (expected = IllegalArgumentException.class)
    public void HexString_OverflowString_ShouldThrowIllegalArgumentException() {
        new HexString("FFFFFFFFFFFFF");
    }

    @Test
    public void HexString_EmptyString_ShouldReturnStringContainingZero() {
        assertThat(new HexString(), is(equalTo("0")));
        assertThat(new HexString(""), is(equalTo("0")));
    }
    
    @Test   (expected = IllegalArgumentException.class)
    public void HexString_StringContainingNonHexadecimal_ShouldThrowIllegalArgumentException() {
        new HexString("012G45");
    }
}
