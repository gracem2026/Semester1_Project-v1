/*
Grace Ma Y11 CS section 11- End of semester project
Completed on 13/12/2023
Project description: This is the 24 math card game, but made in Java.
The goal of the game is to add, subtract, multiply and divide the 4 displayed numbers to get the number 24.
Thanks to Ms Nahar, Mr Tam, as well as StackOverflow for helping :)
 */

import java.util.*;
import java.io.*;
/* import java.util.Scanner;
import java.util.Random;
import java.io.PrintStream;
import java.io.File;
import java.io.FileNotFoundException;*/
// import java.io.RandomAccessFile;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // int currentPos = rd.nextInt(565);
        instructions();
        game(-1);
        //testing
        //test to see if credits can be printed
        //credits();
        //playAgain();
    }
    // start a new game
    // numIndex here takes either -1 or the index of the set of numbers used in the previous game
    // -1 means new random set
    public static void game(int numIndex) throws FileNotFoundException {
        // files
        File file1 = new File("/Users/gracema/IdeaProjects/Semester1_Project/src/game_numbers.txt");
        File file2 = new File("/Users/gracema/IdeaProjects/Semester1_Project/src/current_numbers.txt");
        // initialize variables
        Scanner scanner = new Scanner(System.in);
        Scanner scanFile1 = new Scanner(file1);
        Scanner scanFile2 = new Scanner(file2);
        PrintStream numbers = new PrintStream(file2);
        Random rd = new Random();
        int[] currentArray = new int[4];
        int currentPos = 0;
        int tempResult = 0;
        int finalAnswer = 0;
        int remainingA = 0;
        int remainingB = 0;
        int remainingC = 0;

        // get random list from file array
        String[] possibleNums = new String[565];
        //String[] possibleNums = new String[565];
        int index = 0;
        while (scanFile1.hasNextLine()) {
            possibleNums[index++] = scanFile1.nextLine();
            // System.out.println(index + possibleNums[index]);
        }
        //System.out.println(possibleNums[5]);
        //System.out.println(possibleNums[rd.nextInt(565)]);

        // use random numbers/current numbers depending on user input
        if (numIndex == -1) {
            currentPos = rd.nextInt(565);
            int previousNums = currentPos;
            String currentStr = possibleNums[currentPos];
            currentArray = stringToArray(currentStr, 4);
            //numbers.println("");
            System.out.println("Your numbers are: ");
            System.out.println(possibleNums[currentPos]);
            numbers.println(possibleNums[currentPos]);
            //for (int i = 0; i < 4; i++) System.out.print(currentArray[i] + " ");
        }
        else {
            currentPos = numIndex;
            //System.out.println(currentPos);
            String currentStr = possibleNums[numIndex];
            //test
            //System.out.println(currentStr);
            currentArray = stringToArray(currentStr, 4);
            System.out.println("Your numbers are: ");
            //test
            //System.out.println(currentArray);
            System.out.println(currentStr);

            //System.out.println(possibleNums[currentPos]);
            //numbers.println(possibleNums[currentPos]);
        }

        // start first turn
        System.out.println("\n{Take your first turn here!}");
        System.out.println("Input your first number. Be sure it is amongst the list of numbers!");
        int a = scanner.nextInt();
        if (a != currentArray[0] && a != currentArray[1] && a != currentArray[2] && a != currentArray[3]) {
            System.out.println("Your number is not among the list of numbers, try again: ");
            //testing
            //System.out.println("testing result: incorrect");
            a = scanner.nextInt();
        } else {
            if (a == currentArray[0]) {
                remainingA = currentArray[1];
                remainingB = currentArray[2];
                remainingC = currentArray[3];
            } else if (a == currentArray[1]) {
                remainingA = currentArray[0];
                remainingB = currentArray[2];
                remainingC = currentArray[3];
            } else if (a == currentArray[2]) {
                remainingA = currentArray[0];
                remainingB = currentArray[1];
                remainingC = currentArray[3];
            } else {
                remainingA = currentArray[0];
                remainingB = currentArray[1];
                remainingC = currentArray[2];
            }
            //testing
            //System.out.println("testing result: correct");
        }
        System.out.println("Remaining numbers include: " + remainingA + " " + remainingB + " " + remainingC);

        System.out.println("Input your second number. Be sure it is amongst the list of numbers!");
        int b = scanner.nextInt();
        if (b != currentArray[0] && b != currentArray[1] && b != currentArray[2] && b != currentArray[3]) {
            System.out.println("Your number is not among the list of numbers, try again: ");
            b = scanner.nextInt();
        } else {
            if (a == currentArray[0]) {
                if (b == currentArray[1]) {
                    remainingA = currentArray[2];
                    remainingB = currentArray[3];
                } else if (b == currentArray[2]) {
                    remainingA = currentArray[1];
                    remainingB = currentArray[3];
                } else if (b == currentArray[3]) {
                    remainingA = currentArray[1];
                    remainingB = currentArray[2];
                }
            } else if (a == currentArray[1]) {
                if (b == currentArray[0]) {
                    remainingA = currentArray[2];
                    remainingB = currentArray[3];
                } else if (b == currentArray[2]) {
                    remainingA = currentArray[0];
                    remainingB = currentArray[3];
                } else if (b == currentArray[3]) {
                    remainingA = currentArray[0];
                    remainingB = currentArray[2];
                }
            } else if (a == currentArray[2]) {
                if (b == currentArray[0]) {
                    remainingA = currentArray[1];
                    remainingB = currentArray[3];
                } else if (b == currentArray[1]) {
                    remainingA = currentArray[0];
                    remainingB = currentArray[3];
                } else if (b == currentArray[3]) {
                    remainingA = currentArray[0];
                    remainingB = currentArray[1];
                }
            } else {
                if (b == currentArray[0]) {
                    remainingA = currentArray[1];
                    remainingB = currentArray[2];
                } else if (b == currentArray[1]) {
                    remainingA = currentArray[0];
                    remainingB = currentArray[2];
                } else if (b == currentArray[2]) {
                    remainingA = currentArray[0];
                    remainingB = currentArray[1];
                }
            }
        }
        System.out.println("Remaining numbers include: " + remainingA + " " + remainingB);

        System.out.println("What do you want to do with the numbers? (+, -, * or /)");
        String operator = scanner.next();
        if (operator.equals("+")) {
            tempResult = a + b;
        } else if (operator.equals("-")) {
            tempResult = a - b;
        } else if (operator.equals("*")) {
            tempResult = a * b;
        } else if (operator.equals("/")) {
            tempResult = a / b;
        } else {
            System.out.println("This is not a valid operator, try again: ");
            operator = scanner.next();
        }
        System.out.println("Remaining numbers include: " + tempResult + " " + remainingA + " " + remainingB);
        // test assessment mechanism
        // System.out.println(tempResult);

        // start second turn
        System.out.println("\n{Take your second turn here!}");
        System.out.println("Input your first number. Your number must be among the list of numbers or your previous result");
        int c = scanner.nextInt();
        if (c != currentArray[0] && c != currentArray[1] && c != currentArray[2] && c != currentArray[3] && c != tempResult) {
            System.out.println("Your number must be among the list of numbers or your previous result, try again:");
            c = scanner.nextInt();
        } else {
            if (c == tempResult) {
                System.out.println("Remaining numbers include: " + remainingA + " " + remainingB);
            } else if (c == remainingA) {
                System.out.println("Remaining numbers include: " + tempResult + " " + remainingB);
            } else {
                System.out.println("Remaining numbers include: " + tempResult + " " + remainingA);
            }
        }

        System.out.println("Input your second number. Your number must be among the list of numbers or your previous result");
        int d = scanner.nextInt();
        int otherTemp = 0;
        int tempRemain = 0;
        if (d != currentArray[0] && d != currentArray[1] && d != currentArray[2] && d != currentArray[3] && d != tempResult) {
            System.out.println("Your number must be among the list of numbers or your previous result, try again:");
            d = scanner.nextInt();
        } else {
            otherTemp = tempResult;
            if (c == tempResult) {
                if (d == remainingA) System.out.println("Remaining numbers include: " + remainingB);
                else System.out.println("Remaining numbers include: " + remainingA);
            } else if (c == remainingA) {
                if (d == tempResult) System.out.println("Remaining numbers include: " + remainingB);
                else System.out.println("Remaining numbers include: " + tempResult);
            } else { // c == remainingB
                if (d == tempResult) System.out.println("Remaining numbers include: " + remainingA);
                else System.out.println("Remaining numbers include: " + tempResult);
            }
        }

        System.out.println("What do you want to do with the numbers? (+, -, * or /)");
        operator = scanner.next();
        if (operator.equals("+")) {
            tempResult = c + d;
        } else if (operator.equals("-")) {
            tempResult = c - d;
        } else if (operator.equals("*")) {
            tempResult = c * d;
        } else if (operator.equals("/")) {
            tempResult = c / d;
        } else {
            System.out.println("This is not a valid operator, try again: ");
            operator = scanner.next();
        }

        if (c == otherTemp) {
            if (d == remainingA) {
                System.out.println("Remaining numbers include: " + tempResult + " " + remainingB);
                tempRemain = remainingB;
            }
            else {
                System.out.println("Remaining numbers include: " + tempResult + " " + remainingA);
                tempRemain = remainingA;
            }
        }
        else if (c == remainingA) {
            if (d == otherTemp) {
                System.out.println("Remaining numbers include: " + tempResult + " " + remainingB);
                tempRemain = remainingB;
            }
            else {
                System.out.println("Remaining numbers include: " + tempResult + " " + otherTemp);
                tempRemain = otherTemp;
            }
        } else { // c == remainingB
            if (d == otherTemp) {
                System.out.println("Remaining numbers include: " + tempResult + " " + remainingA);
                tempRemain = remainingA;
            }
            else {
                System.out.println("Remaining numbers include: " + tempResult + " " + otherTemp);
                tempRemain = otherTemp;
            }
        }

        // start third/last turn
        System.out.println("{Take your third and last turn here!}");
        System.out.println("\nInput your first number. Your number must be among the list of numbers or your previous result");
        int e = scanner.nextInt();
        if (e != currentArray[0] && e != currentArray[1] && e != currentArray[2] && e != currentArray[3] && e != tempResult) {
            System.out.println("Your number must be among the list of numbers or your previous result, try again:");
            e = scanner.nextInt();
        } else {
            if (e == tempResult) {
                System.out.println("Remaining numbers include: " + tempRemain);
            } else {
                System.out.println("Remaining numbers include: " + tempResult);
            }
        }

        System.out.println("\nInput your second number. Your number must be among the list of numbers or your previous result");
        int f = scanner.nextInt();
        if (f != currentArray[0] && f != currentArray[1] && f != currentArray[2] && f != currentArray[3] && f != tempResult) {
            System.out.println("Your number must be among the list of numbers or your previous result, try again:");
            f = scanner.nextInt();
        }

        System.out.println("\nWhat do you want to do with the numbers? (+, -, * or /)");
        operator = scanner.next();
        if (operator.equals("+")) {
            finalAnswer = e + f;
        } else if (operator.equals("-")) {
            finalAnswer = e - f;
        } else if (operator.equals("*")) {
            finalAnswer = e * f;
        } else if (operator.equals("/")) {
            finalAnswer = e / f;
        } else {
            System.out.println("This is not a valid operator, try again: ");
        }
        numbers.println(possibleNums[currentPos]);

        // determine W or L
        if (finalAnswer == 24) {
            //testing
            //System.out.println("Player answered: " + finalAnswer);

            System.out.println("\n--~YOU WIN!~--");
            System.out.println("Congratulations! You have reached the answer of 24 :D");
            //playAgain(-1);
        } else {
            //testing
            //System.out.println("Player answered: " + finalAnswer);

            System.out.println("\n--~GAME OVER!~--");
            System.out.println("Unfortunately, your final answer was " + finalAnswer + ", not 24 :(");
            //playAgain(currentPos);
        }
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Would you like to play again?");
        System.out.println("Enter 0 to exit \nEnter 1 to play with a new set of numbers \nEnter 2 to play again with the same set of numbers");
        int input = scanner2.nextInt();
        if (input == 0) {
            credits();
        }
        else if (input == 1) {
            game(-1);
        }
        else if (input == 2){
            game(currentPos);
        }
        else {
            System.out.println("That is not an option. Try again: ");
            input = scanner2.nextInt();
        }
       // file2.close();
        // return currentPos;
    }

    // print game instructions
    public static void instructions() {
        System.out.println("-----------~24 game~-----------");
        System.out.println("Welcome to the 24 game! This is a simple and fun math game made in Java.");
        System.out.println("The goal of this game is to reach the number 24 by adding, subtracting, multiplying and dividing 4 numbers.");
        System.out.println("For each turn, enter your move by inputting the numbers and selecting the operator.");
        System.out.println("If you manage to get the correct answer, you win the game!");
        System.out.println("Have fun :D");
        System.out.println("-------------------------------");
    }

    public static int[] stringToArray(String str, int no) {
        int[] array = new int[no];
        for (int a = 0; a < str.length(); a++) {
            String tempNum = str.substring(a, a+1);
            if (a == 0) {
                array[0] = Integer.parseInt(tempNum);
                //testing
                //System.out.println(array[0]);
            }
            else if (a == 2) {
                if (str.substring(a, a+2).equals("10")) {
                    array[1] = 10;
                }
                else {
                    array[1] = Integer.parseInt(tempNum);
                }
                //testing
                //System.out.println(array[1]);
            }
            else if (a == 4) {
                if (str.substring(a, a+2).equals("10")) {
                    array[2] = 10;
                }
                else {
                    array[2] = Integer.parseInt(tempNum);
                }
                //testing
                //System.out.println(array[2]);
            }
            else if (a == 6) {
                if (str.substring(a, str.length()).equals("10")) {
                    array[3] = 10;
                }
                else {
                    array[3] = Integer.parseInt(tempNum);
                }
                //testing
                //System.out.println(array[3]);
            }
        }
        return array;
    }

    // got rid of the play again function as of 9/1/24, started doing goofy recursion stuff instead w/ game() function

    // play again?
    /* public static void playAgain(int currentIndex) throws FileNotFoundException {
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Would you like to play again?");
        System.out.println("Enter 0 to exit \nEnter 1 to play with a new set of numbers \nEnter 2 to play again with the same set of numbers");
        int input = scanner2.nextInt();
        if (input == 0) {
            credits();
        }
        else if (input == 1) {
            game(-1);
        }
        else if (input == 2){
            game(currentIndex);
        }
        else {
            System.out.println("That is not an option. Try again: ");
            input = scanner2.nextInt();
        }
    } */

    // credits
    public static void credits() {
        System.out.println("-----------~24 game~-----------");
        System.out.println("24- Originally based off of a card game");
        System.out.println("Code by: Grace Ma");
        System.out.println("Language used: Java");
        System.out.println("Coding software used: IntelliJ, OnlineGDB (when IntelliJ crashed)");
        System.out.println("Special thanks to: Ms. Nahar, Mr. Tam");
        System.out.println("Very special thanks to: you, for playing this game :D");
        System.out.println("-------------------------------");
    }
}
