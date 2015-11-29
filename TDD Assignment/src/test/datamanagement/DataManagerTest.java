package test.datamanagement;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import øving.datamanagement.DataManager;
import øving.datamanagement.DataManager.SampleFileReader;

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
        DataManager dm = new DataManager(sfr);
        assertThat(dm.hasNextLine(), is(equalTo(true)));
        assertThat(dm.hasNextLine(), is(equalTo(true)));
        assertThat(dm.hasNextLine(), is(equalTo(false)));
    }
    
    @Test
    public void verifySampleLine_03ac0f_1_110101000000110111001101_001000011110011101001111_ShouldReturnTrue() {
        DataManager dm = new DataManager();
        assertThat(dm.verifySampleLine("03ac0f 1 110101000000110111001101 001000011110011101001111"), is(equalTo(true)));
    }
    
    @Test
    public void verifySampleLine_03ac0f_3_110101000000110111001101_001000011110011101001111_ShouldReturnFalse() {
        DataManager dm = new DataManager();
        assertThat(dm.verifySampleLine("03ac0f 3 110101000000110111001101 001000011110011101001111"), is(equalTo(false)));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void verifySampleLine_03ac0f_1_110101000000110111001101_001000011110011101001111_NOERROR_ShouldReturnFalse() {
        DataManager dm = new DataManager();
        dm.verifySampleLine("03ac0f 1 110101000000110111001101 001000011110011101001111 NOERROR");
    }
    
    @Test (expected = NoSuchElementException.class)
    public void verifySampleLine_03ac0f_1_110101000000110111001101_ShouldThrowIllegalArgumentException() {
        DataManager dm = new DataManager();
        dm.verifySampleLine("03ac0f 1 110101000000110111001101");
    }
}
