package com.example.mvp_demo.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 *
 * Created by hasee on 2016/8/22.
 */
@Entity
public class User extends BasicEntity {

    @Id(autoincrement = true)
    public Long id;
    @NotNull
    public String mUsername;
    public String mUserPassword;
    public String mEmailAddress;

    public User(){

    }

    @Generated(hash = 594399851)
    public User(Long id, @NotNull String mUsername, String mUserPassword, String mEmailAddress) {
        this.id = id;
        this.mUsername = mUsername;
        this.mUserPassword = mUserPassword;
        this.mEmailAddress = mEmailAddress;
    }

    @Override
    public String toString() {
        return super.toString()+",[mUsername]="+mUsername+",[mUserPassword]="+mUserPassword+",[mEmailAddress]="+mEmailAddress;
    }

    @Override
    public boolean equals(User obj) {
        return  mUsername.equals(obj.mUsername);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMUsername() {
        return this.mUsername;
    }

    public void setMUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getMUserPassword() {
        return this.mUserPassword;
    }

    public void setMUserPassword(String mUserPassword) {
        this.mUserPassword = mUserPassword;
    }

    public String getMEmailAddress() {
        return this.mEmailAddress;
    }

    public void setMEmailAddress(String mEmailAddress) {
        this.mEmailAddress = mEmailAddress;
    }
}
