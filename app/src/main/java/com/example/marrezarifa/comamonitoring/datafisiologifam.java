package com.example.marrezarifa.comamonitoring;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class datafisiologifam extends AppCompatActivity {


    private ProgressDialog pdDialogl;
    private String host = "http://comamonitoring.xyz/coma/monitoring.php";
    private TextView idbrpm, ketidbrpm, idbtpm, ketidbtpm, ketidmata;
    private String id_pasien = famlogin.id_pasien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datafisiologi);

        idbrpm = (TextView)findViewById(R.id.idbrpm);
        ketidbrpm = (TextView)findViewById(R.id.ketidbrpm);
        idbtpm = (TextView)findViewById(R.id.idbtpm);
        ketidbtpm = (TextView)findViewById(R.id.ketidbtpm);
        ketidmata = (TextView)findViewById(R.id.ketidmata);
        final String id_pasien = famlogin.id_pasien;
        monitoring(id_pasien);
    }

    private void monitoring(final String id_pasien){
        showDialog(true,"Loading...");
        StringRequest req = new StringRequest(Request.Method.POST, host, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("RequestLogin",s);
                try {
                    JSONObject data = new JSONObject(s);
                    idbrpm.setText(data.getString("brpm"));
                    ketidbrpm.setText(data.getString("stat_jantung"));
                    idbtpm.setText(data.getString("btpm"));
                    ketidbtpm.setText(data.getString("stat_napas"));
                    ketidmata.setText(data.getString("stat_mata"));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Kesalahan dalam koneksi\nPastikan perangkat telah terkoneksi dengan benar!", Toast.LENGTH_SHORT).show();
                    Log.e("RequestLogin",e.getMessage());
                }
                showDialog(false, "");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Kesalahan dalam koneksi\nPastikan perangkat telah terkoneksi dengan benar!", Toast.LENGTH_SHORT).show();
                showDialog(false, "");
                Log.e("RequestLogin",error.getMessage());
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("id_pasien", id_pasien);
                return param;
            }
        };
        Volley.newRequestQueue(this).add(req);
    }

    private void showDialog(boolean show,String message){
        if(pdDialogl == null){
            pdDialogl = new ProgressDialog(this);
        }
        pdDialogl.setMessage(message);
        pdDialogl.setCancelable(false);
        if(show) pdDialogl.show();
        else pdDialogl.dismiss();
    }

}
