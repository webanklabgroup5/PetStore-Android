package com.group5.petstroe.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group5.petstroe.Adapter.OrderItemAdapter;
import com.group5.petstroe.R;
import com.group5.petstroe.apis.Result;
import com.group5.petstroe.base.BaseActivity;
import com.group5.petstroe.models.Order;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.group5.petstroe.utils.ActivityUtils.CODE_ORDER_ACTIVITY;

public class OrderActivity extends BaseActivity {

    @BindView(R.id.tv_order_number) TextView tvOrderNumber;
    @BindView(R.id.rv_order_list) RecyclerView rvOrderList;
    private OrderItemAdapter orderItemAdapter = new OrderItemAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        orderItemAdapter.setOnItemClickListener(new OrderItemAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                showDialog();
            }
        });
        rvOrderList.setAdapter(orderItemAdapter);
        rvOrderList.setLayoutManager(new LinearLayoutManager(this));
        onUiThread(null, 0);
    }

    @Override
    protected <T> void onUiThread(Result<T> result, int resultCode) {
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            orders.add(new Order());
        }
        orderItemAdapter.updateList(orders);
    }

    private void showDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("仲裁");
        dialog.setMessage("对这起交易发起仲裁？");
        dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                shortToast("click yes");
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                shortToast("click no");
            }
        }).show();
    }

    public static void startActivityForResult(Context context) {
        Intent intent = new Intent(context, OrderActivity.class);
        ((Activity) context).startActivityForResult(intent, CODE_ORDER_ACTIVITY);
    }
}
