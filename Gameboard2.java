import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
 
public class Gameboard2 extends JPanel
{
   private JLabel[] label;
   private static final Color BACKGROUND = new Color(204, 204, 204);
   private BufferedImage myImage;
   private Graphics myBuffer;
   private JLabel[] dashLabel;
   private JLabel blankLabel;
   private Hangman hangman;
   private JLabel[] alphabet;
   private JTextField textbox;
   private JButton enter;
   private JButton reset;
   private String word1;
   private int numRight;
   private JPanel subpanel;
   private JPanel subSubpanel1;
   
   private TwoPlayerScoreboard twoScoreboard;   // Class variable for TwoPlayerScoreboard class
   
   public Gameboard2(String word, int hangtype)    //Gameboard2 constructor
   {
      twoScoreboard = new TwoPlayerScoreboard();   
      add(twoScoreboard, BorderLayout.NORTH);      
      
      word1 = word; //word1 is the word the user has to guess
      myImage =  new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
      myBuffer = myImage.getGraphics();
      myBuffer.setColor(BACKGROUND);
   
      int wordLength = word.length();
     
      setLayout(new BorderLayout());
      
      subpanel = new JPanel();
      subpanel.setLayout(new GridLayout(5, 1));
      add(subpanel, BorderLayout.SOUTH);
     
      subSubpanel1 = new JPanel();
      subSubpanel1.setLayout(new FlowLayout());
      subpanel.add(subSubpanel1);
     
      JPanel subSubpanel3 = new JPanel();
      subSubpanel3.setLayout(new FlowLayout());
      subpanel.add(subSubpanel3);
     
      JPanel northSubpanel = new JPanel();
      northSubpanel.setLayout(new FlowLayout());
      add(northSubpanel, BorderLayout.NORTH);
      
      northSubpanel.add(twoScoreboard);                     // Add TwoPlayerScoreboard to JPanel
   
      String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      alphabet = new JLabel[26];
      for(int x = 0; x < alphabet.length; x++)  //Add the alphabet to the panel
      {
         alphabet[x] = new JLabel("" + abc.charAt(x), SwingConstants.CENTER);
         alphabet[x].setFont(new Font("Serif", Font.PLAIN, 30));
         alphabet[x].setForeground(Color.GRAY);
         alphabet[x].setOpaque(true);
         northSubpanel.add(alphabet[x]);
      }
           
      if(hangtype == 1)  //Decide which hangman to use
         hangman = new OriginalHangman();
      else if(hangtype == 2)
         hangman = new JokerHangman();
      else if(hangtype == 3)
         hangman = new SupermanHangman();
        
      add(hangman, BorderLayout.CENTER);
     
      label = new JLabel[20];
      for(int y = 0; y < label.length; y++)
      {
         label[y] = new JLabel("", SwingConstants.CENTER);
         label[y].setFont(new Font("Serif", Font.BOLD, 30));
         subSubpanel1.add(label[y]);
      }
      for(int y = 0; y < word1.length(); y++)   //Make the correct number of dashes for the user to guess the letters
      {
         label[y].setText("-");
      }
   
      blankLabel = new JLabel("Please guess a letter "+ "\n(-1 to quit) ");
      blankLabel.setFont(new Font("Serif", Font.BOLD, 20));
      subSubpanel3.add(blankLabel); 
     
      textbox = new JTextField("", 10);   //Asks the user to guess a letter
      textbox.setFont(new Font("Serif", Font.BOLD, 30));
      textbox.setHorizontalAlignment(SwingConstants.CENTER);
      subpanel.add(textbox);
     
      enter = new JButton("Enter");       //User clicks enter when guessing a word
      enter.addActionListener(new Listener());
      subpanel.add(enter);
     
      reset = new JButton("Reset");       //Reset the game after a win or loss
      reset.addActionListener(new resetListener());
      reset.setEnabled(false);
      subpanel.add(reset);
   }


