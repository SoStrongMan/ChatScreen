# ChatScreen
简单的一个聊天界面，运用recyclerView的type不同实现功能
## 思路整理
聊天界面无非就是自己发送的内容和接收别人发送的内容，最后加上底部的发送信息的layout。清楚这些后，聊天内容部分除了头像和文字换了一个方向，没有什么不同。
只要定义两个item子布局，在收发消息的时候给这个消息附上一个type类型，最后在adapter中根据type不同显示相对应的界面即可（由于封装了recyclerView的Adapter，详情见代码）。

    @Override
    public int getItemViewType(int position) {
        int type = getItemBean(position).getType();
        if (type == TYPE_SEND) {
            return TYPE_SEND;
        } else {
            return TYPE_RECEIVE;
        }
    }

在创建布局的时候判断type值，代码如下：

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

在activity中添加测试数据，代码如下（TYPE_SEND表示自己发送的内容，TYPE_RECEIVE表示接收到的别人发送的内容）：

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
    
 自己点击发送按钮发送数据的时候，同理，在内容后面附上一个type（TYPE_SEND）即可。
 
 ## 小结
 代码比较简单，只要理清思路，就能很快掌握。如果不清楚recyclerView的同学，可以先了解了recyclerView再来看。如果还有在用listView的同学，
 还是尽快换成recyclerView吧。如果要问为什么要用recyclerView替代listView的原因，请百度。
