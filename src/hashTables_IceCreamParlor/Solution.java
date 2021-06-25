package hashTables_IceCreamParlor;

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
     * Complete the 'whatFlavors' function below.
     *
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY cost
     *  2. INTEGER money
     */

    /*
     * Approach: hash map
     * 1. craete a map { cost -> index }
     * 2. go through the loop of cost
     * 3. if money - current cost (= rest) is in map, print index of current and map 
     * 4. otherwise, put current cost with index to map
     * 
     * Complexity
     * Time - O(n) for going through the loop (map operations are O(1))
     * Space - O(n) for the map
     * where n is the size of the list, cost
     */
	public static void whatFlavors(List<Integer> cost, int money) {
		// Write your code here
		// #1
		Map<Integer, Integer> map = new HashMap<>();

		// #2
		for (int i = 0; i < cost.size(); i++) {
			int rest = money - cost.get(i);
			// #3
			if (map.containsKey(rest)) {
				System.out.println(map.get(rest) + " " + (i + 1));
				return;
			}
			// #4
			map.put(cost.get(i), i + 1);
		}
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(bufferedReader.readLine().trim());

		IntStream.range(0, t).forEach(tItr -> {
			try {
				int money = Integer.parseInt(bufferedReader.readLine().trim());

				int n = Integer.parseInt(bufferedReader.readLine().trim());

				List<Integer> cost = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
						.map(Integer::parseInt).collect(toList());

				Result.whatFlavors(cost, money);
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		bufferedReader.close();
	}
}
