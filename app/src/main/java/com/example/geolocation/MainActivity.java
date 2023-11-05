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
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private TextView textView;
    private double doubleLat;
    private double doubleLong;
    private Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextTextPersonName);
        textView = findViewById(R.id.textView);
        geocoder = new Geocoder(this, Locale.getDefault());
    }

    public void buttonGetCoordinates(View view){
        Geocoder geocoder = new Geocoder(this);
        List<Address> addressList;
        try {
            addressList = geocoder.getFromLocationName(editText.getText().toString(), 1);

            if (addressList != null){
                doubleLat = addressList.get(0).getLatitude();
                doubleLong = addressList.get(0).getLongitude();

                textView.setText("Latitude: " + doubleLat + " | " + "Longitude: " +
                        doubleLong);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openMap(View v) {
        String locationName = editText.getText().toString();

        try {
            List<Address> addresses = geocoder.getFromLocationName(locationName, 1);
            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);
                doubleLat = address.getLatitude();
                doubleLong = address.getLongitude();
                Log.i("tag", doubleLat + "," + doubleLong);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("geo:" + doubleLat + "," + doubleLong + "?q=" + Uri.encode(locationName));
                intent.setData(uri);
                Intent chooser = Intent.createChooser(intent, "Launch Maps");
                startActivity(chooser);
            } else {
                // Handle the case where the location doesn't exist
                textView.setText("Location not found. Please enter a valid location.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void validateLocation(View v) {
        String locationName = editText.getText().toString();
        Geocoder geocoder = new Geocoder(this);

        try {
            List<Address> addresses = geocoder.getFromLocationName(locationName, 1);

            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);
                doubleLat = address.getLatitude();
                doubleLong = address.getLongitude();
                Log.i("tag", "Location exists: " + address.getFeatureName());
            } else {
                Log.i("tag", "Location does not exist.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("tag", "Error while validating location.");
        }
    }
}
