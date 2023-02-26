//You are given an even length array; divide it in half and return possible minimum product difference of any two subarrays.
//        Note that the minimal product difference is the smallest absolute difference between any two arrays a and b, which is computed by calculating the difference after multiplying each element of the arrays a and b.
//        Input: {5,2,4,11}
//        Output: 2
//        {5,4} {2,11} result into minimum product difference.


import java.util.Arrays;

public class Question3_A {
    public static int calculateMinimumProductDifference(int[] numbers) {
        Arrays.sort(numbers); // sort the input array in ascending order
        int n = numbers.length;
        int minDifference = Integer.MAX_VALUE;
// iterate through the first half of the array and calculate the product differences
        for (int i = 0; i < n / 2; i++) {
            int product1 = numbers[i] * numbers[n - i - 1]; // calculate product of two elements from opposite ends
            int product2 = numbers[n / 2 + i] * numbers[n - n / 2 - i - 1]; // calculate product of two elements from middle
            int difference = Math.abs(product1 - product2); // calculate the absolute difference
            minDifference = Math.min(minDifference, difference); // update the minimum difference
        }
        return minDifference; // return the minimum product difference
    }

    public static void main(String[] args) {
        int[] numbers = {5, 2, 4, 11};
        int minimumProductDifference = calculateMinimumProductDifference(numbers);
        System.out.println("The minimum product difference is: " + minimumProductDifference); // outputs "The minimum product difference is: 2"
    }
}


