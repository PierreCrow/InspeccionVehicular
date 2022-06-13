package com.futuromovil.inspeccionvehicular.data.datasource.db.store;

import android.content.Context;

import com.futuromovil.inspeccionvehicular.data.datasource.datastore.UsuarioDataStore;
import com.futuromovil.inspeccionvehicular.domain.repository.RepositoryCallback;

public class DbUsuarioDataStore implements UsuarioDataStore {

    public DbUsuarioDataStore(Context context) {
    }

    @Override
    public void login(String email, String password, RepositoryCallback repositoryCallback) {
    }

    @Override
    public void quantityPendingFrequency(RepositoryCallback repositoryCallback) {
    }

}
