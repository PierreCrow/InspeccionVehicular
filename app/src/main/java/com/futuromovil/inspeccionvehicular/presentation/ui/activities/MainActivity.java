package com.futuromovil.inspeccionvehicular.presentation.ui.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.futuromovil.inspeccionvehicular.R;
import com.futuromovil.inspeccionvehicular.presentation.ui.fragments.TabHome;
import com.futuromovil.inspeccionvehicular.presentation.utils.Constants;

public class MainActivity extends BaseActivity {

    FrameLayout containerView;
    public static Context context;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 120;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        injectView();
        validateAndOpenGallery();

        /*
        if(hasLocationPermission())
        {
            initUI();
            loadTabHomeFragment();
        }
        else
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        }

         */

    }



    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {


        if(requestCode==Constants.REQUEST_CODES.REQUEST_CODE_STORAGE)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                   initUI();
                   loadTabHomeFragment();

            }
            else
            {
             //   Toast.makeText(getApplicationContext(), "Debe permitir rastrear su ubicación", Toast.LENGTH_SHORT).show();
                initUI();
                loadTabHomeFragment();
            }
        }


    }


    public boolean hasLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            return false;
        } else {
            return true;
        }
    }

    public boolean hasStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        ) {
            return false;
        } else {
            return true;
        }
    }

    void validateAndOpenGallery() {
        if (!hasStoragePermission()) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE},
                    Constants.REQUEST_CODES.REQUEST_CODE_STORAGE);
        } else {
            initUI();
            loadTabHomeFragment();
        }
    }


    void validadAndOpenLocation()
    {
        if(hasLocationPermission())
        {
            initUI();
            loadTabHomeFragment();
        }
        else
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        }
    }

    public boolean hasCallPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED
        ) {
            return false;
        } else {
            return true;
        }
    }

    private void initUI() {
        containerView = (FrameLayout) findViewById(R.id.containerView);
    }

    void loadTabHomeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TabHome homeFragment = new TabHome();
        fragmentTransaction.replace(R.id.containerView, homeFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {

    }



}