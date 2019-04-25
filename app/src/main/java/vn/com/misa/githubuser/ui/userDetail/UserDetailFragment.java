package vn.com.misa.githubuser.ui.userDetail;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import vn.com.misa.githubuser.R;
import vn.com.misa.githubuser.data.model.User;
import vn.com.misa.githubuser.data.model.UserClickEvent;
import vn.com.misa.githubuser.databinding.FragmentUserDetailBinding;
import vn.com.misa.githubuser.ui.main.MainActivity;

public class UserDetailFragment extends Fragment {

    private static final String TAG = "UserDetailFragment";
    private FragmentUserDetailBinding mBinding;
    private MainActivity mActivity;
    private User mUser;
    private EventBus mEventBus;

    @Subscribe(sticky = true)
    public void onEvent(UserClickEvent userClickEvent) {
        if (userClickEvent != null) {
            if (userClickEvent.getUser() != null) {
                mUser = userClickEvent.getUser();
                Glide.with(this).load(mUser.getAvatarUrl()).apply(new RequestOptions()
                        .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
                        .fitCenter()).into(mBinding.imgUserAvatar);
                mBinding.tvUserName.setText(mUser.getLogin());
                mBinding.tvUserUrl.setText(mUser.getUrl());
            }

        }
    }

    /**
     * Được tạo bởi Nguyễn Bá Linh vào ngày 13/03/2019
     * Mô tả: khởi tạo Fragment
     *
     * @return
     */
    public static UserDetailFragment newInstance() {
        return new UserDetailFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_detail, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
        mEventBus = EventBus.getDefault();

    }

    @Override
    public void onStart() {
        super.onStart();
        mEventBus.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mEventBus.removeStickyEvent(UserClickEvent.class);
        mEventBus.unregister(this);
    }
}
