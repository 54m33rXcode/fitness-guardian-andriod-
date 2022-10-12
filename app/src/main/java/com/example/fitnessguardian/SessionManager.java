package com.example.fitnessguardian;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    Context context;
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;

    public static final String PREF_NAME = "User_Login";
    public static final String LOGIN = "is_user_login";
    public static final String USERNAME = "username";
    public static final String ID = "id";


    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public boolean isUserLogin(){
        return sharedPreferences.getBoolean(LOGIN,false);
    }

    public void UserSessionManager(String username,String id){
        editor.putBoolean(LOGIN,true);
        editor.putString(USERNAME,username);
        editor.putString(ID,id);
        editor.apply();
    }
    public  void  checkLogin(){
        if (!this.isUserLogin()){
            Intent intent = new Intent(context,Login.class);
            context.startActivity(intent);
            ((Exercise) context).finish();
        }
    }
    public HashMap<String,String> userDetails(){
        HashMap<String,String> user = new HashMap<>();
        user.put(USERNAME,sharedPreferences.getString(USERNAME,null));
        user.put(ID,sharedPreferences.getString(ID,null));
        return user;
    }
    public void logout(){
        editor.clear();
        editor.commit();

        Intent intent = new Intent(context,Login.class);
        context.startActivity(intent);
        ((Logout) context).finish();
    }
}
