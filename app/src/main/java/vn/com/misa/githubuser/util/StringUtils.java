package vn.com.misa.githubuser.util;

/**
 * Được tạo bởi Nguyễn Bá Linh vào ngày 13/03/2019
 * Mô tả: Xử lý chuỗi
 */
public class StringUtils {
    public static String getSearchUserUrl(String loginName, String limit) {
        return Constant.LINK_USER + limit + Constant.LOGIN_NAME + loginName;
    }
}
