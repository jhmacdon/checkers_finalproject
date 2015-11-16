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
import javax.swing.*;
import java.io.*;
import java.util.*;
public class CheckerPanel extends JPanel
{
 
  //AR
  private MaxButton[][] but; 
 
  private boolean[][] temp;
  
  private boolean[][] isKing;
  String player1, player2;
 
  int temp1 = 0;
  int temp2 = 0;
  int blackPeices; //number of blk pcs
  int redPeices; //number of red pcs
  int moveMade = 0;
  Color tempColor;
  boolean turn = true; //red=true ; black=false
  
  

  public CheckerPanel()
  {
    //LM
    setLayout (new GridLayout (8, 8));
    
    //I/O
    try{
    Scanner fileIn = new Scanner(new File("players.txt"));
    player1 = fileIn.next();
    player2 = fileIn.next();
    }
    catch(IOException ioEx){
      ioEx.printStackTrace();
    }
    but = new MaxButton[8][8]; 
    
    temp = new boolean[8][8];
    isKing = new boolean[8][8];
    redPeices=12;
    blackPeices=12;
   
    //set up board/peices
    int i=0;
    for(int r=0;r<=7;r++)
    {
      for(int c=0;c<=7;c++)
      {
        temp[r][c]=false;
        isKing[r][c]=false;
      }
    }
    
    
    
    for (int r=0;r<=7; r++)
    {
      for (int c=0; c<=7; c++)
      {
        but[r][c]=new MaxButton();
        //LM
        add(but[r][c]);
        but[r][c].addActionListener(new Move());
        
        
        if(i%2==0)
        {
          if(c%2==0)
          {but[r][c].setOpaque(true);
            if(r<3||r>4){
              but[r][c].setText("<html><font size=18>O</font></html>");
              temp[r][c]=true;}
          }
        }
        else
        {
          if(c%2==1)
          {but[r][c].setOpaque(true);
            if(r<3||r>4){
              but[r][c].setText("<html><center><font size=18>O</font></center></html>");
              temp[r][c]=true;}
          }
        }
        if(r<3)
          but[r][c].player(Color.red);
        if(r>4)
          but[r][c].player(Color.black);
      }
      
      
      
      
      i++;
    }

    
    
    setPreferredSize(new Dimension(500, 500));
    setBackground(Color.red);
  } 
  
//LI
  private class Move implements ActionListener
  {

