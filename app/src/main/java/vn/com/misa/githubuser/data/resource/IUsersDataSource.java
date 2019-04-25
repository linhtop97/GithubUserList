package vn.com.misa.githubuser.data.resource;

import java.util.List;

import vn.com.misa.githubuser.base.listeners.DataCallBack;
import vn.com.misa.githubuser.data.model.User;

/**
 *  Được tạo bởi Nguyễn Bá Linh vào ngày 13/03/2019
  * Mô tả: Class định nghĩa các phương thức cho Nguồn dữ liệu User
 */
public interface IUsersDataSource {
    void getUsers(String url, DataCallBack<List<User>> callback);
}
