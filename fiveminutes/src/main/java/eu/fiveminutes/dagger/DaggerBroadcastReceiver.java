package eu.fiveminutes.dagger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public abstract class DaggerBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Context appContext = context.getApplicationContext();
        ((DaggerApplication) appContext).inject(this);
        handleIntent(context, intent);
    }

    protected abstract void handleIntent(Context context, Intent intent);
}
