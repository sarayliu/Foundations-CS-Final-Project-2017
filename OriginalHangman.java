import javax.swing.*;
import java.awt.*;
 
   /*****************************************************************
   * An OriginalHangman is Hangman that maintains information about its body parts.
   * An OriginalHangman knows how to return its body parts, set its number of body parts, and add different
   * parts to its body.
  
   * @author
   * @version
   ****************************************************************/
public class OriginalHangman extends Hangman
{
   private int parts; 
   private int maxParts;
   private JLabel label;
   public OriginalHangman()
   {
      parts = 0;
    
      maxParts = 6;
    
      label = new JLabel();
      label.setIcon(new ImageIcon("No parts.png"));
      add(label);
   }
 
   /***************************************************************
   * Adds new parts to the OriginalHangman body, not exceeding 6 parts.
   **************************************************************/
   public void addPart()
   {
     setParts(getParts() + 1);
    
      switch(getParts())
      {
         case 1: label.setIcon(new ImageIcon("Head.png"));
            break;
         case 2: label.setIcon(new ImageIcon("Body.png"));
            break;
         case 3: label.setIcon(new ImageIcon("Left arm.png"));
            break;
         case 4: label.setIcon(new ImageIcon("Right arm.png"));
            break;
         case 5: label.setIcon(new ImageIcon("Left leg.png"));
            break;
         case 6: label.setIcon(new ImageIcon("Right leg.png"));
            break;
      }
   }
  
   /***************************************************************
   * Returns the maximum number of parts OriginalHangman has
   * @return    masParts
   **************************************************************/
   public int maxParts()
   {
      return maxParts;
   }
  
   /***************************************************************
   * Returns the number of parts OriginalHangman has
   * @return    parts
   **************************************************************/
   public int getParts()
  {
      return parts;
   }
  
   /***************************************************************
   * Resets the number of body parts to zero.
   **************************************************************/
   public void reset()
   {
      setParts(0);
     label.setIcon(new ImageIcon("No parts.png"));
   }
  
   /***************************************************************
  * Sets the number of parts to the input number.
   * @param x   assigns x to parts
   **************************************************************/
   public void setParts(int x)
   {
      parts = x;
  }
}
 
 
 
 
 
 
 
 
 
 
