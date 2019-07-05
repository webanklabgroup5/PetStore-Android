package com.group5.petstroe.apis;

import android.util.Log;

import com.google.gson.Gson;
import com.group5.petstroe.base.BaseActivity;
import com.group5.petstroe.models.DataUrlStatus;
import com.group5.petstroe.models.Pet;
import com.group5.petstroe.models.Status;
import com.group5.petstroe.models.UrlStatus;
import com.group5.petstroe.utils.ExecutorUtils;

import java.io.File;
import java.util.List;

import okhttp3.HttpUrl;

import static com.group5.petstroe.apis.Constans.CODE_APP_UPLOAD_FILE_API;
import static com.group5.petstroe.apis.Constans.CODE_PET_CHANGE_PET_STATUS_API;
import static com.group5.petstroe.apis.Constans.CODE_PET_CREATE_PET_API;
import static com.group5.petstroe.apis.Constans.CODE_PET_GET_PET_LIST_API;
import static com.group5.petstroe.apis.Constans.HOST;
import static com.group5.petstroe.apis.Constans.PATH_APP_UPLOAD_FILE;
import static com.group5.petstroe.apis.Constans.PATH_CHANGE_PET_STATUS;
import static com.group5.petstroe.apis.Constans.PATH_CREATE_PET;
import static com.group5.petstroe.apis.Constans.PATH_GET_PET_LIST;
import static com.group5.petstroe.apis.Constans.PATH_SIGN_IN;
import static com.group5.petstroe.apis.Constans.PORT;
import static com.group5.petstroe.apis.Constans.PROTOCOL;
import static com.group5.petstroe.utils.ActivityUtils.CODE_PET_ACTIVITY;

public enum  PetApi {
    INSTANCE;

    static class UploadFileForm {
        File photo;
        UploadFileForm(File file) {
            this.photo = file;
        }
    }
    public void uploadFile(File file, BaseActivity activity) {
        ExecutorUtils.getSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                HttpUrl url = new HttpUrl.Builder()
                        .scheme("https")
                        .host("glasses-dev.ksh.fun")
                        .encodedPath("/api/files")
//                        .scheme(PROTOCOL)
//                        .host(HOST)
//                        .port(PORT)
//                        .encodedPath(PATH_APP_UPLOAD_FILE)
                        .build();
                GeneralClient<UploadFileForm, DataUrlStatus> client = new GeneralClient<>(url, UploadFileForm.class, DataUrlStatus.class);
                UploadFileForm postForm = new UploadFileForm(file);
//                Result<UrlStatus> result = client.post(postForm);
                Result<DataUrlStatus> result = client.uploadPost(file);
                {
                    Log.e("fktag", "url:" + url.toString());
//                    Log.e("fktag", "request:" + (new Gson()).toJson(postForm));
                    Log.e("fktag",  "response:" + (new Gson()).toJson(result.get()));
                }
                activity.runOnUiThread(result, CODE_APP_UPLOAD_FILE_API);
            }
        });
    }

    static class CreatePetForm {
        String name;
        int species;
        String photo;
        String birthday;
        String description;
        CreatePetForm(String name, int species, String photo, String birthday, String description) {
            this.name = name;
            this.species = species;
            this.photo = photo;
            this.birthday = birthday;
            this.description = description;
        }
    }
    public void createPet(String name, int species, String photo, String birthday, String description, BaseActivity activity) {
        ExecutorUtils.getSingleThreadExecutor().execute(() -> {
            HttpUrl url = new HttpUrl.Builder()
                    .scheme(PROTOCOL)
                    .host(HOST)
                    .port(PORT)
                    .encodedPath(PATH_CREATE_PET)
                    .build();
            GeneralClient<CreatePetForm, Status> client = new GeneralClient<>(url, CreatePetForm.class, Status.class);
            CreatePetForm postForm = new CreatePetForm(name, species, photo, birthday, description);
            Result<Status> result = client.post(postForm);
            {
                Log.e("fktag", "url:" + url.toString());
                Log.e("fktag", "request:" + (new Gson()).toJson(postForm));
                Log.e("fktag",  "response:" + (new Gson()).toJson(result.get()));
            }
            activity.runOnUiThread(result, CODE_PET_CREATE_PET_API);
        });
    }


    static class ChangePetStatusForm {
        String pet_id;
        int action;
        String remark;
        int price;
        ChangePetStatusForm(String pet_id, int action, String remark, int price) {
            this.pet_id = pet_id;
            this.action = action;
            this.remark = remark;
            this.price = price;
        }
    }
    public void changePetStatus(String pet_id, int action, String remark, int price, BaseActivity activity) {
        ExecutorUtils.getSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                HttpUrl url = new HttpUrl.Builder()
                        .scheme(PROTOCOL)
                        .host(HOST)
                        .port(PORT)
                        .encodedPath(PATH_CHANGE_PET_STATUS)
                        .build();
                GeneralClient<ChangePetStatusForm, Status> client = new GeneralClient<>(url, ChangePetStatusForm.class, Status.class);
                ChangePetStatusForm postForm = new ChangePetStatusForm(pet_id, action, remark, price);
                Result<Status> result = client.post(postForm);
                {
                    Log.e("fktag", "url:" + url.toString());
                    Log.e("fktag", "request:" + (new Gson()).toJson(postForm));
                    Log.e("fktag",  "response:" + (new Gson()).toJson(result.get()));
                }
                activity.runOnUiThread(result, CODE_PET_CHANGE_PET_STATUS_API);
            }
        });
    }


    static class GetPetListResultForm {
        int status;
        List<Pet> pet_list;
    }
    public void getPetList(String id, BaseActivity activity) {
        ExecutorUtils.getSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                HttpUrl url = new HttpUrl.Builder()
                        .scheme(PROTOCOL)
                        .host(HOST)
                        .port(PORT)
                        .encodedPath(PATH_GET_PET_LIST)
                        .addEncodedQueryParameter("user_id", id)
                        .build();
                GeneralClient<Object, GetPetListResultForm> client = new GeneralClient<>(url, null, GetPetListResultForm.class);
                Result<GetPetListResultForm> result = client.get();
                {
                    Log.e("fktag", "url:" + url.toString());
                    Log.e("fktag",  "response:" + (new Gson()).toJson(result.get()));
                }
                if (result.isOk()) {
                    activity.runOnUiThread(Result.ok(result.get().pet_list), CODE_PET_GET_PET_LIST_API);
                } else {
                    activity.runOnUiThread(result, CODE_PET_GET_PET_LIST_API);
                }
            }
        });
    }
}
