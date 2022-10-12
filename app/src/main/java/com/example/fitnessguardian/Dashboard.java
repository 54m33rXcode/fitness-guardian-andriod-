package com.example.fitnessguardian;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fitnessguardian.databinding.DashboardBinding;

import java.util.HashMap;
import java.util.Map;

public class Dashboard extends DrawerBase implements View.OnClickListener {
    DashboardBinding dashboardBinding;

    Button add,view;
    EditText date,age,weight,height,bmi;
    private static final String url="http://192.168.32.42/Fitness_guardian_crud/insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dashboardBinding = DashboardBinding.inflate(getLayoutInflater());
        setContentView(dashboardBinding.getRoot());
        allocateActivityTitle("Dashboard");

        add = findViewById(R.id.btn_add);
        view = findViewById(R.id.btn_view);


        date = findViewById(R.id.edt_date);
        age = findViewById(R.id.edt_age);
        weight = findViewById(R.id.edt_weight);
        height = findViewById(R.id.edt_height);
        bmi = findViewById(R.id.edt_bmi);

        add.setOnClickListener(this);
        view.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:{
                if (TextUtils.isEmpty(date.getText())) {
                    date.setError("date is required.!");
                } else if (TextUtils.isEmpty(age.getText())) {
                    age.setError("age is required.!");
                } else if (TextUtils.isEmpty(weight.getText())) {
                    weight.setError("weight is required.!");
                } else if (TextUtils.isEmpty(height.getText())) {
                    height.setError("age is required.!");
                } else if (TextUtils.isEmpty(bmi.getText())) {
                    bmi.setError("BMI is required.!");
                }

                else {
                    insertdata();
                }
                break;
            }
            case R.id.btn_view:
                Intent intent = new Intent(Dashboard.this,UserListActivity.class);
                startActivity(intent);
                break;
        }

    }
    public void insertdata(){

        final String ddate = date.getText().toString().trim();
        final String dage = age.getText().toString().trim();
        final String dweight = weight.getText().toString().trim();
        final String dheight = height.getText().toString().trim();
        final String dbmi = bmi.getText().toString().trim();

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                date.setText("");
                age.setText("");
                weight.setText("");
                height.setText("");
                bmi.setText("");

                Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();

            }
        }){
            protected Map<String,String> getParams() throws AuthFailureError
            {
                Map<String,String> param =new HashMap<String,String>();
                param.put("date",ddate);
                param.put("age",dage);
                param.put("weight",dweight);
                param.put("height",dheight);
                param.put("bmi",dbmi);
                return param;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);

    }
}