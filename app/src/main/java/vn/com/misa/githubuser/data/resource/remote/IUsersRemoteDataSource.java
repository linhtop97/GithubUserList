package vn.com.misa.githubuser.data.resource.remote;


import java.util.List;

import vn.com.misa.githubuser.base.listeners.DataCallBack;
import vn.com.misa.githubuser.data.model.User;
import vn.com.misa.githubuser.data.resource.IUsersDataSource;

public class IUsersRemoteDataSource implements IUsersDataSource {

    private static IUsersRemoteDataSource sInstance;

    private IUsersRemoteDataSource() {
    }

    public static IUsersRemoteDataSource getInstance() {
        if (sInstance == null) {
            synchronized (IUsersRemoteDataSource.class) {
                if (sInstance == null) {
                    sInstance = new IUsersRemoteDataSource();
                }
            }
        }
        return sInstance;
    }

    @Override
    public void getUsers(String url, DataCallBack<List<User>> callback) {
        new FetchDataFromUrl(callback).execute(url);
    }
}
