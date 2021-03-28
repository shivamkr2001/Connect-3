package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 = o and 1 = Cross
    int activePlayer=0;
    boolean gameIsActive = true;
    // 2 means unplayed
    int gameState[] = {2,2,2,2,2,2,2,2,2};
    int winningPositions[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && gameIsActive==true) {
            gameState[tappedCounter]=activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.final_o);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.final_cross);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(100);
            for(int[] winningPosition : winningPositions){
                if(gameState[winningPosition[0]]==gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]]==gameState[winningPosition[2]] && gameState[winningPosition[0]]!=2){
                    // Someone has Won
                    gameIsActive = false;
                    String message="";
                    if(gameState[winningPosition[0]]==0) message = "O has Won!";
                    else message = "Cross has Won!";
                    TextView winningMessage = (TextView) findViewById(R.id.winnerMessage) ;
                    winningMessage.setText(message);
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }
                else{
                    boolean gameIsOver = true;
                    for(int counterstate:gameState)
                    {
                        if(counterstate==2)gameIsOver=false;
                    }
                    if(gameIsOver==true)
                    {
                        String message = "Game is Over!";
                        TextView winningMessage = (TextView) findViewById(R.id.winnerMessage);
                        winningMessage.setText(message);
                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void playAgain(View view){
        gameIsActive = true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer=0;
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }
        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}