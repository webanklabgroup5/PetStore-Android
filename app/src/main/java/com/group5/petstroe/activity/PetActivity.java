package com.group5.petstroe.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.group5.petstroe.R;
import com.group5.petstroe.apis.Result;
import com.group5.petstroe.base.BaseActivity;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

public class PetActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView tvAccount;
    private RecyclerView rvPetsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
        initView();
    }

    @Override
    protected <T> void onUiThread(Result<T> result, int resultCode) {
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        tvAccount = navigationView.getHeaderView(0).findViewById(R.id.tv_account);
        rvPetsList = findViewById(R.id.rv_pets_list);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_balance:
                return true;
            case R.id.nav_pet:
                return true;
            case R.id.nav_order:
                return true;
            case R.id.nav_arbitration:
                return true;
            default:
                shortToast("clicked");
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, PetActivity.class);
        context.startActivity(intent);
    }
}
