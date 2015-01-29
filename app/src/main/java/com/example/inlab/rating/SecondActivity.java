package com.example.inlab.rating;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class SecondActivity extends ActionBarActivity {

    private TextView mTextViewModel;
    private TextView mTextViewMarca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mTextViewMarca = (TextView) findViewById(R.id.marca);
        mTextViewModel = (TextView) findViewById(R.id.model);


        mTextViewMarca.setText("");
        mTextViewModel.setText("");

        mTextViewModel.setText(getIntent().getExtras().getString("model"));
        mTextViewMarca.setText(getIntent().getExtras().getString("marca"));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
