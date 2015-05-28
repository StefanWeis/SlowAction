package de.dma.slowaction;

import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

class Request implements Runnable {
    private EditText input;
    private TextView output;
    private String message;

    public Request(EditText input, String message) {
        this.input = input;
        this.message = message;
    }

    public Request(TextView output, String message) {
        this.output = output;
        this.message = message;
    }

    public void run() {
        // Wird von main Sekunden+1 mal aufgerufen
        // Sekunden-mal innherhalb der while Schleife
        // und einmal hinter der while Schleife
        Log.i("run_Request", Thread.currentThread().getName());
        if (input != null) {
            input.setText(message);
        } else if (output != null) {
            output.setText(message);
        }
    }
}
