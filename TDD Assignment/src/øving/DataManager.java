package øving;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class DataManager {
    
    public static final String SAMPLEFILE_1 = "sampledata_1.sd";
    private SampleFileReader sfHelper;
    
    public DataManager() {
        
    }
    
    public boolean openSampleFile(String path) {
        sfHelper = new SampleFileReader(path);
        return sfHelper.fileExists() ? true : false;
    }
    
    public String readLine() {
        return sfHelper.readLine();
    }
    
    public boolean hasNextLine() {
        return false;
    }
    
    class SampleFileReader {
        
        private File sampleFile;
        private BufferedReader reader;
        
        private String path;
        
        public SampleFileReader(String path) {
            this.path = path;
            sampleFile = new File(path);
            try {
                sampleFile.createNewFile();
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            } catch (IOException e) {
                e.printStackTrace();
            }
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
    }
}
