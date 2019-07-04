package com.group5.petstroe.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.group5.petstroe.Adapter.PetItemAdapter;
import com.group5.petstroe.R;
import com.group5.petstroe.apis.Result;
import com.group5.petstroe.base.BaseActivity;
import com.group5.petstroe.models.User;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.group5.petstroe.utils.ActivityUtils.CODE_PET_ACTIVITY;

public class PetActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView tvAccount;
    private RecyclerView rvPetsList;
    private PetItemAdapter petItemAdapter = new PetItemAdapter();

    private User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
        Intent intent = getIntent();
        user = (User)intent.getExtras().get("user");
        initView();
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

        (tvAccount = navigationView.getHeaderView(0).findViewById(R.id.tv_account)).setText(user.name);
        rvPetsList = findViewById(R.id.rv_pets_list);
        petItemAdapter.setOnItemClickListener(new PetItemAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                PetInfoActivity.startActivityForResult(PetActivity.this, false, petItemAdapter.getPet(position));
            }
        });
        rvPetsList.setAdapter(petItemAdapter);
        rvPetsList.setLayoutManager(new LinearLayoutManager(this));

        // 获取市场宠物列表
    }

    @Override
    protected <T> void onUiThread(Result<T> result, int resultCode) {
        switch (resultCode) {
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finishActivityWithResult();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_balance:
                BalanceActivity.startActivityForResult(this);
                return true;
            case R.id.nav_pet:
                MyPetActivity.startActivityForResult(this);
                return true;
            case R.id.nav_order:
                OrderActivity.startActivityForResult(this);
                return true;
            case R.id.nav_arbitration:
                ArbitrationActivity.startActivityForResult(this);
                return true;
            case R.id.nav_setting:
                shortToast("clicked");
                return true;
            default:
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void startActivityForResult(Context context, User user) {
        Intent intent = new Intent(context, PetActivity.class);
        intent.putExtra("user", user);
        ((Activity) context).startActivityForResult(intent, CODE_PET_ACTIVITY);
    }

    private void finishActivityWithResult() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}
