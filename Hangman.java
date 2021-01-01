import javax.swing.*;
import java.awt.*;
 
   /*****************************************************************
   * A Hangman is an abstract class that extends JPanel. It maintains information about its body parts.
   * A Hangman knows how to return its body parts, set its number of body parts, and add different
   * parts to its body.
   ****************************************************************/
 
public abstract class Hangman extends JPanel
{
   private int parts;
   private int maxParts;
   private JLabel label;
   /*************************************************************
   * Constructs a hangman with zero parts
   **************************************************************/
   public Hangman()
   {
      parts = 0;
      maxParts = 0;
    
      label = new JLabel();
      label.setIcon(new ImageIcon("No parts.jpg"));
      add(label);
   }
 
   /***************************************************************
   * Returns the number of parts Hangman has
  * @return    parts
   **************************************************************/
   public abstract int getParts();
 
   /***************************************************************
  * Sets the number of parts to the input number.
   * @param x   assigns x to parts
   **************************************************************/
   public abstract void setParts(int x);
 
   /***************************************************************
   * Resets the number of body parts to zero.
   **************************************************************/
   public abstract void reset();
 
   /***************************************************************
   * Adds new parts to the Hangman body
   **************************************************************/
   public abstract void addPart();
 
    /***************************************************************
   * Returns the maximum number of parts that Hangman can have
   * @return   maxParts
   **************************************************************/
   public abstract int maxParts();
}
 
 
 
 
 
 
 
 
 
 
