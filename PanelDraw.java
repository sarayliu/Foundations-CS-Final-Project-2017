//Sara Liu May 16th

import javax.swing.*; //not needed
import java.awt.*;
public class PanelDraw extends JPanel //HangDriver is 750 by 550
{
   public void paintHead(Graphics g)
   {
      g.setColor(Color.BLACK);
      g.drawOval(200, 300, 30, 30);
   }
   public void paintBody(Graphics g)
   {
      g.setColor(Color.BLACK);
      g.drawLine(215, 330, 215, 380);
   }
   public void paintLeftArm(Graphics g)
   {
      g.setColor(Color.BLACK);
      g.drawLine(215, 340, 200, 350);
   }
   public void paintRightArm(Graphics g)
   {
      g.setColor(Color.BLACK);
      g.drawLine(215, 340, 230, 350);
   }
   public void paintLeftLeg(Graphics g)
   {
      g.setColor(Color.BLACK);
      g.drawLine(215, 380, 200, 400);
   }
   public void paintRightLeg(Graphics g)
   {
      g.setColor(Color.BLACK);
      g.drawLine(215, 380, 230, 400);
   }
}