package com.example.osagie.nvsprojekt.model.domain;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import java.sql.Timestamp;
import java.util.Comparator;

/**
 * Created by Lystus on 01.02.2018.
 */

public class User_in_project extends BaseDomain<Integer,User_in_project> {
    private int user,project_member_type,project;

    public User_in_project(int user, int project, int project_member_type) {
        setUser(user);
        setProject_member_type(project_member_type);
        setProject(project);
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getProject_member_type() {
        return project_member_type;
    }

    public void setProject_member_type(int project_member_type) {
        this.project_member_type = project_member_type;
    }

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public int compareTo(@NonNull User_in_project user_in_project) {
        return this.getId().compareTo(user_in_project.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User_in_project)) return false;

        User_in_project that = (User_in_project) o;

        if (user != that.user) return false;
        if (project_member_type != that.project_member_type) return false;
        if (project != that.project) return false;
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
