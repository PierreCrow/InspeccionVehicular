package com.futuromovil.inspeccionvehicular.presentation.ui.fragments.createinspeccion;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.futuromovil.inspeccionvehicular.R;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.inspection.response.WsActivity;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.inspection.response.WsGeneralInfo;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsBrand;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsCity;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsLocation;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsModel;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsVehicle;
import com.futuromovil.inspeccionvehicular.domain.model.ObjetoPrueba;
import com.futuromovil.inspeccionvehicular.presentation.presenter.InspectionPresenter;
import com.futuromovil.inspeccionvehicular.presentation.presenter.MasterPresenter;
import com.futuromovil.inspeccionvehicular.presentation.ui.activities.CreateInspectionActivity;
import com.futuromovil.inspeccionvehicular.presentation.ui.dialogfragment.SearchVinlDialog;
import com.futuromovil.inspeccionvehicular.presentation.ui.fragments.BaseFragment;
import com.futuromovil.inspeccionvehicular.presentation.utils.Constants;
import com.futuromovil.inspeccionvehicular.presentation.utils.Helper;
import com.futuromovil.inspeccionvehicular.presentation.utils.TransparentProgressDialog;
import com.futuromovil.inspeccionvehicular.presentation.view.InspectionView;
import com.futuromovil.inspeccionvehicular.presentation.view.MasterView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

public class DatosInicialesFragment extends BaseFragment implements MasterView, InspectionView {

    @BindView(R.id.spiUbicacion)
    Spinner spiUbicacion;
    @BindView(R.id.spiCiudad)
    Spinner spiCiudad;
    @BindView(R.id.spiMarca)
    Spinner spiMarca;
    @BindView(R.id.spiModelo)
    Spinner spiModelo;
    // @BindView(R.id.tvVIN)
    public static TextView tvVIN;
    @BindView(R.id.etOdometro)
    EditText etOdometro;
    @BindView(R.id.tvFechaRecepción)
    TextView tvFechaRecepción;
    @BindView(R.id.tvFechaEntrega)
    TextView tvFechaEntrega;
    @BindView(R.id.tvFechaRevision)
    TextView tvFechaRevision;
    @BindView(R.id.tvSiguiente)
    LinearLayout tvSiguiente;

    List<WsBrand> marcas;
    List<WsModel> modelos;
    public static List<WsVehicle> vehiculos;
    List<WsModel> modelosFiltered;

    List<WsLocation> ubicaciones;
    List<WsCity> ciudades;

    MasterPresenter masterPresenter;
    InspectionPresenter inspectionPresenter;
    final Calendar myCalendar = Calendar.getInstance();
    TransparentProgressDialog loading;

    int idUbicacion, idCiudad, odometro, idUnidadMedida, idUsuarioMod;
    public static int idVehiculo;
    public static ArrayList<Integer> tiposAcividad;

