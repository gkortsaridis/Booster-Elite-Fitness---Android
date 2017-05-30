package com.georgekortsaridis.boosterelitefitness;


import android.*;
import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class ContactFragment extends Fragment {

    MapView mapViewToumpa,mapViewKalamaria;
    TextView toumpaAddress,toumpaPhone,kalamariaAddress,kalamariaPhone;


    public ContactFragment() {
        // Required empty public constructor
    }

    LatLng boxKalamaria = new LatLng(40.6163138,22.9635971);
    LatLng boxToumpa = new LatLng(40.5672566,22.956897);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        toumpaAddress = (TextView) view.findViewById(R.id.addressToumpa);
        toumpaPhone = (TextView) view.findViewById(R.id.phoneToumpa);
        kalamariaAddress = (TextView) view.findViewById(R.id.addressKalamaria);
        kalamariaPhone = (TextView) view.findViewById(R.id.phoneKalamaria);

        toumpaAddress.setText("Ύδρας 7, 54453, Τούμπα, Θεσσαλονίκη");
        toumpaPhone.setText("(+30) 2310.457.378");
        kalamariaAddress.setText("Λυσίου 4, 55132, Καλαμαριά");
        kalamariaPhone.setText("(+30) 2310.457.378");

        toumpaAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapsToLatLng(boxToumpa);
            }
        });

        toumpaPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone("2310457378");
            }
        });

        kalamariaAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapsToLatLng(boxKalamaria);
            }
        });

        kalamariaPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone("+302310457378");
            }
        });

        // Gets the MapView from the XML layout and creates it
        mapViewToumpa = (MapView) view.findViewById(R.id.mapviewToumpa);
        mapViewToumpa.onCreate(savedInstanceState);
        mapViewToumpa.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                MapsInitializer.initialize(getActivity());
                googleMap.addMarker(new MarkerOptions().position(boxToumpa).title("BOX Τούμπας"));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(boxToumpa, 15));
            }

        });

        // Gets the MapView from the XML layout and creates it
        mapViewKalamaria = (MapView) view.findViewById(R.id.mapviewKalamaria);
        mapViewKalamaria.onCreate(savedInstanceState);
        mapViewKalamaria.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                MapsInitializer.initialize(getActivity());
                googleMap.addMarker(new MarkerOptions().position(boxKalamaria).title("BOX Καλαμαριάς"));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(boxKalamaria, 15));
            }

        });



        return view;
    }

    public void openMapsToLatLng(LatLng pos){

    }

    public void callPhone(final String phone){

        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.CALL_PHONE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                        startActivity(intent);
                    }
                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {/* ... */}
                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
                }).check();
    }

    @Override
    public void onResume() {
        mapViewToumpa.onResume();
        mapViewKalamaria.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapViewToumpa.onDestroy();
        mapViewKalamaria.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapViewKalamaria.onLowMemory();
        mapViewToumpa.onLowMemory();
    }


}
