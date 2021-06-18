package sherlockAndTheValidString;

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
     * Complete the 'isValid' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

	/*
	 * Approach: 
	 * 1. create a hash table (char -> count)
	 * 2. go through the table and create a map of occurrence of char (count -> count of count)
	 * 3. if there are more than 2 counts, return No
	 * 4. if size is 1, no need to remove a character 
	 * 5. if removing one will make all the occurrence the same amount, return Yes otherwise No. 
	 * 
	 * Complexity:
	 * Time - O(n) for go through a loop of s
	 * Space - O(1) storing ctable and countOfCount are both constant 
	 * where n is the length of the string s 
	 */
	public static String isValid(String s) {
		// Write your code here
		// #1
		int[] ctable = new int[26];

		for (char c : s.toCharArray()) {
			ctable[c - 'a']++;
		}

		// #2
		TreeMap<Integer, Integer> countOfCount = new TreeMap<>();
		for (int i : ctable) {
			if (i == 0) {
				continue;
			}
			countOfCount.put(i, countOfCount.getOrDefault(i, 0) + 1);
			// #3
			if (countOfCount.size() > 2) {
				return "NO";
			}
		}

		// #4
		if (countOfCount.size() == 1) {
			return "YES";
		}
		// #5
		if (countOfCount.firstEntry().getValue() == 1 && countOfCount.firstKey() == 1) {
			return "YES";
		}
		if (countOfCount.lastEntry().getValue() == 1 && countOfCount.lastKey() == countOfCount.firstKey() + 1) {
			return "YES";
		}

		return "NO";
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String s = bufferedReader.readLine();

		String result = Result.isValid(s);

		bufferedWriter.write(result);
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
