package com.dmond.android.registration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hosein on 1/22/17.
 */

public class cUser {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("roles")
    @Expose
    private List<String> roles = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}

