/**
 * @author Huandong Chang
 * @version March 2020
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Puzzle
    extends JFrame
    implements ActionListener

{

  private int[][] Total=new int[3][3];
  private int[][] User=new int[3][3];
  private int[][] Machine=new int[3][3];
  private JButton btn00, btn01, btn02, btn10, btn11, btn12, btn20, btn21, btn22;

public static void main(String args[]){
  Puzzle p=new Puzzle();
  p.setPanel();
}

/**
Constructor class
 */
public Puzzle(){
  for (int i = 0; i < Total.length; i++) {
    for (int j = 0; j < Total[i].length; j++) {
      Total[i][j] = 0;
    }
  }

  for (int i = 0; i < User.length; i++) {
    for (int j = 0; j < User[i].length; j++) {
      User[i][j] = 0;
    }
  }

  for (int i = 0; i < Machine.length; i++) {
    for (int j = 0; j < Machine[i].length; j++) {
      User[i][j] = 0;
    }
  }
    btn00 = createButton();
    btn01 = createButton();
    btn02 = createButton();
    btn10 = createButton();
    btn11 = createButton();
    btn12 = createButton();
    btn20 = createButton();
    btn21 = createButton();
    btn22 = createButton();
  }

  /**
   * Create the Panel
   */
	public void setPanel()
	{
		// Set up the grid
		  this.setSize(300,300);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setTitle("Tic-Tac-Toe");
		  JPanel panel1 = new JPanel();
	    panel1.setSize(300,300);
	    panel1.setLayout(new GridLayout(3,3));

		panel1.add(btn00);
		panel1.add(btn01);
		panel1.add(btn02);
		panel1.add(btn10);
		panel1.add(btn11);
		panel1.add(btn12);
		panel1.add(btn20);
		panel1.add(btn21);
		panel1.add(btn22);
	    this.add(panel1);
	    this.setVisible(true);

	}

  /**
   * Instruction for creating a JButton
   */
	private JButton createButton()
	{
		JButton btn = new JButton();
		btn.setPreferredSize(new Dimension(50, 50));
		Font f = new Font("Dialog", Font.PLAIN, 72);
		btn.setFont(f);
		btn.addActionListener(this);
		return btn;
	}

  /**
   * Update the array after a move is made
   *
   * @param e ActionEvent
   */
  public void actionPerformed(ActionEvent e){
    JButton btn = (JButton)e.getSource();

    int row;
    int col;
    if(btn.equals(btn00))
    {
      row=0;
      col=0;
    }
    else if(btn.equals(btn01))
    {
      row=0;
      col=1;
    }
    else if(btn.equals(btn02))
    {
      row=0;
      col=2;
    }
    else if(btn.equals(btn10))
    {
      row=1;
      col=0;
    }
    else if(btn.equals(btn11))
    {
      row=1;
      col=1;
    }
    else if(btn.equals(btn12))
    {
      row=1;
      col=2;
    }
    else if(btn.equals(btn20))
    {
      row=2;
      col=0;
    }
    else if(btn.equals(btn21))
    {
      row=2;
      col=1;
    }
    else
    {
      row=2;
      col=2;
    }



    if(Total[row][col]==1)
      return;
    else
    {
      btn.setText("X");
      Total[row][col]=1;
      User[row][col]=1;
    }

    if(MachineFunctions.win(User))
    {
      JOptionPane.showMessageDialog(null,
        "User Wins!", "Game Over",
        JOptionPane.INFORMATION_MESSAGE);
      resetGame();
    }
    else if(MachineFunctions.full(Total))
    {
      JOptionPane.showMessageDialog(null,
        "Draw!", "Game Over",
        JOptionPane.INFORMATION_MESSAGE);
      resetGame();
    }


    if(!(MachineFunctions.win(User)||MachineFunctions.full(User)))
    {
    //Machine's Move:
    //String machineMove=MachineFunctions.machineMoveSimple(Total);
    //String machineMove=MachineFunctions.machineMoveMedium(Total, User);
    String machineMove=MachineFunctions.machineMoveDifficult(Total, Machine, User);
    switch (machineMove)
		{
			case "00":
				btn00.setText("O");
        Total[0][0]=1;
        Machine[0][0]=1;
				break;
			case "01":
				btn01.setText("O");
        Total[0][1]=1;
        Machine[0][1]=1;
				break;
			case "02":
				btn02.setText("O");
        Total[0][2]=1;
        Machine[0][2]=1;
				break;
			case "10":
				btn10.setText("O");
        Total[1][0]=1;
        Machine[1][0]=1;
				break;
			case "11":
				btn11.setText("O");
        Total[1][1]=1;
        Machine[1][1]=1;
				break;
			case "12":
				btn12.setText("O");
        Total[1][2]=1;
        Machine[1][2]=1;
				break;
			case "20":
				btn20.setText("O");
        Total[2][0]=1;
        Machine[2][0]=1;
				break;
			case "21":
				btn21.setText("O");
        Total[2][1]=1;
        Machine[2][1]=1;
				break;
			case "22":
				btn22.setText("O");
        Total[2][2]=1;
        Machine[2][2]=1;
				break;
		}//switch

    //Check Winning/full
    if(MachineFunctions.win(Machine))
    {
      JOptionPane.showMessageDialog(null,
        "Computer Wins!", "Game Over",
        JOptionPane.INFORMATION_MESSAGE);
      resetGame();
    }
    else if(MachineFunctions.full(Total))
    {
      JOptionPane.showMessageDialog(null,
        "Draw!", "Game Over",
        JOptionPane.INFORMATION_MESSAGE);
      resetGame();
    }
		}
  }

//Reset the game by cleaning the Total, User, and Machine Array
	public void resetGame()
	{
		btn00.setText("");
		btn01.setText("");
		btn02.setText("");
		btn10.setText("");
		btn11.setText("");
		btn12.setText("");
		btn20.setText("");
		btn21.setText("");
		btn22.setText("");

    for (int i = 0; i < Total.length; i++) {
      for (int j = 0; j < Total[i].length; j++) {
        Total[i][j] = 0;
      }
    }

    for (int i = 0; i < User.length; i++) {
      for (int j = 0; j < User[i].length; j++) {
        User[i][j] = 0;
      }
    }

    for (int i = 0; i < Machine.length; i++) {
      for (int j = 0; j < Machine[i].length; j++) {
        Machine[i][j] = 0;
      }
    }
	}
}
