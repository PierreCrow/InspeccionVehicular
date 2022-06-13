package com.futuromovil.inspeccionvehicular.interactor.master;

import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsBrand;

import java.util.List;

public interface ListBrandsCallback {

    void onListBrandsSuccess(List<WsBrand> wsBrands);

    void onListBrandsError(String message);
}
