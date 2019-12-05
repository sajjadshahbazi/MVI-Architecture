package com.example.signupuser.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.example.signupuser.R
import com.example.signupuser.intent.MapIntent
import com.example.signupuser.model.CurrentLocationLocalModel
import com.example.signupuser.navigator.MapNavigator
import com.example.signupuser.state.MapState
import com.example.signupuser.viewmodels.MapViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.sharifin.base.BaseActivity
import com.sharifin.base.createViewModel
import com.sharifin.base.renderError
import com.sharifin.base.renderLoading
import io.reactivex.Observable
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.CameraPosition
import kotlinx.android.synthetic.main.activity_map.*
import com.google.android.gms.maps.SupportMapFragment
import com.jakewharton.rxbinding2.view.clicks


class MapActivity : BaseActivity<
        MapIntent,
        MapState,
        MapViewModel,
        MapNavigator
        >(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap

    override fun onMapReady(googleMap: GoogleMap) {
        this.mMap = googleMap
        val home = LatLng(35.735255, 51.291628)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(home, 18f))
        mMap.animateCamera(CameraUpdateFactory.zoomIn())
        mMap.animateCamera(CameraUpdateFactory.zoomTo(18f), 2000, null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        createViewModel()
        txtToolbarTitle.text = "موقعیت روی نقشه"
        btnToolbarBack.visibility = View.GONE
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapView) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun intents(): Observable<MapIntent> =
        Observable.merge(
            listOf(
                Observable.just(MapIntent.InitialIntent),
                confirmClick()
            )
        )

    fun confirmClick() : Observable<MapIntent.ConfirmIntent> =
        btnConfirm.clicks().map {
            MapIntent.ConfirmIntent(
                lat = mMap.getCameraPosition().target.latitude,
                lng = mMap.getCameraPosition().target.longitude
            )
        }

    override fun render(state: MapState) {
        renderLoading(state.base)
        renderError(state.base)
        if(state.goToFormView){
            val intent = Intent(this@MapActivity, ProfileActivity::class.java)
            intent.putExtra(
                PROFILE_ACTIVITY_TAG,
                CurrentLocationLocalModel(
                    lat = state.lat,
                    lng = state.lng
                ))
            startActivity(intent)
        }
    }
}