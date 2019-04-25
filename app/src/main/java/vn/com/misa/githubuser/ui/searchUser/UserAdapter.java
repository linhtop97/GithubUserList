package vn.com.misa.githubuser.ui.searchUser;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vn.com.misa.githubuser.R;
import vn.com.misa.githubuser.base.adapters.ListAdapter;
import vn.com.misa.githubuser.base.listeners.OnItemClickListener;
import vn.com.misa.githubuser.data.model.User;

public class UserAdapter extends ListAdapter<User> {

    private OnItemClickListener<User> mItemClickListener;

    public UserAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_user, viewGroup, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ((UserViewHolder) viewHolder).bind(mListData.get(i));
    }

    /**
     * Được tạo bởi Nguyễn Bá Linh vào ngày 12/03/2019
     * Mô tả: khởi tạo đối tượng cho listener
     *
     * @param itemClickListener
     */
    public void setItemClickListener(OnItemClickListener<User> itemClickListener) {
        try {
            if (itemClickListener != null) {
                mItemClickListener = itemClickListener;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Được tạo bởi Nguyễn Bá Linh vào ngày 13/03/2019
     * Mô tả: ItemView cho recyclerview
     */
    private class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView tvLogin;
        private TextView tvUserId;

        UserViewHolder(View itemView) {
            super(itemView);
            try {
                tvUserId = itemView.findViewById(R.id.tvUserId);
                tvLogin = itemView.findViewById(R.id.tvLoginName);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mItemClickListener.onItemClick(mListData.get(getAdapterPosition()));
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * Được tạo bởi Nguyễn Bá Linh vào ngày 13/03/2019
         * Mô tả: gán dữ liệu cho item từ user model
         *
         * @param user đối tượng dùng để kết gán dữ liệu cho item
         */
        void bind(User user) {
            try {
                if (user != null) {
                    tvUserId.setText(String.valueOf(user.getId()));
                    tvLogin.setText(user.getLogin());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
