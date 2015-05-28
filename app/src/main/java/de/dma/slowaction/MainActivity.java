package de.dma.slowaction;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clicked(View v) {
        // Wird einmal von main aufgerufen
        Log.i("clicked", Thread.currentThread().getName());
        EditText input = (EditText) findViewById(R.id.editText_eingabe);
        String s = input.getText().toString();
        TextView output = (TextView) findViewById(R.id.textView_ergebnis);

        try {
            long total = Long.parseLong(s);
            if (total < 0) {
                throw new NumberFormatException();
            }
            String format = "SUCCESS";
            Thread t = new LongRunningThread(total, input, output, format);
            t.start();
        }
        catch (Exception e) {
            String message = "FAILURE" + " " + e.getMessage();
            output.setText(message);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
