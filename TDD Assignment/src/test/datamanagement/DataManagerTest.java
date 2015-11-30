package test.datamanagement;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import øving.datamanagement.DataManager;
import øving.datamanagement.DataManager.SampleFileReader;
import øving.datamanagement.DataSample;
import øving.utility.Utility.HexString;
import øving.utility.Utility.BitString;

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
    public void getDataSamples_HexStringContaining03ac0f_ShouldReturnCorrectDataSamples() {
        dm = new DataManager();
        dm.processLine("03ac0f 1 110101000000110111001101 001000011110011101001111");
        dm.processLine("03ac0f 2 001000011110011101001111 000101010101010101111001");
        
        String expectedSampleLine = "03ac0f 1 110101000000110111001101 001000011110011101001111";
        BitString expectedProcessed = new BitString("000000000000010101001101");
        BitString bitString0 = new BitString("110101000000110111001101");
        BitString bitString1 = new BitString("001000011110011101001111");
        int expectedDataVal = expectedProcessed.parseInt();
        int expectedBitsVal0 = bitString0.parseInt();
        int expectedBitsVal1 = bitString1.parseInt();
        
        String expectedSampleLine2 = "03ac0f 2 001000011110011101001111 000101010101010101111001";
        BitString expectedProcessed2 = new BitString("001101011111011101111111");
        BitString bitString02 = new BitString("001000011110011101001111");
        BitString bitString12 = new BitString("000101010101010101111001");
        int expectedDataVal2 = expectedProcessed2.parseInt();
        int expectedBitsVal02 = bitString02.parseInt();
        int expectedBitsVal12 = bitString12.parseInt();
        
        
        List<DataSample> actual = dm.getDataSamples(new HexString("03ac0f"));
        assertThat(actual.get(0).getSampleLine(), is(equalTo(expectedSampleLine)));
        assertThat(actual.get(0).getProcessed(), is(equalTo(expectedProcessed)));
        assertThat(actual.get(0).getDataVal(), is(equalTo(expectedDataVal)));
        assertThat(actual.get(0).getBitsValue0(), is(equalTo(expectedBitsVal0)));
        assertThat(actual.get(0).getBitsValue1(), is(equalTo(expectedBitsVal1)));
        
        assertThat(actual.get(1).getSampleLine(), is(equalTo(expectedSampleLine2)));
        assertThat(actual.get(1).getProcessed(), is(equalTo(expectedProcessed2)));
        assertThat(actual.get(1).getDataVal(), is(equalTo(expectedDataVal2)));
        assertThat(actual.get(1).getBitsValue0(), is(equalTo(expectedBitsVal02)));
        assertThat(actual.get(1).getBitsValue1(), is(equalTo(expectedBitsVal12)));
    }
}
