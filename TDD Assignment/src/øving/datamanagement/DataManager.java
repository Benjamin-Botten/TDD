package �ving.datamanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import �ving.utility.Utility.BitOperator;
import �ving.utility.Utility.BitString;
import �ving.utility.Utility.HexString;

public class DataManager {

    public static final String SAMPLEFILE_1 = "sampledata_1.sd";
    private SampleFileReader sfReader;

    public DataManager() {
    }
    
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

    public boolean verifySampleLine(String sampleLine) {
        
        StringTokenizer tokenizer = new StringTokenizer(sampleLine);
        
        HexString idData = new HexString(tokenizer.nextToken());
        
        int idOperator = Integer.parseInt(tokenizer.nextToken());
        
        if(idOperator <= 0 || idOperator > BitOperator.NUM_OPERATORS) return false;
        
        BitString[] bitStringCouple = new BitString[] {
                new BitString(tokenizer.nextToken()), 
                new BitString(tokenizer.nextToken()) 
        };
        
        if(tokenizer.hasMoreTokens()) throw new IllegalArgumentException("Too many arguments on sample data line, invalid data!");  
        
        return true;
    }
    
    public boolean saveSample(String sampleLine) {
        return false;
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
                if (reader.readLine() != null) {
                    reader.reset();
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

    }
}
