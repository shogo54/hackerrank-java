package minimumTimeRequired;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // global variables
    private static long[] array;
    private static long g;

    /*
     * Approach: Binary Search 
     * 1. find min and max machine in the array 
     * 2. perform binary search between min days and max days to get goal
     * (days = goal * machine / number of machine) 
     * 
     * Complexity: 
     * Time - O(log(g * m / n))
     * Space - O(1) as no extra space is used
     * where g = length of goal, m = max value of machines, and n = size of machines
     */
    // Complete the minTime function below.
	static long minTime(long[] machines, long goal) {
		// assign global variables
		array = machines;
		g = goal;

		int len = machines.length;

		// get min and max in the machines
		long min = machines[0];
		long max = machines[0];

		for (long l : machines) {
			min = Math.min(min, l);
			max = Math.max(max, l);
		}

		// binary search between min days and max days
		return binarySearch(goal * min / len, goal * max / len);
	}

	private static long binarySearch(long left, long right) {
		if (left == right) {
			return left;
		}

		long middle = (left + right) / 2;

		// calculate how many items can be produced in middle days
		long middleValue = 0;
		for (long m : array) {
			middleValue += middle / m;
		}

		// if we can make more than or equal to goal, search between left and middle
		// (inclusive for the case middleValue == g)
		// otherwise, search between middle + 1 and right
		if (middleValue >= g) {
			return binarySearch(left, middle);
		} else {
			return binarySearch(middle + 1, right);
		}
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] nGoal = scanner.nextLine().split(" ");

		int n = Integer.parseInt(nGoal[0]);

		long goal = Long.parseLong(nGoal[1]);

		long[] machines = new long[n];

		String[] machinesItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < n; i++) {
			long machinesItem = Long.parseLong(machinesItems[i]);
			machines[i] = machinesItem;
		}

		long ans = minTime(machines, goal);

		bufferedWriter.write(String.valueOf(ans));
		bufferedWriter.newLine();

		bufferedWriter.close();

		scanner.close();
	}
}
