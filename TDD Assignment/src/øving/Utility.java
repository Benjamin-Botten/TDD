package øving;

import java.util.ArrayDeque;

public class Utility {

    public static class BitString {

        private String bitString;

        public static final int MAX_LEN = 24; // Vi rensker code smell 'magiske
                                              // tall' med å skape en konstant
                                              // for max lengde på bitstrenger
        
        public static final char ZERO_CHAR = '0';
        public static final char ONE_CHAR = '1';
        public static final String EMPTY_BITSTRING = "0";
        
        public BitString() {
            bitString = EMPTY_BITSTRING;
        }

        public BitString(String bitString) {
            if (bitString.isEmpty())
                this.bitString = EMPTY_BITSTRING;
            else
                this.bitString = bitString;
            if (this.bitString.length() > MAX_LEN)
                throw new IllegalArgumentException("Number of characters in BitString exceeds MAX_LEN!");
            if (!this.bitString.matches("[01]+"))
                throw new IllegalArgumentException("String is not binary!");
        }

        public boolean equals(Object obj) {
            return bitString.equals(obj);
        }

        public int parseInt() {
            int result = 0;
            int indexExponent = 0;
            int length = bitString.length() - 1;
            for (int i = length; i >= 0; --i) {
                if (bitString.charAt(i) == ONE_CHAR) {          //If the char at position i in the bitstring is '1', add the value of the current binary position in decimal
                    result += Math.pow(2, indexExponent++);     //and increment the exponent index.
                } else {
                    indexExponent++;
                }
            }
            return result;
        }
        
        public static BitString parseString(int n) {
            String result = new String();
            ArrayDeque<Character> stack = new ArrayDeque<>();
            int remainder;
            int curVal = n;
            while (curVal != 0) {
                remainder = curVal % 2;
                if (remainder == 0)
                    stack.push(ZERO_CHAR);
                else if (remainder == 1)
                    stack.push(ONE_CHAR);
                curVal /= 2;
            }
            while (!stack.isEmpty())
                result += stack.pop();

            return new BitString(result);
        }

        public String toString() {
            return bitString;
        }
    }
    
    public static class HexString {
        
        private String hexString;
        
        public static final int MAX_LEN = 6;
        public static final String EMPTY_HEXSTRING = "0";
        
        public HexString() {
            hexString = EMPTY_HEXSTRING;
        }

        public HexString(String hexString) {
            this.hexString = hexString;
            if(this.hexString.isEmpty()) this.hexString = EMPTY_HEXSTRING;
            if(this.hexString.length() > MAX_LEN) throw new IllegalArgumentException("Number of characters in hexstring exceeds MAX_LEN!");
            if(!this.hexString.matches("[0-9a-fA-F]+")) throw new IllegalArgumentException("Hexstring contains non-hexadecimals!");
        }
        
        public boolean equals(Object obj) {
            return obj.equals(hexString);
        }
    }
}
