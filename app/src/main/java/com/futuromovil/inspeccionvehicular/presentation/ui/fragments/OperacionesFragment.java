package com.futuromovil.inspeccionvehicular.presentation.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.futuromovil.inspeccionvehicular.R;

public class OperacionesFragment extends BaseFragment


      {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View x = inflater.inflate(R.layout.operacionesfrag, null);

        injectView(x);


        return x;
    }



}
