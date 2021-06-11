package markAndToys;

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
     * Complete the 'maximumToys' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY prices
     *  2. INTEGER k
     */

	/*
	 * Approach:
	 * 1. sort the prices list
	 * 2. go through the list
	 * 3. if the value is smaller than or equals to k, decrease k by the value in the list while counting
	 * 3. if the value is bigger than k, return the count
	 * 
	 * Complexity:
	 * Time - O(nlogn) = nlogn for sorting + n for the loop 
	 * Space - O(1) nothing is stored
	 */
	public static int maximumToys(List<Integer> prices, int k) {
		// Write your code here

		// #1
		Collections.sort(prices);
		int count = 0;
		// #2
		for (Integer i : prices) {
			// #3
			if (i <= k) {
				count++;
				k -= i;
			} else { // #4
				break;
			}
		}
		return count;
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int n = Integer.parseInt(firstMultipleInput[0]);

		int k = Integer.parseInt(firstMultipleInput[1]);

		List<Integer> prices = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
				.map(Integer::parseInt).collect(toList());

		int result = Result.maximumToys(prices, k);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
