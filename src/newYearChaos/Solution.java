package newYearChaos;

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
	 * Complete the 'minimumBribes' function below.
	 *
	 * The function accepts INTEGER_ARRAY q as parameter.
	 */

	public static void minimumBribes(List<Integer> q) {
		// Write your code here
		int[] bribe = new int[q.size()];
		int count = 0;
		int index = 0;
		while (index < q.size() - 1) {
			if (q.get(index) < q.get(index + 1)) {
				index++;
			} else {
				bribe[q.get(index) - 1]++;
				if (bribe[q.get(index) - 1] > 2) {
					System.out.println("Too chaotic");
					return;
				}

				swap(q, index, index + 1);
				count++;
				if (index != 0) {
					index--;
				}
			}
		}

		System.out.println(count);
	}

	public static void swap(List<Integer> arr, int i, int j) {
		int save = arr.get(i);
		arr.set(i, arr.get(j));
		arr.set(j, save);
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(bufferedReader.readLine().trim());

		IntStream.range(0, t).forEach(tItr -> {
			try {
				int n = Integer.parseInt(bufferedReader.readLine().trim());

				List<Integer> q = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
						.map(Integer::parseInt).collect(toList());

				Result.minimumBribes(q);
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		bufferedReader.close();
	}
}
