package vn.com.misa.githubuser.data.resource.remote;
;
import android.os.AsyncTask;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSocketFactory;

import vn.com.misa.githubuser.MyApplication;
import vn.com.misa.githubuser.base.listeners.DataCallBack;
import vn.com.misa.githubuser.data.model.User;
import vn.com.misa.githubuser.util.Constant;

/**
 * Được tạo bởi Nguyễn Bá Linh vào ngày 13/03/2019
 * Mô tả: Lớp lấy danh sách user từ url
 */
public class FetchDataFromUrl extends AsyncTask<String, Void, String> {

    private DataCallBack<List<User>> mCallback;

    public FetchDataFromUrl(DataCallBack<List<User>> callback) {
        mCallback = callback;
    }

    @Override
    protected String doInBackground(String... strings) {
        //string[0] là tham chuỗi đầu tiên khi truyền vào - tương ứng url khi truyền vào asyn
        return getJSONStringFromURL(strings[0]);
    }

    @Override
    protected void onPostExecute(String json) {
        super.onPostExecute(json);
        if (json == null) {
            mCallback.onDataFailed(Constant.UNEXPECTED_ERROR);
            return;
        }
        mCallback.onDataSuccess(getUserFromJSONString(json));
    }

    /**
     * Được tạo bởi Nguyễn Bá Linh vào ngày 13/03/2019
     * Mô tả: lấy chuyển đổi chuỗi Json sang danh sách User
     *
     * @param json chuỗi json
     * @return danh sách user
     */
    private List<User> getUserFromJSONString(String json) {
        List<User> users = new ArrayList<>();
//        try {
//            JSONObject jsonObject = new JSONObject(json);
//            JSONArray jsonArray = jsonObject.optJSONArray(Constant.ITEMS);
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject object = jsonArray.optJSONObject(i);
//                users.add(new User(object.getInt(Constant.ID),
//                        object.getString(Constant.LOGIN),
//                        object.getString(Constant.URL),
//                        object.getString(Constant.AVATAR_URL)));
//            }
//            return users;
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    /**
     * Được tạo bởi Nguyễn Bá Linh vào ngày 13/03/2019
     * Mô tả: Phương thức lấy string json từ  chuỗi url
     *
     * @param urlString chuỗi url
     * @return chuỗi json
     */
    private String getJSONStringFromURL(String urlString) {
        HttpURLConnection httpURLConnection;
        try {
            //chỉnh sửa lỗi về https trên android version <4.4
            ProviderInstaller.installIfNeeded(MyApplication.getInstance());
            URL url = new URL(urlString);
            SSLContext sslcontext = SSLContext.getInstance("TLSv1");
            sslcontext.init(null, null, null);
            SSLSocketFactory NoSSLv3Factory = new NoSSLv3SocketFactory(sslcontext.getSocketFactory());
            HttpsURLConnection.setDefaultSSLSocketFactory(NoSSLv3Factory);


            HttpsURLConnection l_connection = (HttpsURLConnection) url.openConnection();
            l_connection.setRequestMethod(Constant.GET_METHOD);
            l_connection.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            String jsonString = sb.toString();
            l_connection.disconnect();
            return jsonString;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        }
        return null;
    }
}
