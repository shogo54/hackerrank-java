package fraudulentActivityNotifications;

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
     * Complete the 'activityNotifications' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY expenditure
     *  2. INTEGER d
     */
	
	/*
	 * Thoughts: 
	 * - We can have two priority queues that have the same size 
	 *   and one of them is acceding order and the other is descending order. 
	 * - Adding to priority queue (heap) is O(n). getting median means
	 *   getting the median of first elements from the queues which is O(1). 
	 *   
	 * *** this is not an optimal solution! As values in expenditure is constant, 
	 * it is better to use a map from value to its counts ***
	 * 
	 * Approach: Two Queues
	 * 1. have a data structure that has two priority queues and one queue.
	 *    priority queues hold the elements in the value order and queue is 
	 *    to keep track of which element to get rid of next. 
	 * 2. go through the given list and add elements
	 * 3. if the list has enough elements, get its median and check with the current value.
	 * 
	 * Complexity:
	 * Time - O(nlogn) = n for going through expenditure * logn to add, remove from priority queues
	 * Space - O(n) at most we have n values in the list
	 * where n is the size of expenditure
	 */
	public static int activityNotifications(List<Integer> expenditure, int d) {
		// Write your code here
		// #1 
		SortedList list = new SortedList(d);
		int count = 0;
		for (int i = 0; i < expenditure.size(); i++) {
			// #3
			if (i >= d) {
				if (expenditure.get(i) >= list.median() * 2) {
					count++;
				}
			}
			// #2
			list.add(expenditure.get(i));
		}
		return count;
	}
}

class SortedList {
	int limit; // limit number to hold in the list
	PriorityQueue<Integer> left; // elements smaller than median
	PriorityQueue<Integer> right; // elements greater than median
	Queue<Integer> order; // queue to keep track of the incoming order of elements 

	public SortedList(int l) {
		limit = l;
		left = new PriorityQueue<>(Collections.reverseOrder());
		right = new PriorityQueue<>();
		order = new ArrayDeque<>();
	}

	public void add(Integer val) {
		// if the list has already limited amount, remove oldest element from queues
		if (order.size() == limit) {
			Integer rm = order.poll();
			if (!right.remove(rm)) {
				left.remove(rm);
			}
		}

		// add the new element to left or right queue
		if (left.size() == 0 || right.size() == 0) {
			left.offer(val);
		} else {
			if (right.peek() > val) {
				left.offer(val);
			} else {
				right.offer(val);
			}
		}
		
		// balance the left and right queues so that its always the same 
		// or left is one bigger than right
		if (right.size() > left.size()) {
			left.offer(right.poll());
		} else if (left.size() > right.size() + 1) {
			right.offer(left.poll());
		}
		
		// add the new element to the list
		order.offer(val);
	}

	public double median() {
		if (limit % 2 == 0) {
			return (left.peek() * 1.0 + right.peek()) / 2;
		} else {
			return left.peek();
		}
	}

}

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
		int n = Integer.parseInt(firstMultipleInput[0]);
		int d = Integer.parseInt(firstMultipleInput[1]);
		List<Integer> expenditure = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
				.map(Integer::parseInt).collect(toList());
		int result = Result.activityNotifications(expenditure, d);
		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();
		bufferedReader.close();
		bufferedWriter.close();
	}
}
