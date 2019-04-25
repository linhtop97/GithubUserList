package vn.com.misa.githubuser.base;

/**
 * Được tạo bởi Nguyễn Bá Linh vào ngày 12/03/2019
 * Mô tả:  Là lớp base cho Presenter
 */
public interface BasePresenter {
    /**
     * Được tạo bởi Nguyễn Bá Linh vào ngày 12/03/2019
     * Mô tả: chịu trách nhiệm khởi tạo các biến, giá trị cần thiết khi presenter
     * được triển khai
     */
    void onStart();

    /**
     * Được tạo bởi Nguyễn Bá Linh vào ngày 12/03/2019
     * Mô tả:  chịu trách nhiệm xử lý khi presenter kết thúc nhiệm vụ của mình
     */
    void onStop();
}
