package com.futuromovil.inspeccionvehicular.data.datasource.datastore;

import android.content.Context;

import com.futuromovil.inspeccionvehicular.data.datasource.cloud.store.CloudInspectionDataStore;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.store.CloudMasterDataStore;
import com.futuromovil.inspeccionvehicular.data.datasource.db.store.DbInspectionDataStore;
import com.futuromovil.inspeccionvehicular.data.datasource.db.store.DbMasterDataStore;

public class InspectionDataStoreFactory {

    public static final int DB = 1;
    public static final int CLOUD = 0;

    private final Context context;

    public InspectionDataStoreFactory(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        this.context = context.getApplicationContext();
    }


    public InspectionDataStore create(int dataSource) {
        InspectionDataStore inspectionDataStore = null;

        switch (dataSource) {
            case CLOUD:
                inspectionDataStore = createCloudDataStore();
                break;
            case DB:
                inspectionDataStore = new DbInspectionDataStore(context);
                break;
        }
        return inspectionDataStore;
    }

    private InspectionDataStore createCloudDataStore() {
        return new CloudInspectionDataStore();
    }

}
