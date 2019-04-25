package vn.com.misa.githubuser.ui.main;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;

    MainPresenter(MainContract.View view) {
        try {
            if (view != null) {
                mView = view;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        try {
            if (mView != null) {
                mView.addSearchFragment();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStop() {

    }
}