   public void showLetter(String guessedWord, JTextField textbox)  //Decides whether letter was right or wrong and takes proper action
   {
      String letter = textbox.getText();
      if(letter.equals("-1"))             //Quit if user enters -1
      {
         System.exit(-1);
      }
      for(int n = 65; n <= 122; n++)
      {
         if(letter.charAt(0) == (char)n)  //Continues if the user enters a single letter
         {
            break;
         }
         if(n == 122 || letter.length() > 1) //Informs the user that they did not enter a letter or entered multiple
         {
            JOptionPane.showMessageDialog(null, "Not a valid selection.");
            textbox.setText("");
            return;
         }
      }
      int correct = 0;     //Number of correct spaces the letter fills
      for(int k = 0; k < word1.length(); k++)
      {
         if(("" + letter.charAt(0)).equalsIgnoreCase("" + guessedWord.charAt(k)))   //Checks to see if letter is part of word
         {
            if(label[k].getText().equalsIgnoreCase("" + guessedWord.charAt(k)))     //Informs the user whether they have already guessed the letter
            {
               JOptionPane.showMessageDialog(null, "You've already guessed this letter!");
               break;
            }
            label[k].setText("" + guessedWord.charAt(k));        //Displays the letter
            correct++;
            numRight++;
         }
      }
      if(correct==0) //If the letter is not part of the word...
      {
         for(int x = 0; x < alphabet.length; x++)
         {
            if(("" + letter.charAt(0)).equalsIgnoreCase(alphabet[x].getText()))  
            {
               if(alphabet[x].getForeground() == Color.GRAY)
               {
                  alphabet[x].setForeground(Color.RED);     //Change the letter to red on the alphabet
                  hangman.addPart();
               }
               else
               {
                  JOptionPane.showMessageDialog(null, "You already guessed this letter; it's wrong.");   //Show message if user already guessed letter
               }
            }
         }
      }
      textbox.setText(""); //Clear the textbox
   }
   public boolean numParts()
   {
      if(hangman.getParts() < hangman.maxParts()) //Confirms that the game is still in play
      {
         return true;
      }
      else
      {
         return false;
      }
   }
   private class Listener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if(numParts() == true) //If game is still in play...
         {
            showLetter(word1, textbox);
            if(numRight == word1.length())   //If the complete word is guessed correctly...
            {
               blankLabel.setText("You win!");  //Allow the user to restart the game
               reset.setEnabled(true);          
               enter.setEnabled(false);
               twoScoreboard.update(); 
               twoScoreboard.winUpdate();       // Update Win streak display
            }
         } 
         else //If user has lost the game...
         {
            blankLabel.setText("You lose.");
            reset.setEnabled(true);             //Allow the user to restart the game
            enter.setEnabled(false);
            twoScoreboard.update(); 
            twoScoreboard.lossUpdate();       // Update lost display
         }
      }
   }
   private class resetListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         hangman.reset();  //Clear the gallows
         reset.setEnabled(false);
         enter.setEnabled(true);
         textbox.setText("");
         numRight = 0;
         for(int x = 0; x < alphabet.length - 1; x++)
         {
            alphabet[x].setForeground(Color.GRAY);    //Clear the alphabet of red letters
         }
         try
         {
            Scanner infile = new Scanner(new File("words.txt"));
            int numitems = infile.nextInt();
            ArrayList<String> words = new ArrayList<String>(numitems);
            for(int x = 0; x < numitems; x ++)
            {
               words.add(infile.next());
            }
            int random = (int)(Math.random()*numitems + 1);
            word1 = words.get(random);
         
            for(int y = 0; y < label.length; y++)
            {
               label[y].setText("");
            }
            for(int y = 0; y < word1.length(); y++)
            {
               label[y].setText("-");
            }
            
                         

            blankLabel.setText("Please guess a letter "+ "\n(-1 to quit) ");
            showLetter(word1, textbox);

         }
         catch(FileNotFoundException ee)
         {
         }
      }
   }
}
 
 
 
 
