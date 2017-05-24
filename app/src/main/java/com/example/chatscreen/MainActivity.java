package com.example.chatscreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.rv_list)
    RecyclerView rvList;
    @InjectView(R.id.edit_send_content)
    EditText editSendContent;
    @InjectView(R.id.tv_send_btn)
    TextView sendBtn;

    private ChatListAdapter mAdapter;
    private List<MsgModel> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        init();
        bindEvent();
        addData();
    }

    /**
     * 初始化数据
     */
    private void init() {
        items = new ArrayList<>();

        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setHasFixedSize(true);
        mAdapter = new ChatListAdapter(this);
        rvList.setAdapter(mAdapter);
    }

    /**
     * 绑定事件
     */
    private void bindEvent() {
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editSendContent.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    return;
                }
                MsgModel bean = new MsgModel(content, MsgModel.TYPE_SEND);
                mAdapter.addItem(bean);
                rvList.scrollToPosition(mAdapter.getItemCount() - 1); //有新消息时，滚动到最后一条
                editSendContent.setText("");
            }
        }); //发送按钮点击事件
    }

    /**
     * 添加测试数据
     */
    private void addData() {
        items.add(new MsgModel("请问你是谁？", MsgModel.TYPE_SEND));
        items.add(new MsgModel("不告诉你", MsgModel.TYPE_RECEIVE));
        items.add(new MsgModel("你自己猜", MsgModel.TYPE_RECEIVE));
        items.add(new MsgModel("我猜个鬼", MsgModel.TYPE_SEND));
        items.add(new MsgModel("不说我删好友了", MsgModel.TYPE_SEND));
        items.add(new MsgModel("。。。", MsgModel.TYPE_RECEIVE));
        mAdapter.addItems(items);
    }
}
