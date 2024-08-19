package edu.utsa.cs3443.clockworks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class KanbanActivity extends AppCompatActivity {

    private final int[] BUTTON_IDS = {R.id.task1, R.id.task2, R.id.task3, R.id.task4, R.id.task5, R.id.task6, R.id.task7, R.id.task8, R.id.task9};
    private KanbanBoard board;
    private Button buttons[] = new Button[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_kanban);

        board = (KanbanBoard) getIntent().getSerializableExtra("board");
        if (board == null) {
            board = new KanbanBoard();
        }

        Button finishTask = findViewById(R.id.toggleFinishOverlay);
        Button addTask = findViewById(R.id.toggleAddOverlay);

        for(int i = 0; i < board.getTaskQueue().size(); i++) {
            Button button = findViewById(BUTTON_IDS[i]);
            button.setVisibility(View.VISIBLE);
            String movedTask = board.getTaskQueue().get(i);
            button.setText(movedTask);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    board.moveToCurrentTask(movedTask);
                    Intent intent = new Intent(KanbanActivity.this, KanbanActivity.class);
                    intent.putExtra("board", board);
                    startActivity(intent);
                }
            });
            buttons[i] = button;
        }

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KanbanActivity.this, AddTaskActivity.class);
                intent.putExtra("board", board);
                startActivity(intent);
            }
        });

        if (board.getCurrentTask() != null) {
            finishTask.setVisibility(View.VISIBLE);
            finishTask.setText(board.getCurrentTask());
            finishTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(KanbanActivity.this, FinishTaskActivity.class);
                    intent.putExtra("board", board);
                    startActivity(intent);
                }
            });
        }

        Button toTimer = findViewById(R.id.toTimer);
        toTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KanbanActivity.this, TimerActivity.class);
                intent.putExtra("board", board);
                startActivity(intent);
            }
        });

        Button toReminders = findViewById(R.id.toReminders);
        toReminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KanbanActivity.this, MainActivity.class);
                intent.putExtra("board", board);
                startActivity(intent);
            }
        });

        Button toMotivation = findViewById(R.id.toMotivation);
        toMotivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KanbanActivity.this, MotivationsActivity.class);
                intent.putExtra("board", board);
                startActivity(intent);
            }
        });
    }
}