package com.appspace.cpnpop.geo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

import com.appspace.cpnpop.MainActivity;
import com.appspace.cpnpop.R;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by siwaweswongcharoen on 8/8/2015 AD.
 */
public class GeofenceBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        GeofencingEvent event = GeofencingEvent.fromIntent(intent);
        String transition = mapTransition(event.getGeofenceTransition());
        String[] temp = getGeofenceTransitionDetails(event.getTriggeringGeofences()).split(",");
        String id = temp[0];
        String name = temp[1];

        Intent myIntent = new Intent(context, MainActivity.class);
        myIntent.putExtra(StoreLocation.LOCATION_ID, id);
        PendingIntent activity = PendingIntent.getActivity(context, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(transition + " " + name)
                .setContentText(name)
                .setTicker("City Hello")
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .setContentIntent(activity)
                .build();
        nm.notify(0, notification);
    }

    private String getGeofenceTransitionDetails( List<Geofence> triggeringGeofences) {

        // Get the name of each geofence that was triggered.
        ArrayList<String> triggeringGeofencesIdsList = new ArrayList<>();
        for (Geofence geofence : triggeringGeofences) {
            triggeringGeofencesIdsList.add(geofence.getRequestId());
        }

        return TextUtils.join(", ", triggeringGeofencesIdsList);
    }

    private String mapTransition(int event) {
        switch (event) {
            case Geofence.GEOFENCE_TRANSITION_ENTER:
                return "ENTER";
            case Geofence.GEOFENCE_TRANSITION_EXIT:
                return "EXIT";
            default:
                return "UNKNOWN";
        }
    }
}
