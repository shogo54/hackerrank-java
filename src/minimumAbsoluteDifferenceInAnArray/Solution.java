package minimumAbsoluteDifferenceInAnArray;

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
     * Complete the 'minimumAbsoluteDifference' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

	/*
	 * Approach: Greedy Sorting
	 * 1. sort the array
	 * 2. take the minimum absolute difference between adjacent values 
	 * 
	 * Complexity:
	 * Time - O(nlogn) for sorting the array
	 * Space - O(1) no extra space
	 * where n is the size of the array
	 */
	public static int minimumAbsoluteDifference(List<Integer> arr) {
		// Write your code here
		// #1
		Collections.sort(arr);

		// #2
		int min = Integer.MAX_VALUE;
		for (int i = 1; i < arr.size(); i++) {
			min = Math.min(min, Math.abs(arr.get(i) - arr.get(i - 1)));
		}
		return min;
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int n = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
				.map(Integer::parseInt).collect(toList());

		int result = Result.minimumAbsoluteDifference(arr);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
