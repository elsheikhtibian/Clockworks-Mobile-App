package edu.utsa.cs3443.clockworks;

import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Motivations {

    private ArrayList<String> quotes;

    public Motivations()
    {
        this.quotes = new ArrayList<>();
    }

    public ArrayList<String> getQuotes() {
        return quotes;
    }

    public void setQuotes(ArrayList<String> quotes) {
        this.quotes = quotes;
    }

    public void loadQuotes(MotivationsActivity motivationsActivity)
    {
        AssetManager manager = motivationsActivity.getAssets();
        Scanner scan;
        String filename = "Quotes.csv";
        InputStream file;

        try {
            file = manager.open(filename);
            scan = new Scanner(file);
            String line;
            String[] tokens;
            for (int i = 0; i < 25; i++) {
                line = scan.nextLine();
                tokens = line.split(";");

                String quote = tokens[0].trim();
                String author = tokens[1].trim();
                String fullQuote = quote + "\n\n -" + author;

                quotes.add(fullQuote);
            }

            scan.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
