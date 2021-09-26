package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static double r1 = 0;
    static double r2 = 0;
    static double c1 = 0;
    static double c2 = 0;
    static double d = 0;
    static double[] distance = new double[1000000];
    static int countLength = 0;


    public static void lowestEvaluation(int[][] arr,int low,int pMax,int cnt){
        System.out.println(".....................................................");
        int lowV = low;
        // Find Smallest number and count its occurrence
        for(int row=0; row<arr.length;row++){
            for(int col=0; col<arr[row].length;col++){
                if(lowV >= arr[row][col]) {
                    lowV = arr[row][col];
                    if(lowV != pMax) {
                        cnt = 1;
                    }else{
                        cnt++;
                    }
                    pMax = lowV;
                    lowV = arr[row][col];
                }

            }
        }

        System.out.println(lowV+" is the lowest value and it occurs "+cnt+" times");
        System.out.println(".....................................................");
        System.out.println();
    }

    public static void printAllPeaks(int[][] array,int radius,int matchValue,int outsideValue){
        boolean firstPeak = true;
        System.out.println(".....................................................");
        int totalPeaks = 0;
        //        Exclusive Radius
        for(int row=radius;row< array.length-radius;row++){
            for(int col=radius;col<array[row].length - radius;col++){
                if(matchValue <= array[row][col]) {
                    outsideValue = array[row][col];
                    int innerCount = 0;
                    boolean checking = true;
                    int currentHighestPeak = outsideValue;
                    for(int OuterRow = row-radius;OuterRow<=row+radius;OuterRow++){
                        for(int OuterCol = col-radius;OuterCol<=col+radius;OuterCol++){

                            int aroundValue = array[OuterRow][OuterCol];

                            if(aroundValue == outsideValue && (row != OuterRow && col != OuterCol)){
                                checking = false;
                            }

                            if(aroundValue > currentHighestPeak){
                                checking = false;
                            }


                        }

                    }
                    if(checking){
                        System.out.println("[ "+row+","+col+" : "+array[row][col]+" ]");
//                        System.out.println("Max Values above 90 at row: "+ row+" and coll: "+ col +" are: "+array[row][col]);
                        if(firstPeak){
                            r1 = row;
                            c1 = col;
                            firstPeak = false;
                        }else{
                            r2 = row;
                            c2 = col;
                            firstPeak = true;
                            calculateDistance();
                        }
                        totalPeaks++;
                    }

                }
            }
        }
        System.out.println("Total Peaks are "+totalPeaks);
        System.out.println(".....................................................");
        System.out.println();
    }

    public static void calculateDistance(){
        d = Math.pow(r1-r2,2) + Math.pow(c1-c2,2);
        d = Math.sqrt(d);
        double result = Math.round(d * 100.0) / 100.0;
        distance[countLength] = result;
        countLength++;
    }

    public static void printDistance(){
        distance = Arrays.copyOf(distance,countLength);
        System.out.println(".....................................................");
        Arrays.sort(distance);
        System.out.println("Distance " + distance[0]);
        System.out.println(".....................................................");
    }

    public static void commonElement(int[][] arr){
        System.out.println();
        System.out.println("Loading.....");
        int counter, element = arr[0][0], timesRepeat = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                counter = 0;
                for (int innerJ = i; innerJ < arr.length; innerJ++) {
                    for (int k = 0; k < arr[innerJ].length; k++) {
                        if (arr[i][j] == arr[innerJ][k]) {
                            counter++;
                        }
                    }

                    if (counter > timesRepeat) {
                        element = arr[i][j];
                        timesRepeat = counter;
                    }
                }
            }
        }

        System.out.println("Lowest element is: "+element+" and it occurs "+timesRepeat+" times in the data set");
    }

    public static void main(String[] args) throws FileNotFoundException {



        final String filename = "ELEVATIONS.TXT";
        File file = new File(filename);
        Scanner fileInput = new Scanner(file);
        int[] firstLine = new int[4];
        int ct = 0;

        // Reading first row from the data set
        while(fileInput.hasNextInt()){
            firstLine[ct] = fileInput.nextInt();
            ct++;
            if(ct ==4){
                break;
            }
        }

        int rowLength = firstLine[0];
        int colLength = firstLine[1];
        int matchValue = firstLine[2];
        int radius = firstLine[3];
        int outsideValue = 0;

        int [][] myArray = new int[rowLength][colLength];
        // Reading data set form here
        while(fileInput.hasNextInt()) {
            for (int i=0; i<myArray.length; i++) {

                for (int j=0; j<myArray[i].length; j++) {
                    myArray[i][j] = fileInput.nextInt();
                }
            }
        }

        long startTime = System.nanoTime();

        int lowValue = myArray[0][0];
        int prevMax = myArray[0][0];
        int count = 0;

        // Answer 1

        /**
         * This method print the lowest element from the data set and its occurrence as well..
         */
        lowestEvaluation(myArray,lowValue,prevMax,count);
        // Answer 2
        printAllPeaks(myArray,radius,matchValue,outsideValue);
        // Answer 3
        printDistance();
        // Answer 4
        commonElement(myArray);

        long endTime = System.nanoTime();

        long duration = (endTime - startTime) /1000000;

        System.out.println("It takes "+duration+" milliseconds");
    }
}
