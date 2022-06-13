package com.futuromovil.inspeccionvehicular.presentation.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.futuromovil.inspeccionvehicular.R;
import com.futuromovil.inspeccionvehicular.presentation.utils.Constants;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class TabHome extends BaseFragment {

    public static TabLayout tabLayout;
   // @BindView(R.id.viewpager)
    public static ViewPager viewPager;
    public static int int_items = 3;
    public static Context generalContext;
    public static String FRAGMENT = "";
    FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View x = inflater.inflate(R.layout.tab_home, null);
    //    injectView(x);
        initUI(x);
        return x;
    }

    private void initUI(View x) {
        tabLayout = (TabLayout) x.findViewById(R.id.tabsappunto);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);
        fab = (FloatingActionButton) x.findViewById(R.id.fab);
        generalContext = getContext();

        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        final BottomNavigationView navigation = (BottomNavigationView) x.findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        navigation.setSelectedItemId(R.id.page_1);
                        fab.hide();
                        break;
                    case 1:
                        navigation.setSelectedItemId(R.id.page_2);
                        fab.hide();
                        break;
                    case 2:
                        navigation.setSelectedItemId(R.id.page_3);
                        fab.hide();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.page_1:
                    viewPager.setCurrentItem(0);
                    FRAGMENT = "INICIO";
                    return true;
                case R.id.page_2:
                    viewPager.setCurrentItem(1);
                    FRAGMENT = "OPERACIONES";
                    return true;
                case R.id.page_3:
                    viewPager.setCurrentItem(2);
                    FRAGMENT = "PERFIL";
                    return true;
            }
            return false;
        }
    };


    class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case Constants.FRAGMENTS_TABS.HOME: {
                    return new HomeFragment();
                }
                case Constants.FRAGMENTS_TABS.OPERACIONES: {
                    return new OperacionesFragment();
                }
                case Constants.FRAGMENTS_TABS.ACCOUNT: {
                    return new PerfilFragment();
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
