package edu.utsa.cs3443.clockworks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewReminderActivity extends Activity {

    private EditText reminderEditText;
    private EditText timeEditText;
    private Button addButton;
    private Button addToWeekButton;
    private KanbanBoard board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        board = (KanbanBoard) getIntent().getSerializableExtra("board");

        reminderEditText = findViewById(R.id.edittext_reminder);
        timeEditText = findViewById(R.id.edittext_when);
        addButton = findViewById(R.id.button_add);
        addToWeekButton = findViewById(R.id.button_add_to_week);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAddReminder(false);  // Add to Today
            }
        });

        addToWeekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAddReminder(true);   // Add to This Week
            }
        });
    }

    private void handleAddReminder(boolean addToWeek) {
        String reminderText = reminderEditText.getText().toString();
        String reminderTime = timeEditText.getText().toString();

        if (reminderText.isEmpty() || reminderTime.isEmpty()) {
            Toast.makeText(NewReminderActivity.this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent resultIntent = new Intent();
        resultIntent.putExtra("reminder_text", reminderText);
        resultIntent.putExtra("reminder_time", reminderTime);
        resultIntent.putExtra("add_to_week", addToWeek);
        resultIntent.putExtra("board", board);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}





