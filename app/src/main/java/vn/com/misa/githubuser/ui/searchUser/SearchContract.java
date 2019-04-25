package vn.com.misa.githubuser.ui.searchUser;

import java.util.List;

import vn.com.misa.githubuser.base.BasePresenter;
import vn.com.misa.githubuser.base.BaseView;
import vn.com.misa.githubuser.data.model.User;

/**
 * Được tạo bởi Nguyễn Bá Linh vào ngày 12/03/2019
 * Mô tả: là lớp contract cho tác vụ tìm kiếm, rằng buộc khởi tạo các phương thức cho
 * View và Presenter
 */
public interface SearchContract {
    interface View extends BaseView {

        void showError(String msg);

        void showListUser(List<User> users);

        void showLoading();


        void hideLoading();

        void setText(String url);
    }

    interface Presenter extends BasePresenter {

        void getGithubUser(String userName, String limit);
    }
}
