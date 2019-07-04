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
import com.group5.petstroe.apis.OrderApi;
import com.group5.petstroe.apis.Result;
import com.group5.petstroe.base.BaseActivity;
import com.group5.petstroe.base.GlobalUser;
import com.group5.petstroe.models.Order;
import com.group5.petstroe.models.Status;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.group5.petstroe.apis.Constans.CODE_ORDER_ARBITRATION_API;
import static com.group5.petstroe.utils.ActivityUtils.CODE_ORDER_ACTIVITY;
import static com.group5.petstroe.apis.Constans.CODE_ORDER_GET_ORDER_LIST_API;

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

        OrderApi.INSTANCE.getOrderList(GlobalUser.user.id, this);
    }

    @Override
    protected <T> void onUiThread(Result<T> result, int resultCode) {
        switch (resultCode) {
            case CODE_ORDER_GET_ORDER_LIST_API:
                List<Order> orders = (List<Order>) result.get();
                orderItemAdapter.updateList(orders);
                tvOrderNumber.setText(orderItemAdapter.getItemCount() + "");
                break;
            case CODE_ORDER_ARBITRATION_API:
                Status status = (Status) result.get();
                if (status.status == 1) {
                    OrderApi.INSTANCE.getOrderList(GlobalUser.user.id, this);
                }
                break;
            default:
                break;
        }
    }

    private void showDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("仲裁");
        dialog.setMessage("对这起交易发起仲裁？");
        dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                OrderApi.INSTANCE.arbitration(orderItemAdapter.getOrder(i).id, OrderActivity.this);
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
