package com.example.fitnessguardian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<Users> usersList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        recyclerView = findViewById(R.id.recyclerList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        usersList = new ArrayList<>();


        LoadAllUsers();
    }

    private void LoadAllUsers() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, "http://192.168.32.42/Fitness_guardian_crud/fetch.php",
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray array) {

                try {
                    for (int i = 0;i<array.length();i++){
                        JSONObject object = array.getJSONObject(i);
                        String Id = object.getString("Id").trim();
                        String Date = object.getString("Date").trim();
                        String Age = object.getString("Age").trim();
                        String Weight = object.getString("Weight").trim();
                        String Height = object.getString("Height").trim();
                        String Bmi = object.getString("Bmi").trim();

                        Users users = new Users();
                        users.setId(Id);
                        users.setDate(Date);
                        users.setAge(Age);
                        users.setWeight(Weight);
                        users.setHeight(Height);
                        users.setBmi(Bmi);
                        usersList.add(users);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                userAdapter = new UserAdapter(UserListActivity.this,usersList);
                recyclerView.setAdapter(userAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserListActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(UserListActivity.this);
        requestQueue.add(request);
    }
}