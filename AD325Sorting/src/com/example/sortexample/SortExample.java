package com.example.sortexample;

import java.util.Random;

public class SortExample {
    private void random(int[] randomNumbers){

        Random r = new Random();

        for(int i = 0; i < randomNumbers.length; i++){
            int addNumber = r.nextInt(100000) + 1;
            randomNumbers[i] = addNumber;
        }
    }

    private void selectionSort(int[] randomNumbers){
        int n = randomNumbers.length;
        for(int i = 0; i < n -1; i++){
            int min = i;
            //find the minimum element in unsorted array
            for(int j = i +1; j < n; j++){
                if(randomNumbers[j] < randomNumbers[min]) {
                    min = j;
                }
            }
            //swap the found minimum element with the first element
            int temp = randomNumbers[min];
            randomNumbers[min] = randomNumbers[i];
            randomNumbers[i] = temp;
        }
    }

    private void insertionSort(int[] randomNumbers){
        int n = randomNumbers.length;
        for(int i = 1; i < n; i++){
            int key = randomNumbers[i];
            int j = i - 1;

            //move elements of array the are greater than key, to one position ahead of their current position
            while(j >= 0 && randomNumbers[j] > key){
                randomNumbers[j + 1] = randomNumbers[j];
                j = j - 1;
            }
            randomNumbers[j + 1] = key;
        }
    }

    private void shellSort(int[] randomNumbers){

        int n = randomNumbers.length;
        //start with a big gap, then reduce the gap
        for(int gap = n/2; gap > 0; gap /= 2){
            for(int i = gap; i < n; i += 1){
                int temp = randomNumbers[i];
                int j;
                for(j = i; j >= gap && randomNumbers[j - gap] > temp; j -= gap){
                    randomNumbers[j] = randomNumbers[j - gap];
                }
                randomNumbers[j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Randomly generating 100,000 numbers...");
        int[] randomNumbers;
        randomNumbers = new int[100000];

        final long startTime1 = System.currentTimeMillis();
        SortExample shell = new SortExample();
        shell.shellSort(randomNumbers);
        final long endTime1 = System.currentTimeMillis();
        System.out.println("Total execution time for Shellsort: " + (endTime1 - startTime1) + " milliseconds");

        final long startTime2 = System.currentTimeMillis();
        SortExample insertion = new SortExample();
        insertion.random(randomNumbers);
        insertion.insertionSort(randomNumbers);
        final long endTime2 = System.currentTimeMillis();
        System.out.println("Total execution time for Insertion Sort: " + (endTime2 - startTime2) + " milliseconds");

        final long startTime3 = System.currentTimeMillis();
        SortExample selection = new SortExample();
        selection.random(randomNumbers);
        selection.selectionSort(randomNumbers);
        final long endTime3 = System.currentTimeMillis();
        System.out.println("Total execution time for Selection Sort: " + (endTime3 - startTime3) + " milliseconds");

    }
}




