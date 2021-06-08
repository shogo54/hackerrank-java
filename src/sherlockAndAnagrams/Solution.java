package sherlockAndAnagrams;

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
     * Complete the 'sherlockAndAnagrams' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */
	
	/*
	 * Approach: 
	 * 1. go through each substring of s
	 * 2. for each substring, create a hash table that maps { char => # of char in the substring }
	 * 3. using the hash table as a key, create a hash map that maps { table => # of occurrence of the table }
	 * 4. for each table pattern, calculate the combination of 2 from the # of occurrence of the table in the map. 
	 * 
	 * Complexity:
	 * Time - O(n^2) as we go through each substring
	 * Space - O(n^2) as there are at most n^2 number of unique hash tables in the map
	 */
	public static int sherlockAndAnagrams(String s) {
		// Write your code here
		
		Map<List<Integer>, Integer> map = new HashMap<>();
		int[] table = new int[26];

		// #1
		for (int i = 0; i < s.length(); i++) {
			table = new int[26];
			for (int j = i; j < s.length(); j++) {
				char c = s.charAt(j);
				table[c - 'a']++;
				// #2 
				// Convert int[] to List<Integer> as List<Integer> is immutable 
				List<Integer> immutable = Arrays.stream(table).boxed().collect(Collectors.toList());
				// #3
				map.put(immutable, map.getOrDefault(immutable, 0) + 1);
			}
		}

		// #4
		int sum = 0;
		for (Integer v : map.values()) {
			sum += v * (v - 1) / 2;
		}
		return sum;
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int q = Integer.parseInt(bufferedReader.readLine().trim());

		IntStream.range(0, q).forEach(qItr -> {
			try {
				String s = bufferedReader.readLine();

				int result = Result.sherlockAndAnagrams(s);

				bufferedWriter.write(String.valueOf(result));
				bufferedWriter.newLine();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		bufferedReader.close();
		bufferedWriter.close();
	}
}
