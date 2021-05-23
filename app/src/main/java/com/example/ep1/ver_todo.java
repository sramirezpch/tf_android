package com.example.ep1;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

public class ver_todo extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList arrayList = new ArrayList<HashMap<String,String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_todo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        leerDatos();
    }

    private void leerDatos() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://jsonplaceholder.typicode.com/users/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("DATOS:", response);
                        llenarLista(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("DATOS:", error.getMessage());
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void llenarLista(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for(int i= 0; i<jsonArray.length();i++){
                String name = jsonArray.getJSONObject(i).getString("name");
                String username = jsonArray.getJSONObject(i).getString("username");
                String email = jsonArray.getJSONObject(i).getString("email");
                HashMap<String,String> map = new HashMap<>();
                map.put("name", name);
                map.put("username", username);
                map.put("email", email);
                arrayList.add(map);
            }

            ListView lvUsuarios = findViewById(R.id.lvUsuarios);
            String[] datos = {"name","username","email"};
            int[] ids = {R.id.txvNombre, R.id.txvUsername, R.id.txvMail};

            ListAdapter listAdapter = new SimpleAdapter(
                    this,
                    arrayList,
                    R.layout.item_ver_todo,
                    datos,
                    ids
            );

            lvUsuarios.setAdapter(listAdapter);
            lvUsuarios.setOnItemClickListener(this);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("POSICION", String.valueOf(position));
        HashMap<String,String> map = (HashMap<String, String>) arrayList.get(position);
        String name = map.get("name");
        Toast.makeText(this,name,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
