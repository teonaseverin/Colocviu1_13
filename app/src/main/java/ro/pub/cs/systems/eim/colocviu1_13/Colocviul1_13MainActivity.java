package ro.pub.cs.systems.eim.colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Colocviul1_13MainActivity extends AppCompatActivity {

    private Button northButton, southButton, eastButton, westButton, navigateButton;
    public int ButtonClicks = 0;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            TextView text = (TextView)findViewById(R.id.textView);
            String value = text.getText().toString();

            switch(view.getId()) {
                case R.id.north:
                    value += ", North";
                    ButtonClicks += 1;
                    text.setText(value);
                    break;
                case R.id.south:
                    value += ", South";
                    ButtonClicks += 1;
                    text.setText(value);
                    break;
                case R.id.east:
                    value += ", East";
                    ButtonClicks += 1;
                    text.setText(value);
                    break;
                case R.id.west:
                    value += ", West";
                    ButtonClicks += 1;
                    text.setText(value);
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviul1_13_main);

        northButton = (Button)findViewById(R.id.north);
        southButton = (Button)findViewById(R.id.south);
        eastButton = (Button)findViewById(R.id.east);
        westButton = (Button)findViewById(R.id.west);
        navigateButton = (Button)findViewById(R.id.navigate);

        northButton.setOnClickListener(buttonClickListener);
        southButton.setOnClickListener(buttonClickListener);
        eastButton.setOnClickListener(buttonClickListener);
        westButton.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("buttonClicks")) {
                ButtonClicks = savedInstanceState.getInt("buttonClicks");
            } else {
                ButtonClicks = 0;
            }
        }
        Log.d("[onCreate]",  "Got " + ButtonClicks + " clicks so far");

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("buttonClicks", ButtonClicks);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("buttonClicks")) {
            ButtonClicks = savedInstanceState.getInt("buttonClicks");
        } else {
            ButtonClicks = 0;
        }
    }
}
