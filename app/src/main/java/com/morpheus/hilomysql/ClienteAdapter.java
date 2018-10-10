package com.morpheus.hilomysql;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteHolder>
{
    private JSONArray array;

    public ClienteAdapter(JSONArray array)
    {
        this.array = array;
    }

    @Override
    public ClienteHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_cliente, parent, false);
        return new ClienteHolder(view);
    }

    @Override
    public void onBindViewHolder(ClienteHolder holder, int position)
    {
        try
        {
            JSONObject object = array.getJSONObject(position);
            holder.txtID.setText("ID: " + object.getString("id"));
            holder.txtNombre.setText("Nombre: " + object.getString("nombre"));
            holder.txtDireccion.setText("Dirección: " + object.getString("direccion"));
            holder.txtEmail.setText("Correo: " + object.getString("email"));
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount()
    {
        return array.length();
    }

    public static class ClienteHolder extends RecyclerView.ViewHolder
    {
        TextView txtID, txtNombre, txtDireccion, txtEmail;
        public ClienteHolder(View itemView)
        {
            super(itemView);
            txtID = itemView.findViewById(R.id.txtID);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtDireccion = itemView.findViewById(R.id.txtDireccion);
            txtEmail = itemView.findViewById(R.id.txtCorreo);
        }
    }
}
