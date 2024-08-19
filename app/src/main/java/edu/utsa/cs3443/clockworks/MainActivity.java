package edu.utsa.cs3443.clockworks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.utsa.cs3443.clockworks.MyRemindersActivity;
import edu.utsa.cs3443.clockworks.NewReminderActivity;
import edu.utsa.cs3443.clockworks.Reminder;
import edu.utsa.cs3443.clockworks.ReminderUtils;
import edu.utsa.cs3443.clockworks.TodayActivity;

public class MainActivity extends Activity {

    private Button buttonPlus;
    private Button buttonMyReminders;
    private KanbanBoard board;

    private static final int NEW_REMINDER_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Clear reminders for testing
        ReminderUtils.clearReminders(this);

        board = (KanbanBoard) getIntent().getSerializableExtra("board");

        buttonPlus = findViewById(R.id.button_plus);
        buttonMyReminders = findViewById(R.id.button_my_reminders);

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewReminderActivity.class);
                intent.putExtra("board", board);
                startActivityForResult(intent, NEW_REMINDER_REQUEST_CODE);
            }
        });

        buttonMyReminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyRemindersActivity.class);
                intent.putExtra("board", board);
                startActivity(intent);
            }
        });

        Button toTimer = findViewById(R.id.toTimer);
        toTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TimerActivity.class);
                intent.putExtra("board", board);
                startActivity(intent);
            }
        });

        Button toKanban = findViewById(R.id.toKanban);
        toKanban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, KanbanActivity.class);
                intent.putExtra("board", board);
                startActivity(intent);
            }
        });

        Button toMotivation = findViewById(R.id.toMotivation);
        toMotivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MotivationsActivity.class);
                intent.putExtra("board", board);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_REMINDER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            String reminderText = data.getStringExtra("reminder_text");
            String reminderTime = data.getStringExtra("reminder_time");

            ReminderUtils.saveReminder(this, new Reminder(reminderText, reminderTime));

            // Update TodayActivity with new reminder
            Intent intent = new Intent(MainActivity.this, TodayActivity.class);
            startActivity(intent);
        }
    }
}

