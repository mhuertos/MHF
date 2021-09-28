/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voxelkata;

/**
 *
 * @author marcos
 */
import java.util.Scanner;
public class VoxelKata {

    /**
     * @param args the command line arguments
     */
    public static final int FINAL_SQUARE = 100;
    public boolean winner = false;
    public int[] usersPos = {1, 1, 1};
    public int[][] snakesSqr ={{16, 49, 46, 62, 64, 99, 95, 92, 89, 74},
                                  {6, 11, 25, 19, 60, 80, 75, 88, 68, 53}};
    
    public int[][] ladderSqr ={{2, 7, 8, 15, 21, 28, 36, 51, 78, 71}, 
                                  {38, 14, 31, 26, 42, 84, 44, 67, 98, 91}};

    public static void main(String[] args) {
        VoxelKata prg = new VoxelKata();
        prg.start();
        // TODO code application logic here
    }
    public void start(){
        while(!winner){    
            for(int i = 0; i < usersPos.length; i++){
                usrThrows(i);
                int diceOutcome = diceRoll();
                if(diceOutcome + usersPos[i] < FINAL_SQUARE){
                    usersPos[i] += diceOutcome;
                    System.out.println("Advance to the box: "+usersPos[i]);
                    usersPos[i] = checksSqr(snakesSqr, usersPos[i]);
                    usersPos[i] = checksSqr(ladderSqr, usersPos[i]);
                }else if(diceOutcome + usersPos[i] == FINAL_SQUARE){
                    System.out.println("Congrats Player "+(usersPos[i]+1)+"! You're the winner!");
                    i = usersPos.length - 1;
                    winner = true;
                }else{
                    System.out.println("Too much player! Keep on box: "+usersPos[i]+" this turn.");
                }
            }
            System.out.println("---------------------------------");
            System.out.println("\nNext turn Players!");
        }
    }
        
    
    public int diceRoll(){
        int num = (int)(Math.floor(Math.random() * 6 + 1));
        System.out.println("Your roll of the dice was: "+num);
        return num;
    }
    
    public void usrThrows(int player){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n¡Come on! Player "+(player+1)+" now is your turn. Please click \"Enter\" key to Throw: ");
        sc.nextLine();

    }
    
    public int checksSqr(int[][] arrayBi, int box){
        for(int i = 0; i < arrayBi[0].length; i++){
            if(box == arrayBi[0][i]){
                box = arrayBi[1][i];
                boolean snakeOrLadder = arrayBi[0][1] < arrayBi[1][1];
                showMessNextBox(snakeOrLadder, box);
            }
        }
        return box;

        
    }
    public void showMessNextBox(boolean outcome, int box){
        if(outcome){
            System.out.println("Oh! You find a ladder, come on up to box: "+box);
        }else{
            System.out.println("Psss...Hey! Watch Out! Snake! Come back to box: "+box);
        }
    }
    
    public void nextTurn(){
        System.out.println("---------------------------------");
        System.out.println("\nNext turn Players!");
    }
}
