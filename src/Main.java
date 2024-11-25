import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize the scanner for user inputs
        Scanner input = new Scanner(System.in);

        int choice; // What type of dictionary will be used
        // Dictionary Choices:
        System.out.println("\t\t###Welcome to The dictionary App###\n\n");
        System.out.println("Please choose an option: \n");
        System.out.println("1.Create new Empty Dictionary.\n2.Load existing Dictionary.\n3.Create new Dictionary with your own words.");
        System.out.print("Option number: ");

        // Initialize dictionary class using the type the user have chosen
        choice = Integer.parseInt(input.nextLine());
        Dictionary d1 = new Dictionary();
        boolean done = false;
        while (!done) {
            if (choice == 1) { // Empty Dictionary
                d1 = new Dictionary();
                done = true;
            } else if (choice == 2) { // Load existing Dictionary with error handling
                try {
                    System.out.print("Enter your file name: ");
                    File f = new File("C:\\KFUPM\\Term - 231\\ICS 202\\Project\\term231proj\\" + input.nextLine());
                    if (f.exists()) {
                        d1 = new Dictionary(f);
                        done = true;
                    } else
                        throw new FileNotFoundException("File not found, Please choose another.");
                } catch (FileNotFoundException e) {
                    System.out.println(e);
                }
            } else if (choice == 3) { // Dictionary with one word
                System.out.print("Enter your First word in the dictionary: ");
                String word = input.nextLine();
                d1 = new Dictionary(word);
                done = true;
            } else {
                System.out.println("Not a valid option");
            }
        }

        // Dictionary operations
        boolean finish = false;
        while (!finish) {
            // Dictionary menu
            System.out.println("---------------------------------------\nChose an Operation:");
            System.out.println("1.Add word\n2.Find word\n3.Remove word\n4.Search for Similar\n5.Save changes\n6.Exit\n");
            System.out.print("Operation number: ");
            try {
                choice = Integer.parseInt(input.nextLine());
                if (choice == 1) { // Adding new word
                    try {
                        System.out.print("Enter the word: ");
                        String word = input.nextLine();
                        d1.addWord(word);
                    } catch (WordAlreadyExistsException e) {
                        System.out.println(e);
                    }
                } else if (choice == 2) { // Finding a word
                    System.out.print("Enter the word: ");
                    String word = input.nextLine();
                    System.out.println(d1.findWord(word) ? "\nFound word.\n" : "\nWord not found\n");
                } else if (choice == 3) { // Delete a word
                    try {
                        System.out.print("Enter the word: ");
                        String word = input.nextLine();
                        d1.deleteWord(word);
                    } catch (WordNotFoundException e) {
                        System.out.println(e);
                    }
                } else if (choice == 4) { // Find similar words
                    System.out.print("Enter the word: ");
                    String word = input.nextLine();
                    d1.findSimilar(word);
                } else if (choice == 5) { // Save changes to a file
                    System.out.print("Enter file name: ");
                    String name = input.nextLine();
                    System.out.println(d1.saveChanges(name) ? "\nSaved successfully.\n" : "\nCouldn't Save changes\n");
                } else if (choice == 6) { // Exit program
                    System.out.println("\nThank you, Have a nice day!");
                    finish = true;
                } else {
                    System.out.println("\nOperation not found, please choose from 1 - 6.\n");
                }
            } catch (Exception e) {
                System.out.println("Invalid option, please choose from 1 - 6.");
            }
        }
    }
}