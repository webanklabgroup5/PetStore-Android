package com.group5.petstroe.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.group5.petstroe.R;
import com.group5.petstroe.apis.PetApi;
import com.group5.petstroe.apis.Result;
import com.group5.petstroe.base.BaseActivity;
import com.group5.petstroe.models.Status;
import com.group5.petstroe.models.UrlStatus;
import com.group5.petstroe.utils.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.group5.petstroe.apis.Constans.CODE_PET_CREATE_PET_API;
import static com.group5.petstroe.utils.ActivityUtils.CODE_CREATE_PET_ACTIVITY;
import static com.group5.petstroe.apis.Constans.CODE_APP_UPLOAD_FILE_API;

public class CreatePetActivity extends BaseActivity {
    private static final int OPEN_ALBUM_CODE = 1111;

    @BindView(R.id.iv_pet_image) ImageView ivPetImage;
    @BindView(R.id.et_pet_name) EditText etPetName;
    @BindView(R.id.et_pet_birthday) EditText etPetBirthday;
    @BindView(R.id.sp_pet_species) Spinner spSpecies;
    @BindView(R.id.et_pet_description) EditText etPetDescription;
    @BindView(R.id.btn_create) Button btnCreatePet;

    private static final String[] speciesArray = {"其他", "狗", "猫", "仓鼠", "兔", "恐龙"};
    private int species = 0;
    private File petImageFile = null;
    private String petImageUrl;
    private Bitmap petImageBitmap;
    private String petName;
    private String petBirthday;
    private String petDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pet);
        ButterKnife.bind(this);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.item_species, speciesArray);
        spSpecies.setAdapter(arrayAdapter);
        spSpecies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                species = i;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case OPEN_ALBUM_CODE:
                if (resultCode == RESULT_OK) {
                    petImageFile = new File(data.getData().getPath());
                    petImageBitmap = getBitmapByUri(data.getData());
                    if (petImageFile != null) {
                        PetApi.INSTANCE.uploadFile(petImageFile, this);
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected <T> void onUiThread(Result<T> result, int resultCode) {
        switch (resultCode) {
            case CODE_APP_UPLOAD_FILE_API:
                if (result.isOk()) {
                    UrlStatus urlStatus = (UrlStatus) result.get();
                    petImageUrl = urlStatus.url;
                    if (StringUtils.isNotNullOrEmpty(petImageUrl)) {
                        ivPetImage.setImageBitmap(petImageBitmap);
                    }
                }
                break;
            case CODE_PET_CREATE_PET_API:
                if (result.isOk()) {
                    Status status = (Status) result.get();
                    finishActivityWithResult(status.status == 1);
                }
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.btn_create, R.id.iv_pet_image})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_create:
                if (isInfoOk()) {
                    PetApi.INSTANCE.createPet(petName, species, petImageUrl, petBirthday, petDescription, this);
                }
                break;
            case R.id.iv_pet_image:
                openAlbum();
                break;
            default:
                break;
        }
    }

    private void openAlbum() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            return;
        }
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, OPEN_ALBUM_CODE);
    }

    private Bitmap getBitmapByUri(Uri uri) {
        Bitmap bitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, options);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private boolean isInfoOk() {
        petImageUrl = "http://ali.theproudsoul.cn:22222/petshop/pet/ll.png";
        if (StringUtils.isNullOrEmpty(petImageUrl)) {
            shortToast("请选择宠物图片");
            return false;
        }
        petName = etPetName.getText().toString().trim();
        if (StringUtils.isNullOrEmpty(petName)) {
            shortToast("请输入宠物名字");
            return false;
        }
        petBirthday = etPetBirthday.getText().toString().trim();
        if (StringUtils.isNullOrEmpty(petBirthday)) {
            shortToast("请输入宠物生日");
            return false;
        }
        petDescription = etPetDescription.getText().toString().trim();
        if (StringUtils.isNullOrEmpty(petDescription)) {
            petDescription = "暂无描述";
        }
        return true;
    }

    public static void startActivityForResult(Context context) {
        Intent intent = new Intent(context, CreatePetActivity.class);
        ((Activity) context).startActivityForResult(intent, CODE_CREATE_PET_ACTIVITY);
    }

    private void finishActivityWithResult(boolean status) {
        Intent intent = new Intent();
        intent.putExtra("status", status);
        setResult(RESULT_OK, intent);
        finish();
    }
}
