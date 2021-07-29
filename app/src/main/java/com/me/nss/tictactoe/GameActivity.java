package com.me.nss.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    int gameState = 1;
    int activePlayer =1;
    boolean flag = false;
    HashSet<Integer> player1 = new HashSet<>();
    HashSet<Integer> player2 = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameState = 1;//1--play   2--GameOver    3 -- Draw
        Random random = new Random();
        activePlayer = random.nextInt(2) + 1;
        if(activePlayer==2){
            Autoplay();
        }
    }
    public void GameBoardClick(View view) {
        ImageView selectedImage = (ImageView) view ;
        int selectedBlock = 0;
        switch (selectedImage.getId()){
            case R.id.rc11 : selectedBlock = 1; break;
            case R.id.rc12 : selectedBlock = 2; break;
            case R.id.rc13 : selectedBlock = 3; break;

            case R.id.rc21 : selectedBlock = 4; break;
            case R.id.rc22 : selectedBlock = 5; break;
            case R.id.rc23 : selectedBlock = 6; break;

            case R.id.rc31 : selectedBlock = 7; break;
            case R.id.rc32 : selectedBlock = 8; break;
            case R.id.rc33 : selectedBlock = 9; break;
        }

        PlayGame(selectedImage,selectedBlock);
    }
    private void Autoplay() {
        int selectedBlock = 0;
        if(player1.size()==0 && player2.size()==0){
            Random random = new Random();
            int randomIndex = random.nextInt(9);
            selectedBlock = randomIndex+1;
        }else{
            selectedBlock = findBestMove();
        }
        ImageView selectedImage = (ImageView) findViewById(R.id.rc11);
        switch (selectedBlock){
            case 1:selectedImage = (ImageView)findViewById(R.id.rc11) ; break;
            case 2:selectedImage = (ImageView)findViewById(R.id.rc12) ; break;
            case 3:selectedImage = (ImageView)findViewById(R.id.rc13) ; break;

            case 4:selectedImage = (ImageView)findViewById(R.id.rc21) ; break;
            case 5:selectedImage = (ImageView)findViewById(R.id.rc22) ; break;
            case 6:selectedImage = (ImageView)findViewById(R.id.rc23) ; break;

            case 7:selectedImage = (ImageView)findViewById(R.id.rc31) ; break;
            case 8:selectedImage = (ImageView)findViewById(R.id.rc32) ; break;
            case 9:selectedImage = (ImageView)findViewById(R.id.rc33) ; break;
        }
        PlayGame(selectedImage,selectedBlock);
//        for(int i = 1;i<10;i++){
//            if(!(player1.contains(i)  || player2.contains(i))){
//                emptyBlock.add(i);
//            }
//        }
//        if(emptyBlock.size()==0) {
//            CheckWinner();
//            if (gameState == 1) {
//                showAlert("Draw");
//            }
//            gameState = 3;
//        }else{

    }
    private void PlayGame(ImageView selectedImage, int selectedBlock) {
        if(gameState==1){
            if(activePlayer==1){
                selectedImage.setImageResource(R.drawable.ttt_x);
                player1.add(selectedBlock);
                selectedImage.setEnabled(false);
                CheckWinner();
                activePlayer = 2;
                Autoplay();
            }else {
                selectedImage.setImageResource(R.drawable.ttt_o);
                player2.add(selectedBlock);
                selectedImage.setEnabled(false);
                CheckWinner();
                activePlayer = 1;
            }

        }
    }
    private void showAlert(String Title) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this,R.style.TransparentDialog);
        alert.setTitle(Title)
                .setMessage("Start a new GAME?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ResetGame();
                    }
                })
                .setNegativeButton("Menu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }
    public void ResetGame(){
        gameState = 1;
        Random random = new Random();
        int randomPlayer = random.nextInt(2);
        activePlayer = randomPlayer + 1;
        player2.clear();
        player1.clear();

        ImageView iv;
        iv = (ImageView) findViewById(R.id.rc11); iv.setImageResource(0); iv.setEnabled(true);
        iv = (ImageView) findViewById(R.id.rc12); iv.setImageResource(0); iv.setEnabled(true);
        iv = (ImageView) findViewById(R.id.rc13); iv.setImageResource(0); iv.setEnabled(true);

        iv = (ImageView) findViewById(R.id.rc21); iv.setImageResource(0); iv.setEnabled(true);
        iv = (ImageView) findViewById(R.id.rc22); iv.setImageResource(0); iv.setEnabled(true);
        iv = (ImageView) findViewById(R.id.rc23); iv.setImageResource(0); iv.setEnabled(true);

        iv = (ImageView) findViewById(R.id.rc31); iv.setImageResource(0); iv.setEnabled(true);
        iv = (ImageView) findViewById(R.id.rc32); iv.setImageResource(0); iv.setEnabled(true);
        iv = (ImageView) findViewById(R.id.rc33); iv.setImageResource(0); iv.setEnabled(true);
        if(activePlayer==2){
            Autoplay();
        }
    }
    private void CheckWinner() {
        int winner = 0;
        //horizontally
        if(player1.contains(1) && player1.contains(2) && player1.contains(3)) { winner = 1;}
        if(player1.contains(4) && player1.contains(5) && player1.contains(6)) { winner = 1;}
        if(player1.contains(7) && player1.contains(8) && player1.contains(9)) { winner = 1;}

        //vertically
        if(player1.contains(1) && player1.contains(4) && player1.contains(7)) { winner = 1;}
        if(player1.contains(2) && player1.contains(5) && player1.contains(8)) { winner = 1;}
        if(player1.contains(3) && player1.contains(6) && player1.contains(9)) { winner = 1;}

        //diagnally
        if(player1.contains(1) && player1.contains(5) && player1.contains(9)) { winner = 1;}
        if(player1.contains(3) && player1.contains(5) && player1.contains(7)) { winner = 1;}




        //horizontally
        if(player2.contains(1) && player2.contains(2) && player2.contains(3)) { winner = 2;}
        if(player2.contains(4) && player2.contains(5) && player2.contains(6)) { winner = 2;}
        if(player2.contains(7) && player2.contains(8) && player2.contains(9)) { winner = 2;}

        //vertically
        if(player2.contains(1) && player2.contains(4) && player2.contains(7)) { winner = 2;}
        if(player2.contains(2) && player2.contains(5) && player2.contains(8)) { winner = 2;}
        if(player2.contains(3) && player2.contains(6) && player2.contains(9)) { winner = 2;}

        //diagnally
        if(player2.contains(1) && player2.contains(5) && player2.contains(9)) { winner = 2;}
        if(player2.contains(3) && player2.contains(5) && player2.contains(7)) { winner = 2;}

        if(winner!=0 && gameState ==1){
            if(winner==1){
                showAlert("You Win the game");
            }else if(winner==2){
                showAlert("player 2 is winner");
            }
            gameState=2;
        }
        if (gameState == 1 && (player1.size()+player2.size())==9) {
            //AlertDialog.Builder b = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            gameState = 3;
            showAlert("Draw");
        }

    }

//    This function returns true if there are moves remaining on the board. It returns false if there are no moves left to play.
    private Boolean isMovesLeft(){
        for(int i = 1;i<10;i++){
            if(!(player1.contains(i)  || player2.contains(i))){
                return true;
            }
        }
        return false;
    }

//This is the evaluation function
    private int evaluate(){
            int winner = 0;
            //horizontally
            if(player1.contains(1) && player1.contains(2) && player1.contains(3)) { winner = 1;}
            if(player1.contains(4) && player1.contains(5) && player1.contains(6)) { winner = 1;}
            if(player1.contains(7) && player1.contains(8) && player1.contains(9)) { winner = 1;}

            //vertically
            if(player1.contains(1) && player1.contains(4) && player1.contains(7)) { winner = 1;}
            if(player1.contains(2) && player1.contains(5) && player1.contains(8)) { winner = 1;}
            if(player1.contains(3) && player1.contains(6) && player1.contains(9)) { winner = 1;}

            //diagnally
            if(player1.contains(1) && player1.contains(5) && player1.contains(9)) { winner = 1;}
            if(player1.contains(3) && player1.contains(5) && player1.contains(7)) { winner = 1;}

            if(winner==1){
                return -10;
            }


            //horizontally
            if(player2.contains(1) && player2.contains(2) && player2.contains(3)) { winner = 2;}
            if(player2.contains(4) && player2.contains(5) && player2.contains(6)) { winner = 2;}
            if(player2.contains(7) && player2.contains(8) && player2.contains(9)) { winner = 2;}

            //vertically
            if(player2.contains(1) && player2.contains(4) && player2.contains(7)) { winner = 2;}
            if(player2.contains(2) && player2.contains(5) && player2.contains(8)) { winner = 2;}
            if(player2.contains(3) && player2.contains(6) && player2.contains(9)) { winner = 2;}

            //diagnally
            if(player2.contains(1) && player2.contains(5) && player2.contains(9)) { winner = 2;}
            if(player2.contains(3) && player2.contains(5) && player2.contains(7)) { winner = 2;}
            if(winner==2){
                return 10;
            }
            // Else if none of them have won then return 0
            return 0;
        }

//This is the minimax function. It considers all the possible ways the game can go and returns the value of the board

    public int minimax(int depth, Boolean isMax){
            int score = evaluate();

            // If Maximizer has won the game
            // return his/her evaluated score
            if (score == 10)
                return score - depth;


            // If Minimizer has won the game
            // return his/her evaluated score
            if (score == -10)
                return score + depth;

            // If there are no more moves and
            // no winner then it is a tie
            if (isMovesLeft() == false)
                return 0;

            // If this maximizer's move
            if (isMax)
            {
                int best = -1000;

                // Traverse all cells
                for(int i = 1;i<10;i++){
                    if(!(player1.contains(i)  || player2.contains(i))){
                        player2.add(i);
                        Log.d("SOLVE VALE ======= ","p2 ==> "+String.valueOf(i)+" d==> "+String.valueOf(depth+1));
                        best = Math.max(best, minimax(depth + 1, !isMax));
                        player2.remove(i);
                    }
                }
                return best;
            }else{ // If this minimizer's move
                int best = 1000;

                // Traverse all cells
                for(int i = 1;i<10;i++){
                    if(!(player1.contains(i)  || player2.contains(i))){
                        player1.add(i);
                        Log.d("SOLVE VALE ======= ","p1 ==> "+String.valueOf(i)+" d==> "+String.valueOf(depth+1));
                        best = Math.min(best, minimax(depth + 1, !isMax));

                        player1.remove(i);
                    }
                }
                return best;
            }
        }

//This will return the best possible move for the player
    private int findBestMove(){
         int bestVal = -1000;
         int bestMove = 0;
         for(int i = 1;i<10;i++){
             if(!(player1.contains(i) || player2.contains(i))){
                 player2.add(i);
                 Log.d("SOLVE VALE ======= ","p2 ==> "+String.valueOf(i)+" d==> "+String.valueOf(0));
                 int moveVal = minimax( 0, false);
                 Log.d("SOLVE VALE ======= "," ==========> "+String.valueOf(i)+" <==> "+String.valueOf(moveVal)+" <=========================");

                 player2.remove(i);
                 if(moveVal > bestVal){
                     bestMove = i;
                     bestVal = moveVal;
                 }
             }
         }
         String val = String.valueOf(bestMove);
        Log.d("FUNCTION VALE ======= ",val);
         return bestMove;
    }
}