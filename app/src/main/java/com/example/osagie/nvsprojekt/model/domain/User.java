package com.example.osagie.nvsprojekt.model.domain;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import java.util.Comparator;

/**
 * Created by Florian on 30.01.2018.
 */

public class User extends BaseDomain<Integer,User> {
    private String username,email,password;

    public User(String username,String email,String password){
        setUsername(username);
        setEmail(email);
        setPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public int compareTo(@NonNull User o) {
        return Comparator.comparing(User::getId).compare(this,o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (username != null ? !username.equals(user.username) : user.username != null)
            return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        return password != null ? password.equals(user.password) : user.password == null;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
