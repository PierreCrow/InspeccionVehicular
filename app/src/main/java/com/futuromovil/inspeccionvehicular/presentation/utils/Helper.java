package com.futuromovil.inspeccionvehicular.presentation.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.widget.ImageView;

import com.futuromovil.inspeccionvehicular.domain.model.Inspector;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Normalizer;

public class Helper {


    public static Inspector getUserPreference(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.PREFERENCES.PREFERENCE_CURRENT_CLIENT, Context.MODE_PRIVATE);
        Inspector userPreference =
                new Inspector(preferences.getInt(Constants.PREFERENCES_KEYS.CURRENT_CLIENT_ID, 0),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_CLIENT_USER, "test"),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_FULL_NAME, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_EMAIL, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_PASSWORD, "1234"),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_TOKEN, ""),
                        preferences.getBoolean(Constants.PREFERENCES_KEYS.CURRENT_USER_LOGGED, false),
                        preferences.getBoolean(Constants.PREFERENCES_KEYS.CURRENT_USER_PHOTO_LOADED, false),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_FOTO1, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_FOTO2, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_FOTO3, ""));

        return userPreference;
    }


    public static void saveUserPreference(Context context, Inspector userPreference) {
        SharedPreferences preferenciasssee = context.getSharedPreferences(Constants.PREFERENCES.PREFERENCE_CURRENT_CLIENT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editoriieei = preferenciasssee.edit();
        editoriieei.putInt(Constants.PREFERENCES_KEYS.CURRENT_CLIENT_ID, userPreference.getId());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_CLIENT_USER, userPreference.getUser());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_FULL_NAME, userPreference.getFull_name());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_EMAIL, userPreference.getMail());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_PASSWORD, userPreference.getPassword());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_TOKEN, userPreference.getToken());
        editoriieei.putBoolean(Constants.PREFERENCES_KEYS.CURRENT_USER_LOGGED, userPreference.isLogged());
        editoriieei.putBoolean(Constants.PREFERENCES_KEYS.CURRENT_USER_PHOTO_LOADED, userPreference.isFotoLoaded());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_FOTO1, userPreference.getFoto1());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_FOTO2, userPreference.getFoto2());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_FOTO3, userPreference.getFoto3());
        editoriieei.apply();
    }


    public static void urlToImageView(String urlFoto, ImageView imagev, Context contexto) {
        Glide.with(contexto)
                .load(urlFoto)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //.override(100, 200)
                .fitCenter()
                .into(imagev);
    }

    public static boolean gpsIsEnabled(Context context) {
        final LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isEmailValid(String email) {
        return !(email == null || TextUtils.isEmpty(email)) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Boolean conectado = null;
        if (connectivity != null) {
            NetworkInfo activeNetwork = connectivity.getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnectedOrConnecting())
                conectado = true;
            else {
                conectado = false;
            }
        } else {
            conectado = false;
        }
        return conectado;
    }

    public static String convertTwoDecimals(float number) {
        BigDecimal bd = new BigDecimal(number);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        String converted = Double.toString(bd.doubleValue());
        return converted;
    }

    public static String convertTwoDecimals(Double number) {
        BigDecimal bd = new BigDecimal(number);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        String converted = Double.toString(bd.doubleValue());
        return converted;
    }

    public static String convertREallyTwoDecimals(Double number) {
        BigDecimal bd = new BigDecimal(number);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        String converted = Double.toString(bd.doubleValue());
        return converted;
    }

    public static String limpiarAcentos(String cadena) {
        String limpio = null;
        String valor = cadena.toUpperCase();

        // Normalizar texto para eliminar acentos, dieresis y tildes
        limpio = Normalizer.normalize(valor, Normalizer.Form.NFD);

        // Quitar caracteres no ASCII excepto la enie, interrogacion que abre, exclamacion que abre, grados, U con dieresis.
        limpio = limpio.replaceAll("[^\\p{ASCII}(N\u0303)(n\u0303)(\u00A1)(\u00BF)(\u00B0)(U\u0308)(u\u0308)]", "");

        // Regresar a la forma compuesta, para poder comparar la enie con la tabla de valores
        limpio = Normalizer.normalize(limpio, Normalizer.Form.NFC);
        limpio = limpio.toLowerCase();

        limpio.trim();

        return limpio;
    }

}
