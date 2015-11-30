package �ving.datamanagement;

import �ving.utility.Utility.BitString;
import �ving.utility.Utility.HexString;

public class DataSample {
    
    private HexString id;
    private BitString data;
    private String sampleLine;
    private int idata;

    
    public DataSample(HexString id, String sampleLine) {
        this.id = id;
        this.sampleLine = sampleLine;
    }
    
    public DataSample(HexString id, BitString data, int idata) {
        this.id = id;
        this.data = data;
        this.idata = idata;
    }
    
    public DataSample(HexString id, BitString data, int idata, String sampleLine) {
        this.id = id;
        this.data = data;
        this.idata = idata;
        this.sampleLine = sampleLine;
    }
    
    public HexString getID() {
        return id;
    }
    
    public BitString getData() {
        return data;
    }
    
    public String getSampleLine() {
        return sampleLine;
    }
    
    public int getIntegerData() {
        return idata;
    }
}
