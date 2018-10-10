package com.morpheus.hilomysql;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DAO
{
    public static DAO dao;

    private DAO()
    {
    }

    public static DAO getInstance()
    {
        if(dao == null)
            dao = new DAO();

        return dao;
    }

    public void getCarrier(Context context, final OnResultListListener<String> listener)
    {
        String url = "http://192.168.1.73/android/spinner.php";
        Peticion.GET get = new Peticion.GET(context, url);
        get.getResponse(new Peticion.OnPeticionListener<String>()
        {
            @Override
            public void onSuccess(String respuesta)
            {
                List<String> carriers = new ArrayList<>();
                try
                {
                    JSONArray array = new JSONArray(respuesta);
                    for(int i = 0; i < array.length(); i++)
                    {
                        JSONObject object = array.getJSONObject(i);
                        carriers.add(object.getString("nombre"));
                    }

                    listener.onSuccess(carriers);
                } catch (JSONException e)
                {
                    e.printStackTrace();
                    listener.onSuccess(null);
                }
            }

            @Override
            public void onFailed(String error, int respuestaHTTP)
            {
                listener.onFailed(error, respuestaHTTP);
            }
        });
    }

    public void getLista(Context context, final OnResultElementListener<JSONArray> listener)
    {
        String url = "http://192.168.1.73/android/recycler.php";
        Peticion.GET get = new Peticion.GET(context, url);
        get.getResponse(new Peticion.OnPeticionListener<String>()
        {
            @Override
            public void onSuccess(String respuesta)
            {
                try
                {
                    JSONArray array = new JSONArray(respuesta);
                    listener.onSuccess(array);
                } catch (JSONException e)
                {
                    e.printStackTrace();
                    listener.onSuccess(null);
                }
            }

            @Override
            public void onFailed(String error, int respuestaHTTP)
            {
                listener.onFailed(error, respuestaHTTP);
            }
        });
    }
}
