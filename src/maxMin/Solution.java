package maxMin;

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
     * Complete the 'maxMin' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY arr
     */

	/*
     * Approach: Greedy
     * 1. sort the array
     * 2. go through the array
     * 3. find the minimum in differences between arr[i] and arr[i + k - 1]
     * 
     * Complexity:
     * Time - O(nlogn) for sorting 
     * Space - O(1) as no extra space is used 
     * where n is the size of array, arr
     */
	public static int maxMin(int k, List<Integer> arr) {
		// Write your code here
		// #1
		Collections.sort(arr);

		// #2
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < arr.size() - k + 1; i++) {
			// #3
			min = Math.min(min, arr.get(k + i - 1) - arr.get(i));
		}

		return min;
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int n = Integer.parseInt(bufferedReader.readLine().trim());

		int k = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> arr = IntStream.range(0, n).mapToObj(i -> {
			try {
				return bufferedReader.readLine().replaceAll("\\s+$", "");
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}).map(String::trim).map(Integer::parseInt).collect(toList());

		int result = Result.maxMin(k, arr);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
