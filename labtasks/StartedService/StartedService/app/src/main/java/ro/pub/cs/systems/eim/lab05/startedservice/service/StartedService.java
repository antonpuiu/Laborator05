package ro.pub.cs.systems.eim.lab05.startedservice.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import ro.pub.cs.systems.eim.lab05.startedservice.general.Constants;

public class StartedService extends Service {
    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(Constants.TAG, "onCreate() method was invoked");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(Constants.TAG, "onBind() method was invoked");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(Constants.TAG, "onUnbind() method was invoked");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(Constants.TAG, "onRebind() method was invoked");
    }

    @Override
    public void onDestroy() {
        Log.d(Constants.TAG, "onDestroy() method was invoked");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(Constants.TAG, "onStartCommand() method was invoked");
        context = this;

        // TODO: exercise 5 - implement and start the ProcessingThread
        new Thread(new Runnable() {
            @Override
            public void run() {
                Intent actionString, actionInteger, actionArrayList;

                actionString = new Intent();
                actionString.setAction(Constants.ACTION_STRING);
                actionString.putExtra(Constants.DATA, Constants.STRING_DATA);

                actionInteger = new Intent();
                actionInteger.setAction(Constants.ACTION_INTEGER);
                actionInteger.putExtra(Constants.DATA, Integer.toString(Constants.INTEGER_DATA));

                actionArrayList = new Intent();
                actionArrayList.setAction(Constants.ACTION_ARRAY_LIST);
                actionArrayList.putExtra(Constants.DATA, Constants.ARRAY_LIST_DATA.toString());

                while (true) {
                    try {
                        context.sendBroadcast(actionString);
                        Thread.sleep(Constants.SLEEP_TIME);
                        context.sendBroadcast(actionInteger);
                        Thread.sleep(Constants.SLEEP_TIME);
                        context.sendBroadcast(actionArrayList);
                        Thread.sleep(Constants.SLEEP_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        return START_REDELIVER_INTENT;
    }

}
