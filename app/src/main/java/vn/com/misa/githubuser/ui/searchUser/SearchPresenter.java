package vn.com.misa.githubuser.ui.searchUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.misa.githubuser.base.listeners.DataCallBack;
import vn.com.misa.githubuser.data.api.GithubService;
import vn.com.misa.githubuser.data.api.ServiceGenerator;
import vn.com.misa.githubuser.data.model.User;
import vn.com.misa.githubuser.data.model.UserResponse;
import vn.com.misa.githubuser.data.resource.UserRepository;
import vn.com.misa.githubuser.data.resource.remote.IUsersRemoteDataSource;

public class SearchPresenter implements SearchContract.Presenter, DataCallBack<List<User>> {

    private static final String TAG = "SearchPresenter";
    private SearchContract.View mView;
    private UserRepository mUserRepository;

    /**
     * Được tạo bởi Nguyễn Bá Linh vào ngày 13/03/2019
     * Mô tả: khởi tạo lớp SearchPresenter với các tham số ban đầu
     *
     * @param view là thể hiện của màn hình Search
     */
    public SearchPresenter(SearchContract.View view) {
        try {
            if (view != null) {
                mView = view;
                mUserRepository = UserRepository.getInstance(IUsersRemoteDataSource.getInstance());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Được tạo bởi Nguyễn Bá Linh vào ngày 12/03/2019
     * Mô tả: phương thức lấy danh sách user github từ tham số đầu vào
     *
     * @param userName là tên user muốn tìm kiếm
     * @param limit    là số lượng kết quả trả về mong muốn
     */
    @Override
    public void getGithubUser(String userName, String limit) {
        try {
            if (userName.equals("") || limit == null) {
                return;
            }
            mView.showLoading();
//            mUserRepository.getUsers(StringUtils.getSearchUserUrl(userName, limit), this);
            GithubService service = ServiceGenerator.createService(GithubService.class);
            service.listUser("10", userName).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    UserResponse userResponse = response.body();
                    mView.showListUser(userResponse.getUsers());
                    mView.hideLoading();
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    mView.showError("ko dduwoc");
                    mView.hideLoading();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDataSuccess(List<User> data) {
        try {
            if (data != null) {
                mView.showListUser(data);
                mView.hideLoading();
            } else {
                mView.hideLoading();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDataFailed(String msg) {
        try {
            if (msg != null) {
                mView.hideLoading();
                mView.showError(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
