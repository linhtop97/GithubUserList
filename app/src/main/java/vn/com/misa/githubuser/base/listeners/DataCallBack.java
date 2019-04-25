package vn.com.misa.githubuser.base.listeners;

/**
 * Được tạo bởi Nguyễn Bá Linh vào ngày 12/03/2019
 * Mô tả: Là interface lắng nghe xứ lý khi lấy dữ liệu
 *
 * @param <T> là kiểu dữ liệu kết quả trả về
 */
public interface DataCallBack<T> {

    /**
     * Được tạo bởi Nguyễn Bá Linh vào ngày 12/03/2019
     * Mô tả:  được gọi khi lấy dữ liệu về thành công
     *
     * @param data là dữ liệu được trả về
     */
    void onDataSuccess(T data);

    /**
     * Được tạo bởi Nguyễn Bá Linh vào ngày 12/03/2019
     * Mô tả:  được gọi khi việc lấy dữ liệu bị thất bại
     *
     * @param msg là thông điệp muốn trả về để xử lý
     */
    void onDataFailed(String msg);
}
