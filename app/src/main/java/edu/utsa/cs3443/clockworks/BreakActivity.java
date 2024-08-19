package edu.utsa.cs3443.clockworks;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BreakActivity extends AppCompatActivity {

    private TextView timerText;
    private Button startNewPomodoroButton;
    private Handler handler = new Handler();
    private TimerModel timerModel;
    private KanbanBoard board;

    private static final int BREAK_TIME = 300; // 5 minutes in seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break);

        timerModel = new TimerModel(BREAK_TIME);

        timerText = findViewById(R.id.breakTimerText);
        startNewPomodoroButton = findViewById(R.id.startNewPomodoroButton);

        timerText.setText(timerModel.formatTime());

        startNewPomodoroButton.setOnClickListener(v -> startNewPomodoro());

        board = (KanbanBoard) getIntent().getSerializableExtra("board");

        Button toKanban = findViewById(R.id.toKanban);
        toKanban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BreakActivity.this, KanbanActivity.class);
                intent.putExtra("board", board);
                startActivity(intent);
            }
        });

        Button toMotivation = findViewById(R.id.toMotivation);
        toMotivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BreakActivity.this, MotivationsActivity.class);
                intent.putExtra("board", board);
                startActivity(intent);
            }
        });

        Button toReminders = findViewById(R.id.toReminders);
        toReminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BreakActivity.this, MainActivity.class);
                intent.putExtra("board", board);
                startActivity(intent);
            }
        });

        startBreakTimer();
    }

    private void startBreakTimer() {
        timerModel.setRunning(true);
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (timerModel.getSeconds() > 0 && timerModel.isRunning()) {
                    timerText.setText(timerModel.formatTime());
                    timerModel.setSeconds(timerModel.getSeconds() - 1);
                    handler.postDelayed(this, 1000);
                } else if (timerModel.getSeconds() == 0) {
                    onBreakFinish();
                }
            }
        });
    }

    private void onBreakFinish() {
        timerModel.setRunning(false);
        timerText.setText("Break finished!");
        startNewPomodoroButton.setVisibility(View.VISIBLE);
    }

    private void startNewPomodoro() {
        Intent intent = new Intent(this, TimerActivity.class);
        startActivity(intent);
        finish();
    }
}
