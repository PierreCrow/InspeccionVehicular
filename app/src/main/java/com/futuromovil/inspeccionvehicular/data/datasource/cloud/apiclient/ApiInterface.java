package com.futuromovil.inspeccionvehicular.data.datasource.cloud.apiclient;

import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.inspection.response.WebServiceListActivities;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.inspection.response.WebServiceRegisterResponse;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.inspection.response.WsGeneralInfo;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WebServiceListBrands;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WebServiceListCities;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WebServiceListLocations;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WebServiceListModels;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WebServiceListVehicles;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.user.parameter.WsLoginParameter;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.user.response.WebServiceLogin;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.user.response.WebServiceQuantityPendingFrequency;
import com.futuromovil.inspeccionvehicular.presentation.utils.Constants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiInterface {

    @POST(Constants.URLS.LIST_BRANDS)
    Call<WebServiceListBrands> listBrands();

    @POST(Constants.URLS.LIST_MODELS)
    Call<WebServiceListModels> listModels();

    @POST(Constants.URLS.LIST_VEHICLES)
    Call<WebServiceListVehicles> listVehicles();

    @POST(Constants.URLS.LIST_LOCATION)
    Call<WebServiceListLocations> listLocations();

    @POST(Constants.URLS.LIST_CITY)
    Call<WebServiceListCities> listCities();

    @POST()
    Call<WebServiceListActivities> listActivitiesByIdVehicle(@Url String url);

    @POST()
    Call<WebServiceListVehicles> listVehiclesByModel(@Url String url);

    @POST(Constants.URLS.REGISTER_VECHICLE_FREQUENCY)
    Call<WebServiceRegisterResponse> registerVehicleFrequency(@Body WsGeneralInfo wsGeneralInfo);

    @POST(Constants.URLS.LOGIN)
    Call<WebServiceLogin> login(@Body WsLoginParameter wsLoginParameter);

    @POST(Constants.URLS.GET_FREQUENCY_QUANTITY)
    Call<WebServiceQuantityPendingFrequency> getQuantityPendingFrequency();

}
