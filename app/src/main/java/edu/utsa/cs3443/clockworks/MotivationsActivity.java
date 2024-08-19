package edu.utsa.cs3443.clockworks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MotivationsActivity extends AppCompatActivity {

    private Motivations motives;
    private TextView quoteTextView;
    private KanbanBoard board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_motivations);

        Random random = new Random();
        int quoteID = random.nextInt(25);

        motives = new Motivations();
        motives.loadQuotes(this);

        quoteTextView = findViewById(R.id.quote_text_view);
        quoteTextView.setText(motives.getQuotes().get(quoteID));

        board = (KanbanBoard) getIntent().getSerializableExtra("board");

        Button toTimer = findViewById(R.id.toTimer);
        toTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MotivationsActivity.this, TimerActivity.class);
                intent.putExtra("board", board);
                startActivity(intent);
            }
        });

        Button toKanban = findViewById(R.id.toKanban);
        toKanban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MotivationsActivity.this, KanbanActivity.class);
                intent.putExtra("board", board);
                startActivity(intent);
            }
        });

        Button toReminders = findViewById(R.id.toReminders);
        toReminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MotivationsActivity.this, MainActivity.class);
                intent.putExtra("board", board);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}