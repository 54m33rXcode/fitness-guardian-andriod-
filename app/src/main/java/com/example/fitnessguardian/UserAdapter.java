package com.example.fitnessguardian;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder>{
    Context context;
    List<Users> usersList;

    public UserAdapter(Context context, List<Users> usersList) {
        this.context = context;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View userLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_list,parent,false);
        return new UserHolder(userLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        Users users = usersList.get(position);
        holder.id.setText(users.getId());
        holder.date.setText(users.getDate());
        holder.age.setText(users.getAge());
        holder.weight.setText(users.getWeight());
        holder.height.setText(users.getHeight());
        holder.bmi.setText(users.getBmi());
        holder.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View editLayout = LayoutInflater.from(context).inflate(R.layout.edit_user_detail,null);
                EditText date = editLayout.findViewById(R.id.edt_date);
                EditText age = editLayout.findViewById(R.id.edt_age);
                EditText weight = editLayout.findViewById(R.id.edt_weight);
                EditText height = editLayout.findViewById(R.id.edt_height);
                EditText bmi = editLayout.findViewById(R.id.edt_bmi);

                date.setText(users.getDate());
                age.setText(users.getAge());
                weight.setText(users.getWeight());
                height.setText(users.getHeight());
                bmi.setText(users.getBmi());


                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Edit Id :  "+users.getId());
                builder.setView(editLayout);
                builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        final String ddate = date.getText().toString();
                        final String dage = age.getText().toString();
                        final String dweight = weight.getText().toString();
                        final String dheight = height.getText().toString();
                        final String dbmi = bmi.getText().toString();
                        final String did = users.getId();

                        if (ddate.isEmpty() || dage.isEmpty() || dweight.isEmpty() || dheight.isEmpty() || dbmi.isEmpty()){
                            Toast.makeText(context, "Don't leave the field empty", Toast.LENGTH_SHORT).show();
                        }else {
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.32.42/Fitness_guardian_crud/update.php",
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }){
                                @Override
                                protected Map<String,String> getParams() throws AuthFailureError {
                                    HashMap<String,String> params = new HashMap<>();
                                    params.put("id",did);
                                    params.put("date",ddate);
                                    params.put("age",dage);
                                    params.put("weight",dweight);
                                    params.put("height",dheight);
                                    params.put("bmi",dbmi);

                                    return params;
                                }
                            };
                            RequestQueue requestQueue = Volley.newRequestQueue(context);
                            requestQueue.add(stringRequest);

                        }
                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete User");
                builder.setMessage("Conform To Delete Id: "+users.getId());
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.32.42/Fitness_guardian_crud/delete.php",
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        try {
                                            JSONObject object = new JSONObject(response);
                                            String check = object.getString("state");
                                            if (check.equals("delete")){
                                                Delete(position);
                                                Toast.makeText(context, "Delete Successful", Toast.LENGTH_SHORT).show();
                                            }else
                                                Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }){
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String,String> deleteParams = new HashMap<>();
                                deleteParams.put("id",users.getId());
                                return deleteParams;
                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(context);
                        requestQueue.add(stringRequest);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public  class UserHolder extends RecyclerView.ViewHolder{
        TextView id, date,age,weight,height,bmi;
        Button Edit;
        ImageButton Delete;
        public UserHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.rcy_id);
            date = itemView.findViewById(R.id.rcy_date);
            age = itemView.findViewById(R.id.rcy_age);
            weight = itemView.findViewById(R.id.rcy_weight);
            height = itemView.findViewById(R.id.rcy_height);
            bmi = itemView.findViewById(R.id.rcy_bmi);

            Edit = itemView.findViewById(R.id.rcy_edit);
            Delete = itemView.findViewById(R.id.rcy_delete);
        }
    }

    public  void Delete(int item){
        usersList.remove(item);
        notifyItemRemoved(item);
    }
}
