package com.example.mathgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.Random;

public class Game extends AppCompatActivity {

    private TextView score, life, time;
    private TextView Question, Answer;
    private Button Nextbutton, OkButton;

    Random random = new Random();
    int number1;
    int number2;

    int UserAnswer;
    int RealAnswer;
    int Userscore;
    int Userlife = 3;

    CountDownTimer timer;
    private static final long START_TIMER = 60000;
    Boolean timerrunning = true;
    long timeleft = START_TIMER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        score = findViewById(R.id.scorecount);
        life = findViewById(R.id.lifecount);
        time = findViewById(R.id.timecount);

        Question = findViewById(R.id.questionplaced);
        Answer = findViewById(R.id.answer);

        Nextbutton = findViewById(R.id.next);
        OkButton = findViewById(R.id.ok);

        life.setText("" + Userlife);

        GameLogic();

        OkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserAnswer = Integer.parseInt(Answer.getText().toString());
                String nn = (Answer.getText().toString());

                if(timerrunning)
                {
                    PauseTimer();
                    if (UserAnswer == RealAnswer)
                    {
                        Userscore += 10;

                        Question.setText("Congrats");
                        score.setText("" + Userscore);
                    }
                    else
                    {
                        Userlife--;
                        Question.setText("Wrong Answer!!.. go and study maths LOL");
                        life.setText("" + Userlife);
                    }
                }
                else
                {
                    if(UserAnswer == RealAnswer)
                    {
                        Toast.makeText(Game.this, "Go to next question", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(Game.this, "You failed Go to next question LOL", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Answer.setText("");

                if(Userlife <= 0)
                {
                    Intent intent = new Intent(Game.this, Result.class);
                    intent.putExtra("score", Userscore);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    ResetTimer();
                    GameLogic();
                }
            }
        });
    }

    public void GameLogic()
    {
        Intent intent = getIntent();
        Boolean Add = intent.getBooleanExtra("add", false);
        Boolean sub = intent.getBooleanExtra("sub", false);
        Boolean mult = intent.getBooleanExtra("mult", false);

        if(Add)
        {
            number1 = random.nextInt(100);
            number2 = random.nextInt(100);
            RealAnswer = number1 + number2;

            Question.setText(number1 + " + " + number2);

        }
        if(sub)
        {
            number1 = random.nextInt(100);
            number2 = random.nextInt(100);
            RealAnswer = number1 - number2;

            Question.setText(number1 + " - " + number2);

        }
        if(mult)
        {
            number1 = random.nextInt(100);
            number2 = random.nextInt(100);
            RealAnswer = number1 * number2;

            Question.setText(number1 + " * " + number2);

        }

        StartTimer();

    }
    public void StartTimer()
    {
       timer = new CountDownTimer(timeleft,1000)
       {
           public void onTick(long milliuntilfinished)
           {
                timeleft = milliuntilfinished;
                Updatetext();
           }
           public void onFinish()
           {
                timerrunning = false;
                PauseTimer();
                ResetTimer();
                Updatetext();
                Userlife--;
                Question.setText("Sorry Time's up!!...");
           }
       }.start();

       timerrunning = true;
    }
    public void Updatetext()
    {
        int seconds = (int)(timeleft / 1000) % 60;
        String secondtext = String.format(Locale.getDefault(), "%02d", seconds);
        time.setText((secondtext));
    }
    public void PauseTimer()
    {
        timer.cancel();
        timerrunning = false;
    }
    public void ResetTimer()
    {
        timeleft = START_TIMER;
        Updatetext();
    }
}