package com.futuromovil.inspeccionvehicular.presentation.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.futuromovil.inspeccionvehicular.R;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.user.response.WsLoginData;
import com.futuromovil.inspeccionvehicular.domain.model.Inspector;
import com.futuromovil.inspeccionvehicular.presentation.presenter.UserPresenter;
import com.futuromovil.inspeccionvehicular.presentation.utils.Helper;
import com.futuromovil.inspeccionvehicular.presentation.utils.SingleClick;
import com.futuromovil.inspeccionvehicular.presentation.utils.TransparentProgressDialog;
import com.futuromovil.inspeccionvehicular.presentation.view.UserView;


import butterknife.BindView;

public class LoginActivity extends BaseActivity implements UserView {

    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPass)
    EditText etPass;
    @BindView(R.id.ivPass)
    ImageView ivPass;

    SingleClick singleClick;
    boolean passView = false;
    UserPresenter userPresenter;
    TransparentProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        injectView();
        initUI();
        onClickListener();
    }

    private void initUI() {
        etEmail.setText("egomez");
        etPass.setText("123");
        loading = new TransparentProgressDialog(getContext());
        userPresenter = new UserPresenter();
        userPresenter.addView(this);
        ivPass.setOnClickListener(singleClick);
        btn_login.setOnClickListener(singleClick);
    }

    private void onClickListener() {

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginnEmailPass();
            }
        });

        ivPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickViewPass();
            }
        });

    }


    void loginnEmailPass() {

        if (Helper.isConnectedToInternet(getContext())) {
            String email = etEmail.getText().toString().trim();
            String pass = etPass.getText().toString().trim();
            if (email.trim().equals("")) {
                Toast.makeText(getContext(), "Ingrese correo electrónico", Toast.LENGTH_SHORT).show();
            } else {
                if (pass.trim().equals("")) {
                    Toast.makeText(getContext(), "Ingrese contraseña", Toast.LENGTH_SHORT).show();
                } else {
                    if (!loading.isShowing()) {
                        loading.show();
                    }
                    userPresenter.login(email, pass);
                }
            }

        } else {
            Toast.makeText(getContext(), "No tienes Internet", Toast.LENGTH_SHORT).show();
        }
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


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        finish();
    }


    @Override
    public void loginSuccess(WsLoginData wsLoginData) {
        if (loading.isShowing()) {
            loading.dismiss();
        }

        Inspector inspector = Helper.getUserPreference(getContext());
        inspector.setLogged(true);
        inspector.setFull_name(wsLoginData.getNomCompleto());
        inspector.setMail(wsLoginData.getNomUsuario());
        inspector.setPassword(wsLoginData.getClaUsuario());
        inspector.setUser(wsLoginData.getNomUsuario());
        Helper.saveUserPreference(getContext(), inspector);
        next(MainActivity.class, null);
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
        if (loading.isShowing()) {
            loading.dismiss();
        }
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

}