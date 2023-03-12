package project.NGG;

import java.util.Random;
import java.util.Scanner;

public class Number_Guessing_Game {
    public static void main(String[] args) {
        Random x = new Random();
        Scanner in = new Scanner(System.in);
        byte com = (byte) x.nextInt(1,101), tries = 0;//here 101 is exclusive
        while (true){
            System.out.print("Enter a number : ");
            byte user = in.nextByte();
            if(user < com) {
                System.out.println("choose a bigger one.");
                tries++;
            }
            else if(user > com) {
                System.out.println("choose a smaller one.");
                tries++;
            }
            else if(user == com){
                System.out.println("your guess is correct, the number chosen by computer is : "+com);
                System.out.println("Number of tries are : "+tries);
                in.close();
                break;
            }
        }
    }
}
