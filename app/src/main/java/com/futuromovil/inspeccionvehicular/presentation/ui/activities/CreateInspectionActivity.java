package com.futuromovil.inspeccionvehicular.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.futuromovil.inspeccionvehicular.R;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.inspection.response.WsImage;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.user.response.WsLoginData;
import com.futuromovil.inspeccionvehicular.domain.model.Inspector;
import com.futuromovil.inspeccionvehicular.presentation.ui.dialogfragment.CerrarInspeccionDialog;
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
import com.futuromovil.inspeccionvehicular.presentation.ui.fragments.createinspeccion.DatosInicialesFragment;
import com.futuromovil.inspeccionvehicular.presentation.ui.fragments.createinspeccion.TabRegistrarInspeccion;
import com.futuromovil.inspeccionvehicular.presentation.utils.Constants;
import com.futuromovil.inspeccionvehicular.presentation.utils.Helper;
import com.futuromovil.inspeccionvehicular.presentation.view.UserView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class CreateInspectionActivity extends BaseActivity implements UserView {

    FrameLayout containerView;
    public static TextView tvTitulo, tvTituloCultivo;
    LinearLayout btnBack;
    Inspector inspector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_inspeccion_activity);
        injectView();
        initUI();
        loadTabFragment();

        Integer value = 0;

        Bundle bundle = getIntent().getBundleExtra("extra");
        if (bundle != null) {
            value = bundle.getInt("Tipo");
        }
        tvTituloCultivo.setText("Datos iniciales");

    }


    void showConfirmacionCerrar() {
        Bundle bundle = new Bundle();
        //  bundle.putSerializable("dbEvento", dbEvento);
        CerrarInspeccionDialog df = new CerrarInspeccionDialog();
        // df.setArguments(bundle);
        df.show(getSupportFragmentManager(), "");
    }

    private void initUI() {
        containerView = (FrameLayout) findViewById(R.id.containerViewCrearCultivoPapa);
        tvTitulo = (TextView) findViewById(R.id.tvTitulo);
        tvTituloCultivo = (TextView) findViewById(R.id.tvTituloCultivo);
        btnBack = (LinearLayout) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmacionCerrar();
            }
        });
    }


    @Override
    public void onAttachFragment(Fragment fragment) {
        //ahhh=fragment;
    }

    public String getPathhh(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else return null;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {

            Bitmap bitmap = null;

            switch (DatosInicialesFragment.tiposAcividad.size()) {
                case 1:

                    TabRegistrarInspeccion.wsImages = new ArrayList<>();
                    if (CrearInspeccionFragmentUno.currentPhotoPath.equals("")) {
                        if (currentPhotoPath.equals("")) {
                        } else {
                            bitmap = BitmapFactory.decodeFile(currentPhotoPath);
                        }
                    } else {
                        bitmap = BitmapFactory.decodeFile(CrearInspeccionFragmentUno.currentPhotoPath);
                    }

                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                    String path = MediaStore.Images.Media.insertImage(CreateInspectionActivity.this.getContentResolver(), bitmap, UUID.randomUUID().toString() + ".png", "drawing");
                    Uri imageUri = Uri.parse(path);
                    String encodedImage = encodeImage(bitmap);

                    if (CrearInspeccionFragmentUno.pictureSelected == 1) {
                        CrearInspeccionFragmentUno.foto1 = encodedImage;
                        CrearInspeccionFragmentUno.ivPicture1.setImageBitmap(bitmap);
                        WsImage wsImage = new WsImage();
                        wsImage.setImage(encodedImage);
                        wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                        TabRegistrarInspeccion.wsImages.add(wsImage);
                        TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);

                        String paty = getPathhh(imageUri);
                        Inspector inspector = Helper.getUserPreference(getContext());
                        inspector.setFoto1(paty);
                        inspector.setFotoLoaded(true);
                        Helper.saveUserPreference(getContext(), inspector);

                    } else {
                        if (CrearInspeccionFragmentUno.pictureSelected == 2) {
                            CrearInspeccionFragmentUno.foto2 = encodedImage;
                            CrearInspeccionFragmentUno.ivPicture2.setImageBitmap(bitmap);
                            WsImage wsImage = new WsImage();
                            wsImage.setImage(encodedImage);
                            wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                            TabRegistrarInspeccion.wsImages.add(wsImage);
                            TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);
                            String paty = getPathhh(imageUri);
                            Inspector inspector = Helper.getUserPreference(getContext());
                            inspector.setFoto2(paty);
                            inspector.setFotoLoaded(true);
                            Helper.saveUserPreference(getContext(), inspector);
                        } else {
                            if (CrearInspeccionFragmentUno.pictureSelected == 3) {
                                CrearInspeccionFragmentUno.foto3 = encodedImage;
                                CrearInspeccionFragmentUno.ivPicture3.setImageBitmap(bitmap);
                                WsImage wsImage = new WsImage();
                                wsImage.setImage(encodedImage);
                                wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                                TabRegistrarInspeccion.wsImages.add(wsImage);
                                TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);
                                String paty = getPathhh(imageUri);
                                Inspector inspector = Helper.getUserPreference(getContext());
                                inspector.setFoto3(paty);
                                inspector.setFotoLoaded(true);
                                Helper.saveUserPreference(getContext(), inspector);
                            } else {

                            }
                        }
                    }


                case 2:

                    TabRegistrarInspeccion.wsImages = new ArrayList<>();

                    if (CrearInspeccionFragmentDos.currentPhotoPath.equals("")) {
                        if (currentPhotoPath.equals("")) {
                        } else {
                            bitmap = BitmapFactory.decodeFile(currentPhotoPath);
                        }
                    } else {
                        bitmap = BitmapFactory.decodeFile(CrearInspeccionFragmentDos.currentPhotoPath);
                    }

                    ByteArrayOutputStream bytess = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytess);
                    String pathh = MediaStore.Images.Media.insertImage(CreateInspectionActivity.this.getContentResolver(), bitmap, UUID.randomUUID().toString() + ".png", "drawing");
                    Uri imageUrii = Uri.parse(pathh);
                    String encodedImagee = encodeImage(bitmap);

                    if (CrearInspeccionFragmentDos.pictureSelected == 1) {
                        CrearInspeccionFragmentDos.foto1 = encodedImagee;
                        CrearInspeccionFragmentDos.ivPicture1.setImageBitmap(bitmap);
                        WsImage wsImage = new WsImage();
                        wsImage.setImage(encodedImagee);
                        wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                        TabRegistrarInspeccion.wsImages.add(wsImage);
                        TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);

                        String paty = getPathhh(imageUrii);
                        Inspector inspector = Helper.getUserPreference(getContext());
                        inspector.setFoto1(paty);
                        inspector.setFotoLoaded(true);
                        Helper.saveUserPreference(getContext(), inspector);
                    } else {
                        if (CrearInspeccionFragmentDos.pictureSelected == 2) {
                            CrearInspeccionFragmentDos.foto2 = encodedImagee;
                            CrearInspeccionFragmentDos.ivPicture2.setImageBitmap(bitmap);
                            WsImage wsImage = new WsImage();
                            wsImage.setImage(encodedImagee);
                            wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                            TabRegistrarInspeccion.wsImages.add(wsImage);
                            TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);

                            String paty = getPathhh(imageUrii);
                            Inspector inspector = Helper.getUserPreference(getContext());
                            inspector.setFoto2(paty);
                            inspector.setFotoLoaded(true);
                            Helper.saveUserPreference(getContext(), inspector);
                        } else {
                            if (CrearInspeccionFragmentDos.pictureSelected == 3) {
                                CrearInspeccionFragmentDos.foto3 = encodedImagee;
                                CrearInspeccionFragmentDos.ivPicture3.setImageBitmap(bitmap);
                                WsImage wsImage = new WsImage();
                                wsImage.setImage(encodedImagee);
                                wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                                TabRegistrarInspeccion.wsImages.add(wsImage);
                                TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);
                                String paty = getPathhh(imageUrii);
                                Inspector inspector = Helper.getUserPreference(getContext());
                                inspector.setFoto3(paty);
                                inspector.setFotoLoaded(true);
                                Helper.saveUserPreference(getContext(), inspector);
                            } else {

                            }
                        }
                    }


                case 3:

                    TabRegistrarInspeccion.wsImages = new ArrayList<>();
                    if (CrearInspeccionFragmentTres.currentPhotoPath.equals("")) {
                        if (currentPhotoPath.equals("")) {
                        } else {
                            bitmap = BitmapFactory.decodeFile(currentPhotoPath);
                        }
                    } else {
                        bitmap = BitmapFactory.decodeFile(CrearInspeccionFragmentTres.currentPhotoPath);
                    }

                    ByteArrayOutputStream bytesss = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytesss);
                    String pathhh = MediaStore.Images.Media.insertImage(CreateInspectionActivity.this.getContentResolver(), bitmap, UUID.randomUUID().toString() + ".png", "drawing");
                    Uri imageUriii = Uri.parse(pathhh);
                    String encodedImageee = encodeImage(bitmap);

                    if (CrearInspeccionFragmentTres.pictureSelected == 1) {
                        CrearInspeccionFragmentTres.foto1 = encodedImageee;
                        CrearInspeccionFragmentTres.ivPicture1.setImageBitmap(bitmap);
                        WsImage wsImage = new WsImage();
                        wsImage.setImage(encodedImageee);
                        wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                        TabRegistrarInspeccion.wsImages.add(wsImage);
                        TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);
                        String paty = getPathhh(imageUriii);
                        Inspector inspector = Helper.getUserPreference(getContext());
                        inspector.setFoto1(paty);
                        inspector.setFotoLoaded(true);
                        Helper.saveUserPreference(getContext(), inspector);
                    } else {
                        if (CrearInspeccionFragmentTres.pictureSelected == 2) {
                            CrearInspeccionFragmentTres.foto2 = encodedImageee;
                            CrearInspeccionFragmentTres.ivPicture2.setImageBitmap(bitmap);
                            WsImage wsImage = new WsImage();
                            wsImage.setImage(encodedImageee);
                            wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                            TabRegistrarInspeccion.wsImages.add(wsImage);
                            TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);
                            String paty = getPathhh(imageUriii);
                            Inspector inspector = Helper.getUserPreference(getContext());
                            inspector.setFoto2(paty);
                            inspector.setFotoLoaded(true);
                            Helper.saveUserPreference(getContext(), inspector);
                        } else {
                            if (CrearInspeccionFragmentTres.pictureSelected == 3) {
                                CrearInspeccionFragmentTres.foto3 = encodedImageee;
                                CrearInspeccionFragmentTres.ivPicture3.setImageBitmap(bitmap);
                                WsImage wsImage = new WsImage();
                                wsImage.setImage(encodedImageee);
                                wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                                TabRegistrarInspeccion.wsImages.add(wsImage);
                                TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);
                                String paty = getPathhh(imageUriii);
                                Inspector inspector = Helper.getUserPreference(getContext());
                                inspector.setFoto3(paty);
                                inspector.setFotoLoaded(true);
                                Helper.saveUserPreference(getContext(), inspector);
                            } else {

                            }
                        }
                    }

                case 4:

                    TabRegistrarInspeccion.wsImages = new ArrayList<>();

                    if (CrearInspeccionFragmentCuatro.currentPhotoPath.equals("")) {
                        if (currentPhotoPath.equals("")) {
                        } else {
                            bitmap = BitmapFactory.decodeFile(currentPhotoPath);
                        }
                    } else {
                        bitmap = BitmapFactory.decodeFile(CrearInspeccionFragmentCuatro.currentPhotoPath);
                    }

                    ByteArrayOutputStream bytessss = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytessss);
                    String pathhhh = MediaStore.Images.Media.insertImage(CreateInspectionActivity.this.getContentResolver(), bitmap, UUID.randomUUID().toString() + ".png", "drawing");
                    Uri imageUriiii = Uri.parse(pathhhh);
                    String encodedImageeee = encodeImage(bitmap);

                    if (CrearInspeccionFragmentCuatro.pictureSelected == 1) {
                        CrearInspeccionFragmentCuatro.foto1 = encodedImageeee;
                        CrearInspeccionFragmentCuatro.ivPicture1.setImageBitmap(bitmap);
                        WsImage wsImage = new WsImage();
                        wsImage.setImage(encodedImageeee);
                        wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                        TabRegistrarInspeccion.wsImages.add(wsImage);
                        TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);
                        String paty = getPathhh(imageUriiii);
                        Inspector inspector = Helper.getUserPreference(getContext());
                        inspector.setFoto1(paty);
                        inspector.setFotoLoaded(true);
                        Helper.saveUserPreference(getContext(), inspector);
                    } else {
                        if (CrearInspeccionFragmentCuatro.pictureSelected == 2) {
                            CrearInspeccionFragmentCuatro.foto2 = encodedImageeee;
                            CrearInspeccionFragmentCuatro.ivPicture2.setImageBitmap(bitmap);
                            WsImage wsImage = new WsImage();
                            wsImage.setImage(encodedImageeee);
                            wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                            TabRegistrarInspeccion.wsImages.add(wsImage);
                            TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);
                            String paty = getPathhh(imageUriiii);
                            Inspector inspector = Helper.getUserPreference(getContext());
                            inspector.setFoto2(paty);
                            inspector.setFotoLoaded(true);
                            Helper.saveUserPreference(getContext(), inspector);
                        } else {
                            if (CrearInspeccionFragmentCuatro.pictureSelected == 3) {
                                CrearInspeccionFragmentCuatro.foto3 = encodedImageeee;
                                CrearInspeccionFragmentCuatro.ivPicture3.setImageBitmap(bitmap);
                                WsImage wsImage = new WsImage();
                                wsImage.setImage(encodedImageeee);
                                wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                                TabRegistrarInspeccion.wsImages.add(wsImage);
                                TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);
                                String paty = getPathhh(imageUriiii);
                                Inspector inspector = Helper.getUserPreference(getContext());
                                inspector.setFoto3(paty);
                                inspector.setFotoLoaded(true);
                                Helper.saveUserPreference(getContext(), inspector);
                            } else {

                            }
                        }
                    }

                case 5:

                    TabRegistrarInspeccion.wsImages = new ArrayList<>();

                    if (CrearInspeccionFragmentCinco.currentPhotoPath.equals("")) {
                        if (currentPhotoPath.equals("")) {
                        } else {
                            bitmap = BitmapFactory.decodeFile(currentPhotoPath);
                        }
                    } else {
                        bitmap = BitmapFactory.decodeFile(CrearInspeccionFragmentCinco.currentPhotoPath);
                    }
                    ByteArrayOutputStream bytesssss = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytesssss);
                    String pathhhhh = MediaStore.Images.Media.insertImage(CreateInspectionActivity.this.getContentResolver(), bitmap, UUID.randomUUID().toString() + ".png", "drawing");
                    Uri imageUriiiii = Uri.parse(pathhhhh);
                    String encodedImageeeee = encodeImage(bitmap);

                    if (CrearInspeccionFragmentCinco.pictureSelected == 1) {
                        CrearInspeccionFragmentCinco.foto1 = encodedImageeeee;
                        CrearInspeccionFragmentCinco.ivPicture1.setImageBitmap(bitmap);
                        WsImage wsImage = new WsImage();
                        wsImage.setImage(encodedImageeeee);
                        wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                        TabRegistrarInspeccion.wsImages.add(wsImage);
                        TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);
                        String paty = getPathhh(imageUriiiii);
                        Inspector inspector = Helper.getUserPreference(getContext());
                        inspector.setFoto1(paty);
                        inspector.setFotoLoaded(true);
                        Helper.saveUserPreference(getContext(), inspector);
                    } else {
                        if (CrearInspeccionFragmentCinco.pictureSelected == 2) {
                            CrearInspeccionFragmentCinco.foto2 = encodedImageeeee;
                            CrearInspeccionFragmentCinco.ivPicture2.setImageBitmap(bitmap);
                            WsImage wsImage = new WsImage();
                            wsImage.setImage(encodedImageeeee);
                            wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                            TabRegistrarInspeccion.wsImages.add(wsImage);
                            TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);
                            String paty = getPathhh(imageUriiiii);
                            Inspector inspector = Helper.getUserPreference(getContext());
                            inspector.setFoto2(paty);
                            inspector.setFotoLoaded(true);
                            Helper.saveUserPreference(getContext(), inspector);
                        } else {
                            if (CrearInspeccionFragmentCinco.pictureSelected == 3) {
                                CrearInspeccionFragmentCinco.foto3 = encodedImageeeee;
                                CrearInspeccionFragmentCinco.ivPicture3.setImageBitmap(bitmap);
                                WsImage wsImage = new WsImage();
                                wsImage.setImage(encodedImageeeee);
                                wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                                TabRegistrarInspeccion.wsImages.add(wsImage);
                                TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);
                                String paty = getPathhh(imageUriiiii);
                                Inspector inspector = Helper.getUserPreference(getContext());
                                inspector.setFoto3(paty);
                                inspector.setFotoLoaded(true);
                                Helper.saveUserPreference(getContext(), inspector);
                            } else {

                            }
                        }
                    }

                case 6:

                    TabRegistrarInspeccion.wsImages = new ArrayList<>();


                    if (CrearInspeccionFragmentSeis.currentPhotoPath.equals("")) {
                        if (currentPhotoPath.equals("")) {
                        } else {
                            bitmap = BitmapFactory.decodeFile(currentPhotoPath);
                        }
                    } else {
                        bitmap = BitmapFactory.decodeFile(CrearInspeccionFragmentSeis.currentPhotoPath);
                    }
                    ByteArrayOutputStream bytessssss = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytessssss);
                    String pathhhhhh = MediaStore.Images.Media.insertImage(CreateInspectionActivity.this.getContentResolver(), bitmap, UUID.randomUUID().toString() + ".png", "drawing");
                    Uri imageUriiiiii = Uri.parse(pathhhhhh);
                    String encodedImageeeeee = encodeImage(bitmap);

                    if (CrearInspeccionFragmentSeis.pictureSelected == 1) {
                        CrearInspeccionFragmentSeis.foto1 = encodedImageeeeee;
                        CrearInspeccionFragmentSeis.ivPicture1.setImageBitmap(bitmap);
                        WsImage wsImage = new WsImage();
                        wsImage.setImage(encodedImageeeeee);
                        wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                        TabRegistrarInspeccion.wsImages.add(wsImage);
                        TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);
                        String paty = getPathhh(imageUriiiiii);
                        Inspector inspector = Helper.getUserPreference(getContext());
                        inspector.setFoto1(paty);
                        inspector.setFotoLoaded(true);
                        Helper.saveUserPreference(getContext(), inspector);
                    } else {
                        if (CrearInspeccionFragmentSeis.pictureSelected == 2) {
                            CrearInspeccionFragmentSeis.foto2 = encodedImageeeeee;
                            CrearInspeccionFragmentSeis.ivPicture2.setImageBitmap(bitmap);
                            WsImage wsImage = new WsImage();
                            wsImage.setImage(encodedImageeeeee);
                            wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                            TabRegistrarInspeccion.wsImages.add(wsImage);
                            TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);
                            String paty = getPathhh(imageUriiiiii);
                            Inspector inspector = Helper.getUserPreference(getContext());
                            inspector.setFoto2(paty);
                            inspector.setFotoLoaded(true);
                            Helper.saveUserPreference(getContext(), inspector);
                        } else {
                            if (CrearInspeccionFragmentSeis.pictureSelected == 3) {
                                CrearInspeccionFragmentSeis.foto3 = encodedImageeeeee;
                                CrearInspeccionFragmentSeis.ivPicture3.setImageBitmap(bitmap);
                                WsImage wsImage = new WsImage();
                                wsImage.setImage(encodedImageeeeee);
                                wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                                TabRegistrarInspeccion.wsImages.add(wsImage);
                                TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);
                                String paty = getPathhh(imageUriiiiii);
                                Inspector inspector = Helper.getUserPreference(getContext());
                                inspector.setFoto3(paty);
                                inspector.setFotoLoaded(true);
                                Helper.saveUserPreference(getContext(), inspector);
                            } else {

                            }
                        }
                    }


                case 7:

                    TabRegistrarInspeccion.wsImages = new ArrayList<>();

                    if (CrearInspeccionFragmentSiete.currentPhotoPath.equals("")) {
                        if (currentPhotoPath.equals("")) {
                        } else {
                            bitmap = BitmapFactory.decodeFile(currentPhotoPath);
                        }
                    } else {
                        bitmap = BitmapFactory.decodeFile(CrearInspeccionFragmentSiete.currentPhotoPath);
                    }
                    ByteArrayOutputStream bytesssssss = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytesssssss);
                    String pathhhhhhh = MediaStore.Images.Media.insertImage(CreateInspectionActivity.this.getContentResolver(), bitmap, UUID.randomUUID().toString() + ".png", "drawing");
                    Uri imageUriiiiiii = Uri.parse(pathhhhhhh);
                    String encodedImageeeeeee = encodeImage(bitmap);

                    if (CrearInspeccionFragmentSiete.pictureSelected == 1) {
                        CrearInspeccionFragmentSiete.foto1 = encodedImageeeeeee;
                        CrearInspeccionFragmentSiete.ivPicture1.setImageBitmap(bitmap);
                        WsImage wsImage = new WsImage();
                        wsImage.setImage(encodedImageeeeeee);
                        wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                        TabRegistrarInspeccion.wsImages.add(wsImage);
                        TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);
                        String paty = getPathhh(imageUriiiiiii);
                        Inspector inspector = Helper.getUserPreference(getContext());
                        inspector.setFoto1(paty);
                        inspector.setFotoLoaded(true);
                        Helper.saveUserPreference(getContext(), inspector);
                    } else {
                        if (CrearInspeccionFragmentSiete.pictureSelected == 2) {
                            CrearInspeccionFragmentSiete.foto2 = encodedImageeeeeee;
                            CrearInspeccionFragmentSiete.ivPicture2.setImageBitmap(bitmap);
                            WsImage wsImage = new WsImage();
                            wsImage.setImage(encodedImageeeeeee);
                            wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                            TabRegistrarInspeccion.wsImages.add(wsImage);
                            TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);
                            String paty = getPathhh(imageUriiiiiii);
                            Inspector inspector = Helper.getUserPreference(getContext());
                            inspector.setFoto2(paty);
                            inspector.setFotoLoaded(true);
                            Helper.saveUserPreference(getContext(), inspector);
                        } else {
                            if (CrearInspeccionFragmentSiete.pictureSelected == 3) {
                                CrearInspeccionFragmentSiete.foto3 = encodedImageeeeeee;
                                CrearInspeccionFragmentSiete.ivPicture3.setImageBitmap(bitmap);
                                WsImage wsImage = new WsImage();
                                wsImage.setImage(encodedImageeeeeee);
                                wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                                TabRegistrarInspeccion.wsImages.add(wsImage);
                                TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);
                                String paty = getPathhh(imageUriiiiiii);
                                Inspector inspector = Helper.getUserPreference(getContext());
                                inspector.setFoto3(paty);
                                inspector.setFotoLoaded(true);
                                Helper.saveUserPreference(getContext(), inspector);
                            } else {

                            }
                        }
                    }


                case 8:

                    TabRegistrarInspeccion.wsImages = new ArrayList<>();

                    if (CrearInspeccionFragmentOcho.currentPhotoPath.equals("")) {
                        if (currentPhotoPath.equals("")) {
                        } else {
                            bitmap = BitmapFactory.decodeFile(currentPhotoPath);
                        }
                    } else {
                        bitmap = BitmapFactory.decodeFile(CrearInspeccionFragmentOcho.currentPhotoPath);
                    }
                    ByteArrayOutputStream bytessssssss = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytessssssss);
                    String pathhhhhhhh = MediaStore.Images.Media.insertImage(CreateInspectionActivity.this.getContentResolver(), bitmap, UUID.randomUUID().toString() + ".png", "drawing");
                    Uri imageUriiiiiiii = Uri.parse(pathhhhhhhh);
                    String encodedImageeeeeeee = encodeImage(bitmap);

                    if (CrearInspeccionFragmentOcho.pictureSelected == 1) {
                        CrearInspeccionFragmentOcho.foto1 = encodedImageeeeeeee;
                        CrearInspeccionFragmentOcho.ivPicture1.setImageBitmap(bitmap);
                        WsImage wsImage = new WsImage();
                        wsImage.setImage(encodedImageeeeeeee);
                        wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                        TabRegistrarInspeccion.wsImages.add(wsImage);
                        TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);
                        String paty = getPathhh(imageUriiiiiiii);
                        Inspector inspector = Helper.getUserPreference(getContext());
                        inspector.setFoto1(paty);
                        inspector.setFotoLoaded(true);
                        Helper.saveUserPreference(getContext(), inspector);
                    } else {
                        if (CrearInspeccionFragmentOcho.pictureSelected == 2) {
                            CrearInspeccionFragmentOcho.foto2 = encodedImageeeeeeee;
                            CrearInspeccionFragmentOcho.ivPicture2.setImageBitmap(bitmap);
                            WsImage wsImage = new WsImage();
                            wsImage.setImage(encodedImageeeeeeee);
                            wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                            TabRegistrarInspeccion.wsImages.add(wsImage);
                            TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);
                            String paty = getPathhh(imageUriiiiiiii);
                            Inspector inspector = Helper.getUserPreference(getContext());
                            inspector.setFoto2(paty);
                            inspector.setFotoLoaded(true);
                            Helper.saveUserPreference(getContext(), inspector);
                        } else {
                            if (CrearInspeccionFragmentOcho.pictureSelected == 3) {
                                CrearInspeccionFragmentOcho.foto3 = encodedImageeeeeeee;
                                CrearInspeccionFragmentOcho.ivPicture3.setImageBitmap(bitmap);
                                WsImage wsImage = new WsImage();
                                wsImage.setImage(encodedImageeeeeeee);
                                wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                                TabRegistrarInspeccion.wsImages.add(wsImage);
                                TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);
                                String paty = getPathhh(imageUriiiiiiii);
                                Inspector inspector = Helper.getUserPreference(getContext());
                                inspector.setFoto3(paty);
                                inspector.setFotoLoaded(true);
                                Helper.saveUserPreference(getContext(), inspector);
                            } else {

                            }
                        }
                    }


                case 9:

                    TabRegistrarInspeccion.wsImages = new ArrayList<>();

                    if (CrearInspeccionFragmentNueve.currentPhotoPath.equals("")) {
                        if (currentPhotoPath.equals("")) {
                        } else {
                            bitmap = BitmapFactory.decodeFile(currentPhotoPath);
                        }
                    } else {
                        bitmap = BitmapFactory.decodeFile(CrearInspeccionFragmentNueve.currentPhotoPath);
                    }
                    ByteArrayOutputStream bytesssssssss = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytesssssssss);
                    String pathhhhhhhhh = MediaStore.Images.Media.insertImage(CreateInspectionActivity.this.getContentResolver(), bitmap, UUID.randomUUID().toString() + ".png", "drawing");
                    Uri imageUriiiiiiiii = Uri.parse(pathhhhhhhhh);
                    String encodedImageeeeeeeee = encodeImage(bitmap);

                    if (CrearInspeccionFragmentNueve.pictureSelected == 1) {
                        CrearInspeccionFragmentNueve.foto1 = encodedImageeeeeeeee;
                        CrearInspeccionFragmentNueve.ivPicture1.setImageBitmap(bitmap);
                        WsImage wsImage = new WsImage();
                        wsImage.setImage(encodedImageeeeeeeee);
                        wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                        TabRegistrarInspeccion.wsImages.add(wsImage);
                        TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);
                        String paty = getPathhh(imageUriiiiiiiii);
                        Inspector inspector = Helper.getUserPreference(getContext());
                        inspector.setFoto1(paty);
                        inspector.setFotoLoaded(true);
                        Helper.saveUserPreference(getContext(), inspector);
                    } else {
                        if (CrearInspeccionFragmentNueve.pictureSelected == 2) {
                            CrearInspeccionFragmentNueve.foto2 = encodedImageeeeeeeee;
                            CrearInspeccionFragmentNueve.ivPicture2.setImageBitmap(bitmap);
                            WsImage wsImage = new WsImage();
                            wsImage.setImage(encodedImageeeeeeeee);
                            wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                            TabRegistrarInspeccion.wsImages.add(wsImage);
                            TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);
                            String paty = getPathhh(imageUriiiiiiiii);
                            Inspector inspector = Helper.getUserPreference(getContext());
                            inspector.setFoto2(paty);
                            inspector.setFotoLoaded(true);
                            Helper.saveUserPreference(getContext(), inspector);
                        } else {
                            if (CrearInspeccionFragmentNueve.pictureSelected == 3) {
                                CrearInspeccionFragmentNueve.foto3 = encodedImageeeeeeeee;
                                CrearInspeccionFragmentNueve.ivPicture3.setImageBitmap(bitmap);
                                WsImage wsImage = new WsImage();
                                wsImage.setImage(encodedImageeeeeeeee);
                                wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                                TabRegistrarInspeccion.wsImages.add(wsImage);
                                TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);
                                String paty = getPathhh(imageUriiiiiiiii);
                                Inspector inspector = Helper.getUserPreference(getContext());
                                inspector.setFoto3(paty);
                                inspector.setFotoLoaded(true);
                                Helper.saveUserPreference(getContext(), inspector);
                            } else {

                            }
                        }
                    }

                case 10:

                    TabRegistrarInspeccion.wsImages = new ArrayList<>();

                    if (CrearInspeccionFragmentDiez.currentPhotoPath.equals("")) {
                        if (currentPhotoPath.equals("")) {
                        } else {
                            bitmap = BitmapFactory.decodeFile(currentPhotoPath);
                        }
                    } else {
                        bitmap = BitmapFactory.decodeFile(CrearInspeccionFragmentDiez.currentPhotoPath);
                    }
                    ByteArrayOutputStream bytessssssssss = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytessssssssss);
                    String pathhhhhhhhhh = MediaStore.Images.Media.insertImage(CreateInspectionActivity.this.getContentResolver(), bitmap, UUID.randomUUID().toString() + ".png", "drawing");
                    Uri imageUriiiiiiiiii = Uri.parse(pathhhhhhhhhh);
                    String encodedImageeeeeeeeee = encodeImage(bitmap);

                    if (CrearInspeccionFragmentDiez.pictureSelected == 1) {
                        CrearInspeccionFragmentDiez.foto1 = encodedImageeeeeeeeee;
                        CrearInspeccionFragmentDiez.ivPicture1.setImageBitmap(bitmap);
                        WsImage wsImage = new WsImage();
                        wsImage.setImage(encodedImageeeeeeeeee);
                        wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                        TabRegistrarInspeccion.wsImages.add(wsImage);
                        TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);
                        String paty = getPathhh(imageUriiiiiiiiii);
                        Inspector inspector = Helper.getUserPreference(getContext());
                        inspector.setFoto1(paty);
                        inspector.setFotoLoaded(true);
                        Helper.saveUserPreference(getContext(), inspector);
                    } else {
                        if (CrearInspeccionFragmentDiez.pictureSelected == 2) {
                            CrearInspeccionFragmentDiez.foto2 = encodedImageeeeeeeeee;
                            CrearInspeccionFragmentDiez.ivPicture2.setImageBitmap(bitmap);
                            WsImage wsImage = new WsImage();
                            wsImage.setImage(encodedImageeeeeeeeee);
                            wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                            TabRegistrarInspeccion.wsImages.add(wsImage);
                            TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);
                            String paty = getPathhh(imageUriiiiiiiiii);
                            Inspector inspector = Helper.getUserPreference(getContext());
                            inspector.setFoto2(paty);
                            inspector.setFotoLoaded(true);
                            Helper.saveUserPreference(getContext(), inspector);
                        } else {
                            if (CrearInspeccionFragmentDiez.pictureSelected == 3) {
                                CrearInspeccionFragmentDiez.foto3 = encodedImageeeeeeeeee;
                                CrearInspeccionFragmentDiez.ivPicture3.setImageBitmap(bitmap);
                                WsImage wsImage = new WsImage();
                                wsImage.setImage(encodedImageeeeeeeeee);
                                wsImage.setIdFrequency(TabRegistrarInspeccion.wsGeneralInfo.getWsActivities().get(0).getIdVehiculoFrecuencia());
                                TabRegistrarInspeccion.wsImages.add(wsImage);
                                TabRegistrarInspeccion.wsGeneralInfo.setWsImages(TabRegistrarInspeccion.wsImages);
                                String paty = getPathhh(imageUriiiiiiiiii);
                                Inspector inspector = Helper.getUserPreference(getContext());
                                inspector.setFoto3(paty);
                                inspector.setFotoLoaded(true);
                                Helper.saveUserPreference(getContext(), inspector);
                            } else {

                            }
                        }
                    }

            }


        } catch (Exception e) {
            String message = e.getMessage();
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {

            case Constants.REQUEST_CODES.REQUEST_CODE_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //  Toast.makeText(getApplicationContext(), "Ahora puede acceder a la galería", Toast.LENGTH_LONG).show();
                    //  showGallery();
                } else {
                    // Toast.makeText(getApplicationContext(), getResources().getString(R.string.main_request_permision_storage), Toast.LENGTH_LONG).show();
                }
                return;
            }

            case Constants.REQUEST_CODES.REQUEST_CODE_CAMERA: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCam();
                } else {
                    //   Toast.makeText(getApplicationContext(), getResources().getString(R.string.main_request_permision_storage), Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }


    public boolean hasCameraPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }


    void showCamera() {
        if (!hasCameraPermission()) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA},
                    Constants.REQUEST_CODES.REQUEST_CODE_CAMERA);
        } else {
            openCam();
        }
    }


    private void openCam() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
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
                startActivityForResult(takePictureIntent, Constants.REQUEST_CODES.REQUEST_CODE_CAMERA);
            }
        }

    }

    public static String currentPhotoPath = "";

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encImage;
    }


    void loadTabFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TabRegistrarInspeccion inspTabRegistrarInspeccion = new TabRegistrarInspeccion();
        fragmentTransaction.replace(R.id.containerViewCrearCultivoPapa, inspTabRegistrarInspeccion);
        fragmentTransaction.commit();
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
        inspector = Helper.getUserPreference(getContext());
        inspector.setFotoLoaded(false);
        Helper.saveUserPreference(getContext(), inspector);
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