package com.example.fitnessguardian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;

public class DrawerBase extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    @Override
    public void setContentView(View view) {
        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.drawer_base,null);
        FrameLayout container = drawerLayout.findViewById(R.id.activityContainer);
        container.addView(view);
        super.setContentView(drawerLayout);

        Toolbar toolbar = drawerLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.menu_drawer_open,R.string.menu_drawer_close);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()){
            case R.id.nav_exercise:
                startActivity(new Intent(this,Exercise.class));
                overridePendingTransition(0,0);
                break;

            case R.id.nav_bmi:
                startActivity(new Intent(this,Bmi.class));
                overridePendingTransition(0,0);
                break;

            case R.id.nav_remainder:
                startActivity(new Intent(this,Remainder.class));
                overridePendingTransition(0,0);
                break;

            case R.id.nav_dashboard:
                startActivity(new Intent(this,Dashboard.class));
                overridePendingTransition(0,0);
                break;

            case R.id.nav_logout:
                startActivity(new Intent(this,Logout.class));
                overridePendingTransition(0,0);
                break;
        }
        return false;
    }

    protected void allocateActivityTitle(String titleString){
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(titleString);
        }
    }
}