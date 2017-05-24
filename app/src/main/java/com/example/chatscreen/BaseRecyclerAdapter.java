package com.example.chatscreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<T, HV extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<HV> {
    private Context context;
    private List<T> mData;
    private OnItemClickListener itemClickListener;
    private OnItemLongClickListener itemLongClickListener;

    public interface OnItemClickListener {
        void onItemClickListener(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClickListener(View view, int position);
    }

    public BaseRecyclerAdapter(Context context) {
        this(context, null);
    }

    public BaseRecyclerAdapter(Context context, List<T> data) {
        this.context = context;
        this.mData = (data == null ? new ArrayList<T>() : data);
    }

    @Override
    public void onBindViewHolder(final HV holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClickListener(v, holder.getAdapterPosition());
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (itemLongClickListener != null) {
                    itemLongClickListener.onItemLongClickListener(v, holder.getAdapterPosition());
                    return true;
                }
                return false;
            }
        });
        mBindViewHolder(holder, position);
    }

    public abstract void mBindViewHolder(HV holder, int position);

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    protected Context getContext() {
        return context;
    }

    public T getItemBean(int position) {
        return mData == null ? null : mData.get(position);
    }

    /**
     * 尽量使用getListData来获取数据，以保持数据源的唯一
     *
     * @return list的数据源
     */
    public List<T> getData() {
        return mData;
    }

    public void addItem(T data) {
        addItem(getItemCount(), data);
    }

    public void addItem(int index, T data) {
        mData.add(index, data);
        notifyItemInserted(index);
        notifyItemRangeChanged(index + 1, getItemCount() - index - 1);
    }

    public void addItems(List<T> listData) {
        addItems(getItemCount(), listData);
    }

    public void addItems(int index, List<T> listData) {
        if (listData == null || listData.size() == 0) {
            return;
        }
        mData.addAll(index, listData);
        notifyItemRangeInserted(index, listData.size());
        notifyItemRangeChanged(index + listData.size(), getItemCount() - index - listData.size());
    }

    public void remove(int index) {
        mData.remove(index);
        notifyItemRemoved(index);
        notifyItemRangeChanged(index, getItemCount() - index);
    }

    public void remove(int start, int count) {
        //如果起始点大于list的大小，或者删除个数为0 那么就什么都不做
        if (start > getItemCount() || count <= 0) {
            return;
        }
        //如果起始点小于0 那么就认为是从头开始删除
        start = start < 0 ? 0 : start;
        //如果起始点+删除个数大于list的大小 那么就认为起始点开始删除到末尾
        if (start + count > getItemCount()) {
            count = getItemCount() - start;
        }
        for (int i = 0; i < count; i++) {
            mData.remove(start);
        }
        notifyItemRangeRemoved(start, count);
        notifyItemRangeChanged(start, getItemCount() - start);
    }

    public void update(List<T> newData) {
        mData.clear();
        if (newData != null) {
            mData.addAll(newData);
        }
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.itemLongClickListener = listener;
    }

    public OnItemClickListener getOnItemClickListener() {
        return itemClickListener;
    }

    public OnItemLongClickListener getItemLongClickListener() {
        return itemLongClickListener;
    }
}
