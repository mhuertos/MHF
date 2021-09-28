# MHF
voxelkata_28/09
/**
 *
 * @author marcos huertos formariz
 */
import java.util.Scanner;
public class VoxelKata {

    public static final int FINAL_SQUARE = 100;
    public boolean winner = false;
    public int[] usersPos = {1, 1, 1}; //This array contains the boxes where the players are.
    public int[][] snakesSqr ={{16, 49, 46, 62, 64, 99, 95, 92, 89, 74},
                                  {6, 11, 25, 19, 60, 80, 75, 88, 68, 53}};
    
    public int[][] ladderSqr ={{2, 7, 8, 15, 21, 28, 36, 51, 78, 71}, 
                                  {38, 14, 31, 26, 42, 84, 44, 67, 98, 91}};

    public static void main(String[] args) {
        VoxelKata prg = new VoxelKata();
        prg.start();
        // TODO code application logic here
    }
    //The method where we'll do a loop still game's get winner;
    public void start(){
        
        while(!winner){    
            for(int i = 0; i < usersPos.length; i++){
                usrThrows(i);
                int diceOutcome = diceRoll();
                if(diceOutcome + usersPos[i] < FINAL_SQUARE){ //while it's not still on finish box.
                    usersPos[i] += diceOutcome; //Update the number of the box where the player is.
                    System.out.println("Advance to the box: "+usersPos[i]);
                    usersPos[i] = checksSqr(snakesSqr, usersPos[i]); 
                    usersPos[i] = checksSqr(ladderSqr, usersPos[i]);
                }else if(diceOutcome + usersPos[i] == FINAL_SQUARE){
                    System.out.println("Congrats Player "+(usersPos[i]+1)+"! You're the winner!");
                    i = usersPos.length - 1; //I know, so ugly solution to escape of the 'for' loop without "break";
                    winner = true;
                }else{
                    System.out.println("Too much player! Keep on box: "+usersPos[i]+" this turn.");
                }
            }
            System.out.println("---------------------------------");
            System.out.println("\nNext turn Players!");
        }
    }
    
    
    public void presentation(){
        System.out.println("Welcome Players! This is the game: SnakesAndLadders.");
        System.out.println("We hope you like it!");
    }
    
    /**
     * It gives us a random number among the 6 possibilities that a die has
     * @return num 
     */
    public int diceRoll(){
        int num = (int)(Math.floor(Math.random() * 6 + 1));
        System.out.println("Your roll of the dice was: "+num);
        return num;
    }
    
    /**
     * With one integer identifying one player, displays the message that calls for rolling the dice
     * @param player 
     */
    public void usrThrows(int player){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n¡Come on! Player "+(player+1)+" now is your turn. Please click \"Enter\" key to Throw: ");
        sc.nextLine();

    }
    
    /**
     * Modify the position of the token if there is any correspondence between 
     * the player's box and the boxes in which any alteration occurs
     * @param arrayBi could be snakesSqr or ladderSqr
     * @param box
     * @return box
     */
    public int checksSqr(int[][] arrayBi, int box){
        for(int i = 0; i < arrayBi[0].length; i++){
            if(box == arrayBi[0][i]){
                box = arrayBi[1][i];
                boolean snakeOrLadder = arrayBi[0][1] < arrayBi[1][1]; //checks if it's snake's or ladder box.
                showMessNextBox(snakeOrLadder, box);
            }
        }
        return box;   
    }
    
    /**
     * show the message to report which outcome has been find, and the new box which player will comes.
     * @param outcome
     * @param box 
     */
    public void showMessNextBox(boolean outcome, int box){
        if(outcome){
            System.out.println("Oh! You find a ladder, come on up to box: "+box);
        }else{
            System.out.println("Psss...Hey! Watch Out! Snake! Come back to box: "+box);
        }
    }
}
