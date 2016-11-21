package com.innovagenesis.aplicaciones.android.activaidadfinalunidadcinco;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Administra todos los tabs
 * Created by Alexis on 20/11/2016.
 */

public class AppBarMain extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.app_bar_main,container,false);
    }
}
