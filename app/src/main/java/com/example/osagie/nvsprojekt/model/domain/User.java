package com.example.osagie.nvsprojekt.model.domain;

import android.support.annotation.NonNull;

/**
 * Created by Florian on 30.01.2018.
 */

public class User extends BaseDomain<Integer,User> {
    private String username,email,passwort;



    @Override
    public int compareTo(@NonNull User o) {
        return 0;
    }
}
