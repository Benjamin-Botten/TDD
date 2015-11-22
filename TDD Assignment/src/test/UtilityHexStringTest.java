package test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class UtilityHexStringTest {

    @Test
    public void HexString_OverflowString_ShouldThrowIllegalArgumentException() {
        new HexString("FFFFFFFFFFFFF");
    }

}
