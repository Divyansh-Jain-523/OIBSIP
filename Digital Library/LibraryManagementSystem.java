package project.LMS;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
final public class LibraryManagementSystem
{
    public static void main(String[] args) throws FileNotFoundException {
        LibraryManagementSystem obj = new LibraryManagementSystem();
        obj.loginPage();
    }
    private final String[][] StudentIndexNum_IssuedBooks = new String[999][7];
    private final String[][] StudentIndexNum_RegistrationDetails = new String[999][6];
    ArrayList<String> BookName = new ArrayList<>();
    ArrayList<Byte> BookQuantity = new ArrayList<>();
    byte[] StudentIndexNum_NoOfIssuedBooks= new byte[999];
    private int bookNum ;
    private short StudentIndexNum = 0;
    Scanner in = new Scanner(System.in);
    LibraryManagementSystem() throws FileNotFoundException {
        Scanner read = new Scanner(new File("//Users//divyanshjain/Documents//Programming//Java//src//project//LMS//bookNames.txt"));
        while (read.hasNextLine()){
            BookName.add(read.nextLine());
        }
        bookNum = BookName.size();
        Random r = new Random();
        for(int i = 0 ; i < bookNum ; i++) BookQuantity.add((byte)(r.nextInt(10, 100)));
        read = new Scanner(new File("//Users//divyanshjain/Documents//Programming//Java//src//project//LMS//studentDetails.txt"));
        while (read.hasNextLine()){
            StudentIndexNum_RegistrationDetails[StudentIndexNum][0] = read.nextLine();
            StudentIndexNum_RegistrationDetails[StudentIndexNum][1] = read.nextLine();
            StudentIndexNum_RegistrationDetails[StudentIndexNum][2] = read.nextLine();
            StudentIndexNum_RegistrationDetails[StudentIndexNum][3] = read.nextLine();
            StudentIndexNum_RegistrationDetails[StudentIndexNum][4] = read.nextLine();
            StudentIndexNum_RegistrationDetails[StudentIndexNum][5] = read.nextLine();
            StudentIndexNum++;
        }
        read.close();
    }
    private void loginPage()
    {
        System.out.print("Enter your ID : ");
        String tempID = in.nextLine();
        System.out.print("Enter Password : ");
        String tempPassword = in.nextLine();
        if (tempID.equals("ID501") && tempPassword.equals("Password501"))
        {
            System.out.println();
            System.out.println("Welcome Admin");
            adminPage();
        }
        else if(tempID.equals("Stu") && tempPassword.equals("NotNull")){
            System.out.println();
            System.out.println("Welcome User");userPage();
        }
        else System.out.println("Invalid Credentials!!!");
    }
    private void adminPage() {
        System.out.println("\n");
        System.out.println("Enter : \n\t1 to Add Books");
        System.out.println("\t2 to View Available Books");
        System.out.println("\t3 to Search a Book");
        System.out.println("\t4 to Issue a Book for Student");
        System.out.println("\t5 to Submit a Book");
        System.out.println("\t6 to Register a Student");
        System.out.println(("\t127 for Advance options"));
        System.out.print("Your choice : ");
        try
        {
            byte choice = in.nextByte();
            switch (choice)
            {
                case 1 ->
                {
                    in.nextLine();
                    addBook();
                    adminPage();
                }
                case 2 ->
                {
                    in.nextLine();
                    availableBooks();
                    adminPage();
                }
                case 3 ->
                {
                    in.nextLine();
                    searchBooks();
                    adminPage();
                }
                case 4 ->
                {
                    in.nextLine();
                    issueBook(tempSID());
                    adminPage();
                }
                case 5 ->
                {
                    in.nextLine();
                    submitBook(tempSID());
                    adminPage();
                }
                case 6 ->
                {
                    in.nextLine();
                    registerStudent();
                    adminPage();
                }
                case 127 ->
                {
                    in.nextLine();
                    advanceMenu();
                    adminPage();
                }
                default ->
                {
                    System.out.println("Retry with valid option.");
                    adminPage();
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Getting this error : "+e);
        }
        in.nextLine();
    }
    private void userPage(){
        System.out.println("\n");
        System.out.println("\t1 to View Available Books");
        System.out.println("\t2 to Search a Book");
        System.out.println("\t3 to Issue a Book for Student");
        System.out.println("\t4 to Submit a Book");
        System.out.print("Your choice : ");
        try
        {
            byte choice = in.nextByte();
            switch (choice)
            {
                case 1 ->
                {
                    in.nextLine();
                    availableBooks();
                    userPage();
                }
                case 2 ->
                {
                    in.nextLine();
                    searchBooks();
                    userPage();
                }
                case 3 ->
                {
                    in.nextLine();
                    issueBook(tempSID());
                    userPage();
                }
                case 4 ->
                {
                    in.nextLine();
                    submitBook(tempSID());
                    userPage();
                }
                default ->
                {
                    System.out.println("Retry with valid option.");
                    userPage();
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Getting this error : "+e);
        }
        in.nextLine();
    }
    private boolean duplicateBookFound(String bookNameTemp)
    {
        for (short i = 0; i < bookNum; i++)
        {
            if (bookNameTemp.equalsIgnoreCase(BookName.get(i)))
            {
                return true;
            }
        }
        return false;
    }
    private boolean isStudentRegistered(String tempEnrollment)
    {
        for (short i = 0 ; i < StudentIndexNum ; i++)
        {
            if (StudentIndexNum_RegistrationDetails[i][1].equalsIgnoreCase(tempEnrollment))
            {
                return true;
            }
        }
        return false;
    }
    private void updateBooks(short tempBookNum)
    {
        System.out.print("How much '"+BookName.get(tempBookNum)+"' are available : ");
        BookQuantity.set(tempBookNum,in.nextByte());
        System.out.println("Book named '"+BookName.get(tempBookNum)+"' successfully updated its Quantity.");
        removeBooksWithNoQuantity(tempBookNum);
    }
    private void removeBooksWithNoQuantity(short tempBookNum)
    {
        if (BookQuantity.get(tempBookNum) == 0)
        {
            BookName.remove(BookName.get(tempBookNum));
            bookNum--;
        }
    }
    private void advanceMenu()
    {
        System.out.println("\n");
        System.out.println("Enter 1 to See Issued Books After Enrollment Number of All Students.");
        System.out.println("Enter 2 to See Issued Books of Specific Students.");
        System.out.println("Enter 3 to see All Students full Details.");
        System.out.println("Enter 4 to see All details of a Specific Student.");
        System.out.print("Your Choice : ");
        try
        {
            byte choice = in.nextByte();
            switch (choice)
            {
                case 1 ->
                {
                    seeIssuedBooksAllStudent();
                    adminPage();
                }
                case 2 ->
                {
                    in.nextLine();
                    showIssuedBooksStudentSpecific(tempSID());
                    adminPage();
                }
                case 3 ->
                {
                    seeAllDetailsOfAllStudents();
                    adminPage();
                }
                case 4 ->
                {
                    in.nextLine();
                    studentDetail(tempSID());
                    adminPage();
                }
                default ->
                {
                    System.out.println("Invalid Choice!!!");
                    advanceMenu();
                }
            }
        }
        catch (Exception e){
            System.out.println("Getting this error : ");
        }
    }
    private short tempSID(){
        System.out.println();
        System.out.print("Enter Enrollment number of Student : ");
        String tempEnroll = in.nextLine();
        short tempSID = 999;
        for (short i = 0; i < StudentIndexNum; i++) {
            if (StudentIndexNum_RegistrationDetails[i][1].equalsIgnoreCase(tempEnroll)) {
                return i;
            }
        }return tempSID;
    }
    private void addBook()
    {
        System.out.println();
        System.out.print("Enter Book name to add : ");
        String tempBook = in.nextLine();
        if (duplicateBookFound(tempBook)){
            System.out.println("Duplicate Book found!!!");
            System.out.print("Do you want to update Book Quantity (Y/N) : ");
            char choice = in.next().charAt(0);
            if (choice == 'y' || choice == 'Y')
            {
                for (short i = 0; i < bookNum; i++)
                {
                    if (BookName.contains(tempBook))
                    {
                        updateBooks(i);
                    }
                }
            }
        }
        else {
            BookName.add(tempBook);
            System.out.print("Enter available Quantity of " + BookName.get(bookNum) + " : ");
            byte tempBQ = in.nextByte();
            if (tempBQ == 0)
            {
                BookName.remove(bookNum);
                System.out.println("As entered Quantity is 0 , the book is discarded.");
            }
            else
            {

                BookQuantity.add(tempBQ);
                System.out.println("Book named '" + BookName.get(bookNum) + "' added Successfully with " + BookQuantity.get(bookNum) + " available books.");
                bookNum++;
            }
            in.nextLine();
        }
    }
    private void availableBooks()
    {
        System.out.println();
        for (short i = 0; i < bookNum; i++)
        {
            System.out.println("=> (" +( i + 1) + ") '" + BookName.get(i)+"'");
            System.out.println("\t having available Quantity : " + BookQuantity.get(i));
        }
        if (bookNum == 0)
        {
            System.out.println("No Books available to display");
            System.out.println("Please add some Books.");
            System.out.print("Do you want to add books (Y/N) : ");
            boolean input = in.next().equalsIgnoreCase("Y");
            if (input)
            {
                in.nextLine();
                addBook();
            }
        }
    }
    private void searchBooks()
    {
        System.out.println();
        System.out.print("Enter book name to search : ");
        String bookNameTemp = in.nextLine();
        boolean bookPresent = false;
        for (short i = 0; i < bookNum; i++)
        {
            if (bookNameTemp.equalsIgnoreCase(BookName.get(i)))
            {
                System.out.println("Book found!");
                System.out.println("Book number is " + (i+1));
                bookPresent = true;
            }
        }
        if (!bookPresent)
        {
            System.out.println("Sorry no such book found,\nYou may search again using different keyword.");
            System.out.print("Do you want to Search again (Y/N) : ");
            char searchChoice = in.next().charAt(0);
            in.nextLine();
            if (searchChoice == 'y' || searchChoice == 'Y')
            {
                searchBooks();
            }
            else
            {
                System.out.print("Do you want to add Books (Y/N) : ");
                char addChoice = in.next().charAt(0);
                if(addChoice == 'y' || addChoice == 'Y')
                {
                    in.nextLine();
                    addBook();
                }
            }
        }
    }
    private void issueBook(short tempSID)
    {
        if (tempSID < StudentIndexNum)
        {
            if (StudentIndexNum_NoOfIssuedBooks[tempSID] >= 0 && StudentIndexNum_NoOfIssuedBooks[tempSID] <= 6) {
                if (BookName.size() != 0){
                    availableBooks();
                    System.out.print("Enter Book number to Issue : ");
                    short tempBookNum = in.nextShort();
                    tempBookNum -= 1;
                    if (tempBookNum < bookNum) {
                        StudentIndexNum_IssuedBooks[tempSID][StudentIndexNum_NoOfIssuedBooks[tempSID]] = BookName.get(tempBookNum);
                        BookQuantity.add(tempBookNum, (byte) (BookQuantity.get(tempBookNum) - 1));
                        StudentIndexNum_NoOfIssuedBooks[tempSID] += 1;
                        removeBooksWithNoQuantity(tempBookNum);
                        System.out.println("Book Issued Successfully.");
                    } else {
                        System.out.println("Invalid Book number!!! ");
                        issueBook(tempSID);
                    }
                }
                else System.out.println("Sorry No Books Available right now!");
            }
            else if (StudentIndexNum_NoOfIssuedBooks[tempSID] == 7){
                System.out.println("Maximum Books are Issued for this user.");
            }
        }
        else System.out.println("This Student is not registered.");
    }
    private void submitBook(short tempSID)
    {
        if (tempSID != 999)
        {
            showIssuedBooks(tempSID);
            if (StudentIndexNum_NoOfIssuedBooks[tempSID] == 0)
            {
                System.out.println("No Books Issued.");
            }
            else
            {
                System.out.print("Enter book number to Submit : ");
                byte tempBook = in.nextByte();
                if (duplicateBookFound(StudentIndexNum_IssuedBooks[tempSID][tempBook - 1]))
                {
                    for (short i = 0; i < bookNum; i++) {
                        if (StudentIndexNum_IssuedBooks[tempSID][tempBook-1].equalsIgnoreCase(BookName.get(i)))
                        {
                            BookQuantity.add(i, (byte) (BookQuantity.get(i) + 1));
                            break;
                        }
                    }
                    for (byte i = tempBook; i < StudentIndexNum_NoOfIssuedBooks[tempSID]; i++)
                    {
                        StudentIndexNum_IssuedBooks[tempSID][tempBook] = StudentIndexNum_IssuedBooks[tempSID][tempBook + 1];
                    }
                }
                else
                {
                    BookName.add(bookNum,StudentIndexNum_IssuedBooks[tempSID][tempBook - 1]);
                    BookQuantity.add(bookNum, (byte) 1);
                    bookNum++;
                }
                StudentIndexNum_NoOfIssuedBooks[tempSID] -= 1;
                System.out.println("Book is Successfully submitted.");
            }
        }
        else
        {
            System.out.println("This Student is not registered.");
        }
    }
    private void registerStudent()
    {
        System.out.println();
        if (StudentIndexNum < StudentIndexNum_RegistrationDetails.length)
        {
            System.out.print("Enter Name : ");
            StudentIndexNum_RegistrationDetails[StudentIndexNum][0] = in.nextLine();
            System.out.print("Enter Enrollment number : ");
            String tempEnrollment = in.nextLine().toUpperCase();
            if (!isStudentRegistered(tempEnrollment))
            {
                StudentIndexNum_RegistrationDetails[StudentIndexNum][1] = tempEnrollment;
                System.out.print("Enter Branch : ");
                StudentIndexNum_RegistrationDetails[StudentIndexNum][2] = in.nextLine().toUpperCase();
                System.out.print("Enter Father's name : ");
                StudentIndexNum_RegistrationDetails[StudentIndexNum][3] = in.nextLine();
                System.out.print("Enter Contact Number : ");
                StudentIndexNum_RegistrationDetails[StudentIndexNum][4] = in.nextLine();
                System.out.print("Enter Batch : ");
                StudentIndexNum_RegistrationDetails[StudentIndexNum][5] = in.nextLine();
                System.out.println("Student named '"+StudentIndexNum_RegistrationDetails[StudentIndexNum][0]+"' Registered Successfully.");
                StudentIndexNum_NoOfIssuedBooks[StudentIndexNum] = 0;
                StudentIndexNum++;
            }
            else System.out.println("Student already Registered.");
        }
        else System.out.println("Cannot Register a Student from now!");
    }
    private void studentDetail(short tempSID)
    {
        System.out.println();
        if (tempSID != 999)
        {
            System.out.println("Name : " + StudentIndexNum_RegistrationDetails[tempSID][0]);
            System.out.println("\tEnrollment number : " + StudentIndexNum_RegistrationDetails[tempSID][1]);
            System.out.println("\tBranch : " + StudentIndexNum_RegistrationDetails[tempSID][2]);
            System.out.println("\tFather's name : " + StudentIndexNum_RegistrationDetails[tempSID][3]);
            System.out.println("\tContact Number : " + StudentIndexNum_RegistrationDetails[tempSID][4]);
            System.out.println("\tBatch : " + StudentIndexNum_RegistrationDetails[tempSID][5]);
        }
        else
        {
            System.out.println("This Student is not registered.");
        }
    }
    private void seeIssuedBooksAllStudent()
    {
        System.out.println();
        in.nextLine();
        for (short i = 0; i < StudentIndexNum; i++)
        {
            showIssuedBooks(i);
        }
    }
    private void showIssuedBooksStudentSpecific(short tempSID)
    {
        if (tempSID != 999)
        {
            if (StudentIndexNum_NoOfIssuedBooks[tempSID] == 0)
            {
                System.out.println("No books are issued for this Student.");
            }
            else showIssuedBooks(tempSID);
        }
        else System.out.println("This Student is not registered.");
    }
    private void showIssuedBooks(short tempSID)
    {
        if (tempSID != 999)
        {
            if (StudentIndexNum_NoOfIssuedBooks[tempSID]!= 0)
            {
                System.out.println(StudentIndexNum_RegistrationDetails[tempSID][1] + " holds "+ StudentIndexNum_NoOfIssuedBooks[tempSID] + " Books");
                for (short j = 0; j < StudentIndexNum_NoOfIssuedBooks[0]; j++)
                {
                    System.out.println("\t" + (j + 1) + " -> " + StudentIndexNum_IssuedBooks[tempSID][j]);
                }
            }
        }
        else System.out.println("This Student is not registered.");
    }
    private void seeAllDetailsOfAllStudents()
    {
        System.out.println();
        if (StudentIndexNum == 0)
        {
            System.out.println("No Student is registered with us.");
        }
        for (short i = 0; i < StudentIndexNum; i++)
        {
            studentDetail(i);
        }
    }
}