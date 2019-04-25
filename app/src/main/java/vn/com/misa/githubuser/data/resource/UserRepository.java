package vn.com.misa.githubuser.data.resource;

import java.util.List;

import vn.com.misa.githubuser.base.listeners.DataCallBack;
import vn.com.misa.githubuser.data.model.User;
import vn.com.misa.githubuser.data.resource.remote.IUsersRemoteDataSource;

public class UserRepository implements IUsersDataSource {
    private static UserRepository sInstance;
    private IUsersRemoteDataSource mUsersRemoteDataSource;

    private UserRepository(IUsersRemoteDataSource usersRemoteDataSource) {
        mUsersRemoteDataSource = usersRemoteDataSource;
    }

    public static UserRepository getInstance(IUsersRemoteDataSource usersRemoteDataSource) {
        if (sInstance == null) {
            synchronized (UserRepository.class) {
                if (sInstance == null) {
                    sInstance = new UserRepository(usersRemoteDataSource);
                }
            }
        }
        return sInstance;
    }

    @Override
    public void getUsers(String url, DataCallBack<List<User>> callback) {
        mUsersRemoteDataSource.getUsers(url, callback);
    }
}
