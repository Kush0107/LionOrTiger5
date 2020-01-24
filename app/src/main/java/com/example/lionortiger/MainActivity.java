package com.example.lionortiger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum Player {
        ONE , TWO , NO
    }

    Player currentPlayer = Player.ONE;
    Player[] playerChoice = new Player[9];
    int [][] winArray = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    private boolean gameOver = false;
    GridLayout gridLayout;
    private Button gameButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i=0;i<=8;i++) {
            playerChoice[i] = Player.NO;
        }


        gameButton = findViewById(R.id.resetbutton);
        gridLayout = findViewById(R.id.grideLayout);
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTheGame();
            }
        });
    }

    public void imgClicked(View tappedImage) {

        ImageView tappedImageView =  (ImageView) tappedImage;

        int imgTag = Integer.parseInt(tappedImage.getTag().toString());
        if(playerChoice[imgTag] == Player.NO && gameOver == false) {

            tappedImageView.setTranslationX(-2002);

            playerChoice[imgTag]=currentPlayer;

            if(currentPlayer == Player.ONE) {
                tappedImageView.setImageResource(R.drawable.tiger);
                currentPlayer=Player.TWO;
            } else {
                tappedImageView.setImageResource(R.drawable.lion);
                currentPlayer = Player.ONE;
            }

            tappedImageView.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(1000);

            Toast.makeText(this,tappedImage.getTag().toString(),Toast.LENGTH_SHORT).show();

            for (int[] winnerColumns : winArray) {

                if (playerChoice[winnerColumns[0]] == playerChoice[winnerColumns[1]] && playerChoice[winnerColumns[1]] == playerChoice[winnerColumns[2]] && playerChoice[winnerColumns[0]]!=Player.NO){
                    gameButton.setVisibility(View.VISIBLE);
                    gameOver = true;

                    if(currentPlayer == Player.ONE){
                        Toast.makeText(this,"Player two is a WINNER",Toast.LENGTH_LONG).show();
                    }else if (currentPlayer == Player.TWO){
                        Toast.makeText(this,"Player one is a WINNER",Toast.LENGTH_LONG).show();
                    }

                }
            }
        }

    }

    private void resetTheGame() {

        for(int i = 0; i<gridLayout.getChildCount();i++) {
            ImageView imageView = (ImageView) gridLayout.getChildAt(i);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.2f);
        }

        gameButton.setVisibility(View.INVISIBLE);

        currentPlayer = Player.ONE;
        for(int i=0;i<=8;i++) {
            playerChoice[i] = Player.NO;
        }

        gameOver = false;
    }
}


