import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class Dictionary {
    private final AVLTree<String> words; // The Main AVL tree storing the data

    public Dictionary(File file) { // Constructor with file loader
        words = new AVLTree<>();
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                words.insertAVL(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
            }
    }

    public Dictionary() { // Empty constructor
        words = new AVLTree<>();
    }

    public Dictionary(String s) { // Constructor for one word dictionary
        words = new AVLTree<>();
        words.insertAVL(s);
    }

    public void addWord(String s) throws WordAlreadyExistsException { // Adding new word function
        if (words.search(s)) // Checking if the word exist before deletion
            throw new WordAlreadyExistsException("Word already exist.");
        else {
            words.insertAVL(s);
            System.out.println("\nWord was added.\n"); // Success message
        }
    }

    public boolean findWord(String s) { // Finding a word using search function from the AVL class
        return words.search(s);
    }

    public boolean saveChanges(String name) { // Saving changes to a new file
        try {
            FileWriter saveFile = new FileWriter(name);
            // Tree BFS traversal
            if(words.getRoot() == null)
                return false;
            LabQueue<BTNode> queue = new LabQueue<>();
            BTNode node = words.getRoot();
            queue.enqueue(node);
            while(! queue.isEmpty()){
                node = queue.dequeue();
                saveFile.write(node.data + "\n");
                if(node.left != null)
                    queue.enqueue(node.left);
                if(node.right != null)
                    queue.enqueue(node.right);
            }
            saveFile.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public void deleteWord(String s) throws WordNotFoundException { // Deleting a word using BST delete by copying
        if (words.search(s)) {
            words.deleteAVL(s);
            System.out.println("\nWord was deleted successfully.\n");
        }else
            throw new WordNotFoundException("Word was not found.");
    }

    public void findSimilar (String s) { // Find similar word
        // This array is used to check different variation of the word
        String[] alphabit = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t" , "u", "v", "w", "x", "y", "z"};
        StringBuilder similar = new StringBuilder(); // the returned words variable
        System.out.println("\nSimilar words: ");
        // A 2d array holding all proper suffex and prefex of the word
        String[][] suf = countSamePrefixSuffix(s, s.length()); // A Helper function that extracts all proper suffex and prefex of the word
        // Looping through the array to check the existing similarities
        for (int i = 0; i < suf[0].length; i++) {
            String word1 = suf[1][suf[1].length-1-i] + suf[0][i];
            if (words.search(word1)) // words that are shorter but with the same letters
                similar.append(word1).append(", ");
            for (String value : alphabit) {
                if (value.compareTo(String.valueOf(s.charAt(s.length() - 1 - i))) != 0) {
                    String word2 = suf[1][suf[1].length - 1 - i] + value + suf[0][i];
                    if (words.search(word2))
                        similar.append(word2).append(", "); // words with only 1 different letter
                }
            }
        }
        if (similar.length() == 0)
            System.out.println("\nNo similar words was found.");
        else
            System.out.println(similar.toString().substring(0, similar.length()-2) + ".");
    }

    public String[][] countSamePrefixSuffix(String s, int n) // As mentioned above in the call
    {
        String[] suffixL = new String[n];
        String[] prefixL = new String[n];
            for (int i = 0; i < s.length(); i++) {
                String prefix = s.substring(0,i);
                String suffix = s.substring(s.length() - i);
                suffixL[i] = suffix;
                prefixL[i] = prefix;
            }
        return new String[][]{suffixL, prefixL};
    }
}
