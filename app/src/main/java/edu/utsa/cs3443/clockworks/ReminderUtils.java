package edu.utsa.cs3443.clockworks;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ReminderUtils {

    private static final String PREFS_NAME = "reminders_prefs";
    private static final String REMINDERS_KEY = "reminders";

    public static void saveReminder(Context context, Reminder reminder) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        ArrayList<Reminder> reminders = loadReminders(context);
        reminders.add(0, reminder); // Add new reminders to the top

        Gson gson = new Gson();
        String json = gson.toJson(reminders);
        editor.putString(REMINDERS_KEY, json);
        editor.apply();
    }

    public static ArrayList<Reminder> loadReminders(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(REMINDERS_KEY, null);

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Reminder>>() {}.getType();
        ArrayList<Reminder> reminders = gson.fromJson(json, type);

        return (reminders == null) ? new ArrayList<Reminder>() : reminders;
    }

    public static void clearReminders(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }
}
