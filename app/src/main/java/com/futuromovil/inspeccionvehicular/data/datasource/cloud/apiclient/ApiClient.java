package com.futuromovil.inspeccionvehicular.data.datasource.cloud.apiclient;

import com.futuromovil.inspeccionvehicular.presentation.utils.Constants;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit;

    public static ApiInterface getApiClient(String token) {

        //  TokenAuthenticator tokenAuthenticator = new TokenAuthenticator();

        String url;

        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(1);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
             //   .addInterceptor(new BasicAuthInterceptor("Bearer "+token))
                .addNetworkInterceptor(new StethoInterceptor())
                //     .authenticator(tokenAuthenticator)
                .build();

        //  okHttpClient.interceptors().add(interceptor);

        //  if(retrofit == null){

    /*    if (vouchers) {
            url = Constantes.URLS.URL_BASE_VOUCHERS;
        } else {
            url = Constantes.URLS.URL_BASE;
        }
*/
        url = Constants.URLS.URL_BASE;
        // Gson gson = new GsonBuilder().serializeNulls().create();
        Gson gson = new GsonBuilder()
                // .setLenient()
                // .serializeNulls()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //   }
        return retrofit.create(ApiInterface.class);
    }


}
