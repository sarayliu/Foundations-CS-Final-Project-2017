import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
public class HangPanel extends JPanel
{
   private JLabel label;
   private double total;
   private Gameboard1 gameboard1;
   private Gameboard2 gameboard2;
   private Hangman hangman;
   
   public HangPanel(int x, String word, int hangtype)
   {
      if(x == 0) //Quits the game
      {
         System.exit(-1);
      }
      if(x == 1)//One player
      {
         setLayout(new BorderLayout());
         
         JLabel label = new JLabel("One player testing");
         add(label);
         
         gameboard1 = new Gameboard1(word, hangtype);                      // Gameboard1 is for single player
         add(gameboard1, BorderLayout.CENTER);
      }
      if(x == 2)//Two players
      {
         setLayout(new BorderLayout());
      
         gameboard2 = new Gameboard2(word, hangtype);                      // Gameboard2 is for two players
         add(gameboard2, BorderLayout.CENTER);
      }
   }
}
 
 
 
 
