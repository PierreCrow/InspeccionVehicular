package com.futuromovil.inspeccionvehicular.presentation.ui.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.futuromovil.inspeccionvehicular.R;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.inspection.response.WsActivity;
import com.futuromovil.inspeccionvehicular.domain.model.Inspector;
import com.futuromovil.inspeccionvehicular.presentation.ui.fragments.createinspeccion.DatosInicialesFragment;
import com.futuromovil.inspeccionvehicular.presentation.ui.fragments.createinspeccion.TabRegistrarInspeccion;
import com.futuromovil.inspeccionvehicular.presentation.utils.Constants;
import com.futuromovil.inspeccionvehicular.presentation.utils.Helper;
import com.futuromovil.inspeccionvehicular.presentation.utils.TransparentProgressDialog;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class CrearInspeccionFragmentDiez extends BaseFragment


      {

          @BindView(R.id.tvSiguiente)
          LinearLayout tvSiguiente;
          @BindView(R.id.tvAtras)
          LinearLayout tvAtras;
          @BindView(R.id.tvTitulo)
          TextView tvTitulo;

          @BindView(R.id.llUno)
          LinearLayout llUno;
          @BindView(R.id.chbxUno)
          CheckBox chbxUno;
          @BindView(R.id.tvUno)
          TextView tvUno;

          @BindView(R.id.llDos)
          LinearLayout llDos;
          @BindView(R.id.chbxDos)
          CheckBox chbxDos;
          @BindView(R.id.tvDos)
          TextView tvDos;

          @BindView(R.id.llTres)
          LinearLayout llTres;
          @BindView(R.id.chbxTres)
          CheckBox chbxTres;
          @BindView(R.id.tvTres)
          TextView tvTres;

          @BindView(R.id.llCuatro)
          LinearLayout llCuatro;
          @BindView(R.id.chbxCuatro)
          CheckBox chbxCuatro;
          @BindView(R.id.tvCuatro)
          TextView tvCuatro;

          @BindView(R.id.llCinco)
          LinearLayout llCinco;
          @BindView(R.id.chbxCinco)
          CheckBox chbxCinco;
          @BindView(R.id.tvCinco)
          TextView tvCinco;

          @BindView(R.id.llSeis)
          LinearLayout llSeis;
          @BindView(R.id.chbxSeis)
          CheckBox chbxSeis;
          @BindView(R.id.tvSeis)
          TextView tvSeis;

          @BindView(R.id.llSiete)
          LinearLayout llSiete;
          @BindView(R.id.chbxSiete)
          CheckBox chbxSiete;
          @BindView(R.id.tvSiete)
          TextView tvSiete;

          @BindView(R.id.llOcho)
          LinearLayout llOcho;
          @BindView(R.id.chbxOcho)
          CheckBox chbxOcho;
          @BindView(R.id.tvOcho)
          TextView tvOcho;

          @BindView(R.id.llNueve)
          LinearLayout llNueve;
          @BindView(R.id.chbxNueve)
          CheckBox chbxNueve;
          @BindView(R.id.tvNueve)
          TextView tvNueve;

          @BindView(R.id.llDiez)
          LinearLayout llDiez;
          @BindView(R.id.chbxDiez)
          CheckBox chbxDiez;
          @BindView(R.id.tvDiez)
          TextView tvDiez;

          @BindView(R.id.btnGuardar)
          Button btnGuardar;

          @BindView(R.id.tvTituloFotos)
          TextView tvTituloFotos;
          @BindView(R.id.tvTituloIndicacionesFotos)
          TextView tvTituloIndicacionesFotos;


          public static LinearLayout llPictures;
          public static ImageView ivPicture1,ivPicture2,ivPicture3;
          public static String foto1,foto2,foto3;
          public static Integer pictureSelected;

          List<WsActivity> wsActivitiesPageOne;
          TransparentProgressDialog loading;
          Inspector inspector;

          @Nullable
          @Override
          public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                   Bundle savedInstanceState) {
              View x = inflater.inflate(R.layout.crear_inspeccion_diez, null);

              injectView(x);
              initUI(x);
              loadPictures();
              clickListener();


              return x;
          }

          void cargaImagen(String path, ImageView imageView) {
              File imgFile = new File(path);
              if (imgFile.exists()) {
                  Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                  imageView.setImageBitmap(myBitmap);

              }
          }

          void loadPictures() {
              inspector = Helper.getUserPreference(getContext());
              if (inspector.isFotoLoaded()) {
                  if (!inspector.getFoto1().equals("")) {
                      cargaImagen(inspector.getFoto1(), ivPicture1);
                      foto1 = inspector.getFoto1();

                  }
                  if (!inspector.getFoto2().equals("")) {
                      cargaImagen(inspector.getFoto2(), ivPicture2);
                      foto2 = inspector.getFoto2();

                  }
                  if (!inspector.getFoto3().equals("")) {
                      cargaImagen(inspector.getFoto3(), ivPicture3);
                      foto3 = inspector.getFoto3();

                  }
              }
          }


          void updateActivities()
          {


              if(chbxUno.isChecked())
              {
                  Integer idActivity=wsActivitiesPageOne.get(0).getId();

                  for(WsActivity wsActivity: TabRegistrarInspeccion.wsGeneralInfo.getWsActivities())
                  {
                      if(idActivity==wsActivity.getId())
                      {
                          wsActivity.setValorActividad(true);
                      }
                  }
              }

              if(chbxDos.isChecked())
              {
                  Integer idActivity=wsActivitiesPageOne.get(1).getId();

                  for(WsActivity wsActivity: TabRegistrarInspeccion.wsGeneralInfo.getWsActivities())
                  {
                      if(idActivity==wsActivity.getId())
                      {
                          wsActivity.setValorActividad(true);
                      }
                  }
              }

              if(chbxTres.isChecked())
              {
                  Integer idActivity=wsActivitiesPageOne.get(2).getId();

                  for(WsActivity wsActivity: TabRegistrarInspeccion.wsGeneralInfo.getWsActivities())
                  {
                      if(idActivity==wsActivity.getId())
                      {
                          wsActivity.setValorActividad(true);
                      }
                  }
              }

              if(chbxCuatro.isChecked())
              {
                  Integer idActivity=wsActivitiesPageOne.get(3).getId();

                  for(WsActivity wsActivity: TabRegistrarInspeccion.wsGeneralInfo.getWsActivities())
                  {
                      if(idActivity==wsActivity.getId())
                      {
                          wsActivity.setValorActividad(true);
                      }
                  }
              }

              if(chbxCinco.isChecked())
              {
                  Integer idActivity=wsActivitiesPageOne.get(4).getId();

                  for(WsActivity wsActivity: TabRegistrarInspeccion.wsGeneralInfo.getWsActivities())
                  {
                      if(idActivity==wsActivity.getId())
                      {
                          wsActivity.setValorActividad(true);
                      }
                  }
              }

              if(chbxSeis.isChecked())
              {
                  Integer idActivity=wsActivitiesPageOne.get(5).getId();

                  for(WsActivity wsActivity: TabRegistrarInspeccion.wsGeneralInfo.getWsActivities())
                  {
                      if(idActivity==wsActivity.getId())
                      {
                          wsActivity.setValorActividad(true);
                      }
                  }
              }

              if(chbxSiete.isChecked())
              {
                  Integer idActivity=wsActivitiesPageOne.get(6).getId();

                  for(WsActivity wsActivity: TabRegistrarInspeccion.wsGeneralInfo.getWsActivities())
                  {
                      if(idActivity==wsActivity.getId())
                      {
                          wsActivity.setValorActividad(true);
                      }
                  }
              }

              if(chbxOcho.isChecked())
              {
                  Integer idActivity=wsActivitiesPageOne.get(7).getId();

                  for(WsActivity wsActivity: TabRegistrarInspeccion.wsGeneralInfo.getWsActivities())
                  {
                      if(idActivity==wsActivity.getId())
                      {
                          wsActivity.setValorActividad(true);
                      }
                  }
              }

              if(chbxNueve.isChecked())
              {
                  Integer idActivity=wsActivitiesPageOne.get(8).getId();

                  for(WsActivity wsActivity: TabRegistrarInspeccion.wsGeneralInfo.getWsActivities())
                  {
                      if(idActivity==wsActivity.getId())
                      {
                          wsActivity.setValorActividad(true);
                      }
                  }
              }

              if(chbxDiez.isChecked())
              {
                  Integer idActivity=wsActivitiesPageOne.get(9).getId();

                  for(WsActivity wsActivity: TabRegistrarInspeccion.wsGeneralInfo.getWsActivities())
                  {
                      if(idActivity==wsActivity.getId())
                      {
                          wsActivity.setValorActividad(true);
                      }
                  }
              }


          }

          void initUI(View x) {
              loading = new TransparentProgressDialog(getContext());
              ivPicture1=(ImageView)x.findViewById(R.id.ivPicture1);
              ivPicture2=(ImageView)x.findViewById(R.id.ivPicture2);
              ivPicture3=(ImageView)x.findViewById(R.id.ivPicture3);
              llPictures=(LinearLayout) x.findViewById(R.id.llPictures);

              foto1="";
              foto2="";
              foto3="";

              pictureSelected=0;

              wsActivitiesPageOne = new ArrayList<>();

              if (DatosInicialesFragment.tiposAcividad != null) {

                  if(DatosInicialesFragment.tiposAcividad.size()==10)
                  {
                      tvSiguiente.setVisibility(View.GONE);
                      btnGuardar.setVisibility(View.VISIBLE);
                      tvTituloFotos.setVisibility(View.VISIBLE);
                      tvTituloIndicacionesFotos.setVisibility(View.VISIBLE);
                      llPictures.setVisibility(View.VISIBLE);
                  }

                  switch (DatosInicialesFragment.tiposAcividad.size())
                  {
                      case 1:
                          if(DatosInicialesFragment.tiposAcividad.size()>9)
                          {
                              Integer idTipoActividad = DatosInicialesFragment.tiposAcividad.get(9);
                              for (WsActivity wsActivity : TabRegistrarInspeccion.wsGeneralInfo.getWsActivities()) {
                                  if (wsActivity.getIdTipoActividad() == idTipoActividad) {
                                      wsActivitiesPageOne.add(wsActivity);
                                      tvTitulo.setText(wsActivity.getDescTipoActividad());
                                  }
                              }
                              switch (wsActivitiesPageOne.size())
                              {
                                  case 1:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      llDos.setVisibility(View.GONE);
                                      llTres.setVisibility(View.GONE);
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 2:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      llTres.setVisibility(View.GONE);
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 3:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 4:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 5:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 6:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 7:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 8:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 9:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      tvNueve.setText(wsActivitiesPageOne.get(8).getDescActividad());
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 10:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      tvNueve.setText(wsActivitiesPageOne.get(8).getDescActividad());
                                      tvDiez.setText(wsActivitiesPageOne.get(9).getDescActividad());
                                      break;
                              }
                          }

                          break;

                      case 2:
                          if(DatosInicialesFragment.tiposAcividad.size()>9)
                          {
                              Integer idTipoActividad = DatosInicialesFragment.tiposAcividad.get(9);
                              for (WsActivity wsActivity : TabRegistrarInspeccion.wsGeneralInfo.getWsActivities()) {
                                  if (wsActivity.getIdTipoActividad() == idTipoActividad) {
                                      wsActivitiesPageOne.add(wsActivity);
                                      tvTitulo.setText(wsActivity.getDescTipoActividad());
                                  }
                              }
                              switch (wsActivitiesPageOne.size())
                              {
                                  case 1:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      llDos.setVisibility(View.GONE);
                                      llTres.setVisibility(View.GONE);
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 2:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      llTres.setVisibility(View.GONE);
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 3:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 4:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 5:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 6:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 7:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 8:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 9:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      tvNueve.setText(wsActivitiesPageOne.get(8).getDescActividad());
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 10:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      tvNueve.setText(wsActivitiesPageOne.get(8).getDescActividad());
                                      tvDiez.setText(wsActivitiesPageOne.get(9).getDescActividad());
                                      break;
                              }
                          }

                          break;

                      case 3:
                          if(DatosInicialesFragment.tiposAcividad.size()>9) {
                              Integer idTipoActividad = DatosInicialesFragment.tiposAcividad.get(9);
                              for (WsActivity wsActivity : TabRegistrarInspeccion.wsGeneralInfo.getWsActivities()) {
                                  if (wsActivity.getIdTipoActividad() == idTipoActividad) {
                                      wsActivitiesPageOne.add(wsActivity);
                                      tvTitulo.setText(wsActivity.getDescTipoActividad());
                                  }
                              }
                              switch (wsActivitiesPageOne.size()) {
                                  case 1:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      llDos.setVisibility(View.GONE);
                                      llTres.setVisibility(View.GONE);
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 2:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      llTres.setVisibility(View.GONE);
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 3:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 4:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 5:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 6:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 7:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 8:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 9:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      tvNueve.setText(wsActivitiesPageOne.get(8).getDescActividad());
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 10:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      tvNueve.setText(wsActivitiesPageOne.get(8).getDescActividad());
                                      tvDiez.setText(wsActivitiesPageOne.get(9).getDescActividad());
                                      break;
                              }
                          }
                          break;

                      case 4:
                          if(DatosInicialesFragment.tiposAcividad.size()>9) {
                              Integer idTipoActividad = DatosInicialesFragment.tiposAcividad.get(9);
                              for (WsActivity wsActivity : TabRegistrarInspeccion.wsGeneralInfo.getWsActivities()) {
                                  if (wsActivity.getIdTipoActividad() == idTipoActividad) {
                                      wsActivitiesPageOne.add(wsActivity);
                                      tvTitulo.setText(wsActivity.getDescTipoActividad());
                                  }
                              }
                              switch (wsActivitiesPageOne.size()) {
                                  case 1:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      llDos.setVisibility(View.GONE);
                                      llTres.setVisibility(View.GONE);
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 2:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      llTres.setVisibility(View.GONE);
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 3:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 4:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 5:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 6:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 7:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 8:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 9:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      tvNueve.setText(wsActivitiesPageOne.get(8).getDescActividad());
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 10:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      tvNueve.setText(wsActivitiesPageOne.get(8).getDescActividad());
                                      tvDiez.setText(wsActivitiesPageOne.get(9).getDescActividad());
                                      break;
                              }
                          }
                          break;

                      case 5:
                          if(DatosInicialesFragment.tiposAcividad.size()>9) {
                              Integer idTipoActividad = DatosInicialesFragment.tiposAcividad.get(9);
                              for (WsActivity wsActivity : TabRegistrarInspeccion.wsGeneralInfo.getWsActivities()) {
                                  if (wsActivity.getIdTipoActividad() == idTipoActividad) {
                                      wsActivitiesPageOne.add(wsActivity);
                                      tvTitulo.setText(wsActivity.getDescTipoActividad());
                                  }
                              }
                              switch (wsActivitiesPageOne.size()) {
                                  case 1:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      llDos.setVisibility(View.GONE);
                                      llTres.setVisibility(View.GONE);
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 2:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      llTres.setVisibility(View.GONE);
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 3:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 4:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 5:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 6:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 7:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 8:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 9:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      tvNueve.setText(wsActivitiesPageOne.get(8).getDescActividad());
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 10:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      tvNueve.setText(wsActivitiesPageOne.get(8).getDescActividad());
                                      tvDiez.setText(wsActivitiesPageOne.get(9).getDescActividad());
                                      break;
                              }
                          }
                          break;

                      case 6:
                          if(DatosInicialesFragment.tiposAcividad.size()>9) {
                              Integer idTipoActividad = DatosInicialesFragment.tiposAcividad.get(9);
                              for (WsActivity wsActivity : TabRegistrarInspeccion.wsGeneralInfo.getWsActivities()) {
                                  if (wsActivity.getIdTipoActividad() == idTipoActividad) {
                                      wsActivitiesPageOne.add(wsActivity);
                                      tvTitulo.setText(wsActivity.getDescTipoActividad());
                                  }
                              }
                              switch (wsActivitiesPageOne.size()) {
                                  case 1:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      llDos.setVisibility(View.GONE);
                                      llTres.setVisibility(View.GONE);
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 2:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      llTres.setVisibility(View.GONE);
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 3:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 4:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 5:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 6:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 7:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 8:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 9:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      tvNueve.setText(wsActivitiesPageOne.get(8).getDescActividad());
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 10:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      tvNueve.setText(wsActivitiesPageOne.get(8).getDescActividad());
                                      tvDiez.setText(wsActivitiesPageOne.get(9).getDescActividad());
                                      break;
                              }
                          }
                          break;

                      case 7:
                          if(DatosInicialesFragment.tiposAcividad.size()>9) {
                              Integer idTipoActividad = DatosInicialesFragment.tiposAcividad.get(9);
                              for (WsActivity wsActivity : TabRegistrarInspeccion.wsGeneralInfo.getWsActivities()) {
                                  if (wsActivity.getIdTipoActividad() == idTipoActividad) {
                                      wsActivitiesPageOne.add(wsActivity);
                                      tvTitulo.setText(wsActivity.getDescTipoActividad());
                                  }
                              }
                              switch (wsActivitiesPageOne.size()) {
                                  case 1:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      llDos.setVisibility(View.GONE);
                                      llTres.setVisibility(View.GONE);
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 2:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      llTres.setVisibility(View.GONE);
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 3:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 4:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 5:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 6:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 7:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 8:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 9:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      tvNueve.setText(wsActivitiesPageOne.get(8).getDescActividad());
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 10:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      tvNueve.setText(wsActivitiesPageOne.get(8).getDescActividad());
                                      tvDiez.setText(wsActivitiesPageOne.get(9).getDescActividad());
                                      break;
                              }
                          }
                          break;

                      case 8:
                          if(DatosInicialesFragment.tiposAcividad.size()>9) {
                              Integer idTipoActividad = DatosInicialesFragment.tiposAcividad.get(9);
                              for (WsActivity wsActivity : TabRegistrarInspeccion.wsGeneralInfo.getWsActivities()) {
                                  if (wsActivity.getIdTipoActividad() == idTipoActividad) {
                                      wsActivitiesPageOne.add(wsActivity);
                                      tvTitulo.setText(wsActivity.getDescTipoActividad());
                                  }
                              }
                              switch (wsActivitiesPageOne.size()) {
                                  case 1:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      llDos.setVisibility(View.GONE);
                                      llTres.setVisibility(View.GONE);
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 2:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      llTres.setVisibility(View.GONE);
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 3:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 4:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 5:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 6:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 7:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 8:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 9:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      tvNueve.setText(wsActivitiesPageOne.get(8).getDescActividad());
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 10:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      tvNueve.setText(wsActivitiesPageOne.get(8).getDescActividad());
                                      tvDiez.setText(wsActivitiesPageOne.get(9).getDescActividad());
                                      break;
                              }
                          }
                          break;

                      case 9:
                          if(DatosInicialesFragment.tiposAcividad.size()>9) {
                              Integer idTipoActividad = DatosInicialesFragment.tiposAcividad.get(9);
                              for (WsActivity wsActivity : TabRegistrarInspeccion.wsGeneralInfo.getWsActivities()) {
                                  if (wsActivity.getIdTipoActividad() == idTipoActividad) {
                                      wsActivitiesPageOne.add(wsActivity);
                                      tvTitulo.setText(wsActivity.getDescTipoActividad());
                                  }
                              }
                              switch (wsActivitiesPageOne.size()) {
                                  case 1:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      llDos.setVisibility(View.GONE);
                                      llTres.setVisibility(View.GONE);
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 2:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      llTres.setVisibility(View.GONE);
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 3:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 4:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 5:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 6:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 7:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 8:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 9:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      tvNueve.setText(wsActivitiesPageOne.get(8).getDescActividad());
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 10:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      tvNueve.setText(wsActivitiesPageOne.get(8).getDescActividad());
                                      tvDiez.setText(wsActivitiesPageOne.get(9).getDescActividad());
                                      break;
                              }
                          }
                          break;

                      case 10:
                          if(DatosInicialesFragment.tiposAcividad.size()>9) {
                              Integer idTipoActividad = DatosInicialesFragment.tiposAcividad.get(9);
                              for (WsActivity wsActivity : TabRegistrarInspeccion.wsGeneralInfo.getWsActivities()) {
                                  if (wsActivity.getIdTipoActividad() == idTipoActividad) {
                                      wsActivitiesPageOne.add(wsActivity);
                                      tvTitulo.setText(wsActivity.getDescTipoActividad());
                                  }
                              }
                              switch (wsActivitiesPageOne.size()) {
                                  case 1:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      llDos.setVisibility(View.GONE);
                                      llTres.setVisibility(View.GONE);
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 2:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      llTres.setVisibility(View.GONE);
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 3:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      llCuatro.setVisibility(View.GONE);
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 4:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      llCinco.setVisibility(View.GONE);
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 5:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      llSeis.setVisibility(View.GONE);
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 6:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      llSiete.setVisibility(View.GONE);
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;
                                  case 7:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      llOcho.setVisibility(View.GONE);
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 8:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      llNueve.setVisibility(View.GONE);
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 9:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      tvNueve.setText(wsActivitiesPageOne.get(8).getDescActividad());
                                      llDiez.setVisibility(View.GONE);
                                      break;

                                  case 10:
                                      tvUno.setText(wsActivitiesPageOne.get(0).getDescActividad());
                                      tvDos.setText(wsActivitiesPageOne.get(1).getDescActividad());
                                      tvTres.setText(wsActivitiesPageOne.get(2).getDescActividad());
                                      tvCuatro.setText(wsActivitiesPageOne.get(3).getDescActividad());
                                      tvCinco.setText(wsActivitiesPageOne.get(4).getDescActividad());
                                      tvSeis.setText(wsActivitiesPageOne.get(5).getDescActividad());
                                      tvSiete.setText(wsActivitiesPageOne.get(6).getDescActividad());
                                      tvOcho.setText(wsActivitiesPageOne.get(7).getDescActividad());
                                      tvNueve.setText(wsActivitiesPageOne.get(8).getDescActividad());
                                      tvDiez.setText(wsActivitiesPageOne.get(9).getDescActividad());
                                      break;
                              }
                          }
                          break;
                  }

              }


          }

          void clickListener() {
              tvAtras.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      TabRegistrarInspeccion.viewPager.setCurrentItem(4);
                  }
              });
              tvSiguiente.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {

                      updateActivities();
                      TabRegistrarInspeccion.viewPager.setCurrentItem(6);


                  }
              });

              btnGuardar.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {

                      updateActivities();
                      showAlert();


                  }
              });

              ivPicture1.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {

                      pictureSelected=1;
                      showCamera();

                  }
              });

              ivPicture2.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {

                      pictureSelected=2;
                      showCamera();

                  }
              });

              ivPicture3.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {

                      pictureSelected=3;
                      showCamera();

                  }
              });


          }

          public boolean hasCameraPermission() {
              if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                  return false;
              } else {
                  return true;
              }
          }


          void showCamera()
          {
              if (!hasCameraPermission()) {
                  ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.CAMERA},
                          Constants.REQUEST_CODES.REQUEST_CODE_CAMERA);
              } else {
                  openCam(getActivity());
              }
          }


          private void openCam(Activity activity)
          {

              Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
              // Ensure that there's a camera activity to handle the intent
              if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                  // Create the File where the photo should go
                  File photoFile = null;
                  try {
                      photoFile = createImageFile();
                  } catch (IOException ex) {
                      // Error occurred while creating the File

                  }
                  // Continue only if the File was successfully created
                  if (photoFile != null) {
                      Uri photoURI = FileProvider.getUriForFile(getContext(),
                              "com.futuromovil.inspeccionvehicular.fileprovider",
                              photoFile);
                      takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                      activity.startActivityForResult(takePictureIntent, Constants.REQUEST_CODES.REQUEST_CODE_CAMERA);
                  }
              }

          }

          public static String currentPhotoPath="";
          private File createImageFile() throws IOException {
              // Create an image file name
              String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
              String imageFileName = "JPEG_" + timeStamp + "_";
              File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
              File image = File.createTempFile(
                      imageFileName,  /* prefix */
                      ".jpg",         /* suffix */
                      storageDir      /* directory */
              );

              // Save a file: path for use with ACTION_VIEW intents
              currentPhotoPath = image.getAbsolutePath();
              return image;
          }


          void showAlert() {
              new AlertDialog.Builder(getContext())
                      // .setIcon(android.R.drawable.ic_dialog_alert)
                      .setTitle("¿Guardar inspección?")
                      .setMessage("Esta seguro que desea guardar las actividades realizadas?")
                      .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialog, int which) {
                              if (Helper.isConnectedToInternet(getContext())) {
                                  if (!loading.isShowing()) {
                                      loading.show();
                                  }

                                  TabRegistrarInspeccion.inspectionPresenter.registerVehicleFrequency(TabRegistrarInspeccion.wsGeneralInfo);

                              } else {
                                  Toast.makeText(getContext(), "No tienes Internet", Toast.LENGTH_SHORT).show();
                              }
                          }

                      })
                      .setNegativeButton("No", null)
                      .show();
          }


          @Override
          public void setUserVisibleHint(boolean isVisibleToUser) {
              super.setUserVisibleHint(isVisibleToUser);
              if (isVisibleToUser) {
                  initUI(getView());
              } else {
              }
          }




      }
