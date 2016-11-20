package com.innovagenesis.aplicaciones.android.activaidadfinalunidadcinco;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private String[] etiquetaSubMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        /** Traen las etiquetas e imagenes del subMenu*/
        etiquetaSubMenu = getResources().getStringArray(R.array.nombreMenu);
        @SuppressLint("Recycle")
        TypedArray imgSubMenu = getResources().obtainTypedArray(R.array.nombreImgMenu);
        String[] uriIntent = getResources().getStringArray(R.array.uriMenu);

        Intent intent;

        SubMenu versionWeb = menu.addSubMenu(R.string.version_web);

        for (int i = 0; i < etiquetaSubMenu.length - 1; i++) {
            /** Agrega submenu, se envia uri a traves de un arreglo
             * Se le suma uno a i para reutilizar arreglo de etiquetas*/

            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriIntent[i + 1]));

            versionWeb.add(0, i + 1, i + 1, etiquetaSubMenu[i + 1])
                    .setIcon(imgSubMenu.getResourceId(i + 1, 0))
                    .setIntent(intent);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

/*        switch (id){
            *//** Selector del menu lateral*//*
            case R.id.vWeb:
                break;
            case R.id.share:
                break;
            case R.id.configMenu:
                break;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        int positionArray;

        switch (id) {
            /** Selector de fragmentos del Drawer*/
            case R.id.nav_home:
                positionArray = 0;
                break;
            case R.id.nav_facebook:
                positionArray = 1;
                break;
            case R.id.nav_instagram:
                positionArray = 2;
                break;
            case R.id.nav_plus:
                positionArray = 3;
                break;
            case R.id.nav_tweeter:
                positionArray = 4;
                break;
            default:
                positionArray = 0;
                break;
        }

        /** Inicializa el color */

        @SuppressLint("Recycle")
        TypedArray arrayColorToolbar = getResources().obtainTypedArray(R.array.colorToolbar);

        @SuppressWarnings("ResourceType")
                /** Se suprime alerta dado que el error no es consistente*/
        int color = arrayColorToolbar.getResourceId(positionArray, 0);
        int cambiarColor = ContextCompat.getColor(getBaseContext(), color);

        Drawable toolbarColor = new ColorDrawable(cambiarColor);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(toolbarColor);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(cambiarColor);
        }


        /** Cambia el titulo, subtitulo y color */
        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setSubtitle(etiquetaSubMenu[positionArray]);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
