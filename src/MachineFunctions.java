/**
 * @author Huandong Chang
 * @version March 2020
 */
import java.util.*;


public class MachineFunctions{

  /**
   * Check whether the User or the Machine has won or not
   *
   * @param A a 2-D array
   * @return a boolean, whether there is already a winner or not
   */
  public static Boolean win(int[][] A){
    int sum1=0;
    int sum2=0;
    int sum3=0;
    int sum4=0;

    for (int i=0;i<A.length;i++){
      for (int j=0;j<A.length;j++){
        sum1+=A[i][j];
        sum2+=A[j][i];
      }
      if(sum1==A.length||sum2==A.length)
      {
        return true;
      }
      sum1=0;
      sum2=0;
    }

    for(int i=0;i<A.length;i++){
      sum3+=A[i][i];
      sum4+=A[A.length-i-1][i];
    }
    if(sum3==A.length||sum4==A.length)
    {
        return true;
    }
    return false;
  }

  public static Boolean full(int[][] A){
    int sum=0;
    for (int i=0;i<A.length;i++){
      for (int j=0;j<A.length;j++){
        sum+=A[i][j];
      }
    }

    if (sum==A.length*A.length)
      return true;
    else
      return false;
  }

  /**
   * The easiest move: use LFSR random number generator to randomly choose a position
   *
   * @param Total a 2-D array
   * @return a string, the position of the move(eg: "11")
   */

  public static String machineMoveSimple(int[][] Total){
    int[] seed=new int[]{0,0,1,0,1,1,0,1};
   int[] taps=new int[]{5,3,2,0};
   LFSR2 obj=new LFSR2(seed,taps);
   int num1=obj.generateNext()%3;
   int num2=obj.generateNext()%3;

   if(Total[num1][num2]==0)
     return num1+""+num2;
  else{
    for (int i=0;i<Total.length;i++){
      for (int j=0;j<Total.length;j++){
        if(Total[i][j]==0)
          return i+""+j;
      }
    }
    return "NotFound";
  }
  }

  /**
   * The medium level: Block the user if they will win in the next step
   *
   * @param Total a 2-D array
   * @param User a 2-D array
   * @return a string, the position of the move(eg: "11")
   */

  public static String machineMoveMedium(int[][] Total, int[][] User){
    if(block(User,Total).equals("NothingToBlock"))
      return machineMoveSimple(Total);
    else
        return block(User, Total);
  }

  /**
   * The difficult level: Win if the Machine can win and block the user if they will win in the next step
   *
   * @param Total a 2-D array
  * @param Machine a 2-D array
   * @param User a 2-D array
   * @return a string, the position of the move(eg: "11")
   */

  public static String machineMoveDifficult(int[][] Total, int[][] Machine, int[][] User){
    String user=block(User,Total);
    String machine=block(Machine,Total);

    //There is a move to win:
    if(!(machine.equals("NothingToBlock")))
      return machine;

    //There requires a move to block the user:
    else if(!(user.equals("NothingToBlock")))
      return user;

    else
        return machineMoveSimple(Total);
  }

  /**
   * Check where to block to prevent the user from winning
   *
   * @param Total a 2-D array
   * @param A a 2-D array
   * @return a string, the position of the move(eg: "11")
   */
  public static String block(int[][] A, int[][] Total){
    int sum1=0;
    int track1=0;
    int sum2=0;
    int track2=0;
    int sum3=0;
    int track3=0;
    int sum4=0;
    int track4=0;

//Horizontal and Vertical
    for (int i=0;i<A.length;i++){
      for (int j=0;j<A.length;j++){
        sum1+=A[i][j];
        if(A[i][j]==0)
          track1=j;
        sum2+=A[j][i];
        if(A[j][i]==0)
          track2=j;
      }
      if(sum1==A.length-1&&Total[i][track1]==0)
        return i+""+track1;
      if(sum2==A.length-1&&Total[track2][i]==0)
        return track2+""+i;
      sum1=0;
      sum2=0;
    }
//Diagonal
    for(int i=0;i<A.length;i++){
      sum3+=A[i][i];
      if(A[i][i]==0)
        track3=i;
      sum4+=A[A.length-i-1][i];
      if(A[A.length-i-1][i]==0)
        track4=i;
    }
    if(sum3==A.length-1&&Total[track3][track3]==0)
      return track3+""+track3;
    if(sum4==A.length-1&&Total[A.length-track4-1][track4]==0)
      return (A.length-track4-1)+""+track4;


    //If nothing to Block
      return "NothingToBlock";
  }


}
