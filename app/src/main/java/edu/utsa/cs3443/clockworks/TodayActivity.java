package edu.utsa.cs3443.clockworks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class TodayActivity extends Activity {

    private ImageButton buttonArrow;
    private ListView reminderListView;
    private ReminderAdapter reminderAdapter;
    private ArrayList<Reminder> reminderList;
    private KanbanBoard board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);

        board = (KanbanBoard) getIntent().getSerializableExtra("board");

        buttonArrow = findViewById(R.id.button_arrow);
        reminderListView = findViewById(R.id.reminder_list);

        reminderList = new ArrayList<>();
        reminderList.add(new Reminder("Meeting", "6pm"));
        reminderList.add(new Reminder("Run", "10pm"));

        // Load additional reminders from SharedPreferences
        reminderList.addAll(ReminderUtils.loadReminders(this));

        reminderAdapter = new ReminderAdapter(this, reminderList);
        reminderListView.setAdapter(reminderAdapter);

        buttonArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TodayActivity.this, MyRemindersActivity.class);
                intent.putExtra("board", board);
                startActivity(intent);
                finish();
            }
        });

        handleNewReminder();
    }

    private void handleNewReminder() {
        Intent intent = getIntent();
        String reminderText = intent.getStringExtra("reminder_text");
        String reminderTime = intent.getStringExtra("reminder_time");
        boolean addToWeek = intent.getBooleanExtra("add_to_week", false);

        if (reminderText != null && reminderTime != null) {
            if (addToWeek) {
                // Save to week reminders (This will be handled in ActivityThisWeek)
                ReminderUtils.saveReminder(this, new Reminder(reminderText, reminderTime));
            } else {
                reminderList.add(0, new Reminder(reminderText, reminderTime));
                reminderAdapter.notifyDataSetChanged();
            }
        }
    }
}






