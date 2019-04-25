package vn.com.misa.githubuser.ui.searchUser;

import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import vn.com.misa.githubuser.R;
import vn.com.misa.githubuser.base.listeners.OnItemClickListener;
import vn.com.misa.githubuser.data.model.User;
import vn.com.misa.githubuser.data.model.UserClickEvent;
import vn.com.misa.githubuser.databinding.FragmentSearchBinding;
import vn.com.misa.githubuser.ui.main.MainActivity;
import vn.com.misa.githubuser.ui.userDetail.UserDetailFragment;
import vn.com.misa.githubuser.util.Navigator;

public class SearchFragment extends Fragment implements SearchContract.View, View.OnClickListener, OnItemClickListener<User> {

    private static final String TAG = "SearchFragment";
    private FragmentSearchBinding mBinding;
    private SearchContract.Presenter mPresenter;
    private ProgressDialog mDialog;
    private MainActivity mActivity;
    private UserAdapter mUserAdapter;
    private Navigator mNavigator;
    private List<User> mUsers;

    /**
     * Được tạo bởi Nguyễn Bá Linh vào ngày 14/03/2019
     * Mô tả: khởi tạo fragment
     *
     * @return fragment search
     */
    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mNavigator = new Navigator(mActivity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //lấy lại danh sách user nếu danh sách đã tồn tại khi fragment được trở về lại từ fragment khác
        try {
            if (mUsers != null) {
                mUserAdapter.setListData(mUsers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        //lưu lại danh sách user trước khi fragment đi vào trạng thái stop
        try {
            mUsers = mUserAdapter.getListData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        mPresenter = new SearchPresenter(this);
        initView();
        initAction();
        return mBinding.getRoot();
    }

    /**
     * Được tạo bởi Nguyễn Bá Linh vào ngày 13/03/2019
     * Mô tả: khởi tạo và gắn các sự kiện cho view
     */
    private void initAction() {
        try {
            mBinding.btnSearch.setOnClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Được tạo bởi Nguyễn Bá Linh vào ngày 13/03/2019
     * Mô tả: khởi tạo biến, đối tượng ban đầu
     */
    private void initView() {
        try {
            mDialog = new ProgressDialog(mActivity) {
                @Override
                public void onBackPressed() {
                    super.onBackPressed();
                }
            };
            mDialog.setMessage(mActivity.getString(R.string.msg_searching));
            mDialog.setCancelable(false);
            mDialog.setCanceledOnTouchOutside(false);

            mUserAdapter = new UserAdapter(mActivity);
            mUserAdapter.setItemClickListener(this);
            mBinding.rcUserGithub.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
            mBinding.rcUserGithub.setAdapter(mUserAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSearch:
                try {
                    if (isNetworkAvailable()) {
                        mPresenter.getGithubUser(mBinding.edUserName.getText().toString(), mBinding.edNumOfResult.getText().toString());
                        mNavigator.hideKeyboard();
                    } else {
                        mUserAdapter.clear();
                        mBinding.tvNotification.setText(getString(R.string.network_not_avaiable));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    /**
     * Được tạo bởi Nguyễn Bá Linh vào ngày 12/03/2019
     * Mô tả: thông báo tới user lỗi không hợp lệ(lỗi mạng, server)
     *
     * @param msg là thông điệp muốn thông báo tới người dùng
     */
    @Override
    public void showError(String msg) {
        try {
            mUserAdapter.clear();
            if (msg != null) {
                mBinding.tvNotification.setText(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Được tạo bởi Nguyễn Bá Linh vào ngày 12/03/2019
     * Mô tả: Hiển thị danh sách user
     *
     * @param users danh sách được trả về từ model
     */
    @Override
    public void showListUser(List<User> users) {
        try {
            if (users != null) {
                mDialog.dismiss();
                if (users.size() == 0) {
                    mUserAdapter.clear();
                    mBinding.tvNotification.setText(getString(R.string.user_not_found));
                } else {
                    mBinding.tvNotification.setText(getString(R.string.nothing));
                    mUserAdapter.setListData(users);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Được tạo bởi Nguyễn Bá Linh vào ngày 12/03/2019
     * Mô tả: Hiển thị dialog loading
     */
    @Override
    public void showLoading() {
        try {
            if (mDialog != null && !mDialog.isShowing()) {
                mDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Được tạo bởi Nguyễn Bá Linh vào ngày 12/03/2019
     * Mô tả: Ẩn xóa dialiog loading
     */
    @Override
    public void hideLoading() {
        try {
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setText(String url) {
        mNavigator.showToast(url);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            if (context != null) {
                mActivity = (MainActivity) context;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(User data) {
        try {
            if (data != null) {
                if (isNetworkAvailable()) {
                    mNavigator.addFragment(R.id.clMain, UserDetailFragment.newInstance(), true,
                            Navigator.NavigateAnim.RIGHT_LEFT, UserDetailFragment.class.getSimpleName());
                    EventBus.getDefault().postSticky(new UserClickEvent(data));
                } else {
                    mUserAdapter.clear();
                    mBinding.tvNotification.setText(getString(R.string.network_not_avaiable));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Được tạo bởi Nguyễn Bá Linh vào ngày 13/03/2019
     * Mô tả: kiểm tra tra có kết nối mạng không
     *
     * @return
     */
    private boolean isNetworkAvailable() {
        try {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) mActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
