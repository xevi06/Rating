package com.example.daomodule;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;


public class MainActivity extends OrmLiteBaseActivity<RestaurantORMHelper> {

    private Button llegir;
    private Button escriure;
    private Button borrar;
    private TextView llista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llegir = (Button) findViewById(R.id.llegir);
        escriure = (Button) findViewById(R.id.guardar);
        borrar = (Button) findViewById(R.id.borrar);
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
                    String frase = "";
                    try{
                        Dao<RestaurantORMDao,Integer> restaurantDao = getHelper().getDao();
                        List<RestaurantORMDao> llistaa = restaurantDao.queryForAll();
                        frase += "Numero de Restaurants: " + llistaa.size() + "\n";
                        for(RestaurantORMDao rest : llistaa){
                            frase += rest.getName() + " " + rest.getRate() + " " + rest.getType() + "\n";
                        }

                        llista.setText(frase);

                    } catch (SQLException e) {
                        Log.i("OnCreate", "Exception");
                    }
            }
        });
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    TableUtils.clearTable(getConnectionSource(), RestaurantORMDao.class);
                } catch (SQLException e) {
                    throw new RuntimeException();
                }
                llista.setText("BD Borrada");
            }/*

            new AlertDialog.Builder(

            getApplicationContext()

            )
                    .

            setIcon(android.R.drawable.ic_dialog_alert)

            .

            setTitle("Cleaning BD")

            .

            setMessage("Are you sure you want to clean this BD?")

            .

            setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                @Override
                public void onClick (DialogInterface dialog,int which){

                    try {
                        TableUtils.clearTable(getConnectionSource(), RestaurantORMDao.class);
                    } catch (SQLException e) {
                        throw new RuntimeException();
                    }
                    llista.setText("BD Borrada");
                }

            }

            )
                    .

            setNegativeButton("No",null)

            .

            show();
        }*/
    });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to close this activity?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

}