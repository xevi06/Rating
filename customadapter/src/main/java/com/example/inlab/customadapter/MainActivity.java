package com.example.inlab.customadapter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.inlab.customadapter.adapter.custom_adapter;
import com.example.inlab.customadapter.model.Restaurant;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private ListView mListView;
    private EditText mEditText;
    private Button mButton;
    private custom_adapter adapter;

    private ArrayList<Restaurant> restaurants = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.ListView);
        mEditText = (EditText) findViewById(R.id.editText);
        mButton = (Button) findViewById(R.id.button);

        fetchData();
        adapter = new custom_adapter(getApplicationContext(),restaurants);
        mListView.setAdapter(adapter);



        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Restaurant r = new Restaurant(mEditText.getText().toString(),"Low");
                adapter.add(r);
                mEditText.setText("");
            }
        });
    }

    private void fetchData(){
        restaurants.add(new Restaurant("Mc-Donalds","Low"));
        restaurants.add(new Restaurant("Pans", "Higth"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
