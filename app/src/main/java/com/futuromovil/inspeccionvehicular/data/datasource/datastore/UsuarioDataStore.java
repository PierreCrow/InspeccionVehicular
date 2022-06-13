package com.futuromovil.inspeccionvehicular.data.datasource.datastore;

import com.futuromovil.inspeccionvehicular.domain.repository.RepositoryCallback;

public interface UsuarioDataStore {

    void login(String email, String password, RepositoryCallback repositoryCallback);

    void quantityPendingFrequency(RepositoryCallback repositoryCallback);

}
