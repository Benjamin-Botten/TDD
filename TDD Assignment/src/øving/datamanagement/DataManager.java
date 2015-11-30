package øving.datamanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import øving.utility.Utility.BitOperator;
import øving.utility.Utility.BitString;
import øving.utility.Utility.HexString;

public class DataManager {

    public static final String SAMPLEFILE_1 = "sampledata_1.sd";
    private SampleFileReader sfReader;
    private Map<HexString, List<DataSample>> sampleMap = new HashMap<HexString, List<DataSample>>();

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

    public boolean processLine(String sampleLine) {
        StringTokenizer tokenizer = new StringTokenizer(sampleLine);
        HexString id = new HexString(tokenizer.nextToken());
        int operator = Integer.parseInt(tokenizer.nextToken());
        BitString bstr0 = new BitString(tokenizer.nextToken());
        BitString bstr1 = new BitString(tokenizer.nextToken());
        
        if (tokenizer.hasMoreTokens())
            throw new IllegalArgumentException("Invalid sample line, too many tokens!");

        
        BitString processed;
        int dataVal, valBits0, valBits1;
        
        processed = processBitStrings(operator, bstr0, bstr1);
        dataVal = processed.parseInt();
        valBits0 = bstr0.parseInt();
        valBits1 = bstr1.parseInt();
        
        DataSample result = new DataSample(processed, dataVal, sampleLine, valBits0, valBits1);
        if(sampleMap.get(id) != null) {
            sampleMap.get(id).add(result);
        } else {
            List<DataSample> list = new ArrayList<>();
            list.add(result);
            sampleMap.put(id, list);   
        }
        
        return true;
    }
    
    private BitString processBitStrings(int operator, BitString bstr0, BitString bstr1) {
        if (operator == 1) {
            return BitOperator.AND.applyTo(bstr0, bstr1);
        } 
        else if (operator == 2) {
            return BitOperator.OR.applyTo(bstr0, bstr1);
        }
        return new BitString();
    }
    
    public List<DataSample> getDataSamples(HexString id) {
        return sampleMap.get(id);
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
