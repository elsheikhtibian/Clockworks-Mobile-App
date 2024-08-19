package edu.utsa.cs3443.clockworks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ReminderAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Reminder> reminderList;

    public ReminderAdapter(Context context, ArrayList<Reminder> reminderList) {
        this.context = context;
        this.reminderList = reminderList;
    }

    @Override
    public int getCount() {
        return reminderList.size();
    }

    @Override
    public Object getItem(int position) {
        return reminderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item_reminder, parent, false);
        }

        TextView reminderText = convertView.findViewById(R.id.reminder_text);
        final ImageView circleImage = convertView.findViewById(R.id.circle_image);

        final Reminder reminder = reminderList.get(position);
        reminderText.setText(reminder.getText() + ": " + reminder.getTime());

        if (reminder.isCircleFilled()) {
            circleImage.setImageResource(R.drawable.black_circle);
        } else {
            circleImage.setImageResource(R.drawable.white_circle_black_border);
        }

        circleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reminder.toggleCircleFilled();
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}

