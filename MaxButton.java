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
import javax.swing.*;
import java.awt.*;
//CL
public class MaxButton extends JButton
{
  Color color;
  public MaxButton()
  {
    super();
  }
  
  public void player(Color c)
  {
    color = c;
    setForeground(c);
  }
  
  
  public Color getColor()
  {
  return color;
  }
}