package luckBalance;

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
     * Complete the 'luckBalance' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. 2D_INTEGER_ARRAY contests
     */

	/*
	 * Approach: Greedy
	 * 1. sort the list by element[1] in acceding order and then element[0] in decending order
	 * 2. go through the list 
	 * 3. add element[0] to luck as long as k is positive. otherwise subtract by element[0]
	 * 
	 * Complexity:
	 * Time - O(nlogn) for sorting the list 
	 * Space - O(1) as no extra space is required
	 * where n is the size of the list, contests 
	 */
	public static int luckBalance(int k, List<List<Integer>> contests) {
		// Write your code here
		// #1
		Collections.sort(contests, (List<Integer> l1, List<Integer> l2) -> {
			if (l1.get(1) == l2.get(1)) {
				return l2.get(0) - l1.get(0);
			}
			return l1.get(1) - l2.get(1);
		});

		// #2
		int luck = 0;

		for (List<Integer> contest : contests) {
			// #3
			if (k - contest.get(1) >= 0) {
				k -= contest.get(1);
				luck += contest.get(0);
			} else {
				luck -= contest.get(0);
			}
		}

		return luck;
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int n = Integer.parseInt(firstMultipleInput[0]);

		int k = Integer.parseInt(firstMultipleInput[1]);

		List<List<Integer>> contests = new ArrayList<>();

		IntStream.range(0, n).forEach(i -> {
			try {
				contests.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
						.map(Integer::parseInt).collect(toList()));
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		int result = Result.luckBalance(k, contests);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
