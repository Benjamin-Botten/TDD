package øving.datamanagement;

import øving.utility.Utility.BitString;
import øving.utility.Utility.HexString;

public class DataSample {

    private BitString processed;
    private String sampleLine;
    private int dataVal;
    private int bitsValue0, bitsValue1;

    public DataSample(BitString processed, int dataVal, String sampleLine, int bitsValue0, int bitsValue1) {
        this.processed = processed;
        this.dataVal = dataVal;
        this.sampleLine = sampleLine;
        this.bitsValue0 = bitsValue0;
        this.bitsValue1 = bitsValue1;
    }

    public BitString getProcessed() {
        return processed;
    }

    public String getSampleLine() {
        return sampleLine;
    }

    public int getDataVal() {
        return dataVal;
    }

    public int getBitsValue0() {
        return bitsValue0;
    }

    public int getBitsValue1() {
        return bitsValue1;
    }
}
