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

}
