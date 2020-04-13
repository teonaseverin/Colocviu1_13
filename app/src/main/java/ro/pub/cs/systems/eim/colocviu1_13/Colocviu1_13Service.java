package ro.pub.cs.systems.eim.colocviu1_13;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class Colocviu1_13Service extends Service {

        ProcessingThread processingThread = null;

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            Log.d ("[onStartCommand]", " service start");
            String value = intent.getStringExtra("Directions");
            processingThread = new ProcessingThread(this, value);
            processingThread.start();
            return Service.START_REDELIVER_INTENT;
        }

        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public void onDestroy() {
            processingThread.stopThread();
        }
}
