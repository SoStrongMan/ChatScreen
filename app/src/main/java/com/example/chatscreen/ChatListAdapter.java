package com.example.chatscreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static com.example.chatscreen.MsgModel.TYPE_RECEIVE;
import static com.example.chatscreen.MsgModel.TYPE_SEND;

/**
 * 作者：徐欣
 * 日期：2017/5/23
 * 描述：
 */

public class ChatListAdapter extends BaseRecyclerAdapter<MsgModel, RecyclerView.ViewHolder> {

    public ChatListAdapter(Context context) {
        this(context, null);
    }

    public ChatListAdapter(Context context, List<MsgModel> data) {
        super(context, data);
    }

    @Override
    public void mBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder vh = (MyViewHolder) holder;
        MsgModel bean = getItemBean(position);
        vh.tvContent.setText(bean.getContent());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_SEND) { //发送的内容
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_my_content, parent, false);
        } else { //接收的内容
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_other_content, parent, false);
        }
        return new MyViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        int type = getItemBean(position).getType();
        if (type == TYPE_SEND) {
            return TYPE_SEND;
        } else {
            return TYPE_RECEIVE;
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvContent;

        private MyViewHolder(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
