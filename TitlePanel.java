/******************************************
*                                         
* Official Name:  Maxwell Burggraf       
*                                         
* Nickname:  Max                      
*                                         
* E-mail:  mlburggr@syr.edu             
*                                         
* Final Project:  2-player checkers game              
*                                         
* Compiler:  drJava on a pc            
*                                         
* Date:  Dec. 2, 2013              
*                                         
*******************************************/
import java.awt.*;
import java.awt.event.*;
import javax. swing. *;
import java.util.*;
import java.io.*;
public class TitlePanel extends JPanel {
  private boolean playPressed=false;
  public TitlePanel() {
    
    setLayout(null);
    setPreferredSize(new Dimension(500, 500));
    setBackground(Color.blue);
    //DR
    JLabel title = new JLabel("");
    title.setText("<html><font color='white'><font size=30>Checkers</font></html>");
    title.setLocation(175,0);
    title.setSize(200,200);
    JLabel credit = new JLabel("<html><font color='white'>Created by Max Burggraf</font></html>");
    credit.setLocation(200,0);
    credit.setSize(100,300);
    JButton play = new JButton("Play");
    play.setLocation(220,350);
    play.setSize(100,50);
    add(title);
    add(credit);
    play.addActionListener(new ButtonListener());
    add(play);
    
  }
  //LI
   private class ButtonListener implements ActionListener
  {

    public void actionPerformed(ActionEvent event)
    {
      setVisible(false);
    //GUI2
    JFrame title = new JFrame("Checkers");
    title.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    CheckerPanel main = new CheckerPanel();
    title.getContentPane().add(main);
    title.pack();
    title.setVisible(true);
    title.setBackground(Color.black);
    title.setResizable(false);
    }
  }
   public boolean isPressed(){
     return playPressed;
   }
}