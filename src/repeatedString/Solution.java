package repeatedString;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

	/*
	 * Complete the 'repeatedString' function below.
	 *
	 * The function is expected to return a LONG_INTEGER. The function accepts
	 * following parameters: 1. STRING s 2. LONG_INTEGER n
	 */

	public static long repeatedString(String s, long n) {
		// Write your code here
		int len = s.length();
		long[] countA = new long[len];

		countA[0] = (s.charAt(0) == 'a') ? 1 : 0;
		for (int i = 1; i < len; i++) {
			countA[i] = countA[i - 1];
			if (s.charAt(i) == 'a') {
				countA[i]++;
			}
		}

		int mod = (int) (n % len);
		long count = n / len * countA[len - 1];
		if (mod > 0) {
			count += countA[mod - 1];
		}
		return count;
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String s = bufferedReader.readLine();

		long n = Long.parseLong(bufferedReader.readLine().trim());

		long result = Result.repeatedString(s, n);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
