package com.example.parse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.parse.modules.delete_dialog;
import com.parse.DeleteCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ListView mListView;
    private EditText mEditText;
    private Button mButton;
    private ArrayAdapter<String> adapter;

    private ArrayList<String> data = new ArrayList<>();
    private List<ParseObject> onlineData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "ZBmrOXlUMMmSXKObue13sjWbnVGGcrpwY0ZprZAN", "x0QLNKaYNQO9ubNCYX7qGEx9YkRQfN9XVxiyEWrP");


        mListView = (ListView) findViewById(R.id.list);
        mEditText = (EditText) findViewById(R.id.edittext);
        mButton = (Button) findViewById(R.id.button);

        fetchData();

        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                data);
        mListView.setAdapter(adapter);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ParseObject parseObject = new ParseObject("Restaurants");
                parseObject.put("name", mEditText.getText().toString());
                parseObject.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        Toast.makeText(getApplicationContext(), "Saved success", Toast.LENGTH_SHORT).show();
                        adapter.add(mEditText.getText().toString());
                        mEditText.setText("");
                        onlineData.add(parseObject);
                    }

                });
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Remove?")
                        .setTitle("Item");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ParseObject toDelete = onlineData.get(position);

                        toDelete.deleteInBackground(new DeleteCallback() {
                            @Override
                            public void done(ParseException e) {
                                onlineData.remove(position);
                                data.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });
    }

    private void fetchData() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Restaurants");
        try {
            onlineData = query.find();
            for (ParseObject object : onlineData) {
                data.add(object.getString("name"));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
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