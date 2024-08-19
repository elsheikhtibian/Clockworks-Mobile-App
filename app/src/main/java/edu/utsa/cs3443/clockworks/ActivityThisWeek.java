package edu.utsa.cs3443.clockworks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import edu.utsa.cs3443.clockworks.Reminder;
import edu.utsa.cs3443.clockworks.ReminderAdapter;

public class ActivityThisWeek extends Activity {

    private ImageButton buttonBack;
    private ListView reminderListView;
    private ReminderAdapter reminderAdapter;
    private ArrayList<Reminder> reminderList;
    private KanbanBoard board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_this_week);

        board = (KanbanBoard) getIntent().getSerializableExtra("board");

        buttonBack = findViewById(R.id.button_arrow);
        reminderListView = findViewById(R.id.reminder_list);

        reminderList = new ArrayList<>();
        // Initialize the list with predefined reminders
        reminderList.add(new Reminder("Grocery Shopping", "Thursday, 1pm"));
        reminderList.add(new Reminder("Doc appt", "Friday, 2:30pm"));
        reminderList.add(new Reminder("Brunch with Jo", "Saturday, 12pm"));

        reminderAdapter = new ReminderAdapter(this, reminderList);
        reminderListView.setAdapter(reminderAdapter);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityThisWeek.this, MyRemindersActivity.class);
                intent.putExtra("board", board);
                startActivity(intent);
                finish();
            }
        });
    }
}

