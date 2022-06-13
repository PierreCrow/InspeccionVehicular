package com.futuromovil.inspeccionvehicular.presentation.ui.dialogfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.futuromovil.inspeccionvehicular.R;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsVehicle;
import com.futuromovil.inspeccionvehicular.presentation.ui.adapters.VinListDataAdapter;
import com.futuromovil.inspeccionvehicular.presentation.ui.fragments.createinspeccion.DatosInicialesFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchVinlDialog extends DialogFragment implements
        VinListDataAdapter.OnVinListDataAdapterClickListener{

    VinListDataAdapter.OnVinListDataAdapterClickListener mlistener;
    VinListDataAdapter adpater;

    Button btnClose;
    RecyclerView rvVins;
    EditText etVin;
    List<WsVehicle> wsVehicles;
   List<WsVehicle> vehiclesFiltered;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.search_vin_dialog, new LinearLayout(getActivity()), false);

        initUI(view);
        clickListener();
        fillList(wsVehicles);
        searchablee();
        Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.setContentView(view);
        return builder;
    }


    void initUI(View view) {
        btnClose = (Button) view.findViewById(R.id.btnClose);
        rvVins = (RecyclerView) view.findViewById(R.id.rvVins);
        etVin = (EditText) view.findViewById(R.id.etVin);
        mlistener=this;
        wsVehicles= DatosInicialesFragment.vehiculos;
        vehiclesFiltered= new ArrayList<>();
    }




    void searchablee()
    {
        etVin.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                vehiclesFiltered= new ArrayList<>();
                for(WsVehicle wsVehicle:wsVehicles)
                {
                    if(wsVehicle.getVin().toUpperCase(Locale.ROOT).contains(s.toString().trim().toUpperCase(Locale.ROOT)))
                    {
                        vehiclesFiltered.add(wsVehicle);
                    }
                }
                fillList(vehiclesFiltered);
            }
        });
    }



    void fillList(List<WsVehicle> wsVehicles)
    {
        adpater = new VinListDataAdapter(mlistener, getContext(),wsVehicles);
        rvVins.setHasFixedSize(true);
        rvVins.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvVins.setAdapter(adpater);
        int[] ATTRS = new int[]{android.R.attr.listDivider};
        TypedArray a = getContext().obtainStyledAttributes(ATTRS);
        Drawable divider = a.getDrawable(0);
        int inset = getResources().getDimensionPixelSize(R.dimen.marginrecyclwe);
        InsetDrawable insetDivider = new InsetDrawable(divider, inset, 0, inset, 0);
        a.recycle();
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(insetDivider);
        rvVins.addItemDecoration(itemDecoration);
    }


    void clickListener()
    {

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

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
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
    }



    @Override
    public void onCancel(DialogInterface dialog) {
        dismiss();
    }

    @Override
    public void onVinListDataAdapterClicked(WsVehicle wsVehicle) {
        DatosInicialesFragment.tvVIN.setText(wsVehicle.getVin());

        for(WsVehicle vehiculo: DatosInicialesFragment.vehiculos)
        {
            if(DatosInicialesFragment.tvVIN.getText().toString().equals(vehiculo.getVin()))
            {
                DatosInicialesFragment.idVehiculo=vehiculo.getId();
            }
        }

        dismiss();
    }
}
