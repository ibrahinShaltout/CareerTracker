package com.example.ibrahimshaltout.test.signup;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibrahimshaltout.test.R;
import com.example.ibrahimshaltout.test.dataclass.CountriesListClass;
import com.example.ibrahimshaltout.test.signup.individual.IndividuaGeneralinfoActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;


public class YourLocation extends AppCompatActivity {



    AutoCompleteTextView locationCountriesSpinner;
    ArrayAdapter<String> arrayAdapterCountries;
    private int spinnerItemSelcectedCountries;
    private Button btnCountinueLocation;
    private FirebaseAuth auth;

    //Location
//    private static final String TAG = "CurrentLocationApp";
//    private GoogleApiClient mGoogleApiClient;
//    private Location mLastLocation;
//    private TextView mLatitudeText;
//    private TextView mLongitudeText;
//    private FirebaseDatabase mFirebaseDatabase;
//    private DatabaseReference mLocationDatabaseReference;
//    Button saveLocationToFirebase;
//    String value_lat = null;
//    String value_lng = null;
    //////////////////////////////////////////////////////////

    private Button b;
    private TextView t;
    private LocationManager locationManager;
    private LocationListener listener;

    CountriesListClass countriesListClass = new CountriesListClass();
    List countries = countriesListClass.Countries();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_location);


        auth = FirebaseAuth.getInstance();
        btnCountinueLocation = (Button) findViewById(R.id.countinue_location_button);
        arrayAdapterCountries = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, countries);

        locationCountriesSpinner = (AutoCompleteTextView) findViewById(R.id.locationSpinnerCountry);
        locationCountriesSpinner.setAdapter(arrayAdapterCountries);
        locationCountriesSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                spinnerItemSelcectedCountries = position;
                String x = CountryIs();
//                Toast.makeText(getApplicationContext(), x, Toast.LENGTH_SHORT).show();
            }
        });

        btnCountinueLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String individualId = auth.getUid();
                String listSkillString = locationCountriesSpinner.getText().toString();

                final String locationList[] = listSkillString.split(",");
                List listLocation = new ArrayList<String>(Arrays.asList(locationList));
                FirebaseDatabase.getInstance().getReference("Users").child(individualId).child("locationList").setValue(listLocation);

                Intent intent = new Intent(YourLocation.this, IndividuaGeneralinfoActivity.class);
                startActivity(intent);
                finish();

            }
        });

//location
//        FirebaseApp.initializeApp(this);
//        mFirebaseDatabase = FirebaseDatabase.getInstance();
//        mLocationDatabaseReference = mFirebaseDatabase.getReference().child("my current location");
//        mLatitudeText = (TextView) findViewById((R.id.latitude_text));
//        mLongitudeText = (TextView) findViewById((R.id.longitude_text));
//        saveLocationToFirebase = (Button) findViewById(R.id.save_location);
//        buildGoogleApiClient();


        t = (TextView) findViewById(R.id.textView);
        b = (Button) findViewById(R.id.button);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                t.append("\n " + location.getLongitude() + " " + location.getLatitude());
            }
            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            }
            @Override
            public void onProviderEnabled(String s) {
            }
            @Override
            public void onProviderDisabled(String s) {

                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };

        configure_button();
    }

    LocationListener locationListener = new android.location.LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            // Push your location to FireBase
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };




    private String CountryIs() {
        String theLevel = "null";
        for (int i = 0; i < countries.size(); i++) {
            if (spinnerItemSelcectedCountries == i) {
                theLevel = (String) countries.get(i);
            }
        }
        return (theLevel);
    }


//location
//    protected synchronized void buildGoogleApiClient() {
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .addApi(LocationServices.API)
//                .build();
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        mGoogleApiClient.connect();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if (mGoogleApiClient.isConnected()) {
//            mGoogleApiClient.disconnect();
//        }
//    }
//    @Override
//    public void onConnected(Bundle connectionHint) {
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
//
//        if (mLastLocation != null) {
//
//            value_lat= String.valueOf(mLastLocation.getLatitude());
//            value_lng =String.valueOf(mLastLocation.getLongitude());
//            mLatitudeText.setText(value_lat);
//            mLongitudeText.setText(value_lng);
//
//            saveLocationToFirebase.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    mLocationDatabaseReference.push().setValue("Latitude : "+value_lat +"  & Longitude : "+value_lng);
//                    Toast.makeText(YourLocation.this ,"Location saved to the Firebasedatabase",Toast.LENGTH_LONG).show();
//                }
//            });
//        }
//    }
//    @Override
//    public void onConnectionFailed(ConnectionResult result) {
//        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
//    }
//    @Override
//    public void onConnectionSuspended(int cause) {
//        Log.i(TAG, "Connection suspended");
//        mGoogleApiClient.connect();
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu top_bar_menu) {
//        getMenuInflater().inflate(R.top_bar_menu.menu_main, top_bar_menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
////        if (id == R.id.action_settings) {
////            return true;
////        }
//        return super.onOptionsItemSelected(item);
//    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                configure_button();
                break;
            default:
                break;
        }
    }

    void configure_button(){
        // first check for permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}
                        ,10);
            }
            return;
        }

        // this code won't execute IF permissions are not allowed, because in the line above there is return statement.
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //noinspection MissingPermission
                locationManager.requestLocationUpdates("gps", 5000, 0, listener);
            }
        });
    }
}
