package com.peixing.greendaodemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peixing.greendaodemo.bean.User;

import java.util.List;

/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    public List<User> users;


    private OnRecyclerViewItemClickListener mListener = null;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }


    public UserAdapter(List<User> userArrayList) {
        this.users = userArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder itemViewHolder, final int position) {
        itemViewHolder.tvName.setText(users.get(position).getName());
        itemViewHolder.tvId.setText(users.get(position).getId().toString());
        //Here you can fill your row view

        if (mListener != null) {
            itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(v, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {

        return users.size();
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mListener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvId;


        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvId = (TextView) itemView.findViewById(R.id.tv_id);
        }
    }
}
