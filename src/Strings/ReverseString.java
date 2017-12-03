package Strings;

public class ReverseString {

	static String test[] = { "S", "A", "H", "I", "L" };

	void swap(int pos1, int pos2) {
		String temp;
		temp = test[pos1];
		test[pos1] = test[pos2];
		test[pos2] = temp;
	}

	void reverseString(String str[], int startIdx, int endIdx) {
		if (endIdx <= startIdx) {
			return;
		}
		swap(startIdx, endIdx);
		reverseString(str, startIdx + 1, endIdx - 1);
	}

	void print(String str[]) {
		reverseString(str, 0, str.length - 1);
		for (int i = 0; i < str.length; i++) {
			System.out.print(str[i]);
		}
	}

	public static void main(String args[]) {
		ReverseString obj = new ReverseString();
		obj.print(test);
	}
}
