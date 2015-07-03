/* MergeSort.java
   CSC 225 - Summer 2015
   Assignment 2 - Template for Merge Sort (Linked List version)
      
   This template includes some testing code to help verify the implementation.
   To interactively provide test inputs, run the program with
	java MergeSort
	
   To conveniently test the algorithm with a large input, create a 
   text file containing space-separated integer values and run the program with
	java MergeSort file.txt
   where file.txt is replaced by the name of the text file.

   NOTE: For large input files, the depth of recursion may cause the Java
   runtime environment to run out of stack space. To remedy this, run java
   with the extra parameter "-Xss64m" (which increases the stack size to 64
   megabytes). For example: java -Xss64m MergeSort input_data.txt
   
   B. Bird - 03/22/2015
*/

import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import java.io.File;

//Do not change the name of the MergeSort class
public class MergeSort{
	/* MergeSort(head)
		Given a reference to the head of a list, sort the contents of the list 
		with merge sort and return a reference to the sorted list. Your 
		implementation may create a new list or modify the input list.
		
		To achieve full marks, you may not use any iterative loop constructs
		(including for loops, while	loops or do-while loops); all iteration
		must be accomplished with recursion. 
		
		You may add additional functions (or classes) if needed, but the entire program must be
		contained in this file. 

		Do not change the function signature (name/parameters).
	*/
	public static ListNode splitList(ListNode head) { //This function outputs an array with alternate values from the input array
		if (head == null || head.next == null) {
			return head;
		}

		ListNode nextPointer = head.next;  //Points to the value to skip (the alternate value)

		return new ListNode(head.value, splitList(nextPointer.next)); //Create another array with next of the NextPointer

	}	

	public static ListNode MergeSort(ListNode head) {
		
	  if (head == null || head.next == null) 
	   	return head; 

	  ListNode l1 = splitList(head);	// Create two arrays from the input array, each having alternate values. 
	  ListNode l2 = splitList(head.next); 

	  ListNode s1 = MergeSort(l1); // S1 and S2 have sorted l1 and l2 respectively
	  ListNode s2 = MergeSort(l2);

	  return merge(s1, s2); //Merge sorted s1 and s2
	} 

	public static ListNode merge(ListNode list1, ListNode list2) 
    { 
    	//Base Cases
    	if (list1 == null) 
    		return list2; //If nothing left in list1, return list 2

 	 		if (list2 == null)
 	 			return list1; //If nothing left in list2, return list 1

		  if (list1.value < list2.value) {
		    list1.next = merge(list1.next, list2);
		    return list1;
		  } else {
		    list2.next = merge(list2.next, list1);
		    return list2;
		  }
		}
	
	
	/* ListNode class */
	/* Do not change, add or remove any aspect of the class definition below.
	   If any of the contents of this class are changed, your submission will
	   not be marked.
	   
	   The members of the class should be accessed directly (e.g. node.next, 
	   node.value), since no get/set methods exist.
	   
	   However, you may create a subclass of this class if you want to extend
	   its functionality. Ensure that your subclass is contained in MergeSort.java
	   with the rest of your code (since you may only submit one file).
	*/
	public static class ListNode{
		int value;
		ListNode next;
		public ListNode(int value){
			this.value = value;
			this.next = null;
		}
		public ListNode(int value, ListNode next){
			this.value = value;
			this.next = next;
		}
	}
	
	/* Testing code */
	
	/* listLength(node)
	   Compute the length of the list starting at the provided node.
	*/
	public static int listLength(ListNode node){
		if (node == null)
			return 0;
		return 1 + listLength(node.next);
	}
	
	/* testSorted(node)
	   Returns true if all elements of the list starting at the provided node
	   are in ascending order.
	*/
	public static boolean testSorted(ListNode node){
		//An empty list is always considered sorted.
		if (node == null)
			return true;
		//A list with one element is always considered sorted.
		if (node.next == null)
			return true;
		//Test whether the first element is greater than the second element
		if (node.value > node.next.value){
			//If so, the list is not sorted.
			return false;
		}else{
			//Otherwise, test whether the remaining elements are sorted and
			//return the result.
			return testSorted(node.next);
		}
	}
	
	/* printList(node)
	   Print all list elements starting at the provided node.
	*/
	public static void printList(ListNode node){
		if (node == null)
			System.out.println();
		else{
			System.out.print(node.value + " ");
			printList(node.next);
		}
	}
	
	/* readInput(inputScanner)
	   Read integer values from the provided scanner into a linked list.
	   Each recursive call reads one value, and recursion ends when the user
	   enters a negative value or the input file ends.
	*/
	public static ListNode readInput(Scanner inputScanner){
		//If no input is left, return an empty list (i.e. null)
		if (!inputScanner.hasNextInt())
			return null;
		int v = inputScanner.nextInt();
		//If the user entered a negative value, return an empty list.
		if (v < 0)
			return null;
		//If the user entered a valid input value, create a list node for it.
		ListNode node = new ListNode(v);
		//Scan for more values recursively, and set the returned list of values
		//to follow the node we just created.
		node.next = readInput(inputScanner);
		//Return the created list.
		return node;
	}

	/* main()
	   Contains code to test the MergeSort function. Nothing in this function 
	   will be marked. You are free to change the provided code to test your 
	   implementation, but only the contents of the MergeSort() function above 
	   will be considered during marking.
	*/
	public static void main(String[] args){
		Scanner s;
		if (args.length > 0){
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			s = new Scanner(System.in);
			System.out.printf("Enter a list of non-negative integers. Enter a negative value to end the list.\n");
		}
		ListNode inputListHead = readInput(s);
		
		int inputLength = listLength(inputListHead);
		System.out.printf("Read %d values.\n",inputLength);
		
		
		long startTime = System.currentTimeMillis();
		
		ListNode sortedListHead = MergeSort(inputListHead);
		
		long endTime = System.currentTimeMillis();
		
		double totalTimeSeconds = (endTime-startTime)/1000.0;
		
		//Compute the length of the output list
		int sortedListLength = listLength(sortedListHead);
		
		//Don't print out the values if there are more than 100 of them
		if (sortedListLength <= 100){
			System.out.println("Sorted values:");
			printList(sortedListHead);
		}
		
		//Check whether the sort was successful
		boolean isSorted = testSorted(sortedListHead);
		
		System.out.printf("List %s sorted.\n",isSorted? "is":"is not");
		System.out.printf("Total Time (seconds): %.2f\n",totalTimeSeconds);
	}
}