    public void actionPerformed(ActionEvent event)
    {
      for(int r=0; r<=7; r++)
      {
        for (int c=0; c<=7; c++)
        {
          if(event.getSource() == but[r][c])
           
            
            
            
          {
            //check for a win
            if(redPeices==0){
              //game over ; black wins
              System.out.println("black wins!!!");
              //GUI3
                        JFrame title = new JFrame("Winner");
    title.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    GameOverPanelBlack main = new GameOverPanelBlack(player2);
    title.getContentPane().add(main);
    title.pack();
    title.setVisible(true);
    title.setBackground(Color.black);
    title.setResizable(false);
            }
            else if(blackPeices==0){
              //game over ; red wins
              System.out.println("red wins!!!");
              //GUI4
              JFrame title = new JFrame("Winner");
    title.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    GameOverPanelRed main = new GameOverPanelRed(player1);
    title.getContentPane().add(main);
    title.pack();
    title.setVisible(true);
    title.setBackground(Color.black);
    title.setResizable(false);
            }
            else if(moveMade==0) //select peice to move
            {
              if(!temp[r][c])
              {
                System.out.println("Invalid move");
                JOptionPane.showMessageDialog(null, "You have selected an empty square. Select a square with a peice.", "Error", JOptionPane.ERROR_MESSAGE);
              }
              else{
                but[r][c].setText("");
                temp[r][c]=false;
                temp1=r;
                temp2=c;
                moveMade++;
                tempColor=but[r][c].getColor();
              }
            }
            //kings
            else if(isKing[temp1][temp2]==true){
              if(tempColor==Color.black && turn){
                System.out.println("Error code = 12 ; not your turn");
                JOptionPane.showMessageDialog(null, "Not your turn!", "Error", JOptionPane.ERROR_MESSAGE);
                moveMade=0;
                but[temp1][temp2].setText("<html><font size=18>K</font></html>O");
                  temp[temp1][temp2]=true;
                  break;
              }
              if(tempColor==Color.red && !(turn)){
                System.out.println("Error code = 13 ; not your turn");
                JOptionPane.showMessageDialog(null, "Not your turn!", "Error", JOptionPane.ERROR_MESSAGE);
                moveMade=0;
                but[temp1][temp2].setText("<html><font size=18>K</font></html>O");
                  temp[temp1][temp2]=true;
                  break;
              }
              if(r==temp1-2){
               
                if(temp[r][c]==true){
                  System.out.println("Error code = 6 ; cannot cover other piece");
                  JOptionPane.showMessageDialog(null, "Can't cover over another peice", "Error", JOptionPane.ERROR_MESSAGE);
                  moveMade=0;
                  but[temp1][temp2].setText("<html><font size=18>K</font></html>O");
                  temp[temp1][temp2]=true;
                  break;
                }
                if(c==temp2+2){
                  if(tempColor == but[r+1][c-1].getColor()){
                    System.out.println("Error code = 8 ; cannot jump your own peice");
                    JOptionPane.showMessageDialog(null, "Can't jump your own peice", "Error", JOptionPane.ERROR_MESSAGE);
                    moveMade=0;
                  but[temp1][temp2].setText("<html><font size=18>K</font></html>O");
                  temp[temp1][temp2]=true;
                  break;
                  }
              but[r][c].setText("<html><font size=18>K</font></html>O");
              temp[r][c]=true;
              if(but[r+1][c-1].getColor()==Color.red){
                redPeices--;
              }
              else if(but[r+1][c-1].getColor()==Color.black){
                blackPeices--;
              }
              isKing[r][c]=true;
              isKing[temp1][temp2]=false;
              temp[temp1][temp2]=false;
              temp[r+1][c-1]=false;
              but[r+1][c-1].setText("");
              
              moveMade=0;
              turn = !(turn);
              but[r][c].player(tempColor);
                }
                else if(c==temp2-2){
                  if(tempColor == but[r+1][c+1].getColor()){
                    System.out.println("Error code = 9 ; cannot jump your own peice");
                     JOptionPane.showMessageDialog(null, "Can't jump your own peice", "Error", JOptionPane.ERROR_MESSAGE);
                    moveMade=0;
                  but[temp1][temp2].setText("<html><font size=18>K</font></html>O");
                  temp[temp1][temp2]=true;
                  break;
                  }
                   but[r][c].setText("<html><font size=18>K</font></html>O");
              temp[r][c]=true;
              if(but[r+1][c+1].getColor()==Color.red){
                redPeices--;
              }
              else if(but[r+1][c+1].getColor()==Color.black){
                blackPeices--;
              }
              temp[temp1][temp2]=false;
              isKing[r][c]=true;
              isKing[temp1][temp2]=false;
              temp[r+1][c+1]=false;
              but[r+1][c+1].setText("");
              
              moveMade=0;
              turn = !(turn);
              but[r][c].player(tempColor);
                }
                else{System.out.println("Error Code = 4 ; cannot go there");
                 JOptionPane.showMessageDialog(null, "Can't move there", "Error", JOptionPane.ERROR_MESSAGE);}
                
              }
              else if(r==temp1+2){
              
                if(temp[r][c]==true){
                  System.out.println("Error code = 7 ; cannot cover other piece");
                  JOptionPane.showMessageDialog(null, "Can't cover over another peice", "Error", JOptionPane.ERROR_MESSAGE);
                  moveMade=0;
                  but[temp1][temp2].setText("<html><font size=18>K</font></html>O");
                  temp[temp1][temp2]=true;
                  break;
                }
                 if(c==temp2+2){
                   if(tempColor == but[r-1][c-1].getColor()){
                    System.out.println("Error code = 10 ; cannot jump your own peice");
                     JOptionPane.showMessageDialog(null, "Can't jump your own peice", "Error", JOptionPane.ERROR_MESSAGE);
                    moveMade=0;
                  but[temp1][temp2].setText("<html><font size=18>K</font></html>O");
                  temp[temp1][temp2]=true;
                  break;
                  }
              but[r][c].setText("<html><font size=18>K</font></html>O");
              temp[r][c]=true;
              if(but[r-1][c-1].getColor()==Color.red){
                redPeices--;
              }
              else if(but[r-1][c-1].getColor()==Color.black){
                blackPeices--;
              }
              temp[temp1][temp2]=false;
              isKing[r][c]=true;
              isKing[temp1][temp2]=false;
              temp[r-1][c-1]=false;
              but[r-1][c-1].setText("");
              
              moveMade=0;
              turn = !(turn);
              but[r][c].player(tempColor);
                }
                else if(c==temp2-2){
                  if(tempColor == but[r-1][c+1].getColor()){
                    System.out.println("Error code = 11 ; cannot jump your own peice");
                     JOptionPane.showMessageDialog(null, "Can't jump your own peice", "Error", JOptionPane.ERROR_MESSAGE);
                    moveMade=0;
                  but[temp1][temp2].setText("<html><font size=18>K</font></html>O");
                  temp[temp1][temp2]=true;
                  break;
                  }
                   but[r][c].setText("<html><font size=18>K</font></html>O");
              temp[r][c]=true;
               if(but[r-1][c+1].getColor()==Color.red){
                redPeices--;
              }
              else if(but[r-1][c+1].getColor()==Color.black){
                blackPeices--;
              }
              temp[temp1][temp2]=false;
              isKing[r][c]=true;
              isKing[temp1][temp2]=false;
              temp[r-1][c+1]=false;
              but[r-1][c+1].setText("");
             
              moveMade=0;
              turn = !(turn);
              but[r][c].player(tempColor);
                }
                
            }
              else if(r==temp1 && c==temp2){
                temp[r][c]=true;
                but[r][c].setText("<html><font size=18>K</font></html>O");
                isKing[r][c]=true;
                moveMade=0;
                
                
              }
              else if(temp[r][c]||(c!=temp2+1&&c!=temp2-1))
              {System.out.println("Error Code = 1 ; cannot go there ; r= " + r + " c= " + c + " temp1= " + temp1 + " temp2= "+temp2);
              JOptionPane.showMessageDialog(null, "Can't move there", "Error", JOptionPane.ERROR_MESSAGE);}
           
              
              else if((c!=temp2+1&&c!=temp2-1))
              {System.out.println("Error Code = 3 ; cannot go there");
              JOptionPane.showMessageDialog(null, "Can't move there", "Error", JOptionPane.ERROR_MESSAGE);}
              
                else{
              but[r][c].setText("<html><font size=18>K</font></html>O");
              temp[r][c]=true;
              temp[temp1][temp2]=false;
              isKing[r][c]=true;
              isKing[temp1][temp2]=false;
              moveMade=0;
              turn = !(turn);
              but[r][c].player(tempColor);}
               
            }
            //regular pieces
            else
            {
              if(tempColor==Color.black && turn){
                System.out.println("Error code = 12 ; not your turn");
                JOptionPane.showMessageDialog(null, "Not your turn!", "Error", JOptionPane.ERROR_MESSAGE);
                moveMade=0;
                but[temp1][temp2].setText("<html><font size=18>O</font></html>O");
                  temp[temp1][temp2]=true;
                  break;
              }
              if(tempColor==Color.red && !(turn)){
                System.out.println("Error code = 13 ; not your turn");
                JOptionPane.showMessageDialog(null, "Not your turn!", "Error", JOptionPane.ERROR_MESSAGE);
                moveMade=0;
                but[temp1][temp2].setText("<html><font size=18>O</font></html>O");
                  temp[temp1][temp2]=true;
                  break;
              }
              if(r==temp1-2){
               
                if(temp[r][c]==true){
                  System.out.println("Error code = 6 ; cannot cover other piece");     
                  JOptionPane.showMessageDialog(null, "Can't cover over another peice", "Error", JOptionPane.ERROR_MESSAGE);
                  moveMade=0;
                  but[temp1][temp2].setText("<html><font size=18>O</font></html>O");
                  temp[temp1][temp2]=true;
                  break;
                }
                if(c==temp2+2){
                  if(tempColor == but[r+1][c-1].getColor()){
                    System.out.println("Error code = 8 ; cannot jump your own peice");
                     JOptionPane.showMessageDialog(null, "Can't jump your own peice", "Error", JOptionPane.ERROR_MESSAGE);
                    moveMade=0;
                  but[temp1][temp2].setText("<html><font size=18>O</font></html>O");
                  temp[temp1][temp2]=true;
                  break;
                  }
              but[r][c].setText("<html><font size=18>O</font></html>O");
              temp[r][c]=true;
               if(but[r+1][c-1].getColor()==Color.red){
                redPeices--;
              }
              else if(but[r+1][c-1].getColor()==Color.black){
                blackPeices--;
              }
              temp[temp1][temp2]=false;
              temp[r+1][c-1]=false;
              but[r+1][c-1].setText("");
              moveMade=0;
              turn = !(turn);
              but[r][c].player(tempColor);
                }
                else if(c==temp2-2){
                  if(tempColor == but[r+1][c+1].getColor()){
                    System.out.println("Error code = 9 ; cannot jump your own peice");
                     JOptionPane.showMessageDialog(null, "Can't jump your own peice", "Error", JOptionPane.ERROR_MESSAGE);
                    moveMade=0;
                  but[temp1][temp2].setText("<html><font size=18>O</font></html>O");
                  temp[temp1][temp2]=true;
                  break;
                  }
                   but[r][c].setText("<html><font size=18>O</font></html>O");
              temp[r][c]=true;
               if(but[r+1][c+1].getColor()==Color.red){
                redPeices--;
              }
              else if(but[r+1][c+1].getColor()==Color.black){
                blackPeices--;
              }
              temp[temp1][temp2]=false;
              temp[r+1][c+1]=false;
              but[r+1][c+1].setText("");
              moveMade=0;
              turn = !(turn);
              but[r][c].player(tempColor);
                }
                else{System.out.println("Error Code = 4 ; cannot go there");
                JOptionPane.showMessageDialog(null, "Can't move there", "Error", JOptionPane.ERROR_MESSAGE);}
                
              }
              else if(r==temp1+2){
               
                if(temp[r][c]==true){
                  System.out.println("Error code = 7 ; cannot cover other piece");
                  JOptionPane.showMessageDialog(null, "Can't cover over another peice", "Error", JOptionPane.ERROR_MESSAGE);
                  moveMade=0;
                  but[temp1][temp2].setText("<html><font size=18>O</font></html>O");
                  temp[temp1][temp2]=true;
                  break;
                }
                 if(c==temp2+2){
                   if(tempColor == but[r-1][c-1].getColor()){
                    System.out.println("Error code = 10 ; cannot jump your own peice");
                     JOptionPane.showMessageDialog(null, "Can't jump your own peice", "Error", JOptionPane.ERROR_MESSAGE);
                    moveMade=0;
                  but[temp1][temp2].setText("<html><font size=18>O</font></html>O");
                  temp[temp1][temp2]=true;
                  break;
                  }
              but[r][c].setText("<html><font size=18>O</font></html>O");
              temp[r][c]=true;
               if(but[r-1][c-1].getColor()==Color.red){
                redPeices--;
              }
              else if(but[r-1][c-1].getColor()==Color.black){
                blackPeices--;
              }
              temp[temp1][temp2]=false;
              temp[r-1][c-1]=false;
              but[r-1][c-1].setText("");
              moveMade=0;
              turn = !(turn);
              but[r][c].player(tempColor);
                }
                else if(c==temp2-2){
                  if(tempColor == but[r-1][c+1].getColor()){
                    System.out.println("Error code = 11 ; cannot jump your own peice");
                     JOptionPane.showMessageDialog(null, "Can't jump your own peice", "Error", JOptionPane.ERROR_MESSAGE);
                    moveMade=0;
                  but[temp1][temp2].setText("<html><font size=18>O</font></html>O");
                  temp[temp1][temp2]=true;
                  break;
                  }
                   but[r][c].setText("<html><font size=18>O</font></html>O");
              temp[r][c]=true;
               if(but[r-1][c+1].getColor()==Color.red){
                redPeices--;
              }
              else if(but[r-1][c+1].getColor()==Color.black){
                blackPeices--;
              }
              temp[temp1][temp2]=false;
              temp[r-1][c+1]=false;
              but[r-1][c+1].setText("");
              moveMade=0;
              turn = !(turn);
              but[r][c].player(tempColor);
                }
                else{System.out.println("Error Code = 5 ; cannot go there");
                JOptionPane.showMessageDialog(null, "Can't move there", "Error", JOptionPane.ERROR_MESSAGE);}
              }
              else if(r==temp1 && c==temp2){
                temp[r][c]=true;
                but[r][c].setText("<html><font size=18>O</font></html>O");
                moveMade=0;
                
                
              }
              else if(temp[r][c]||(c!=temp2+1&&c!=temp2-1))
              {System.out.println("Error Code = 1 ; cannot go there ; r= " + r + " c= " + c + " temp1= " + temp1 + " temp2= "+temp2);
              JOptionPane.showMessageDialog(null, "Can't move there", "Error", JOptionPane.ERROR_MESSAGE);}
              
              else if(((but[temp1][temp2].getColor()==(Color.red)&&r!=temp1+1))||(but[temp1][temp2].getColor()==(Color.black)&&r!=temp1-1))
              {System.out.println("Error Code = 2 ; cannot move backwards");
              JOptionPane.showMessageDialog(null, "Can't move backwards with a regular peice", "Error", JOptionPane.ERROR_MESSAGE);}
              else if((c!=temp2+1&&c!=temp2-1))
              {System.out.println("Error Code = 3 ; cannot go there");
              JOptionPane.showMessageDialog(null, "Can't move there", "Error", JOptionPane.ERROR_MESSAGE);}
              
                else{
              but[r][c].setText("<html><font size=18>O</font></html>O");
              temp[r][c]=true;
              temp[temp1][temp2]=false;
              moveMade=0;
              turn = !(turn);
              but[r][c].player(tempColor);}
               //make kings if the peices reach the opposite side of the board 
                if(but[r][c].getColor()==Color.black && r==0){
                  System.out.println("King me! Black King");
                  JOptionPane.showMessageDialog(null, "Black King created!", "King me!", JOptionPane.ERROR_MESSAGE);
                  isKing[r][c]=true;
                  System.out.println(blackPeices + " " + redPeices);
                  but[r][c].setText("<html><font size=18>K</font></html>O");
                }
                if(but[r][c].getColor()==Color.red && r==7){
                  System.out.println("King me! Red King");
                  JOptionPane.showMessageDialog(null, "Red King created!", "King me!", JOptionPane.ERROR_MESSAGE);
                  isKing[r][c]=true;
                  System.out.println(blackPeices + " " + redPeices);
                  but[r][c].setText("<html><font size=18>K</font></html>O");
                }
                  
            }
            
          }
        }
      }
      
    }  
  } 
  
} 
