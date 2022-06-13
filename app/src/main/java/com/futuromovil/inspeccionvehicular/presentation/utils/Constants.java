package com.futuromovil.inspeccionvehicular.presentation.utils;

public class Constants {

    //region WebServicesConection
    //---------------------------------------------------------------------------------------

    public class URLS {
        public static final String URL_BASE = "http://hitzversolution-001-site1.dtempurl.com/";
        public static final String LIST_LOCATION = "Maestros/ObtenerUbicacion";
        public static final String LIST_CITY = "Maestros/ObtenerCiudad";
        public static final String LIST_BRANDS = "Maestros/ObtenerMarcasMov";
        public static final String LIST_MODELS = "Maestros/ObtenerModelosMov";
        public static final String LIST_VEHICLES = "Vehiculo/ObtenerVehiculos";
        public static final String LIST_VEHICLES_BY_MODEL = "/Vehiculo/ObtenerVehiculoPorModelo?idModelo=";
        public static final String LIST_ACTIVITIES_BY_IDVEHICLE = "Vehiculo/ObtenerVehiculosFrecuenciaActivoPorIdVehiculo?idVehiculo=";
        public static final String REGISTER_VECHICLE_FREQUENCY = "Vehiculo/RegistrarVehiculoFrecuencia";
        public static final String LOGIN = "Seguridad/LoginMov";
        public static final String GET_FREQUENCY_QUANTITY = "Vehiculo/ObtenerFrecuenciasProgramadasMes";
    }

    public static class STORE {
        public static final int CLOUD = 0;
        public static final int DB = 1;
    }

    //---------------------------------------------------------------------------------------
    //endregion

    public class HTTPS_CODE_RESPONSE {
        public static final int OK = 200;
        public static final int UNAUTHORIZED = 401;
        public static final int NOT_FOUND = 404;
        public static final int INTERNAL_SERVER_ERROR = 500;
    }

    public class HTTPS_MESSAGE_RESPONSE {
        public static final String UNAUTHORIZED_CODE = "31";
        public static final String UNAUTHORIZED = "Su sesión ha caducado (401)";
        public static final String NOT_FOUND = "Enlace muerto (404) ";
        public static final String INTERNAL_SERVER_ERROR = "Error interno del servidor (500)";
        public static final String UNKNOWN_ERROR = "Error desconocido";
        public static final String NOT_INTERNET = "No tienes Internet";
    }

    public class PREFERENCES {
        public static final String PREFERENCE_CURRENT_CLIENT = "PREFERENCE_CURRENT_USER";
    }

    public class PREFERENCES_KEYS {
        public static final String CURRENT_CLIENT_ID = "CURRENT_USER_ID";
        public static final String CURRENT_CLIENT_USER = "CURRENT_USER_USER";
        public static final String CURRENT_USER_FULL_NAME = "CURRENT_USER_FULL_NAME";
        public static final String CURRENT_USER_EMAIL = "CURRENT_USER_EMAIL";
        public static final String CURRENT_USER_PASSWORD = "CURRENT_USER_PASSWORD";
        public static final String CURRENT_USER_TOKEN = "CURRENT_USER_TOKEN";
        public static final String CURRENT_USER_LOGGED = "CURRENT_USER_LOGGED";
        public static final String CURRENT_USER_PHOTO_LOADED = "CURRENT_USER_PHOTO_LOADED";
        public static final String CURRENT_USER_FOTO1 = "CURRENT_USER_FOTO1";
        public static final String CURRENT_USER_FOTO2 = "CURRENT_USER_FOTO2";
        public static final String CURRENT_USER_FOTO3 = "CURRENT_USER_FOTO3";
    }

    public class FRAGMENTS_TABS {
        public static final int HOME = 0;
        public static final int OPERACIONES = 1;
        public static final int ACCOUNT = 2;
    }

    public static class REQUEST_CODES {
        public static final int REQUEST_CODE_LOCATION = 123;
        public static final int REQUEST_CODE_STORAGE = 124;
        public static final int REQUEST_CODE_CALENDAR = 789;
        public static final int REQUEST_CODE_CAMERA = 966;
    }

    public class UNIDADES_MEDIDA {
        public static final int EMPTY = 0;
    }

}



