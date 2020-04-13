package ro.pub.cs.systems.eim.colocviu1_13;

import android.content.Context;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.util.Log;

import java.util.Date;

public class ProcessingThread extends Thread {

        private Context context = null;
        private String value = "";

        public ProcessingThread(Context context, String value) {
            this.context = context;
            this.value = value;
        }
        @Override
        public void run() {
            Log.d("[ProcessingThread]", "Thread has started! PID: ");
            sleep();
            sendMessage();
            Log.d("[ProcessingThread]", "Thread has stopped!");
        }

        private void sendMessage() {
            Intent intent = new Intent();
            intent.setAction("ro.pub.cs.systems.eim.colocviu1_13.Colocviu1_13Service.string");
            intent.putExtra("message",
                    new Date(System.currentTimeMillis()) + value);
            context.sendBroadcast(intent);
        }

        private void sleep() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }

    public void stopThread() {
       // nothing
    }

}
