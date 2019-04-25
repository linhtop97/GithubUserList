package vn.com.misa.githubuser.data.model;

public class UserClickEvent {
    private User mUser;
    public UserClickEvent(User user){
        mUser = user;
    }

    public User getUser() {
        return mUser;
    }
}
