package project.ATM;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    private Scanner in = new Scanner(System.in);
    private int balance;
    ArrayList<Integer> amount = new ArrayList<>();
    ArrayList<String> action = new ArrayList<>();
    ArrayList<String> time = new ArrayList<>();
    ArrayList<String> beneficiary = new ArrayList<>();
    private boolean isAuthorized(){
        System.out.print("Enter ID : ");
        String id = in.nextLine();
        System.out.print("Enter Password : ");
        String pass = in.nextLine();
        return (id.equals("001") && pass.equals("523"));
    }


    public ATM() {
        balance = 10000;
    }
    private void HomePage(){
        System.out.println("\nEnter 1 for Withdraw");
        System.out.println("\t  2 for Deposit");
        System.out.println("\t  3 for Transfer Money");
        System.out.println("\t  4 To check Transaction History");
        System.out.println("\t  5 to Check balance");
        System.out.println("\t  6 to Exit");
        System.out.print("Your Choice : ");
        byte choice  = in.nextByte();
        switch (choice){
            case 1 -> {
                WithdrawMoney(Amount());
                HomePage();
            }
            case 2 -> {
                DepositMoney(Amount());
                HomePage();
            }
            case 3 -> {
                TransferMoney(Amount());
                HomePage();
            }
            case 4 -> {
                TransferHistory();
                HomePage();
            }
            case 5 -> {
                System.out.printf("Available Balance : %,d\n",balance);
                HomePage();
            }
            case 6 -> {
                in.close();
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        ATM m= new ATM();
       if(m.isAuthorized()){
           m.HomePage();
       }
       else System.out.println("Invalid User");
    }
    private void WithdrawMoney(int amount){
        if(amount <= balance){
            balance -= amount;
            Backend(amount,"Dr.","Self");
            System.out.println("Amount Successfully withdrawn.");
        }
        else System.out.println("Insufficient funds!");
    }
    private void DepositMoney(int amount){
        balance += amount;
        System.out.println("Amount successfully credited to your account.");
        Backend(amount,"Cr.","Self");
    }
    private void TransferMoney(int amount){
        if(amount <= balance){
            System.out.print("Enter Beneficiary's Account number : ");
            in.nextLine();
            String beneficiary = in.nextLine();
            System.out.println("Amount Successfully transferred!");
            balance -= amount;
            Backend(amount,"Dr.",beneficiary);
        }
    }
    private void TransferHistory(){
        if(amount.size() > 0) {
            System.out.printf("%-10s |%-8s%-11s |%16s\n", "AMOUNT", " ", "TIME", "BENEFICIARY");
            for (int i = 0; i < amount.size(); i++) {
                System.out.printf("%6d %s |%19s |%16s\n", amount.get(i), action.get(i), time.get(i), beneficiary.get(i));
            }
        }
        else System.out.println("No Transaction Found!!!");
    }
    private void Backend(int amount, String action, String beneficiary){
        this.amount.add(amount);
        this.action.add(action);
        StringBuilder time = new StringBuilder(String.valueOf(LocalDateTime.now()));
        time.setCharAt(10 ,' ');
        this.time.add(String.valueOf(time.substring(0,19)));
        this.beneficiary.add(beneficiary);
    }
    private int Amount(){
        System.out.print("Enter amount : ");
        Integer amount = in.nextInt();
        return amount;
    }
}
