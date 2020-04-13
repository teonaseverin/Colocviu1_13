package ro.pub.cs.systems.eim.colocviu1_13;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Colocviu1_13SeconderyActivity extends AppCompatActivity {
        String value;

        private ButtonClickListener buttonClickListener = new ButtonClickListener();
        private class ButtonClickListener implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.ok:
                        setResult(10, null);
                        break;
                    case R.id.cancel:
                        setResult(-2, null);
                        break;
                }
                finish();
            }
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_colocviu1_13_secondary);
            TextView view = (TextView)findViewById(R.id.text);

            Intent intent = getIntent();
            if (intent != null && intent.getExtras().containsKey("Directions")) {
                value = intent.getStringExtra("Directions");
                view.setText(String.valueOf(value));
            }

            Button okButton = (Button)findViewById(R.id.ok);
            okButton.setOnClickListener(buttonClickListener);
            Button cancelButton = (Button)findViewById(R.id.cancel);
            cancelButton.setOnClickListener(buttonClickListener);
        }
    }
