package gmaps.com.ejemplo;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;


import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class ventana extends MapActivity implements LocationListener{

	MapController mControl;
	GeoPoint GeoP;
	MapView mapV;
	
	private String bestProvider;
	private LocationManager lm;
	@Override
	protected void onCreate(Bundle icicle) {
		// TODO Auto-generated method stub
		super.onCreate(icicle);
		setContentView(R.layout.main);
		
		mapV = (MapView) findViewById(R.id.mapavista); //mapV.setSatellite(true);
        Log.v("Droidnova", "1");
        mapV.displayZoomControls(true);
        Log.v("Droidnova", "2");
        mapV.setBuiltInZoomControls(true);
        Log.v("Droidnova", "3");
        double lat = -12.1667 ;
        double longi = -76.9667 ;

        GeoP = new GeoPoint((int)(lat*1E6), (int)(longi*1E6));

        mControl = mapV.getController();

        mControl.animateTo(GeoP);

        mControl.setZoom(15);
        
        //Definir locationmanager
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		//lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		
        //Obtener todos los proveedores
        List<String> providers = lm.getAllProviders();
        Log.v("Droidnova","Best provider: "+providers.size());
        //Criterio de búsqueda
        Criteria crta = new Criteria();
        crta.setAccuracy(Criteria.ACCURACY_FINE);
        crta.setAltitudeRequired(false);
        crta.setBearingRequired(false);
        crta.setCostAllowed(true);
        crta.setPowerRequirement(Criteria.POWER_LOW); 
        
        //Obtener proveedor
        bestProvider = lm.getBestProvider(crta, true);
        Log.v("Droidnova","Best provider: "+bestProvider);
//        Location location = lm.getLastKnownLocation(bestProvider);
        lm.requestLocationUpdates(bestProvider, 0, 0, this);
        
        Log.v("Droidnova","Best provider: "+bestProvider);
	}
	
	

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
		if (location != null) {
			int  lat = (int)(location.getLatitude()*1E6);
			int  lng = (int)(location.getLongitude()* 1E6);
			//String currentLocation = "The location is changed to Lat: " + lat + " Lng: " + lng;
			//myLoc.setText(currentLocation);
			GeoPoint GeoP2 = new GeoPoint(lat,lng);
			Log.v("Droidnova", "Latitud: "+GeoP2.getLatitudeE6()+" -- Longitud: "+GeoP2.getLongitudeE6());
			mControl.animateTo(GeoP2);
			mControl.setZoom(15);
		}		
		
	}

	
	
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	
    
}