    String fechaRecepcion, fechaEntrega, fechaRevision;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View x = inflater.inflate(R.layout.datos_iniciales, null);
        injectView(x);
        initUI(x);
        return x;
    }


    void initUI(View x) {
        if (!TabRegistrarInspeccion.alreadyLoaded) {

            fechaRecepcion = "";
            fechaEntrega = "";
            fechaRevision = "";

            tvVIN = (TextView) x.findViewById(R.id.tvVIN);

            idUbicacion = Constants.UNIDADES_MEDIDA.EMPTY;
            idCiudad = Constants.UNIDADES_MEDIDA.EMPTY;
            idVehiculo = Constants.UNIDADES_MEDIDA.EMPTY;
            odometro = Constants.UNIDADES_MEDIDA.EMPTY;
            idUnidadMedida = Constants.UNIDADES_MEDIDA.EMPTY;
            idUsuarioMod = Constants.UNIDADES_MEDIDA.EMPTY;

            loading = new TransparentProgressDialog(getContext());

            masterPresenter = new MasterPresenter();
            masterPresenter.addView(this);

            inspectionPresenter = new InspectionPresenter();
            inspectionPresenter.addView(this);

            if (Helper.isConnectedToInternet(getContext())) {
                if (!loading.isShowing()) {
                    loading.show();
                }
                masterPresenter.listLocations();

                spinnerEvents();
                clickListener();
                loadDatePickerRecepcion();
                loadDatePickerEntrega();
                loadDatePickerRevision();
                //  setSpinners();

            } else {
                Toast.makeText(getContext(), "No tienes Internet", Toast.LENGTH_SHORT).show();
            }


        }


    }


    void clickListener() {

        tvSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validations()) {
                    listActivities();
                } else {
                }

            }
        });


        tvVIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSearchableVIN();
            }
        });


    }


    boolean validations() {
        boolean everythingOK = true;

        if (idUbicacion == Constants.UNIDADES_MEDIDA.EMPTY) {
            everythingOK = false;
            Toast.makeText(getContext(), "Escoja ubicación", Toast.LENGTH_SHORT).show();
        } else {
            if (idCiudad == Constants.UNIDADES_MEDIDA.EMPTY) {
                everythingOK = false;
                Toast.makeText(getContext(), "Escoja ciudad", Toast.LENGTH_SHORT).show();
            } else {

                if (idVehiculo == Constants.UNIDADES_MEDIDA.EMPTY) {
                    everythingOK = false;
                    Toast.makeText(getContext(), "Escoja VIN", Toast.LENGTH_SHORT).show();
                } else {

                    if (etOdometro.getText().toString().trim().equals("")) {
                        everythingOK = false;
                        Toast.makeText(getContext(), "Ingrese odómetro", Toast.LENGTH_SHORT).show();
                    } else {
                        if (tvFechaRecepción.getText().toString().trim().equals("")) {
                            everythingOK = false;
                            Toast.makeText(getContext(), "Ingrese fecha recepción", Toast.LENGTH_SHORT).show();
                        } else {
                            if (tvFechaEntrega.getText().toString().trim().equals("")) {
                                everythingOK = false;
                                Toast.makeText(getContext(), "Ingrese fecha entrega", Toast.LENGTH_SHORT).show();
                            } else {
                                if (tvFechaRevision.getText().toString().trim().equals("")) {
                                    everythingOK = false;
                                    Toast.makeText(getContext(), "Ingrese fecha revisión", Toast.LENGTH_SHORT).show();
                                } else {
                                    everythingOK = true;
                                }
                            }
                        }


                    }
                }

            }

        }

        return everythingOK;
    }


    void listActivities() {

        if (!loading.isShowing()) {
            loading.show();
        }
          Integer idVeh=idVehiculo;
        //Integer idVeh = 1;
        inspectionPresenter.listActivitiesByIdVehicle(idVeh.toString());
    }


    //region WebServiceResponse

    @Override
    public void listBrandsSucces(List<WsBrand> wsBrands) {
        masterPresenter.listModels();
        marcas = wsBrands;
        setSpinnerMarcas(wsBrands, spiMarca, getContext());
    }

    @Override
    public void listModelsSucces(List<WsModel> wsModels) {
        masterPresenter.listVehicles();
        modelos = wsModels;
    }

    @Override
    public void listVehiclesSucces(List<WsVehicle> wsVehicles) {
        vehiculos = wsVehicles;
        //  setSpinnerVehicle(wsVehicles, spiVIN, getContext());
        if (loading.isShowing()) {
            loading.hide();
        }
    }

    @Override
    public void listLocationsSucces(List<WsLocation> wsLocations) {
        masterPresenter.listCities();
        ubicaciones = wsLocations;
        setSpinnerLocations(wsLocations, spiUbicacion, getContext());
    }

    @Override
    public void listCitiesSucces(List<WsCity> wsCities) {
        masterPresenter.listBrands();
        ciudades = wsCities;
        setSpinnerCities(wsCities, spiCiudad, getContext());
    }

    @Override
    public void listVehiclesByModelSucces(List<WsVehicle> wsVehicles) {
        vehiculos = wsVehicles;
        if (loading.isShowing()) {
            loading.hide();
        }
        tvVIN.setText("");
        idVehiculo=Constants.UNIDADES_MEDIDA.EMPTY;
    }


    @Override
    public void listActivitiesSuccess(WsGeneralInfo wsGeneralInfo) {

        if (loading.isShowing()) {
            loading.hide();
        }

        paginacionDinamica(wsGeneralInfo);

    }

    @Override
    public void registerVehicleFrequencySuccess(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showErrorMessage(String message) {
        if (loading.isShowing()) {
            loading.hide();
        }
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    //endregion


    //region DatePickers

    void loadDatePickerRecepcion() {
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "yyyy-MM-dd'T'HH:mm:ss"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                fechaRecepcion = sdf.format(myCalendar.getTime());

                String myFormatFormulario = "dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat sdfFormulario = new SimpleDateFormat(myFormatFormulario, Locale.US);

                tvFechaRecepción.setText(sdfFormulario.format(myCalendar.getTime()));
            }

        };

        tvFechaRecepción.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
    }

    void loadDatePickerEntrega() {
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "yyyy-MM-dd'T'HH:mm:ss"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                fechaEntrega = sdf.format(myCalendar.getTime());

                String myFormatFormulario = "dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat sdfFormulario = new SimpleDateFormat(myFormatFormulario, Locale.US);

                tvFechaEntrega.setText(sdfFormulario.format(myCalendar.getTime()));
            }

        };

        tvFechaEntrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
    }

    void loadDatePickerRevision() {
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "yyyy-MM-dd'T'HH:mm:ss"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                fechaRevision = sdf.format(myCalendar.getTime());

                String myFormatFormulario = "dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat sdfFormulario = new SimpleDateFormat(myFormatFormulario, Locale.US);

                tvFechaRevision.setText(sdfFormulario.format(myCalendar.getTime()));
            }

        };

        tvFechaRevision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
    }

    //endregion


    //region Spinners


    void spinnerEvents() {

        spiUbicacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                int positionSelected = spiUbicacion.getSelectedItemPosition();

                if (positionSelected == 0) {

                } else {
                    Integer idSelectedUbicacion = ubicaciones.get(positionSelected - 1).getId();
                    idUbicacion = idSelectedUbicacion;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        spiCiudad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                int positionSelected = spiCiudad.getSelectedItemPosition();

                if (positionSelected == 0) {

                } else {
                    Integer idSelectedUbicacion = ciudades.get(positionSelected - 1).getId();
                    idCiudad = idSelectedUbicacion;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        spiMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                int positionSelected = spiMarca.getSelectedItemPosition();

                if (positionSelected == 0) {

                } else {
                    Integer idSelectedMarca = marcas.get(positionSelected - 1).getId();

                    modelosFiltered = new ArrayList<>();
                    for (WsModel wsModel : modelos) {
                        Integer modelllll = wsModel.getIdBrand();
                        if (modelllll == idSelectedMarca) {
                            modelosFiltered.add(wsModel);
                        }
                    }
                    setSpinnerModelos(modelosFiltered, spiModelo, getContext());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        spiModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                int positionSelected = spiModelo.getSelectedItemPosition();

                if (positionSelected == 0) {

                } else {

                    if (Helper.isConnectedToInternet(getContext())) {
                        if (!loading.isShowing()) {
                            loading.show();
                        }

                        Integer idSelectedModel = modelos.get(positionSelected - 1).getId();
                        masterPresenter.listVehiclesByModel(idSelectedModel);

                    } else {
                        Toast.makeText(getContext(), "No tienes Internet", Toast.LENGTH_SHORT).show();
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


    }

    public void setSpinnerMarcas(List<WsBrand> countries, Spinner spiner, Context ctx) {
        final List<String> paises = new ArrayList<String>();
        paises.add("Seleccione");
        for (Integer i = 0; i < countries.size(); i++) {
            paises.add(countries.get(i).getDescription());
        }

        final ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(
                ctx, R.layout.spinneritem, paises) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                //   tv.setTextColor(Color.BLACK);
                return view;
            }
        };
        spinnerArrayAdapter1.setDropDownViewResource(R.layout.spinneritem);
        spiner.setAdapter(spinnerArrayAdapter1);
    }


    public void setSpinnerModelos(List<WsModel> countries, Spinner spiner, Context ctx) {
        final List<String> paises = new ArrayList<String>();
        paises.add("Seleccione");
        for (Integer i = 0; i < countries.size(); i++) {
            paises.add(countries.get(i).getDescription());
        }

        final ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(
                ctx, R.layout.spinneritem, paises) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                //   tv.setTextColor(Color.BLACK);
                return view;
            }
        };
        spinnerArrayAdapter1.setDropDownViewResource(R.layout.spinneritem);
        spiner.setAdapter(spinnerArrayAdapter1);
    }


    public void setSpinnerVehicle(List<WsVehicle> countries, Spinner spiner, Context ctx) {
        final List<String> paises = new ArrayList<String>();
        paises.add("Seleccione");
        for (Integer i = 0; i < countries.size(); i++) {
            paises.add(countries.get(i).getVin());
        }

        final ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(
                ctx, R.layout.spinneritem, paises) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                //   tv.setTextColor(Color.BLACK);
                return view;
            }
        };
        spinnerArrayAdapter1.setDropDownViewResource(R.layout.spinneritem);
        spiner.setAdapter(spinnerArrayAdapter1);
    }

    public void setSpinnerLocations(List<WsLocation> countries, Spinner spiner, Context ctx) {
        final List<String> paises = new ArrayList<String>();
        paises.add("Seleccione");
        for (Integer i = 0; i < countries.size(); i++) {
            paises.add(countries.get(i).getDescription());
        }

        final ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(
                ctx, R.layout.spinneritem, paises) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                //   tv.setTextColor(Color.BLACK);
                return view;
            }
        };
        spinnerArrayAdapter1.setDropDownViewResource(R.layout.spinneritem);
        spiner.setAdapter(spinnerArrayAdapter1);
    }

    public void setSpinnerCities(List<WsCity> countries, Spinner spiner, Context ctx) {
        final List<String> paises = new ArrayList<String>();
        paises.add("Seleccione");
        for (Integer i = 0; i < countries.size(); i++) {
            paises.add(countries.get(i).getDescription());
        }

        final ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(
                ctx, R.layout.spinneritem, paises) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                //   tv.setTextColor(Color.BLACK);
                return view;
            }
        };
        spinnerArrayAdapter1.setDropDownViewResource(R.layout.spinneritem);
        spiner.setAdapter(spinnerArrayAdapter1);
    }

    public void setSpinner(ArrayList<ObjetoPrueba> countries, Spinner spiner, Context ctx) {
        final List<String> paises = new ArrayList<String>();
        paises.add("Seleccione");
        for (Integer i = 0; i < countries.size(); i++) {
            paises.add(countries.get(i).getName());
        }

        final ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(
                ctx, R.layout.spinneritem, paises) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                //     tv.setTextColor(Color.BLACK);
                return view;
            }
        };
        spinnerArrayAdapter1.setDropDownViewResource(R.layout.spinneritem);
        spiner.setAdapter(spinnerArrayAdapter1);
    }


    //endregion


    void paginacionDinamica(WsGeneralInfo wsGeneralInfo) {
        TabRegistrarInspeccion.wsGeneralInfo = wsGeneralInfo;

        tiposAcividad = new ArrayList<>();

        Integer tipoAc = 0;

        for (WsActivity wsActivity : wsGeneralInfo.getWsActivities()) {
            Integer tipA = wsActivity.getIdTipoActividad();
            if (tipoAc != tipA) {
                tiposAcividad.add(wsActivity.getIdTipoActividad());
            }
            tipoAc = tipA;
        }

        TabRegistrarInspeccion.alreadyLoaded = true;

        TabRegistrarInspeccion.wsGeneralInfo.setIdUbicacion(idUbicacion);
        //  insp_TabRegistrarInspeccion.wsGeneralInfo.setId(idCiudad);
        TabRegistrarInspeccion.wsGeneralInfo.setFecRecepcion(fechaRecepcion);
        TabRegistrarInspeccion.wsGeneralInfo.setFecEntrega(fechaEntrega);
        TabRegistrarInspeccion.wsGeneralInfo.setFecRevision(fechaRevision);
        TabRegistrarInspeccion.wsGeneralInfo.setOdometro(Integer.parseInt(etOdometro.getText().toString().trim()));

        TabRegistrarInspeccion.viewPager.setCurrentItem(1);


    }


    void showSearchableVIN() {
        Bundle bundle = new Bundle();
        //  bundle.putSerializable("dbEvento", dbEvento);
        SearchVinlDialog df = new SearchVinlDialog();
        df.setArguments(bundle);
        df.show(getFragmentManager(), "");
    }


    void loadTitulo() {
        CreateInspectionActivity.tvTituloCultivo.setText("Datos iniciales");
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            loadTitulo();
            //    insp_TabRegistrarInspeccion.FRAGMENT = Constants.FRAGMENTS_TABS_CREAR_CULTIVO_PAPA.LOCALIZACION_PARCELA;
        } else {
        }
    }
}
