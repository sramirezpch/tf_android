package com.example.ep1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

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

public class productos_marca extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ArrayList arrayList = new ArrayList<HashMap<String,String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_marca);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        leerDatos();
    }


    private void leerDatos() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://products-api-2.herokuapp.com/products";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("DATOS:", response);
                        Log.i("ONRESPONSE: ", "Entrando al response");
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
            Log.i("ONLIST: ", "Entrando a la función llenarLista()");
            Log.i("", "llenarLista()");
            JSONArray jsonArray = new JSONArray(response);
            for(int i= 0; i<jsonArray.length();i++){
                String name = jsonArray.getJSONObject(i).getString("name");
                String company = jsonArray.getJSONObject(i).getString("company");
                String price = jsonArray.getJSONObject(i).getString("price");
                String description = jsonArray.getJSONObject(i).getString("description");
                HashMap<String,String> map = new HashMap<>();
                map.put("name", name);
                map.put("company", company);
                map.put("price", price);
                map.put("description", description);
                arrayList.add(map);
            }

            ListView lvProductosMarca = findViewById(R.id.lvProductosMarca);
            String[] datos = {"name","company","price", "description"};
            int[] ids = {R.id.txvProductName,  R.id.txvCompany, R.id.txvPrice, R.id.txvDescription};

            ListAdapter listAdapter = new SimpleAdapter(
                    this,
                    arrayList,
                    R.layout.item_productos_marca,
                    datos,
                    ids
            );

            lvProductosMarca.setAdapter(listAdapter);
            lvProductosMarca.setOnItemClickListener(this);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String,String> map = (HashMap<String, String>) arrayList.get(position);
        String name = map.get("'name'");
        String company = map.get("company   ");

        Toast.makeText(this, name + " es de la marca " + company,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}