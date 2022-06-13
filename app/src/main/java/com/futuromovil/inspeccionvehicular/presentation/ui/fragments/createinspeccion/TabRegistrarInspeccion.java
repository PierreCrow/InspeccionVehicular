package com.futuromovil.inspeccionvehicular.presentation.ui.fragments.createinspeccion;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.futuromovil.inspeccionvehicular.R;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.inspection.response.WsGeneralInfo;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.inspection.response.WsImage;
import com.futuromovil.inspeccionvehicular.domain.model.Inspector;
import com.futuromovil.inspeccionvehicular.presentation.presenter.InspectionPresenter;
import com.futuromovil.inspeccionvehicular.presentation.ui.activities.MainActivity;
import com.futuromovil.inspeccionvehicular.presentation.ui.fragments.BaseFragment;
import com.futuromovil.inspeccionvehicular.presentation.ui.fragments.CrearInspeccionFragmentCinco;
import com.futuromovil.inspeccionvehicular.presentation.ui.fragments.CrearInspeccionFragmentCuatro;
import com.futuromovil.inspeccionvehicular.presentation.ui.fragments.CrearInspeccionFragmentDiez;
import com.futuromovil.inspeccionvehicular.presentation.ui.fragments.CrearInspeccionFragmentDos;
import com.futuromovil.inspeccionvehicular.presentation.ui.fragments.CrearInspeccionFragmentNueve;
import com.futuromovil.inspeccionvehicular.presentation.ui.fragments.CrearInspeccionFragmentOcho;
import com.futuromovil.inspeccionvehicular.presentation.ui.fragments.CrearInspeccionFragmentSeis;
import com.futuromovil.inspeccionvehicular.presentation.ui.fragments.CrearInspeccionFragmentSiete;
import com.futuromovil.inspeccionvehicular.presentation.ui.fragments.CrearInspeccionFragmentTres;
import com.futuromovil.inspeccionvehicular.presentation.ui.fragments.CrearInspeccionFragmentUno;
import com.futuromovil.inspeccionvehicular.presentation.utils.Helper;
import com.futuromovil.inspeccionvehicular.presentation.utils.NonSwipeableViewPager;
import com.futuromovil.inspeccionvehicular.presentation.view.InspectionView;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class TabRegistrarInspeccion extends BaseFragment implements InspectionView {

    // @BindView(R.id.viewpager)
    public static NonSwipeableViewPager viewPager;
    public static int int_items = 11;
    public static Context generalContext;
    public static Integer FRAGMENT;
    public static TabLayout tabLayout;
    public static WsGeneralInfo wsGeneralInfo;
    public static List<WsImage> wsImages;
    public static boolean alreadyLoaded;
    public static InspectionPresenter inspectionPresenter;
    Inspector inspector;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View x = inflater.inflate(R.layout.tab_registrar_inspeccion, null);
        //injectView(x);
        initUI(x);
        return x;
    }

    private void initUI(View x) {
        inspectionPresenter = new InspectionPresenter();
        inspectionPresenter.addView(this);
        alreadyLoaded = false;
        tabLayout = (TabLayout) x.findViewById(R.id.tabs_registrar_inspeccionn);//HIDE
        viewPager = (NonSwipeableViewPager) x.findViewById(R.id.viewpagerRegistrarCultivo);
        generalContext = getContext();
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
    }

    @Override
    public void listActivitiesSuccess(WsGeneralInfo wsGeneralInfo) {

    }

    @Override
    public void registerVehicleFrequencySuccess(String message) {

        inspector = Helper.getUserPreference(getContext());
        inspector.setFotoLoaded(false);
        Helper.saveUserPreference(getContext(), inspector);
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        next(MainActivity.class, getContext(), null);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }


    public static class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: {
                    return new DatosInicialesFragment();
                }
                case 1: {
                    return new CrearInspeccionFragmentUno();
                }
                case 2: {
                    return new CrearInspeccionFragmentDos();
                }
                case 3: {
                    return new CrearInspeccionFragmentTres();
                }
                case 4: {
                    return new CrearInspeccionFragmentCuatro();
                }
                case 5: {
                    return new CrearInspeccionFragmentCinco();
                }
                case 6: {
                    return new CrearInspeccionFragmentSeis();
                }
                case 7: {
                    return new CrearInspeccionFragmentSiete();
                }
                case 8: {
                    return new CrearInspeccionFragmentOcho();
                }
                case 9: {
                    return new CrearInspeccionFragmentNueve();
                }
                case 10: {
                    return new CrearInspeccionFragmentDiez();
                }
            }
            return null;
        }

        @Override
        public int getCount() {
            return int_items;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }

}
