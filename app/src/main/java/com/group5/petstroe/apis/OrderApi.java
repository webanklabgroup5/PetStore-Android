package com.group5.petstroe.apis;

import com.group5.petstroe.base.BaseActivity;
import com.group5.petstroe.models.Order;
import com.group5.petstroe.models.Status;
import com.group5.petstroe.utils.ExecutorUtils;

import java.util.List;

import okhttp3.HttpUrl;

import static com.group5.petstroe.apis.Constans.CODE_ORDER_ARBITRATION_API;
import static com.group5.petstroe.apis.Constans.CODE_ORDER_GET_ARBITRATION_LIST_API;
import static com.group5.petstroe.apis.Constans.CODE_ORDER_GET_ORDER_LIST_API;
import static com.group5.petstroe.apis.Constans.HOST;
import static com.group5.petstroe.apis.Constans.PATH_ARBITRATION;
import static com.group5.petstroe.apis.Constans.PATH_GET_ARBITRATION_LIST;
import static com.group5.petstroe.apis.Constans.PATH_GET_ORDER_LIST;
import static com.group5.petstroe.apis.Constans.PORT;
import static com.group5.petstroe.apis.Constans.PROTOCOL;

public enum OrderApi {
    INSTANCE;

    static class GetOrderListResultForm {
        int status;
        List<Order> trade_list;
    }
    public void getOrderList(String id, BaseActivity activity) {
        ExecutorUtils.getSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                HttpUrl url = new HttpUrl.Builder()
                        .scheme(PROTOCOL)
                        .host(HOST)
                        .port(PORT)
                        .encodedPath(PATH_GET_ORDER_LIST)
                        .addEncodedQueryParameter("user_id", id)
                        .build();
                GeneralClient<Object, GetOrderListResultForm> client = new GeneralClient<>(url, null, GetOrderListResultForm.class);
                Result<GetOrderListResultForm> result = client.get();
                if (result.isOk()) {
                    activity.runOnUiThread(Result.ok(result.get().trade_list), CODE_ORDER_GET_ORDER_LIST_API);
                } else {
                    activity.runOnUiThread(result, CODE_ORDER_GET_ORDER_LIST_API);
                }
            }
        });
    }

    static class ArbitrationForm {
        String trade_id;
        ArbitrationForm(String id) {
            this.trade_id = id;
        }
    }
    public void arbitration(String id, BaseActivity activity) {
        ExecutorUtils.getSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                HttpUrl url = new HttpUrl.Builder()
                        .scheme(PROTOCOL)
                        .host(HOST)
                        .port(PORT)
                        .encodedPath(PATH_ARBITRATION)
                        .build();
                GeneralClient<ArbitrationForm, Status> client = new GeneralClient<>(url, null, Status.class);
                Result<Status> result = client.post(new ArbitrationForm(id));
                activity.runOnUiThread(result, CODE_ORDER_ARBITRATION_API);
            }
        });
    }

    public void getArbitrationList(int limit, int offset, BaseActivity activity) {
        ExecutorUtils.getSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                HttpUrl url = new HttpUrl.Builder()
                        .scheme(PROTOCOL)
                        .host(HOST)
                        .port(PORT)
                        .encodedPath(PATH_GET_ARBITRATION_LIST)
                        .addEncodedQueryParameter("limit", limit+"")
                        .addEncodedQueryParameter("offset", offset+"")
                        .build();
                GeneralClient<Object, Order> client = new GeneralClient<>(url, null, Order.class);
                Result<Order> result = client.get();
                activity.runOnUiThread(result, CODE_ORDER_GET_ARBITRATION_LIST_API);
            }
        });
    }
}
