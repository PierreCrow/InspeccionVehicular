package com.futuromovil.inspeccionvehicular.presentation.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.futuromovil.inspeccionvehicular.R;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.user.response.WsLoginData;
import com.futuromovil.inspeccionvehicular.presentation.presenter.UserPresenter;
import com.futuromovil.inspeccionvehicular.presentation.ui.activities.CreateInspectionActivity;
import com.futuromovil.inspeccionvehicular.presentation.utils.Constants;
import com.futuromovil.inspeccionvehicular.presentation.utils.Helper;
import com.futuromovil.inspeccionvehicular.presentation.utils.TransparentProgressDialog;
import com.futuromovil.inspeccionvehicular.presentation.view.UserView;

import butterknife.BindView;

public class HomeFragment extends BaseFragment implements UserView {


    @BindView(R.id.ivCultivoAsegurado)
    ImageView ivCultivoAsegurado;

    @BindView(R.id.tvCultivoAsegurado)
    TextView tvCultivoAsegurado;

    @BindView(R.id.llCultivoAsegurado)
    LinearLayout llCultivoAsegurado;

    @BindView(R.id.llAgregarCultivo)
    LinearLayout llAgregarCultivo;

    @BindView(R.id.rlAgregarCultivo)
    LinearLayout rlAgregarCultivo;


    @BindView(R.id.containerPapa)
    LinearLayout containerPapa;
    @BindView(R.id.containerQuinua)
    LinearLayout containerQuinua;
    @BindView(R.id.containerArroz)
    LinearLayout containerArroz;
    @BindView(R.id.fab_tutorial)
    LinearLayout fab_tutorial;
    @BindView(R.id.btnIniciarInspeccion)
    Button btnIniciarInspeccion;
    @BindView(R.id.tvCantidadInspecciones)
    TextView tvCantidadInspecciones;
    @BindView(R.id.tvTituloEmpresa)
    TextView tvTituloEmpresa;
    @BindView(R.id.tvTituloUsuario)
    TextView tvTituloUsuario;


    UserPresenter userPresenter;
    TransparentProgressDialog loading;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View x = inflater.inflate(R.layout.home_fragment, null);
        injectView(x);
        clickListener();
        loadQuantity();

        return x;
    }


    void loadQuantity() {

        String inuput = "Bienvenido a L&L";
        tvTituloEmpresa.setText(inuput);

        String nombre = "";
        String nombreCOmpleto = Helper.getUserPreference(getContext()).getFull_name();
        int i = nombreCOmpleto.indexOf(' ');
        if (i > 0) {
            nombre = nombreCOmpleto.substring(0, i);
        } else {
            nombre = nombreCOmpleto;
        }

        tvTituloUsuario.setText("Hola " + nombre);

        loading = new TransparentProgressDialog(getContext());
        userPresenter = new UserPresenter();
        userPresenter.addView(this);


        if (Helper.isConnectedToInternet(getContext())) {
            if (!loading.isShowing()) {
                loading.show();
            }
            userPresenter.quantityPendingFrequency();

        } else {
            Toast.makeText(getContext(), Constants.HTTPS_MESSAGE_RESPONSE.NOT_INTERNET, Toast.LENGTH_SHORT).show();
        }
    }


    void clickListener() {

        btnIniciarInspeccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                next(CreateInspectionActivity.class, getContext(), null);

            }
        });

    }


    @Override
    public void loginSuccess(WsLoginData wsLoginData) {

    }

    @Override
    public void quantityPendingFrequencySuccess(Integer quantity) {
        if (loading.isShowing()) {
            loading.dismiss();
        }
        tvCantidadInspecciones.setText(quantity.toString());
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
            loading.dismiss();
        }
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
