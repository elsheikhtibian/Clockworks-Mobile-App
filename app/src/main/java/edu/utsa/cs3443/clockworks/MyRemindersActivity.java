package edu.utsa.cs3443.clockworks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import edu.utsa.cs3443.clockworks.TodayActivity;

public class MyRemindersActivity extends Activity {

    private Button buttonToday;
    private Button buttonThisWeek;
    private ImageButton backButton;
    private KanbanBoard board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);

        board = (KanbanBoard) getIntent().getSerializableExtra("board");

        buttonToday = findViewById(R.id.button_today);
        buttonThisWeek = findViewById(R.id.button_this_week);
        backButton = findViewById(R.id.button_arrow);

        buttonToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyRemindersActivity.this, TodayActivity.class);
                intent.putExtra("board", board);
                startActivity(intent);
            }
        });

        buttonThisWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyRemindersActivity.this, ActivityThisWeek.class);
                intent.putExtra("board", board);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to MainActivity
                Intent intent = new Intent(MyRemindersActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("board", board);
                startActivity(intent);
                finish();
            }
        });
    }
}
