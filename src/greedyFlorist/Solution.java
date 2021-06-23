package greedyFlorist;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

	/*
	 * Approach: Greedy
	 * 1. sort the array
	 * 2. go through the array from the end and add cost * multiply
	 * 3. if everyone buys the same amount, increase the multiply
	 * 
	 * Complexity
	 * Time - O(nlogn) for sorting 
	 * Space - O(1) as no extra space is required
	 * where n is the size of the array, c
	 */
	// Complete the getMinimumCost function below.
	static int getMinimumCost(int k, int[] c) {

		// #1
		Arrays.sort(c);

		int cost = 0;
		int multiply = 1;
		int currentK = k;

		// #2
		for (int i = c.length - 1; i >= 0; i--) {
			cost += c[i] * multiply;

			// #3
			if (--currentK == 0) {
				currentK = k;
				multiply++;
			}
		}

		return cost;
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] nk = scanner.nextLine().split(" ");

		int n = Integer.parseInt(nk[0]);

		int k = Integer.parseInt(nk[1]);

		int[] c = new int[n];

		String[] cItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < n; i++) {
			int cItem = Integer.parseInt(cItems[i]);
			c[i] = cItem;
		}

		int minimumCost = getMinimumCost(k, c);

		bufferedWriter.write(String.valueOf(minimumCost));
		bufferedWriter.newLine();

		bufferedWriter.close();

		scanner.close();
	}
}
