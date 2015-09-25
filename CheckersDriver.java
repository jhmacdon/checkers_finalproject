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
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.awt.*;
import javax.swing.*;


import javax.swing.JFrame;


public class CheckersDriver
{ 
  public static void main(String[] args)
  {
    //GUI1
    JFrame title = new JFrame("Checkers");
    title.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    TitlePanel main = new TitlePanel();
    title.getContentPane().add(main);
    title.pack();
    title.setVisible(true);
    title.setBackground(Color.black);
    title.setResizable(false);
    
  }
}