package com.group5.petstroe.apis;

import android.util.Log;

import com.google.gson.Gson;
import com.group5.petstroe.base.BaseActivity;
import com.group5.petstroe.models.Pet;
import com.group5.petstroe.models.Status;
import com.group5.petstroe.utils.ExecutorUtils;

import java.util.List;

import okhttp3.HttpUrl;

import static com.group5.petstroe.apis.Constans.CODE_STORE_BUY_PET_API;
import static com.group5.petstroe.apis.Constans.CODE_STORE_GET_ON_SALE_PET_API;
import static com.group5.petstroe.apis.Constans.HOST;
import static com.group5.petstroe.apis.Constans.PATH_BUY_PET;
import static com.group5.petstroe.apis.Constans.PATH_CHANGE_PET_STATUS;
import static com.group5.petstroe.apis.Constans.PATH_GET_ON_SALE_PET;
import static com.group5.petstroe.apis.Constans.PORT;
import static com.group5.petstroe.apis.Constans.PROTOCOL;

public enum StoreApi {
    INSTANCE;

    static class GetOnSalePetResultForm {
        int status;
        List<Pet> pet_list;
    }
    public void getOnSalePet(int limit, int offset, BaseActivity activity) {
        ExecutorUtils.getSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                HttpUrl url = new HttpUrl.Builder()
                        .scheme(PROTOCOL)
                        .host(HOST)
                        .port(PORT)
                        .encodedPath(PATH_GET_ON_SALE_PET)
                        .addEncodedQueryParameter("limit", limit+"")
                        .addEncodedQueryParameter("offset", offset+"")
                        .build();
                GeneralClient<Object, GetOnSalePetResultForm> client = new GeneralClient<>(url, null, GetOnSalePetResultForm.class);
                Result<GetOnSalePetResultForm> result = client.get();
                Log.e("fktag", "url:" + url.toString());
                Log.e("fktag",  "response:" + (new Gson()).toJson(result.get()));
                if (result.isOk()) {
                    activity.runOnUiThread(Result.ok(result.get().pet_list), CODE_STORE_GET_ON_SALE_PET_API);
                } else {
                    activity.runOnUiThread(result, CODE_STORE_GET_ON_SALE_PET_API);
                }
            }
        });
    }

    static class BuyPetForm {
        String pet_id;
        BuyPetForm (String pet_id) {
            this.pet_id = pet_id;
        }
    }
    public void buyPet(String id, BaseActivity activity) {
        ExecutorUtils.getSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                HttpUrl url = new HttpUrl.Builder()
                        .scheme(PROTOCOL)
                        .host(HOST)
                        .port(PORT)
                        .encodedPath(PATH_BUY_PET)
                        .build();
                GeneralClient<BuyPetForm, Status> client = new GeneralClient<>(url, BuyPetForm.class, Status.class);
                Result<Status> result = client.post(new BuyPetForm(id));
                Log.e("fktag", "url:" + url.toString());
                Log.e("fktag",  "response:" + (new Gson()).toJson(result.get()));
                activity.runOnUiThread(result, CODE_STORE_BUY_PET_API);
            }
        });
    }
}
