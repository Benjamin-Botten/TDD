package øving;

import java.util.ArrayDeque;

public class Utility {

    public static class BitString {

        private String bitString;

        public static final int MAX_LEN = 24;
        public static final char ZERO_CHAR = '0';
        public static final char ONE_CHAR = '1';
        public static final String EMPTY_BITSTRING = "0";
        public static final String REGEX_PATTERN_BINARY = "[01]+";

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
            if (!this.bitString.matches(REGEX_PATTERN_BINARY))
                throw new IllegalArgumentException("String is not binary!");
        }

        public boolean equals(Object obj) {
            return bitString.equals(obj);
        }

        public int parseInt() {
            int result = 0;
            int indexOfExponent = 0;
            int length = bitString.length() - 1;
            return getBitStringValue(result, length, indexOfExponent);
        }

        // TODO: Since this method only needs to be called once for any unique
        // bitstring, we can optimize this further.
        private int getBitStringValue(int result, int length, int indexOfExponent) {
            for (int i = length; i >= 0; --i) {
                if (bitString.charAt(i) == ONE_CHAR) { // If the char at
                                                       // position i in the
                                                       // bitstring is '1', add
                                                       // the value of the
                                                       // current binary
                                                       // position in decimal
                    result += Math.pow(2, indexOfExponent++); // and increment
                                                              // the exponent
                                                              // index.
                } else {
                    indexOfExponent++;
                }
            }
            return result;
        }

        public static BitString parseString(int n) {
            String result = new String();
            ArrayDeque<Character> stack = new ArrayDeque<>();
            int remainder;
            getBitsFromInteger(n, stack);
            while (!stack.isEmpty())
                result += stack.pop();

            int nZerosToPrepend = MAX_LEN - result.length();
            result = prependZero(nZerosToPrepend, result);

            return new BitString(result);
        }

        private static String prependZero(int n, String string) {
            String tmp = "";
            for (int i = 0; i < n; ++i) {
                tmp += "0";
            }
            return tmp + string;
        }

        private static void getBitsFromInteger(int n, ArrayDeque<Character> stack) {
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
        }

        public String toString() {
            return bitString;
        }
    }

    public static class HexString {

        private String hexString;

        public static final int MAX_LEN = 6;
        public static final String EMPTY_HEXSTRING = "0";
        public static final String REGEX_PATTERN_HEXADECIMAL = "[0-9a-fA-F]+";

        public HexString() {
            hexString = EMPTY_HEXSTRING;
        }

        public HexString(String hexString) {
            this.hexString = hexString;
            if (this.hexString.isEmpty())
                this.hexString = EMPTY_HEXSTRING;
            if (this.hexString.length() > MAX_LEN)
                throw new IllegalArgumentException("Number of characters in hexstring exceeds MAX_LEN!");
            if (!this.hexString.matches(REGEX_PATTERN_HEXADECIMAL))
                throw new IllegalArgumentException("Hexstring contains non-hexadecimals!");
        }

        public boolean equals(Object obj) {
            return obj.equals(hexString);
        }
        
        public String toString() {
            return hexString;
        }
    }

    public static class BitOperator {

        private static final Operation BITSTRING_AND = new Operation() {
            public BitString applyTo(BitString str0, BitString str1) {
                return BitString.parseString(str0.parseInt() & str1.parseInt());
            }
        };
        
        private static final Operation BITSTRING_OR = new Operation() {
            public BitString applyTo(BitString str0, BitString str1) {
                return BitString.parseString(str0.parseInt() | str1.parseInt());
            }
        };
        
        
        public static final BitOperator AND = new BitOperator(BITSTRING_AND);
        public static final BitOperator OR = new BitOperator(BITSTRING_OR);

        private Operation op;

        public BitOperator(Operation op) {
            this.op = op;
        }

        public BitString applyTo(BitString str0, BitString str1) {
            return op.applyTo(str0, str1);
        }
    }
}
