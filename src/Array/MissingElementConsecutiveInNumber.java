package Array;

/**
 * 
 * @author Sahil
 * 
 * 
 *         It is based on following property of XOR operation: 1) If you XOR any
 *         number with itself, you will get ZERO. i.e.. A ^ A = 0 2) If you XOR
 *         any number with ZERO, you get the same number i.e. A ^ 0 = A Say n= 5
 *         and array is 1, 2, 4, 5 (i.e. 3 is missing here), so based on method
 *         2: 1) X1 = 1 ^ 2 ^ 4 ^ 5 (XOR all the array elements) 2) X2 = 1 ^ 2 ^
 *         3 ^ 4 ^ 5 (XOR all numbers from 1 to n) 3) missing number = X1 ^ X2 =
 *         (1 ^ 2 ^ 4 ^ 5) ^ (1 ^ 2 ^ 3 ^ 4 ^ 5) = 3 Here each number in X1 gets
 *         XORed with same number present in X2 and it's becomes ZERO (1 ^ 1 =
 *         0, 2 ^ 2 = 0, 4 ^ 4 = 0, 5 ^ 5 = 0). The missing number 3 is only in
 *         X2 and not in X1, so it's becomes 0 ^ 3 = 3
 * 
 */

public class MissingElementConsecutiveInNumber {
	static int missingNumber(int arr[]) {

		int[] copyArray = new int[arr.length];
		for (int i = 0; i < copyArray.length; i++) {
			copyArray[i] = i + 1;
		}
		int answer = 0;
		for (int i = 0; i < copyArray.length; i++) {
			answer = copyArray[i] ^ arr[i] ^ answer;
		}

		return answer;

	}

	public static void main(String args[]) {
		int missingArray[] = { 1, 2, 4, 5, 6, 7, 8, 0 };
		// Missing number is 4
		System.out.print("Missing number in array : "
				+ missingNumber(missingArray));
	}
}
