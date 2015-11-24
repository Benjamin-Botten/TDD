package test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import øving.DataManager;

public class DataManagerTest {

    private String samplePath = DataManager.SAMPLEFILE_1;
    
    @Test
    public void openSampleFile_StringPathToLocalSampleFile_ShouldReturnTrue() {
        DataManager ds = new DataManager();
        assertThat(ds.openSampleFile(samplePath), is(equalTo(true)));
    }

     @Test
     public void
     readLine_MockedSampleFileContents_ShouldReturn03ac0f_1_110101000000110111001101_001000011110011101001111Andac0e1e_2_001000011110011101001111_000101010101010101111001()
     {
         DataManager dm = mock(DataManager.class);
         when(dm.readLine()).thenReturn("03ac0f 1 110101000000110111001101 001000011110011101001111").thenReturn("ac0e1e 2 001000011110011101001111 000101010101010101111001");
         assertThat(dm.readLine(), is(equalTo("03ac0f 1 110101000000110111001101 001000011110011101001111")));
         assertThat(dm.readLine(), is(equalTo("ac0e1e 2 001000011110011101001111 000101010101010101111001")));
     }
     
     @Test
     public void hasNextLine_MockedSampleFileContents_ShouldReturnTrueTrueAndFalse() {
         DataManager dm = mock(DataManager.class);
         when(dm.readLine()).thenReturn("03ac0f 1 110101000000110111001101 001000011110011101001111").thenReturn("ac0e1e 2 001000011110011101001111 000101010101010101111001");
         assertThat(dm.hasNextLine(), is(equalTo(true)));
         assertThat(dm.hasNextLine(), is(equalTo(true)));
         assertThat(dm.hasNextLine(), is(equalTo(false)));
     }
}
