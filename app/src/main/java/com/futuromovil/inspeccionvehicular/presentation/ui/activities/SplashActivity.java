package com.futuromovil.inspeccionvehicular.presentation.ui.activities;

import android.os.Bundle;
import android.os.Handler;

import com.futuromovil.inspeccionvehicular.R;
import com.futuromovil.inspeccionvehicular.presentation.utils.Helper;

public class SplashActivity extends BaseActivity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_dialog);
        timerSplash();

    }




    void timerSplash() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //HACER CONDICION SI YA SE LOGUIO
                if (Helper.getUserPreference(getApplicationContext()).isLogged()) {
                    next(MainActivity.class, null);
                } else {
                    next(LoginActivity.class, null);
                }
            }
        }, 3000);
    }



    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {

    }


}