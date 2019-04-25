package vn.com.misa.githubuser.ui.main;

import vn.com.misa.githubuser.base.BasePresenter;
import vn.com.misa.githubuser.base.BaseView;

/**
 * Được tạo bởi Nguyễn Bá Linh vào ngày 13/03/2019
 * Mô tả: Lớp contract cho màn hình main rằng buộc view và presenter
 */
public interface MainContract {
    interface View extends BaseView {
        void addSearchFragment();
    }

    interface Presenter extends BasePresenter {

    }
}
