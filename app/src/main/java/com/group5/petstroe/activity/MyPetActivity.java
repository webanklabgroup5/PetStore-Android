package com.group5.petstroe.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group5.petstroe.Adapter.PetItemAdapter;
import com.group5.petstroe.R;
import com.group5.petstroe.apis.PetApi;
import com.group5.petstroe.apis.Result;
import com.group5.petstroe.base.BaseActivity;
import com.group5.petstroe.base.GlobalUser;
import com.group5.petstroe.models.Pet;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.group5.petstroe.utils.ActivityUtils.CODE_CREATE_PET_ACTIVITY;
import static com.group5.petstroe.utils.ActivityUtils.CODE_MY_PET_ACTIVITY;
import static com.group5.petstroe.apis.Constans.CODE_PET_GET_PET_LIST_API;
import static com.group5.petstroe.utils.ActivityUtils.CODE_PET_INFO_ACTIVITY;

public class MyPetActivity extends BaseActivity {

    @BindView(R.id.tv_pet_number) TextView tvPetNumber;
    @BindView(R.id.rv_pets_list) RecyclerView rvPetsList;
    @BindView(R.id.btn_create_pet) Button btnCreatePet;
    private PetItemAdapter petItemAdapter = new PetItemAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pet);
        ButterKnife.bind(this);
        petItemAdapter.setOnItemClickListener(new PetItemAdapter.onItemClickListener() {
            /**
             * 跳转宠物信息页面（context，true从“我的宠物”页面跳转，宠物对象）
             * @param position
             */
            @Override
            public void onItemClick(int position) {
                PetInfoActivity.startActivityForResult( MyPetActivity.this, true, petItemAdapter.getPet(position));
            }
        });
        petItemAdapter.setActivity(this);
        rvPetsList.setAdapter(petItemAdapter);
        rvPetsList.setLayoutManager(new LinearLayoutManager(this));

        /**
         * 获取用户宠物列表
         */
        PetApi.INSTANCE.getPetList(GlobalUser.user.id, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case CODE_CREATE_PET_ACTIVITY:
                if (requestCode == RESULT_OK) {
                    boolean status0 = data.getBooleanExtra("status", false);
                    if (status0) {
                        /**
                         * 创建宠物成功，刷新列表
                         */
                        PetApi.INSTANCE.getPetList(GlobalUser.user.id, this);
                    }
                }
                break;
            case CODE_PET_INFO_ACTIVITY:
                boolean status1 = data.getBooleanExtra("status", false);
                if (status1) {
                    /**
                     * 上下架宠物成功，刷新列表
                     */
                    PetApi.INSTANCE.getPetList(GlobalUser.user.id, this);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected <T> void onUiThread(Result<T> result, int resultCode) {
        switch (resultCode) {
            case CODE_PET_GET_PET_LIST_API:
                if (result.isOk()) {
                    zlog("获取用户宠物列表 ok");
                    List<Pet> pets = (List<Pet>) result.get();
                    petItemAdapter.updateList(pets);
                } else {
                    zlog("获取用户宠物列表 error");
                }
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.btn_create_pet)
    void onClick() {
        /**
         * 跳转创建宠物页面
         */
        CreatePetActivity.startActivityForResult(this);
    }

    public static void startActivityForResult(Context context) {
        Intent intent = new Intent(context, MyPetActivity.class);
        ((Activity) context).startActivityForResult(intent, CODE_MY_PET_ACTIVITY);
    }
}
