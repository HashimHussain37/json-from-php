package com.example.json_from_php;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button bttn_sqlConn;
    TextView textview;
    //ArrayList<User> userList;
    ListView listView;
    DBHelper mydb;

    phpConn phpC = new phpConn();
    final String fetch = "http://192.168.43.175/sqli/Fetch2.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview=(TextView)findViewById(R.id.textView);
        bttn_sqlConn= (Button)findViewById(R.id.bttnSQLCon);
        bttn_sqlConn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    phpC.urlCon(fetch);
                    //getting an array back
                    JSONArray js_array = new JSONArray(phpC.getAnswer());

                    textview.setText(js_array.toString());
                    // int numRows = data.getCount();
                    /* reading the JSON array line by line */
                    for (int i = 0; i < js_array.length(); i++) {
                        JSONObject value=js_array.getJSONObject(i);
                        String name=value.getString("id");
                        Log.v("george json", i+"="+name.toString());

                        ArrayList<String> fetchAll = new ArrayList<String>();
                        fetchAll = mydb.getAllContacts();
                         /*
                        try {
                            URL url = new URL(users_url);

                            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                            httpURLConnection.setDoInput(true);


                            InputStream inputStream = httpURLConnection.getInputStream();
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                            StringBuilder stringBuilder = new StringBuilder();


                            while((JOSN_STRING = bufferedReader.readLine())!= null) {
                                stringBuilder.append(JOSN_STRING+"\n");
                            }

                            bufferedReader.close();
                            inputStream.close();
                            httpURLConnection.disconnect();

                            return stringBuilder.toString().trim();

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //Intent intent = new Intent(this, Display.class);
                        //intent.putStringArrayListExtra("fetchAll", fetchAll);
                        //getApplicationContext().startActivity(intent);

                        if(numRows == 0){
                            Toast.makeText(main.this,"The Database is empty  :(.",Toast.LENGTH_LONG).show();
                        }else{
                            int i=0;
                            while(data.moveToNext()){
                                user = new User(data.getString(1),data.getString(2),data.getString(3));
                                userList.add(i,user);
                                System.out.println(data.getString(1)+" "+data.getString(2)+" "+data.getString(3));
                                System.out.println(userList.get(i).getFirstName());
                                i++;
                            }
                            ThreeColumn_ListAdapter adapter =  new ThreeColumn_ListAdapter(this,R.layout.list_adapter_view, userList);
                            listView = (ListView) findViewById(R.id.listView);
                            listView.setAdapter(adapter);

 */
                    }
                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }
            }
        });

    }

}