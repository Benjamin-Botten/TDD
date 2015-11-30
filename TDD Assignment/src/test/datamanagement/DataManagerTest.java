package test.datamanagement;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import �ving.datamanagement.DataManager;
import �ving.datamanagement.DataManager.SampleFileReader;
import �ving.datamanagement.DataSample;
import �ving.utility.Utility.HexString;
import �ving.utility.Utility.BitString;

public class DataManagerTest {

    private String samplePath = DataManager.SAMPLEFILE_1;
    private DataManager dm;

    @Mock
    SampleFileReader sfr;

    @Before
    public void create() {
        initMocks(this);
        when(sfr.openSampleFile(anyString())).thenReturn(false);
        when(sfr.openSampleFile(samplePath)).thenReturn(true);
        when(sfr.readLine()).thenReturn("03ac0f 1 110101000000110111001101 001000011110011101001111").thenReturn("ac0e1e 2 001000011110011101001111 000101010101010101111001").thenReturn(null);
        when(sfr.hasNextLine()).thenReturn(true).thenReturn(true).thenReturn(false);
    }

    @Test
    public void openSampleFile_StringPathToLocalSampleFile_ShouldReturnTrue() {
        dm = new DataManager(sfr);
        assertThat(dm.openSampleFile(samplePath), is(equalTo(true)));
        assertThat(dm.openSampleFile(anyString()), is(equalTo(false)));
    }

    @Test
    public void readLine_MockedSampleFileContents_ShouldReturn03ac0f_1_110101000000110111001101_001000011110011101001111Andac0e1e_2_001000011110011101001111_000101010101010101111001() {
        dm = new DataManager(sfr);
        assertThat(dm.readLine(), is(equalTo("03ac0f 1 110101000000110111001101 001000011110011101001111")));
        assertThat(dm.readLine(), is(equalTo("ac0e1e 2 001000011110011101001111 000101010101010101111001")));
    }

    @Test
    public void hasNextLine_MockedSampleFileContents_ShouldReturnTrueTrueAndFalse() {
        dm = new DataManager(sfr);
        assertThat(dm.hasNextLine(), is(equalTo(true)));
        assertThat(dm.hasNextLine(), is(equalTo(true)));
        assertThat(dm.hasNextLine(), is(equalTo(false)));
    }
    
    @Test
    public void processLine_StringContaining03ac0f_1_110101000000110111001101_001000011110011101001111_ShouldReturnTrue() {
        dm = new DataManager();
        assertThat(dm.processLine("03ac0f 1 110101000000110111001101 001000011110011101001111"), is(equalTo(true)));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void processLine_InvalidSampleLine_ShouldThrowIllegalArgumentException() {
        dm = new DataManager();
        dm.processLine("03ac0f 1 110101000000110111001101 001000011110011101001111 NOERRORHERE");
    }
    
    @Test
    public void getSampleData_HexStringContainingID03ac0f_ShouldReturnCorrectDataSample() {
        dm = new DataManager();
        String expectedSampleLine = "03ac0f 1 110101000000110111001101 001000011110011101001111";
        BitString expectedProcessed = new BitString("000000000000010101001101");
        BitString bitString0 = new BitString("110101000000110111001101");
        BitString bitString1 = new BitString("001000011110011101001111");
        int expectedDataVal = expectedProcessed.parseInt();
        int expectedBitsVal0 = bitString0.parseInt();
        int expectedBitsVal1 = bitString1.parseInt();
        
        DataSample actual = dm.getDataSample(new HexString("03ac0f"));
        assertThat(actual.getSampleLine(), is(equalTo(expectedSampleLine)));
        assertThat(actual.getProcessed(), is(equalTo(expectedProcessed)));
        assertThat(actual.getDataVal(), is(equalTo(expectedDataVal)));
        assertThat(actual.getBitsValue0(), is(equalTo(expectedBitsVal0)));
        assertThat(actual.getBitsValue1(), is(equalTo(expectedBitsVal1)));
    }
}
