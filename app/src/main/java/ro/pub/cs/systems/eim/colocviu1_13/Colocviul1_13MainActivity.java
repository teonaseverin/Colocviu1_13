package ro.pub.cs.systems.eim.colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Colocviul1_13MainActivity extends AppCompatActivity {

    private Button northButton, southButton, eastButton, westButton, navigateButton;
    public int ButtonClicks = 0;
    private IntentFilter intentFilter = new IntentFilter();
    private int serviceStatus = 14;
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
                case R.id.navigate:
                    Intent intent = new Intent(getApplicationContext(), Colocviu1_13SeconderyActivity.class);
                    ButtonClicks = 0;
                    text.setText("");
                    intent.putExtra("Directions", value);
                    startActivityForResult(intent, 101);
                    break;
            }

            if (ButtonClicks > 4) {
                Intent intent = new Intent(getApplicationContext(), Colocviu1_13Service.class);
                intent.putExtra("Directions", value);
                getApplicationContext().startService(intent);
                serviceStatus = 20;
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
        navigateButton.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("buttonClicks")) {
                ButtonClicks = savedInstanceState.getInt("buttonClicks");
            } else {
                ButtonClicks = 0;
            }
        }
        intentFilter.addAction("ro.pub.cs.systems.eim.colocviu1_13.Colocviu1_13Service.string");
        Log.d("[onCreate]",  "Got " + ButtonClicks + " clicks so far");

    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[onReceive Main]:", intent.getStringExtra("Directions"));
        }
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 101) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, Colocviu1_13Service.class);
        stopService(intent);
        super.onDestroy();
    }
}
