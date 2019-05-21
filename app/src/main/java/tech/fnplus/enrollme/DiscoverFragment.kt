package tech.fnplus.enrollme


import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.location.LocationRequest.*
import com.google.android.gms.tasks.Task
import org.jetbrains.anko.toast
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

private const val REQUEST_CHECK_SETTINGS: Int = 12
private const val RC_LOCATION = 21


class DiscoverFragment : Fragment() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_discover, container, false)
        // Inflate the layout for this fragment
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this@DiscoverFragment.activity!!)

        requestUserLocation()

        return rootView
    }

    @AfterPermissionGranted(RC_LOCATION)
    fun requestUserLocation() {

        val perms = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
        if (EasyPermissions.hasPermissions(this.context!!, *perms)) {

            createLocationRequest()
        }
        else {
            EasyPermissions.requestPermissions(this@DiscoverFragment.activity!!, "Required to obtain your current location",  RC_LOCATION, *perms)
        }
    }

    @SuppressLint("MissingPermission")
    private fun createLocationRequest() {
        val locationRequest = create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = PRIORITY_HIGH_ACCURACY
        }

        val builder = LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)

        val client: SettingsClient = LocationServices.getSettingsClient(this@DiscoverFragment.activity!!)
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())


        task.addOnSuccessListener {
            // All location settings are satisfied. The client can initialize
            // location requests here.
            // ...
            fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: android.location.Location? ->
                        // Got last known location. In some rare situations this can be null.
                        if (location == null) {
                            createLocationRequest()

                        }

                        else {
                            this@DiscoverFragment.activity!!.toast("${location?.latitude} ${location?.longitude}")

                        }
                    }
        }

        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    exception.startResolutionForResult(this@DiscoverFragment.activity!!,
                            REQUEST_CHECK_SETTINGS)
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CHECK_SETTINGS && resultCode == Activity.RESULT_OK) {

            createLocationRequest()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)

    }


}
