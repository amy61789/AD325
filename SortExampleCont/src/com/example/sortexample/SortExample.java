package com.example.sortexample;

import java.util.Random;

public class SortExample {
    private void random(int[] randomNumbers) {

        Random r = new Random();

        for (int i = 0; i < randomNumbers.length; i++) {
            int addNumber = r.nextInt(100000) + 1;
            randomNumbers[i] = addNumber;
        }
    }

    private void selectionSort(int[] randomNumbers) {
        int n = randomNumbers.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            //find the minimum element in unsorted array
            for (int j = i + 1; j < n; j++) {
                if (randomNumbers[j] < randomNumbers[min]) {
                    min = j;
                }
            }
            //swap the found minimum element with the first element
            int temp = randomNumbers[min];
            randomNumbers[min] = randomNumbers[i];
            randomNumbers[i] = temp;
        }
    }

    private void insertionSort(int[] randomNumbers) {
        int n = randomNumbers.length;
        for (int i = 1; i < n; i++) {
            int key = randomNumbers[i];
            int j = i - 1;

            //move elements of array the are greater than key, to one position ahead of their current position
            while (j >= 0 && randomNumbers[j] > key) {
                randomNumbers[j + 1] = randomNumbers[j];
                j = j - 1;
            }
            randomNumbers[j + 1] = key;
        }
    }

    private void shellSort(int[] randomNumbers) {

        int n = randomNumbers.length;
        //start with a big gap, then reduce the gap
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i += 1) {
                int temp = randomNumbers[i];
                int j;
                for (j = i; j >= gap && randomNumbers[j - gap] > temp; j -= gap) {
                    randomNumbers[j] = randomNumbers[j - gap];
                }
                randomNumbers[j] = temp;
            }
        }
    }

    private void merge(int arr[], int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];


        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    private void sort(int arr[], int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    private int partition(int arr[], int low, int high)
    {
        int pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }


    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    private void sort2(int arr[], int low, int high)
    {
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            sort(arr, low, pi-1);
            sort(arr, pi+1, high);
        }
    }

    public static void main(String[] args) {
        System.out.println("Randomly generating 100,000 numbers...");
        int[] randomNumbers;
        randomNumbers = new int[100000];

        SortExample shell = new SortExample();
        shell.random(randomNumbers);
        final long startTime1 = System.currentTimeMillis();
        shell.shellSort(randomNumbers);
        final long endTime1 = System.currentTimeMillis();
        System.out.println("Total execution time for Shellsort: " + (endTime1 - startTime1) + " milliseconds");


        SortExample insertion = new SortExample();
        insertion.random(randomNumbers);
        final long startTime2 = System.currentTimeMillis();
        insertion.insertionSort(randomNumbers);
        final long endTime2 = System.currentTimeMillis();
        System.out.println("Total execution time for Insertion Sort: " + (endTime2 - startTime2) + " milliseconds");


        SortExample selection = new SortExample();
        selection.random(randomNumbers);
        final long startTime3 = System.currentTimeMillis();
        selection.selectionSort(randomNumbers);
        final long endTime3 = System.currentTimeMillis();
        System.out.println("Total execution time for Selection Sort: " + (endTime3 - startTime3) + " milliseconds");

        SortExample merge = new SortExample();
        merge.random(randomNumbers);
        final long startTime4 = System.currentTimeMillis();
        merge.sort(randomNumbers, 0, randomNumbers.length - 1);
        final long endTime4 = System.currentTimeMillis();
        System.out.println("Total execution time for Merge Sort: " + (endTime4 - startTime4) + " milliseconds");

        SortExample quick = new SortExample();
        quick.random(randomNumbers);
        final long startTime5 = System.currentTimeMillis();
        quick.sort2(randomNumbers, 0, randomNumbers.length - 1);
        final long endTime5 = System.currentTimeMillis();
        System.out.println("Total execution time for Quick Sort: " + (endTime5 - startTime5) + " milliseconds");
    }
}




