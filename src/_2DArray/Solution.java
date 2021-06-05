package _2DArray;

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
	 * Complete the 'hourglassSum' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts
	 * 2D_INTEGER_ARRAY arr as parameter.
	 */

	public static int hourglassSum(List<List<Integer>> arr) {
		// Write your code here
		int max = Integer.MIN_VALUE;
		int len = arr.size();

		for (int i = 1; i < len - 1; i++) {
			int count = arr.get(i - 1).get(0);
			count += arr.get(i - 1).get(1);
			count += arr.get(i + 1).get(0);
			count += arr.get(i + 1).get(1);
			for (int j = 1; j < len - 1; j++) {
				count += arr.get(i - 1).get(j + 1);
				count += arr.get(i).get(j);
				count += arr.get(i + 1).get(j + 1);
				if (count > max) {
					max = count;
				}
				count -= arr.get(i - 1).get(j - 1);
				count -= arr.get(i).get(j);
				count -= arr.get(i + 1).get(j - 1);
			}
		}

		return max;
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		List<List<Integer>> arr = new ArrayList<>();

		IntStream.range(0, 6).forEach(i -> {
			try {
				arr.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt)
						.collect(toList()));
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		int result = Result.hourglassSum(arr);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
