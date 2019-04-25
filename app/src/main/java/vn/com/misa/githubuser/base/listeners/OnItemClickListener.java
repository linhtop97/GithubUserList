package vn.com.misa.githubuser.base.listeners;

/**
 * Create by Nguyen Ba Linh on 12/03/2019
 * Description: Là listener chịu trách nhiệm cho sự kiện click cho View
 *
 * @param <T> là kiểu dữ liệu được truyền đi khi sự kiện click xảy ra
 */
public interface OnItemClickListener<T> {
    /**
     * Create by Nguyen Ba Linh on 12/03/2019
     * Description: là phương thức được sử dụng khi sự kiện click của View được kích hoạt
     *
     * @param data là tham số được truyền vào khi View được click
     */
    void onItemClick(T data);
}
