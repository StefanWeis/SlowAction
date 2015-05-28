package de.dma.slowaction;

import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class LongRunningThread extends Thread {

    private long total;
    private EditText input;
    private TextView output;
    private String format;

    public LongRunningThread(long total, EditText input, TextView output, String format) {
        this.total = total;
        this.input = input;
        this.output = output;
        this.format = format;
    }

    public void run() {
        // Wird einmal von Thread-xxx aufgerufen
        Log.i("run_LongRunningThread", Thread.currentThread().getName());
        long rest = total;
        while (rest > 0) {
            long thisTime = Math.min(rest, 1000L);

            try {
                Thread.sleep(thisTime);
            }
            catch (Exception e) {}

            rest -= thisTime;
            Request req = new Request(input, "" + rest);
            input.post(req);
        }
        String message = String.format(format, total);
        Request req = new Request(output, message);
        input.post(req);
    }
}
