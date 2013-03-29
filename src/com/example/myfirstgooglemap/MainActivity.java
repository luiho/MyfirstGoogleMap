package com.example.myfirstgooglemap;

import org.xml.sax.Locator;

import com.google.android.gms.internal.a;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnMarkerClickListener, OnInfoWindowClickListener, OnMarkerDragListener {
	
	private GoogleMap map = null;
	private LocationManager locationManager = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		configMapOptions();
		
		Location location = getCurrentPosition();
		
		double latitude = location.getLatitude();
		double longtitude = location.getLongitude();
		
		LatLng lal = new LatLng(latitude, longtitude);
		CameraPosition cp = new CameraPosition.Builder().target(new LatLng(latitude, longtitude)).zoom(18).bearing(90).tilt(90).build();
		
		map.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
		
		MarkerOptions marker = new MarkerOptions().position(lal)
				.title("my point")
				.snippet("hello world")
				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
		map.addMarker(marker);
	}
	
	public void configMapOptions() {
		if(map == null) { Log.v("map", "is null");
			map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
			
			if (map != null) {
				Log.v("map", "not null");
				// setting map options
				map.setMyLocationEnabled(true);
				map.getUiSettings().setAllGesturesEnabled(true);
				map.getUiSettings().setCompassEnabled(true);
//				map.getUiSettings().setMyLocationButtonEnabled(true);
				map.getUiSettings().setRotateGesturesEnabled(true);
				map.getUiSettings().setScrollGesturesEnabled(true);
				map.getUiSettings().setTiltGesturesEnabled(true);
				map.getUiSettings().setZoomControlsEnabled(true);
				map.getUiSettings().setZoomGesturesEnabled(true);
				map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				map.setOnMarkerClickListener(this);
				map.setInfoWindowAdapter(new MyInfoWindow(this));
				map.setOnMarkerDragListener(this);
				map.setOnInfoWindowClickListener(this);
			}
		} else {
			Log.v("testing", "not null");
		}
	}
	
	public Location getCurrentPosition() {
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		
		boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		
		Criteria criteria = new Criteria();
		criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);
		criteria.setAccuracy(Criteria.NO_REQUIREMENT);
		String bestProvider = locationManager.getBestProvider(criteria, true);
		
		Location currentLocation = locationManager.getLastKnownLocation(bestProvider);
		
		return currentLocation;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onMarkerDrag(Marker arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMarkerDragEnd(Marker arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMarkerDragStart(Marker arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInfoWindowClick(Marker arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		// TODO Auto-generated method stub
		
		Log.v("info", marker.getSnippet());
		
		Toast.makeText(this, marker.getSnippet(), Toast.LENGTH_SHORT).show();
		
		
		return false;
	}

}
