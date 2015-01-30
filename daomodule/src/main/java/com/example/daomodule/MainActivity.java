package com.example.daomodule;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.daomodule.models.RestaurantORMDao;
import com.example.daomodule.models.RestaurantORMHelper;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.Random;


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

        escriure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int numeroRandom = random.nextInt(100);
                String nomRest = "aaa" + numeroRandom;
                String tipus;
                switch (numeroRandom%3) {
                    case 1:
                        tipus = "Fast";
                        break;
                    case 2:
                        tipus = "xino";
                        break;
                    default:
                        tipus = "clase";
                        break;
                }

                int val = random.nextInt(11);

                RestaurantORMDao restaurantORMDao = new RestaurantORMDao(nomRest,val, tipus);

                try{
                    Dao<RestaurantORMDao,Integer> dao = getHelper().getDao();
                    dao.create(restaurantORMDao);
                    llista.setText("Restaurant: " + nomRest + "inserit bé");
                } catch (SQLException e) {
                    llista.setText("Error al inserir: " + nomRest);
                    e.printStackTrace();
                }
            }
        });

        llegir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}