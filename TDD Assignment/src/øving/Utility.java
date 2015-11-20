package øving;

import java.util.ArrayDeque;

public class Utility {

	public static class BitString {
		
		private String bitString;
		
		public static final int MAX_LEN = 24; //Vi rensker code smell 'magiske tall' med å skape en konstant for max lengde på bitstrenger
		
		public BitString() {
			bitString = "0";
		}
		
		public BitString(String bitString) {
			if(bitString.equals("")) this.bitString = "0";
			else this.bitString = bitString;
			if(this.bitString.length() > MAX_LEN) throw new IllegalArgumentException("Number of characters in BitString exceeds MAX_LEN!");
			if(!this.bitString.matches("[01]+")) throw new IllegalArgumentException("String is not binary!");
		}
		
		public boolean equals(Object obj) {
			return bitString.equals(obj);
		}
		
		public int length() {
			return bitString.length();
		}
		
		public int parseInt() {
			int result = 0;
			final char ZERO = '0';
			final char ONE = '1';
			int index = 0;
			for(int i = bitString.length() - 1; i >= 0; --i) {
				if(bitString.charAt(i) == ONE) {
					result += Math.pow(2, index++);
				} else {
					index++;
				}
			}
			return result;
		}
		
		public static BitString parseString(int n) {
            String result = "";
            final char ZERO = '0';
            final char ONE = '1';
            ArrayDeque<Character> stack = new ArrayDeque<>();
            int remainder = 0;
            int curVal = n;
            while(curVal != 0) {
                remainder = curVal % 2;
                if(remainder == 0) stack.push(ZERO);
                else if(remainder == 1) stack.push(ONE);
                curVal /= 2;
            }
            while(!stack.isEmpty())
                result += stack.pop();
            
		    return new BitString(result);
		}
		
		public String toString() {
		    return bitString;
		}
	}
}
