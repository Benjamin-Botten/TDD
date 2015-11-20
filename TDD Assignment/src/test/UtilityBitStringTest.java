package test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import �ving.Utility;
import �ving.Utility.BitString;

//Konvensjon: navnP�MetodeSomTestes_InputParametre_ForventetResultat()

public class UtilityBitStringTest {

	@Test	(expected = IllegalArgumentException.class)
	public void BitString_OverflowString_ShouldThrowIllegalArgumentException() {
		BitString bs = new BitString("01010101010101010101010101010101010101010101010101010101010101010101");
	}
	
	@Test
	public void BitString_EmptyString_ShouldReturnStringContainingZero() {
		assertThat(new BitString(), is(equalTo("0")));
		assertThat(new BitString(""), is(equalTo("0")));
	}
	
	@Test	(expected = IllegalArgumentException.class)
	public void BitString_FaultyCharString_ShouldThrowIllegalArgumentException() {
		BitString bs = new BitString("010101010201010101010");
	}

}
