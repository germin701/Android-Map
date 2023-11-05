package com.example.geolocation;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import android.util.Log;
import android.content.Intent;
import android.net.Uri;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private TextView textView;
    private double doubleLat;
    private double doubleLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextTextPersonName);
        textView = findViewById(R.id.textView);
    }

    public void buttonGetCoordinates(View view){
        Geocoder geocoder = new Geocoder(this);
        List<Address> addressList;

        /*try {
            addressList = geocoder.getFromLocationName(editText.getText().toString(), 1);

            if (addressList != null){
                doubleLat = addressList.get(0).getLatitude();
                doubleLong = addressList.get(0).getLongitude();

                textView.setText("Latitude: " + String.valueOf(doubleLat) + " | " + "Longitude: " +
                        String.valueOf(doubleLong));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        try {
            addressList = geocoder.getFromLocationName(editText.getText().toString(), 1);

            if (addressList != null){
                doubleLat = addressList.get(0).getLatitude();
                doubleLong = addressList.get(0).getLongitude();

                textView.setText("Latitude: " + String.valueOf(doubleLat) + " | " + "Longitude: " +
                        String.valueOf(doubleLong));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*private void openLocationInMaps(double latitude, double longitude, String label) {
        Uri geoLocation = Uri.parse("geo:" + latitude + "," + longitude + "?q=" + Uri.encode(label));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, geoLocation);

        // Check if the Google Maps app is available and set the package name.
        mapIntent.setPackage("com.google.android.apps.maps");

        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            // Handle the case where the app is not installed or the action cannot be performed.
            Log.d("tag", "cannot");
        }
    }

    public void buttonGetCoordinates(View view){
        Geocoder geocoder = new Geocoder(this);
        List<Address> addressList;

        try {
            addressList = geocoder.getFromLocationName(editText.getText().toString(), 1);

            if (addressList != null){
                double doubleLat = addressList.get(0).getLatitude();
                double doubleLong = addressList.get(0).getLongitude();

                textView.setText("Latitude: " + String.valueOf(doubleLat)
                        + " | " + "Longitude: " + String.valueOf(doubleLong));

                // Open the location in Google Maps
                openLocationInMaps(doubleLat, doubleLong, "Location Label");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public void openMap(View v) {
        double Lat = 5.3416038;
        double Long = 100.2792958;
        String locationName = editText.getText().toString();
        Log.i("tag", doubleLat + "," + doubleLong);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse("geo:0,0?q=" + Uri.encode(editText.getText().toString()));
        /*Uri uri = Uri.parse("geo:" + doubleLat + "," + doubleLong + "?q=" + Uri.encode("Marker Label"));
        + "?q=" + Uri.encode("Marker Label")*/
        intent.setData(uri);
        Intent chooser = Intent.createChooser(intent, "Launch Maps");
        startActivity(chooser);
    }
}
