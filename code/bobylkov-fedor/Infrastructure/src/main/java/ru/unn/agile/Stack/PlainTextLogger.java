package ru.unn.agile.Stack;

import ru.unn.agile.Stack.ViewModel.ILogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlainTextLogger implements ILogger {
    private final String filename;
    private final BufferedWriter writer;

    public PlainTextLogger(final String logFilename) {
        filename = logFilename;

        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer = bufferedWriter;
    }

    @Override
    public void log(final String message) {
        try {
            writer.write(new LogMessage(new Date(), message).toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getLog() {
        List<String> log = new ArrayList<>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return log;
        }

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                log.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return log;
    }
}

