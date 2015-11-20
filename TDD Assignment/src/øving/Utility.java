package øving;

public class Utility {

	public static class BitString {
		
		private String bitString;
		
		public BitString() {
			bitString = "0";
		}
		
		public BitString(String bitString) {
			if(bitString.equals("")) this.bitString = "0";
			else this.bitString = bitString;
			if(this.bitString.length() > 24) throw new IllegalArgumentException("Number of characters in BitString exceeds MAX_LEN!");
			if(!this.bitString.matches("[01]+")) throw new IllegalArgumentException("String is not binary!");
		}
		
		public boolean equals(Object obj) {
			return bitString.equals(obj);
		}
		
	}
}
