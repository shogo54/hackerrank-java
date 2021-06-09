package frequencyQueries;

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

public class Solution {

	// #1 & #2
	static Map<Integer, Integer> valueToCount = new HashMap<>();
	static Map<Integer, Set<Integer>> countToValues = new HashMap<>();

	static void updateMaps(int val, int newCount, int oldCount) {
		valueToCount.put(val, newCount);
		if (countToValues.containsKey(oldCount)) {
			countToValues.get(oldCount).remove(val);
		}
		if (!countToValues.containsKey(newCount)) {
			countToValues.put(newCount, new HashSet<>());
		}
		countToValues.get(newCount).add(val);
	}

	// Complete the freqQuery function below.
	
	/*
	 * Approach: 
	 * 1. keep track of the value and count pair in valueToCount map { value => count }
	 * 2. also keep track of the count to values (set) pair in countToValues map 
	 *    { count => set of values }
	 * 3. whenever the element is added or removed, update both maps accordingly
	 * 4. when you check the occurrence z, check countToValues map for faster result
	 * 
	 * Complexity:
	 * Time - O(n) as each operation is O(1) and we do it n times.
	 * Space - O(n) as the maps contains at most n elements.
	 * where n is the number of operations in given queries. 
	 */
	static List<Integer> freqQuery(List<List<Integer>> queries) {

		List<Integer> retVal = new ArrayList<>();
		
		for (List<Integer> q : queries) {
			int val = q.get(1);
			
			if (q.get(0) == 1) {
				// #3
				int newCount = valueToCount.getOrDefault(val, 0) + 1;
				updateMaps(val, newCount, newCount - 1);
			} else if (q.get(0) == 2) {
				// #3
				if (valueToCount.containsKey(val) && valueToCount.get(val) > 0) {
					int newCount = valueToCount.get(val) - 1;
					updateMaps(val, newCount, newCount + 1);
				}
			} else {
				// #4
				if (countToValues.containsKey(val) && countToValues.get(val).size() > 0) {
					retVal.add(1);
				} else {
					retVal.add(0);
				}
			}
		}
		
		return retVal;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int q = Integer.parseInt(bufferedReader.readLine().trim());

		List<List<Integer>> queries = new ArrayList<>();

		IntStream.range(0, q).forEach(i -> {
			try {
				queries.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
						.map(Integer::parseInt).collect(toList()));
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		List<Integer> ans = freqQuery(queries);

		bufferedWriter.write(ans.stream().map(Object::toString).collect(joining("\n")) + "\n");

		bufferedReader.close();
		bufferedWriter.close();
	}
}
