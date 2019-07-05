package com.group5.petstroe.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.group5.petstroe.Adapter.PetItemAdapter;
import com.group5.petstroe.R;
import com.group5.petstroe.apis.Result;
import com.group5.petstroe.apis.StoreApi;
import com.group5.petstroe.apis.UserApi;
import com.group5.petstroe.base.BaseActivity;
import com.group5.petstroe.base.GlobalUser;
import com.group5.petstroe.models.Pet;
import com.group5.petstroe.models.Status;
import com.group5.petstroe.utils.ActivityUtils;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.group5.petstroe.apis.Constans.CODE_STORE_GET_ON_SALE_PET_API;
import static com.group5.petstroe.utils.ActivityUtils.CODE_MY_PET_ACTIVITY;
import static com.group5.petstroe.utils.ActivityUtils.CODE_PET_ACTIVITY;
import static com.group5.petstroe.apis.Constans.CODE_USER_SIGN_OUT_API;
import static com.group5.petstroe.utils.ActivityUtils.CODE_PET_INFO_ACTIVITY;

public class PetActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView tvAccount;
    private RecyclerView rvPetsList;
    private PetItemAdapter petItemAdapter = new PetItemAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        StoreApi.INSTANCE.getOnSalePet(999, 0, this);
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        tvAccount = navigationView.getHeaderView(0).findViewById(R.id.tv_account);
        tvAccount.setText(GlobalUser.user.name);
        rvPetsList = findViewById(R.id.rv_pets_list);
        petItemAdapter.setOnItemClickListener(position -> {
            /**
             * 跳转宠物信息页面（context，false不是从“我的宠物跳转，pet宠物对象）
             */
            PetInfoActivity.startActivityForResult(PetActivity.this, false, petItemAdapter.getPet(position));
        });
        petItemAdapter.setActivity(this);
        rvPetsList.setAdapter(petItemAdapter);
        rvPetsList.setLayoutManager(new LinearLayoutManager(this));

        // 获取市场宠物列表
        StoreApi.INSTANCE.getOnSalePet(999, 0, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case CODE_PET_INFO_ACTIVITY:
                boolean status0 = data.getBooleanExtra("status", false);
                /**
                 * 购买成功，刷新列表
                 */
                if (status0) {
                    StoreApi.INSTANCE.getOnSalePet(999, 0, this);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected <T> void onUiThread(Result<T> result, int resultCode) {
        switch (resultCode) {
            /**
             * 用户退出登录回调
             */
            case CODE_USER_SIGN_OUT_API:
                if (result.isOk()) {
                    Status status = (Status) result.get();
                    if (status.status == 1) {
                        finishActivityWithResult();
                    } else {
                        shortToast("退出登陆失败");
                    }
                }
                break;
            /**
             * 获取市场宠物回调
             */
            case CODE_STORE_GET_ON_SALE_PET_API:
                if (result.isOk()) {
                    zlog("获取市场宠物 ok");
                    List<Pet> pets = (List<Pet>)result.get();
                    petItemAdapter.updateList(pets);
                } else {
                    zlog("获取市场宠物 error");
                }
                break;
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
            /**
             * 点击两次返回退出应用
             */
            ActivityUtils.finishAllActivity();
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
                UserApi.INSTANCE.signOut(this);
                return true;
            default:
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void startActivityForResult(Context context) {
        Intent intent = new Intent(context, PetActivity.class);
        ((Activity) context).startActivityForResult(intent, CODE_PET_ACTIVITY);
    }

    private void finishActivityWithResult() {
        finish();
    }
}
