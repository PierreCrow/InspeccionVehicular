package com.futuromovil.inspeccionvehicular.presentation.ui.dialogfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.DialogFragment;

import com.futuromovil.inspeccionvehicular.R;
import com.futuromovil.inspeccionvehicular.domain.model.Inspector;
import com.futuromovil.inspeccionvehicular.presentation.ui.activities.MainActivity;
import com.futuromovil.inspeccionvehicular.presentation.utils.Helper;

public class CerrarInspeccionDialog extends DialogFragment {


    Button btnContinuar,btnVolverInicio;
    Inspector inspector;

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.cerrar_creacion_cultivo_dialog, new LinearLayout(getActivity()), false);

        btnContinuar=(Button)view.findViewById(R.id.btnContinuar) ;
        btnVolverInicio=(Button)view.findViewById(R.id.btnVolverInicio) ;

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
            }
        });

        btnVolverInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                inspector = Helper.getUserPreference(getContext());
                inspector.setFotoLoaded(false);
                Helper.saveUserPreference(getContext(),inspector);

                dismiss();
                getActivity().finish();
                Intent intent= new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        init();

        Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.setContentView(view);
        return builder;
    }

    void init()
    {
      //  Bundle bundle = getArguments();
        // usuario = (Usuario) bundle.getSerializable("usuario");

        //  Integer id=usuario.getId();


    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().getAttributes().alpha = 1f;
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        dismiss();
    }

}
