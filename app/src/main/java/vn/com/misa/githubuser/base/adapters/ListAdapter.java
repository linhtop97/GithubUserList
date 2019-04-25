package vn.com.misa.githubuser.base.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import vn.com.misa.githubuser.base.listeners.OnItemClickListener;
import vn.com.misa.githubuser.base.listeners.OnItemLongClickListener;

/**
 * Được tạo bởi Nguyễn Bá Linh vào ngày 12/03/2019
 * Mô tả: Là class base cho các adapter dành cho recyclerView
 *
 * @param <T>
 */
public class ListAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Context mContext;
    protected List<T> mListData;

    /**
     * Được tạo bởi Nguyễn Bá Linh vào ngày 12/03/2019
     * Mô tả:   là phương thức khởi tạo cho ListAdapter
     *
     * @param context là được truyền tới từ context nơi khởi tạo thể hiện của lớp
     */
    public ListAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return mListData != null ? mListData.size() : 0;
    }

    /**
     * Được tạo bởi Nguyễn Bá Linh vào ngày 12/03/2019
     * Mô tả: lấy danh sách dữ liệu hiện tại
     *
     * @return danh sách dữ liệu hiện tại
     */
    public List<T> getListData() {
        return mListData;
    }

    /**
     * Được tạo bởi Nguyễn Bá Linh vào ngày 12/03/2019
     * Mô tả: truyền dữ liệu cho danh sách
     *
     * @param listData danh sách dữ liệu được truyền vào
     */
    public void setListData(List<T> listData) {
        mListData = listData;
        notifyDataSetChanged();
    }

    /**
     * Được tạo bởi Nguyễn Bá Linh vào ngày 12/03/2019
     * Mô tả: thêm dữ liệu cho danh sách dữ liệu đã có
     *
     * @param listData danh sách dữ liệu được thêm vào
     */
    public void addData(List<T> listData) {
        if (mListData == null) {
            mListData = listData;
        } else {
            mListData.addAll(listData);
        }
        notifyDataSetChanged();
    }

    /**
     * Được tạo bởi Nguyễn Bá Linh vào ngày 12/03/2019
     * Mô tả: được dùng để xóa dữ liệu của danh sách
     */
    public void clear() {
        if (mListData != null) {
            mListData.clear();
            notifyDataSetChanged();
        }
    }

    /**
     *  Được tạo bởi Nguyễn Bá Linh vào ngày 12/03/2019
      * Mô tả: lấy item tại vị trí position
     * @param position là vị trí muốn lấy
     * @return item của list
     */
    public T getItem(int position) {
        return mListData.get(position);
    }

}
