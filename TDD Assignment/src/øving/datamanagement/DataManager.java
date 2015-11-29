package �ving.datamanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class DataManager {
    
    public static final String SAMPLEFILE_1 = "sampledata_1.sd";
    private SampleFileReader sfReader;
    
    public DataManager(SampleFileReader sfReader) {
        this.sfReader = sfReader;
    }
    
    public boolean openSampleFile(String path) {
        return sfReader.openSampleFile(path);
    }
    
    public String readLine() {
        return sfReader.readLine();
    }
    
    public boolean hasNextLine() {
        return sfReader.hasNextLine();
    }
    
    public class SampleFileReader {
        
        private File sampleFile;
        private BufferedReader reader;
        private String path;
        
        public boolean openSampleFile(String path) {
            this.path = path;
            sampleFile = new File(path);
            try {
                sampleFile.createNewFile();
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return fileExists();
        }
        
        public boolean fileExists() {
            return sampleFile.exists();
        }
        
        public String readLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        
        public boolean hasNextLine() {
            try {
                reader.mark(0);
                if(reader.readLine() != null) {
                    reader.reset();
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        
    }

    public boolean verifySampleLine(String string) {
        return false;
    }
}
