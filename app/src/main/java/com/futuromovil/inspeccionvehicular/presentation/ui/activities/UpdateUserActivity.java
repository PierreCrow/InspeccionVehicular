package com.futuromovil.inspeccionvehicular.presentation.ui.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.futuromovil.inspeccionvehicular.R;

import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.user.response.WsLoginData;
import com.futuromovil.inspeccionvehicular.domain.model.Inspector;
import com.futuromovil.inspeccionvehicular.domain.model.ObjetoPrueba;
import com.futuromovil.inspeccionvehicular.presentation.utils.Helper;
import com.futuromovil.inspeccionvehicular.presentation.utils.TransparentProgressDialog;
import com.futuromovil.inspeccionvehicular.presentation.view.UserView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class UpdateUserActivity extends BaseActivity implements UserView {

    @BindView(R.id.etPass)
    EditText etPass;
    @BindView(R.id.ivPass)
    ImageView ivPass;

    @BindView(R.id.etNombres)
    EditText etNombres;

    @BindView(R.id.etEmail)
    EditText etEmail;


    @BindView(R.id.btnBack)
    LinearLayout btnBack;


    TransparentProgressDialog loading;
    boolean passView = false;
    ArrayList<ObjetoPrueba> tiposDoc;

    Inspector inspector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_user_activity);
        injectView();
        initUI();
        loadData();

    }

    private void initUI() {


      //  userPresenter.login("admin","1234");

        clickListener();

    }

    void loadData()
    {
        inspector = Helper.getUserPreference(getContext());

        etNombres.setText(inspector.getFull_name());
        etEmail.setText(inspector.getMail());
        etPass.setText(inspector.getPassword());

    }


    void clickViewPass() {
        if (!passView) {
            etPass.setTransformationMethod(null);
            passView = true;
        } else {
            etPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
            passView = false;
        }
    }



    void showAlert() {
        new AlertDialog.Builder(getContext())
                // .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("¿Guardar datos?")
                .setMessage("Esta seguro que desea guardar sus datos?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        next(MainActivity.class, null);
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    void clickListener()
    {

        ivPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             clickViewPass();
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });



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

    @Override
    protected void onPause() {
        super.onPause();

    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        finish();
    }



    @Override
    public void loginSuccess(WsLoginData wsLoginData) {

    }

    @Override
    public void quantityPendingFrequencySuccess(Integer quantity) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public Context getContext() {
        return this;
    }
}