package edu.utsa.cs3443.clockworks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class FinishTaskActivity extends AppCompatActivity  {

    private KanbanBoard board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.finish_task_overlay);

        board = (KanbanBoard) getIntent().getSerializableExtra("board");
        if (board == null) {
            board = new KanbanBoard();
        }

        TextView currentText = findViewById(R.id.currentTask);
        currentText.setText(board.getCurrentTask());

        Button finishThis = findViewById(R.id.finishThis);

        finishThis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                board.finishCurrentTask();

                Intent intent = new Intent(FinishTaskActivity.this, KanbanActivity.class);
                intent.putExtra("board", board);
                startActivity(intent);
            }
        });
    }
}
