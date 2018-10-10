package com.morpheus.hilomysql;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private Spinner spCarriers;
    private RecyclerView rcLista;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spCarriers = findViewById(R.id.spOpciones);
        rcLista = findViewById(R.id.rcLista);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, new String[]{"Cargando..."});
        spCarriers.setAdapter(adapter);

        rcLista.setHasFixedSize(true);
        rcLista.setLayoutManager(new LinearLayoutManager(this));

        //Hilos de carga
        spCarriers.post(hiloSpinner);
        rcLista.post(hiloRecycler);
    }

    //Hilo de carga del Spinner
    Runnable hiloSpinner = new Runnable()
    {
        @Override
        public void run()
        {
            DAO.getInstance().getCarrier(MainActivity.this, new OnResultListListener<String>()
            {
                @Override
                public void onSuccess(List<String> result)
                {
                    try
                    {
                        Thread.sleep(2000);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, result);
                    spCarriers.setAdapter(adapter);
                }

                @Override
                public void onFailed(String message, int error)
                {
                    Toast.makeText(MainActivity.this, message + " " +  error, Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    //Hilo de carga del recycler
    Runnable hiloRecycler = new Runnable()
    {
        @Override
        public void run()
        {
            DAO.getInstance().getLista(MainActivity.this, new OnResultElementListener<JSONArray>()
            {
                @Override
                public void onSuccess(JSONArray result)
                {
                    try
                    {
                        Thread.sleep(2000);
                    }catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                    ClienteAdapter adapter = new ClienteAdapter(result);
                    rcLista.setAdapter(adapter);
                }

                @Override
                public void onFailed(String message, int code)
                {
                    Toast.makeText(MainActivity.this, message + " " + code, Toast.LENGTH_SHORT).show();
                }
            });
        }
    };
}
