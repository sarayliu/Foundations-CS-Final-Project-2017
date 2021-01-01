import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*; 
 
public class Gameboard extends JPanel 
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
   public Gameboard(String word, int hangtype)
   {
      word1 = word;
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
   
      String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      alphabet = new JLabel[26];
      for(int x = 0; x < alphabet.length; x++)
      {
         alphabet[x] = new JLabel("" + abc.charAt(x), SwingConstants.CENTER);
         alphabet[x].setFont(new Font("Serif", Font.PLAIN, 30));
         alphabet[x].setForeground(Color.GRAY);
         alphabet[x].setOpaque(true);
         northSubpanel.add(alphabet[x]);
      }
      
      if(hangtype == 1)
         hangman = new OriginalHangman();
      else if(hangtype == 2)
         hangman = new JokerHangman();
      else if(hangtype == 3)
         hangman = new SupermanHangman();
         
      add(hangman, BorderLayout.CENTER);
      
      label = new JLabel[wordLength];
      for(int y = 0; y < word.length(); y++)
      {
         label[y] = new JLabel("-", SwingConstants.CENTER);
         label[y].setFont(new Font("Serif", Font.BOLD, 30));
         subSubpanel1.add(label[y]);
      }
      
      blankLabel = new JLabel("Please guess a letter "+ "\n(-1 to quit) " + word1);
      blankLabel.setFont(new Font("Serif", Font.BOLD, 20));
      subSubpanel3.add(blankLabel);  
      
      textbox = new JTextField("", 10);
      textbox.setFont(new Font("Serif", Font.BOLD, 30));
      textbox.setHorizontalAlignment(SwingConstants.CENTER);
      subpanel.add(textbox);
      
      enter = new JButton("Enter");
      enter.addActionListener(new Listener());
      subpanel.add(enter);
      
      reset = new JButton("Reset");
      reset.addActionListener(new resetListener());
      reset.setEnabled(false);
      subpanel.add(reset);
   }
   public void showLetter(String guessedWord, JTextField textbox) 
   {
      String letter = textbox.getText();
      if(letter.equals("-1"))
      {
         System.exit(-1);
      }
      for(int n = 65; n <= 122; n++)
      {
         if(letter.charAt(0) == (char)n)
         {
            break;
         }
         if(n == 122 || letter.length() > 1)
         {
            JOptionPane.showMessageDialog(null, "Not a valid selection.");
            textbox.setText(""); 
            return;
         }
      }
      int correct = 0;
      for(int k = 0; k < label.length; k++)
      {
         if(("" + letter.charAt(0)).equalsIgnoreCase("" + guessedWord.charAt(k)))
         {
            label[k].setText("" + guessedWord.charAt(k));
            correct++;
            numRight++;
         }
      }
      if(correct==0)
      {
         for(int x = 0; x < alphabet.length; x++)
         {
            if(("" + letter.charAt(0)).equalsIgnoreCase(alphabet[x].getText()))
            {
               if(alphabet[x].getForeground() == Color.GRAY)
               {
                  alphabet[x].setForeground(Color.RED);
                  hangman.addPart();
               }
               else
               {
                  JOptionPane.showMessageDialog(null, "You already guessed this letter; it's wrong.");
               }
            }
         }
      }
      textbox.setText(""); 
   }
   public boolean numParts()
   {
      if(hangman.getParts() < hangman.maxParts())
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
         if(numParts() == true)
         {
            showLetter(word1, textbox);
            if(numRight == word1.length())
            {
               blankLabel.setText("You win!");
               reset.setEnabled(true);
               enter.setEnabled(false);
            }
         }  
         else
         {
            blankLabel.setText("You lose.");
            reset.setEnabled(true);
            enter.setEnabled(false);
         }
      }
   }
   private class resetListener implements ActionListener //doesn't work right now
   {
      public void actionPerformed(ActionEvent e) 
      {
         hangman.reset();
         reset.setEnabled(false);
         enter.setEnabled(true);
         for(int x = 0; x < alphabet.length - 1; x++)
         {
            alphabet[x].setForeground(Color.GRAY);
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
            for(int y = 0; y < word1.length(); y++)
            {
               label[y].setText("");
            }
            
            
           //  JLabel[] label1 = new JLabel[word1.length()];
//             for(int y = 0; y < word1.length(); y++)
//             {
//                label1[y] = new JLabel("-", SwingConstants.CENTER);
//                label1[y].setFont(new Font("Serif", Font.BOLD, 30));
//                subSubpanel1.add(label1[y]);
//             }
            blankLabel.setText("Please guess a letter "+ "\n(-1 to quit) " + word1);
            showLetter(word1, textbox);
         
         }
         catch(FileNotFoundException ee)
         {
         }
         numRight = 0;
      }
   }
}