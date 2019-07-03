package com.group5.petstroe.apis;

import com.group5.petstroe.base.BaseActivity;
import com.group5.petstroe.models.Status;
import com.group5.petstroe.utils.ExecutorUtils;

import okhttp3.HttpUrl;

import static com.group5.petstroe.apis.Constans.HOST;
import static com.group5.petstroe.apis.Constans.PATH_CREATE_PET;
import static com.group5.petstroe.apis.Constans.PATH_SIGN_IN;
import static com.group5.petstroe.apis.Constans.PORT;
import static com.group5.petstroe.apis.Constans.PROTOCOL;
import static com.group5.petstroe.utils.ActivityUtils.CODE_PET_ACTIVITY;

public enum  PetApi {
    INSTANCE;

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
    private Result<Status> createPet(String name, int species, String photo, String birthday, String description) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme(PROTOCOL)
                .host(HOST)
                .port(PORT)
                .encodedPath(PATH_CREATE_PET)
                .build();
        GeneralClient<CreatePetForm, Status> client = new GeneralClient<>(url, CreatePetForm.class, Status.class);
        Result<Status> result = client.post(new CreatePetForm(name, species, photo, birthday, description));
        return result;
    }
    public void createPet(String name, int species, String photo, String birthday, String description, BaseActivity activity) {
        ExecutorUtils.getSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                activity.runOnUiThread(createPet(name, species, photo, birthday, description), CODE_PET_ACTIVITY);
            }
        });
    }


    static class ChangePetStatusForm {
        int pet_id;
        int action;
        String remark;
        int price;

        ChangePetStatusForm(int pet_id, int action, String remark, int price) {
            this.pet_id = pet_id;
            this.action = action;
            this.remark = remark;
            this.price = price;
        }
    }
    private Result<Status> changePetStatus(int pet_id, int action, String remark, int price) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme(PROTOCOL)
                .host(HOST)
                .port(PORT)
                .encodedPath(PATH_CREATE_PET)
                .build();
        GeneralClient<ChangePetStatusForm, Status> client = new GeneralClient<>(url, ChangePetStatusForm.class, Status.class);
        Result<Status> result = client.post(new ChangePetStatusForm(pet_id, action, remark, price));
        return  result;
    }
    public void changePetStatus(int pet_id, int action, String remark, int price, BaseActivity activity) {
        ExecutorUtils.getSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                activity.runOnUiThread(changePetStatus(pet_id, action, remark, price), CODE_PET_ACTIVITY);
            }
        });
    }

}
