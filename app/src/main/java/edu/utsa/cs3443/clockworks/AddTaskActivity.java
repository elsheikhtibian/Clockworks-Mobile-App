package edu.utsa.cs3443.clockworks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {

    private KanbanBoard board;
    private String taskText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.add_task_overlay);

        board = (KanbanBoard) getIntent().getSerializableExtra("board");
        if (board == null) {
            board = new KanbanBoard();
        }

        EditText taskInput = findViewById(R.id.toBeAdded);
        Button addThis = findViewById(R.id.addThis);

        addThis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskText = taskInput.getText().toString();
                if (!taskText.isEmpty()) {
                    board.addTask(taskText);
                }

                Intent intent = new Intent(AddTaskActivity.this, KanbanActivity.class);
                intent.putExtra("board", board);
                startActivity(intent);
            }
        });
    }
}
