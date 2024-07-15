package com.example.anitratask

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.anitratask.databinding.ActivityMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.Marker

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

//    All the variables used for the further implementation
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var lastLocation: Location
//    fused location client is used for getting the location of the user precisely
    private lateinit var fusedLocationClient: FusedLocationProviderClient

//    a list is being initiated in order to make record of all the markers or location which is added by the user
    private val markers = mutableListOf<Marker>()

    companion object {
        private const val LOCATION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setting initial screen of the activity
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        in order to integrate Google Maps into an Android app fragment manager is used
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

//        initialising the fused location client
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

//        setting up the clear button use that what to do when that button is clicked
        binding.btnclrlocations.setOnClickListener {
            clearAllMarkersFromMap()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

//      enableling the zoom option on the map so that the user can zoom the map
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)

//        function used to locate the current location of the user
        setUpTheMap()

//      what will happen if the user long-press on the map
        mMap.setOnMapLongClickListener { latLng ->
            placeMarker(latLng)

//          setting the latitude and longitude to the text view in order to get location that user pressed
            binding.tvLatitude.text = latLng.latitude.toString()
            binding.tvLongitude.text = latLng.longitude.toString()
        }
    }

    private fun setUpTheMap() {

//        checking for the permission is granted or not
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
//            if permission not granted then setting it to true
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                LOCATION_REQUEST_CODE
            )
            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                placeMarker(currentLatLng)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
            } else {
                Toast.makeText(this, "Location not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

//    used to pin the marker of the loction selected
    private fun placeMarker(latLng: LatLng) {
        val markerOptions = MarkerOptions().position(latLng)
        markerOptions.title("$latLng")
        val marker = mMap.addMarker(markerOptions)
        marker?.let { markers.add(it) }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        return marker != null
    }

    private fun clearAllMarkersFromMap() {
        // Clear all markers except the current location marker
        for (marker in markers) {
            marker.remove()
        }
        markers.clear()
        setUpTheMap() // Reset current location marker
    }
}
