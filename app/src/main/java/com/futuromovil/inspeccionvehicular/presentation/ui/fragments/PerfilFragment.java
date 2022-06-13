package com.futuromovil.inspeccionvehicular.presentation.ui.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.futuromovil.inspeccionvehicular.R;
import com.futuromovil.inspeccionvehicular.domain.model.Inspector;
import com.futuromovil.inspeccionvehicular.presentation.ui.activities.LoginActivity;
import com.futuromovil.inspeccionvehicular.presentation.ui.activities.UpdateUserActivity;
import com.futuromovil.inspeccionvehicular.presentation.utils.Helper;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class PerfilFragment extends BaseFragment {

    @BindView(R.id.tvFullName)
    TextView tvFullName;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.llLogout)
    LinearLayout llLogout;
    @BindView(R.id.llEditarPerfil)
    LinearLayout llEditarPerfil;
    @BindView(R.id.ivUserImage)
    CircleImageView ivUserImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View x = inflater.inflate(R.layout.perfil_fragment, null);
        injectView(x);
        setTittle();
        clickListener();
        return x;
    }

    void clickListener() {
        llLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert();
            }
        });

        llEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getContext(), UpdateUserActivity.class);
                startActivity(intent);
            }
        });

    }



    void showAlert() {

        new AlertDialog.Builder(getContext())
                // .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("¿Cerrar sesión?")
                .setMessage("Esta seguro que desea cerrar sesión?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Inspector userPreference = Helper.getUserPreference(getContext());
                        userPreference.setFull_name("");
                        userPreference.setMail("");
                        userPreference.setPassword("");
                        userPreference.setLogged(false);
                        Helper.saveUserPreference(getContext(), userPreference);
                        next(LoginActivity.class, getContext(), null);
                    }

                })
                .setNegativeButton("No", null)
                .show();

    }

    void setTittle() {
        Inspector userPreference = Helper.getUserPreference(getContext());
        tvFullName.setText(userPreference.getFull_name());
        tvEmail.setText(userPreference.getMail());

    }

}
