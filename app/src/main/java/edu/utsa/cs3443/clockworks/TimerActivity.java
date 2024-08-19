package edu.utsa.cs3443.clockworks;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TimerActivity extends AppCompatActivity {

    private TextView timerText;
    private Button startButton;
    private Button resetButton;
    private Button pauseButton;
    private Handler handler = new Handler();
    private TimerModel timerModel;
    private KanbanBoard board;

    private static final int WORK_TIME = 1500; // 25 minutes in seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_timer);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        timerModel = new TimerModel(WORK_TIME);

        timerText = findViewById(R.id.timerText);
        startButton = findViewById(R.id.startButton);
        resetButton = findViewById(R.id.resetButton);
        pauseButton = findViewById(R.id.pauseButton);

        timerText.setText(timerModel.formatTime());

        startButton.setOnClickListener(v -> startTimer());
        resetButton.setOnClickListener(v -> resetTimer());
        pauseButton.setOnClickListener(v -> pauseResumeTimer());

        board = (KanbanBoard) getIntent().getSerializableExtra("board");

        Button toKanban = findViewById(R.id.toKanban);
        toKanban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TimerActivity.this, KanbanActivity.class);
                intent.putExtra("board", board);
                startActivity(intent);
            }
        });

        Button toMotivation = findViewById(R.id.toMotivation);
        toMotivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TimerActivity.this, MotivationsActivity.class);
                intent.putExtra("board", board);
                startActivity(intent);
            }
        });

        Button toReminders = findViewById(R.id.toReminders);
        toReminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TimerActivity.this, MainActivity.class);
                intent.putExtra("board", board);
                startActivity(intent);
            }
        });

    }

    private void startTimer() {
        if (!timerModel.isRunning()) {
            timerModel.setRunning(true);
            runTimer();
            pauseButton.setText("Pause");
            startButton.setEnabled(false);
        }
    }

    private void pauseResumeTimer() {
        if (timerModel.isRunning()) {
            timerModel.setRunning(false);
            handler.removeCallbacksAndMessages(null);
            pauseButton.setText("Resume");
        } else {
            timerModel.setRunning(true);
            runTimer();
            pauseButton.setText("Pause");
        }
    }

    private void resetTimer() {
        timerModel.reset();
        timerText.setText(timerModel.formatTime());
        handler.removeCallbacksAndMessages(null);
        pauseButton.setText("Pause");
        startButton.setEnabled(true);
    }

    private void runTimer() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (timerModel.getSeconds() > 0 && timerModel.isRunning()) {
                    timerText.setText(timerModel.formatTime());
                    timerModel.setSeconds(timerModel.getSeconds() - 1);
                    handler.postDelayed(this, 1000);
                } else if (timerModel.getSeconds() == 0) {
                    onTimerFinish();
                }
            }
        });
    }

    private void onTimerFinish() {
        timerModel.setRunning(false);
        startBreakActivity();
    }

    private void startBreakActivity() {
        Intent intent = new Intent(this, BreakActivity.class);
        startActivity(intent);
        finish();
    }
}
