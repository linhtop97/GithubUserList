package vn.com.misa.githubuser.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import vn.com.misa.githubuser.R;
import vn.com.misa.githubuser.ui.searchUser.SearchFragment;
import vn.com.misa.githubuser.util.Navigator;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainContract.Presenter mPresenter;
    private Navigator mNavigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_main);
        mNavigator = new Navigator(this);
        mPresenter = new MainPresenter(this);
        mPresenter.onStart();
    }

    /**
     * Được tạo bởi Nguyễn Bá Linh vào ngày 13/03/2019
     * Mô tả: gắn search fragment vào main activity
     */
    @Override
    public void addSearchFragment() {
        mNavigator.addFragment(R.id.clMain, SearchFragment.newInstance(), false, Navigator.NavigateAnim.NONE, SearchFragment.class.getSimpleName());
    }
}
