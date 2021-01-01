import javax.swing.JFrame;
import javax.swing.*;
import java.io.*;
import java.util.*;  
 
public class HangDriver
{
   //guessedWord is the word that the user will end up trying to guess
   public static String guessedWord = "";
   public static void main(String[] args) throws Exception
   {  
      //playerMode indicates the type of gameplay
      int playerMode = 0;
      //choice is what the user chooses from the first JOptionPane
      int choice = 0;
      int choice2 = 0;
      int hangman = 0;
      
      Scanner infile = new Scanner(new File("words.txt"));
      int numitems = infile.nextInt();
      ArrayList<String> words = new ArrayList<String>(numitems);
      for(int x = 0; x < numitems; x ++)
      {
         words.add(infile.next());
      }
      
      String message = "What type of game play?";
      message = message + "\n1. SINGE PLAYER";
      message = message + "\n2. DOUBLE PLAYER";
      message = message + "\n3. QUIT";
      choice = Integer.parseInt(JOptionPane.showInputDialog(message));
      switch(choice)
      {
         case 1: playerMode = 1;
            randWord(words, numitems);    //gets a random word for the player to guess
            break;
         case 2: playerMode = 2;
            setWord(words);               //changes settings for double player mode in the method setWord()
            break;
         case 3: 
            JOptionPane.showMessageDialog(null, "Bye-bye!");      //exits the program
            break;
         default: System.out.println("Not a valid selection.");
            break;
      }
      
      String message2 = "What type of hangman?";
      message2 = message2 + "\n1. ORIGINAL: HARD";
      message2 = message2 + "\n2. JOKER: MEDIUM";
      message2 = message2 + "\n3. SUPERMAN: EASY";
      choice2 = Integer.parseInt(JOptionPane.showInputDialog(message2));
      
      if(choice2 == 1)
         hangman = 1;
      else if(choice2 == 2)
         hangman = 2;
      else if(choice2 == 3)
         hangman = 3;
      else
         System.out.println("Not a valid selection.");
   
      //creates the frame
      JFrame frame = new JFrame("Hangman");
      frame.setSize(1800, 1000);
      frame.setLocation(10, 10);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //creates a HangPanel that knows the type of gameplay(playermode) and the word that is being guessed
      frame.setContentPane(new HangPanel(playerMode, guessedWord, hangman));
      frame.setVisible(true);
   }
   
   //Generates a word for two player mode
   public static void setWord(ArrayList<String> words)throws Exception
   {
      //Reads words from a text file and saves in in an ArrayList named 'words'
      String word = JOptionPane.showInputDialog("What word would you like your opponent to guess?" + "\n(-1 to quit)");
      
      //exits if the user enters '-1'
      if(word.equals("-1"))
         System.exit(-1);
      
      //if the word is in the dictionary, then 1 is assigned to the variable existingWord and nothing else happens.
      //if the word is not in the dictionary, then the user is given two options: either use the word for just that
      //round, or add it to the dictionary so that later users will have it in the dictionary.   
      if(words.contains("" + word))
      {
         guessedWord = word;
      }
      else
      {
         String message = "Your word is not in the dictionary";
         message = message + "\n1. Add word to dictionary and continue";
         message = message + "\n2. Continue with word";
         int choice = Integer.parseInt(JOptionPane.showInputDialog(message));
         if(choice == 1)
         {
            words.add(word);  //adds the word into the ArrayList
            System.out.println(words.size());
            System.setOut(new PrintStream(new FileOutputStream("words.txt")));
            for(int k = 0; k < words.size(); k++)
               System.out.println(words.get(k));
            System.out.println(words.size());
         }
         else if(choice == 2)
            guessedWord = word;
         else
            JOptionPane.showMessageDialog(null, "Not a valid selection.");
      }
      guessedWord = word;
   }
   public static void randWord(ArrayList<String> words, int numitems) throws Exception
   {
      int random = (int)(Math.random()*numitems + 1);
      guessedWord = words.get(random);
   }
}
 
 
 
 
 
