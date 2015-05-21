/* PairUp.java
   CSC 225 - Summer 2015
   Programming Assignment 1 - Template for PairUp
   
   This template includes some testing code to help verify the implementation.
   To interactively provide test inputs, run the program with
  java PairUp
  
   To conveniently test the algorithm with a large input, create a 
   text file containing space-separated integer values and run the program with
  java PairUp file.txt
   where file.txt is replaced by the name of the text file.

   B. Bird - 03/09/2015
*/

import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import java.io.File;

//Do not change the name of the PairUp class
public class PairUp{
  /* PairUp225(A)
    The input array A will contain non-negative integers. If
    the array A can be completely divided into pairs (x,y) where
      x + y = 225,
    then the function will return true. Otherwise, the function
    will return false.
    
    The function is permitted to modify the input array A.
    
    Do not change the function signature (name/parameters).
  */
  public static boolean PairUp225(int[] A){
    int n = A.length;
    boolean canPair = false;
    
    int[] T = new int[226];               // Hash to store all the possible values to add upt0 225
    int count = 0;

    if (n%2 == 0 && n != 0) {            //Do any logic only if the array has even no. of elements and not zero elements
      for (int i=0; i<n; i++) {
          int j = A[i];
          if (j >= 0 && j <= 225){
            T[j]++;                     // Increase the A array element's corresponding hash value in the hash table         
          }
        }
  
      for (int k = 0; k <= 225/2; k++) {      // Go through only half the array as the highest value pair from the first half of the array 
                                              // can only be (112, 113) and 113 is from the second half of the array
        if ((T[k] > 0 && T[225-k] > 0) && (T[k] == T[225-k])) {  // Check if pairs exist
          count = count + T[k];                                  // If yes, count the pairs
        }
      }

      canPair = (count == n/2 ? true : false);        //If all the elements are to be paired, then there will be exactly n/2 pairs
      return canPair;
    }

    return canPair;
  }

  /* main()
     Contains code to test the PairUp225 function. Nothing in this function 
     will be marked. You are free to change the provided code to test your 
     implementation, but only the contents of the PairUp225() function above 
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
    Vector<Integer> inputVector = new Vector<Integer>();
    
    int v;
    while(s.hasNextInt() && (v = s.nextInt()) >= 0)
      inputVector.add(v);
    
    int[] array = new int[inputVector.size()];
    
    for (int i = 0; i < array.length; i++)
      array[i] = inputVector.get(i);

    System.out.printf("Read %d values.\n",array.length);
    
    
    long startTime = System.currentTimeMillis();
    
    boolean canPairUp = PairUp225(array);
    
    long endTime = System.currentTimeMillis();
    
    double totalTimeSeconds = (endTime-startTime)/1000.0;
    
    System.out.printf("Array values %s be paired up.\n",canPairUp? "can":"cannot");
    System.out.printf("Total Time (seconds): %.2f\n",totalTimeSeconds);
  }
}
