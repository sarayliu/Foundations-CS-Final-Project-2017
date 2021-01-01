import javax.swing.*;
import java.awt.*;
public class TwoPlayerScoreboard extends JPanel
{
   //stores the number of wins and losses for both players
   private JLabel label1, label2, label3, label4, label5;
   private JLabel loss1a, loss1b, loss2a, loss2b;
   private int count1, count2, turn;
   private int loss1, loss2;
   public TwoPlayerScoreboard()
   {
      setLayout(new GridLayout(1, 9));
      count1 = count2 = turn = 0;
      loss1 = loss2 = 0;

      //labels that keep track of the wins and losses for Player 1      
      label1 = new JLabel("1's wins: ", SwingConstants.RIGHT);
      label1.setBackground(Color.YELLOW);
      label1.setOpaque(true);
      add(label1);
      
      label2 = new JLabel(""+count1);
      label2.setHorizontalAlignment(SwingConstants.LEFT); 
      label2.setBackground(Color.YELLOW); 
      label2.setForeground(Color.GREEN);
      label2.setOpaque(true);   
      add(label2);
      
      loss1a = new JLabel("1's losses: ", SwingConstants.RIGHT);
      loss1a.setBackground(Color.YELLOW);
      loss1a.setOpaque(true);
      add(loss1a); 
      
      loss1b = new JLabel(""+loss1);
      loss1b.setHorizontalAlignment(SwingConstants.LEFT);
      loss1b.setBackground(Color.YELLOW);
      loss1b.setForeground(Color.RED);
      loss1b.setOpaque(true);
      add(loss1b);

      //adds "HANGMAN" to center      
      label3 = new JLabel("HANGMAN", SwingConstants.CENTER); 
      label3.setFont(new Font("Serif", Font.BOLD, 20));
      label3.setForeground(Color.BLUE);
      add(label3);

      //labels that keep track of the wins and losses for Player 2      
      label4 = new JLabel("2's wins: ", SwingConstants.RIGHT);
      label4.setOpaque(true);
      add(label4);
     
      label5 = new JLabel(""+count2);
      label5.setHorizontalAlignment(SwingConstants.LEFT);
      label5.setForeground(Color.GREEN);
      label5.setOpaque(true);
      add(label5);
      
      loss2a = new JLabel("2's losses: ", SwingConstants.RIGHT);
      loss2a.setOpaque(true);
      add(loss2a); 
      
      loss2b = new JLabel(""+loss2);
      loss2b.setHorizontalAlignment(SwingConstants.LEFT);
      loss2b.setForeground(Color.RED);
      loss2b.setOpaque(true);
      add(loss2b);
   }
   
   //Switches the yellow background back and forth to show whose turn it is
   public void update()
   {
      if(turn % 2 == 0)
      {
         label4.setBackground(Color.YELLOW);
         label5.setBackground(Color.YELLOW);
         loss2a.setBackground(Color.YELLOW);
         loss2b.setBackground(Color.YELLOW);
         label1.setBackground(Color.LIGHT_GRAY);
         label2.setBackground(Color.LIGHT_GRAY);
         loss1a.setBackground(Color.LIGHT_GRAY);
         loss1b.setBackground(Color.LIGHT_GRAY);
         turn++;
      }
      else
      {
         label1.setBackground(Color.YELLOW);
         label2.setBackground(Color.YELLOW);
         loss1a.setBackground(Color.YELLOW);
         loss1b.setBackground(Color.YELLOW);
         label4.setBackground(Color.LIGHT_GRAY);
         label5.setBackground(Color.LIGHT_GRAY);
         loss2a.setBackground(Color.LIGHT_GRAY);
         loss2b.setBackground(Color.LIGHT_GRAY);
         turn++;
      }
   } 
   
   //updates which player won and displays it  
   public void winUpdate()
   {
      if(turn % 2 == 1)
      {
         count2++;
         label5.setText(""+count2);
      }
      else
      {
         count1++;
         label2.setText(""+count1);
      }
   } 
 
   //updates which player lost and displays it       
   public void lossUpdate()
   {
      if(turn % 2 == 1)
      {
         loss2++;
         loss2b.setText(""+loss2);
      }
      else
      {
         loss1++;
         loss1b.setText(""+loss1);
      }
   }     
}
