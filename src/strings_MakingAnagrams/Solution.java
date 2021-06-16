package strings_MakingAnagrams;

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
     * Complete the 'makeAnagram' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING a
     *  2. STRING b
     */

	public static int makeAnagram(String a, String b) {
		// Write your code here
		int[] charA = new int[26];
		int[] charB = new int[26];

		for (int i = 0; i < a.length(); i++) {
			charA[a.charAt(i) - 'a']++;
		}

		for (int i = 0; i < b.length(); i++) {
			charB[b.charAt(i) - 'a']++;
		}

		int count = 0;
		for (int i = 0; i < charA.length; i++) {
			count += Math.max(charA[i], charB[i]) - Math.min(charA[i], charB[i]);
		}

		return count;
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String a = bufferedReader.readLine();

		String b = bufferedReader.readLine();

		int res = Result.makeAnagram(a, b);

		bufferedWriter.write(String.valueOf(res));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
