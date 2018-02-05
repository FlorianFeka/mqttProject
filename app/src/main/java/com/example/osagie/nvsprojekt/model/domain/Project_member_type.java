package com.example.osagie.nvsprojekt.model.domain;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import java.util.Comparator;

/**
 * Created by Lystus on 01.02.2018.
 */

public class Project_member_type extends BaseDomain<Integer,Project_member_type> {
    private String token,description;

    public Project_member_type(String token, String description) {
        setToken(token);
        setDescription(description);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public int compareTo(@NonNull Project_member_type project_member_type) {
        return this.getId().compareTo(project_member_type.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project_member_type)) return false;

        Project_member_type that = (Project_member_type) o;

        if (token != null ? !token.equals(that.token) : that.token != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
