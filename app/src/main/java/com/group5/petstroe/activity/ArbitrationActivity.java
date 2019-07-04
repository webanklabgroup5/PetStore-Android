package com.group5.petstroe.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group5.petstroe.Adapter.ArbitrationItemAdapter;
import com.group5.petstroe.Adapter.OrderItemAdapter;
import com.group5.petstroe.R;
import com.group5.petstroe.apis.OrderApi;
import com.group5.petstroe.apis.Result;
import com.group5.petstroe.base.BaseActivity;
import com.group5.petstroe.base.GlobalUser;
import com.group5.petstroe.models.Order;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.group5.petstroe.utils.ActivityUtils.CODE_ARBITRATION_ACTIVITY;
import static com.group5.petstroe.apis.Constans.CODE_ORDER_GET_ORDER_LIST_API;

public class ArbitrationActivity extends BaseActivity {

    @BindView(R.id.tv_arbitration_number) TextView tvArbitrationNumber;
    @BindView(R.id.rv_arbitration_list) RecyclerView rvArbitrationList;
    private ArbitrationItemAdapter arbitrationItemAdapter = new ArbitrationItemAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arbitration);
        ButterKnife.bind(this);
        arbitrationItemAdapter.setOnItemClickListener(new ArbitrationItemAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                shortToast("click " + position);
            }
        });
        rvArbitrationList.setAdapter(arbitrationItemAdapter);
        rvArbitrationList.setLayoutManager(new LinearLayoutManager(this));

        OrderApi.INSTANCE.getOrderList(GlobalUser.user.id, this);
    }

    @Override
    protected <T> void onUiThread(Result<T> result, int resultCode) {
        switch (resultCode) {
            case CODE_ORDER_GET_ORDER_LIST_API:
                List<Order> orders = (List<Order>) result.get();
                List<Order> arbitrations = new ArrayList<>();
                if (orders != null) {
                    for (Order order : orders) {
                        if (order.status != 0) {
                            arbitrations.add(order);
                        }
                    }
                    arbitrationItemAdapter.updateList(arbitrations);
                }
                tvArbitrationNumber.setText(arbitrationItemAdapter.getItemCount() + "");
                break;
            default:
                break;
        }
    }

    public static void startActivityForResult(Context context) {
        Intent intent = new Intent(context, ArbitrationActivity.class);
        ((Activity) context).startActivityForResult(intent, CODE_ARBITRATION_ACTIVITY);
    }
}
