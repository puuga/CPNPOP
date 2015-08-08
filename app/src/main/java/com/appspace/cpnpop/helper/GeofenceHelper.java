package com.appspace.cpnpop.helper;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.appspace.cpnpop.apimanager.StoreLocationManager;
import com.appspace.cpnpop.geo.GeofenceBroadcastReceiver;
import com.appspace.cpnpop.geo.StoreLocation;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by siwaweswongcharoen on 8/8/2015 AD.
 */
public class GeofenceHelper {
    ReactiveLocationProvider locationProvider;
    Context context;

    public GeofenceHelper(ReactiveLocationProvider locationProvider, Context context) {
        this.locationProvider = locationProvider;
        this.context = context;
    }

    public void activeGeofence() {
        AsyncHttpClient client = new AsyncHttpClient();
//        Log.d("JSONObject", "go");
        client.get(StoreLocationManager.API_LOCATION_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                Log.d("JSONObject", response.toString());
                try {
//                    Log.d("JSONObject", response.getJSONArray("location").toString());
                    Type listType = new TypeToken<ArrayList<StoreLocation>>() {
                    }.getType();
                    List<StoreLocation> storeLocationList =
                            new Gson().fromJson(response.getJSONArray("location").toString(), listType);
                    Log.i("API_LOCATION", "There are " + storeLocationList.size() + " locations to monitor");

                    GeofencingRequest geofencingRequest = createGeofencingRequest(storeLocationList);
                    addGeofence(geofencingRequest);
                } catch (JSONException e) {
                    Log.d("JSONObject", "no location");
                    e.printStackTrace();
                }
            }
        });
    }

    private void addGeofence(final GeofencingRequest geofencingRequest) {
        final PendingIntent pendingIntent = createNotificationBroadcastPendingIntent();

        locationProvider
                .removeGeofences(pendingIntent)
                .flatMap(new Func1<Status, Observable<Status>>() {
                    @Override
                    public Observable<Status> call(Status pendingIntentRemoveGeofenceResult) {
                        return locationProvider.addGeofences(pendingIntent, geofencingRequest);
                    }
                })
                .subscribe(new Action1<Status>() {
                    @Override
                    public void call(Status addGeofenceResult) {
                        Log.d("City geo", "Geofence added, success: " + addGeofenceResult.isSuccess());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.d("City geo", "Error adding geofence.", throwable);
                    }
                });
    }

    private GeofencingRequest createGeofencingRequest(List<StoreLocation> storeLocationList) {
        List<Geofence> geofenceList = new ArrayList<>();
//        List<StoreLocation> storeLocationList = StoreLocation.getStoreLocation();
//        List<StoreLocation> storeLocationList = StoreLocationManager.getStoreLocation();

        for (StoreLocation storeL : storeLocationList) {
            Geofence geofence = new Geofence.Builder()
                    .setRequestId(storeL.id+","+storeL.name)
                    .setCircularRegion(storeL.latitude, storeL.longitude, storeL.radius)
                    .setExpirationDuration(Geofence.NEVER_EXPIRE)
                    .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                    .build();

            geofenceList.add(geofence);
        }

        return new GeofencingRequest.Builder().addGeofences(geofenceList).build();
    }

    private void clearGeofence(ReactiveLocationProvider locationProvider) {
        locationProvider
                .removeGeofences(createNotificationBroadcastPendingIntent())
                .subscribe(new Action1<Status>() {
                    @Override
                    public void call(Status status) {
                        Log.d("City geo", "Geofences removed");
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.d("City geo", "Error removing geofences", throwable);
                    }
                });
    }

    private PendingIntent createNotificationBroadcastPendingIntent() {
        return PendingIntent.getBroadcast(
                context,
                0,
                new Intent(context, GeofenceBroadcastReceiver.class),
                PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
