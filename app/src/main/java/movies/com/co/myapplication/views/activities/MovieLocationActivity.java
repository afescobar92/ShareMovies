package movies.com.co.myapplication.views.activities;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import movies.com.co.myapplication.R;
import movies.com.co.myapplication.helper.Constants;
import movies.com.co.myapplication.helper.Utilities;
import movies.com.co.myapplication.model.Cinemas;
import movies.com.co.myapplication.model.LocationCinemax;
import movies.com.co.myapplication.model.MapMovie;


public class MovieLocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public String TAG = "MovieLocationActivity";
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;
    private FusedLocationProviderClient mFusedLocationClient;
    protected Location mLastLocation;
    private MapMovie mapMovie;
    LatLng myLocation = null;
    ArrayList<LatLng> pointsRoutes;
    int colorTrace = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        loadView();
        if(checkPlayServices()) {
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }else{
            //TODO
        }
    }

    private void loadView() {
        if(getIntent() != null) {
            mapMovie = (MapMovie) getIntent().getSerializableExtra(Constants.LIST_CINEMA);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if(Utilities.isNight()){
            mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this,R.raw.style_map_night));
            colorTrace = R.color.colorPrimary;
        }else{
            mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this,R.raw.style_map_standard));
           colorTrace = R.color.colorBlack;
        }
        mMap.clear();
        initMapUiSettings();
        List<Cinemas> locationList = this.mapMovie.getCinemas();
        ArrayList<LatLng> points = new ArrayList<>();
        myLocation = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        mMap.addMarker(new MarkerOptions().position(myLocation).title(getString(R.string.lblLocationUser)).icon(getBitMapFromVector(this, R.drawable.ic_location_movie)));
        points.add(myLocation);

        for (Cinemas cinema:locationList) {

            List<LocationCinemax> locationCinema = cinema.getLocationList();

            for (LocationCinemax location:locationCinema) {
                movies.com.co.myapplication.model.Location locationMap = location.getLocations();
                LatLng point = new LatLng(location.getLocations().getCoordinates()[0], location.getLocations().getCoordinates()[1]);
                mMap.addMarker(new MarkerOptions().position(point).title(location.getName()).icon(getBitMapFromVector(this,R.drawable.ic_location_movie)));
                points.add(point);
            }

        }

        createTraceRoute(points);
    }

    private void initMapUiSettings(){
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
    }

    private void createTraceRoute(ArrayList<LatLng> points){

        Routing routing = new Routing.Builder()
                .travelMode(AbstractRouting.TravelMode.DRIVING)
                .waypoints(points)
                .key(getString(R.string.google_maps_key))
                .optimize(true)
                .withListener(getRouteListener())
                .build();
        routing.execute();

        centerRoutes(points);

    }

    private void centerRoutes(ArrayList<LatLng> points) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        pointsRoutes = new ArrayList<>(points);
        for(LatLng latLng: pointsRoutes){
            builder.include(latLng);
        }
        LatLngBounds bounds = builder.build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds,50);
        mMap.animateCamera(cameraUpdate);
    }

    private RoutingListener getRouteListener(){

        RoutingListener routingListener = new RoutingListener() {

            @Override
            public void onRoutingFailure(RouteException e) {
                Log.e(TAG,e != null ?e.getMessage():"Error Routing Failure");
            }

            @Override
            public void onRoutingStart() {
                Log.e(TAG,"Routing Start");
            }

            @Override
            public void onRoutingSuccess(ArrayList<Route> routes, int shortestRoutsIndex) {

                ArrayList<Polyline> polylines = new ArrayList();

                for (Route route: routes){
                    PolylineOptions polylineOptions = new PolylineOptions();
                    polylineOptions.color(ContextCompat.getColor(getApplicationContext(),R.color.colorBlack));
                    polylineOptions.width(10);
                    polylineOptions.addAll(route.getPoints());
                    Polyline polyline = mMap.addPolyline(polylineOptions);
                    polylines.add(polyline);

                    int distance = route.getDistanceValue();
                    int duration = route.getDurationValue();

                    Toast.makeText(MovieLocationActivity.this, "Distance: "+distance + " Duration: "+duration, Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onRoutingCancelled() {
                Log.e(TAG,"Routing Cancelled");
            }
        };

        return routingListener;
    }

    public BitmapDescriptor getBitMapFromVector(Context context, int idVector) {
        Bitmap bitMap = null;

        Drawable drawableVector = ContextCompat.getDrawable(context,idVector);
        drawableVector.setBounds(0,0,drawableVector.getIntrinsicWidth(),drawableVector.getIntrinsicHeight());
        bitMap = Bitmap.createBitmap(drawableVector.getIntrinsicWidth(),drawableVector.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitMap);
        drawableVector.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitMap);
    }

    private PolylineOptions getCustomPolyLine(LatLng pointOne, LatLng pointTwo){
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.add(pointOne).add(pointTwo).width(6).color(Color.BLACK);
        return polylineOptions;
    }

    private boolean checkPlayServices(){

        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();

        int result = googleApiAvailability.isGooglePlayServicesAvailable(this);

        if(result != ConnectionResult.SUCCESS){
            if(googleApiAvailability.isUserResolvableError(result)){
                googleApiAvailability.getErrorDialog(this,result,5000).show();
            }
            return Boolean.FALSE;
        }

        return Boolean.TRUE;

    }


    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION);

        if (shouldProvideRationale) {
            Log.i(TAG, "Displaying permission rationale to provide additional context.");

            showSnackBar(R.string.permission_rationale, android.R.string.ok,
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startLocationPermissionRequest();
                        }
                    });

        } else {
            Log.i(TAG, "Requesting permission");
            startLocationPermissionRequest();
        }
    }

    @SuppressWarnings("MissingPermission")
    private void getLastLocation() {
        mFusedLocationClient.getLastLocation()
                .addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            mLastLocation = task.getResult();
                        } else {
                            Log.w(TAG, "getLastLocation:exception", task.getException());
                            showSnackBar(getString(R.string.no_location_detected));
                        }
                    }
                });
    }

    private void showSnackBar(final String text) {
        View container = findViewById(R.id.map);
        if (container != null) {
            Snackbar.make(container, text, Snackbar.LENGTH_LONG).show();
        }
    }

    private void showSnackBar(final int mainTextStringId, final int actionStringId,
                              View.OnClickListener listener) {
        Snackbar.make(findViewById(android.R.id.content),
                getString(mainTextStringId),
                Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(actionStringId), listener).show();
    }

    private void startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                REQUEST_PERMISSIONS_REQUEST_CODE);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!checkPermissions()) {
            requestPermissions();
        } else {
            getLastLocation();
        }
    }

}
