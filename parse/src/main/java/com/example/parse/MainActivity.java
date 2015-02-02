package com.example.parse;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private Button button;
    private ListView listView;
    private EditText editText;
    private ArrayList<String> restaurants;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "ZBmrOXlUMMmSXKObue13sjWbnVGGcrpwY0ZprZAN", "x0QLNKaYNQO9ubNCYX7qGEx9YkRQfN9XVxiyEWrP");

        final ParseObject testObject = new ParseObject("Restaurant");

        button = (Button) findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.list);
        editText = (EditText) findViewById(R.id.edittext);
        restaurants = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,restaurants);
        listView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Edit empty text",Toast.LENGTH_SHORT).show();
                }else{
                    String s = editText.getText().toString();
                    adapter.add(s);
                    testObject.put("name", s);
                    testObject.saveInBackground();
                    editText.setText("");
                }
            }
        });



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
            adapter.clear();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
