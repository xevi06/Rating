package com.example.daomodule;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.daomodule.models.RestaurantORMHelper;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;


public class MainActivity extends OrmLiteBaseActivity<RestaurantORMHelper> {

    private Button llegir;
    private Button escriure;
    private TextView llista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llegir = (Button) findViewById(R.id.llegir);
        escriure = (Button) findViewById(R.id.guardar);
        llista = (TextView) findViewById(R.id.llista);


    }

}