import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
public class OnePlayerScoreboard extends JPanel
{
   //stores number of wins, number of games, current win streak, longest win streak, and win/lose percentage
   int wins, games, currentStreak, longestStreak;
   double percentage;
   //displays the scoreboard
   JLabel winLabel, curStrLabel, lonStrLabel;
   JLabel emptyLabel1, emptyLabel2, hangmanLabel;
   public OnePlayerScoreboard()
   {
      setLayout(new GridLayout(2, 3));
      
      wins = 0;
      games = 0;
      percentage = 0;
      currentStreak = 0;
      longestStreak = 0;
      
      //labels for spacing
      emptyLabel1 = new JLabel("");
      add(emptyLabel1);
      
      //adds "HANGMAN" to center
      hangmanLabel = new JLabel("HANGMAN", SwingConstants.CENTER);
      hangmanLabel.setFont(new Font("Serif", Font.BOLD, 20));
      hangmanLabel.setForeground(Color.BLUE);
      add(hangmanLabel);
      
      //labels for spacing
      emptyLabel2 = new JLabel("");
      add(emptyLabel2);
      
      //labels that keep track of wins, the current win streak, and the longest win streak
      winLabel = new JLabel("Wins: 0/0 (-,--%)", SwingConstants.CENTER);
      winLabel.setBackground(Color.YELLOW);
      winLabel.setOpaque(true);
      add(winLabel);
      
      curStrLabel = new JLabel("Current Streak: 0", SwingConstants.CENTER);
      curStrLabel.setBackground(Color.YELLOW);
      curStrLabel.setOpaque(true);
      add(curStrLabel);
      
      lonStrLabel = new JLabel("Longest Streak: 0", SwingConstants.CENTER);
      lonStrLabel.setBackground(Color.YELLOW);
      lonStrLabel.setOpaque(true);
      add(lonStrLabel);
   }
   
   //Increases the number of wins by one and the current streak by one. If the current streak is greater than the
   //longest streak, it sets the longest streak to the current streak
   public void winIncrease()
   {
      wins++;
      currentStreak++;
      if(currentStreak > longestStreak)
      {
         longestStreak++;
      }
   }
   
   //If the player loses, then the currentStreak is set to 0
   public void lostStreak()
   {
      currentStreak = 0;
   }
   
   //Once a game restarts, this updates the scoreboard with the winning percentage, current streak, and longest streak
   public void perUpdate()
   {
      games++;
      DecimalFormat decimal = new DecimalFormat("0.00");
      percentage = (double)wins * 100/games;  
      winLabel.setText("Wins: " + wins + "/" + games + "(" + decimal.format(percentage) + "%)"); 
      curStrLabel.setText("Current Streak: " + currentStreak);	
      lonStrLabel.setText("Longest Streak: " + longestStreak);
   }
}
