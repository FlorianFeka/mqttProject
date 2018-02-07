package com.example.osagie.nvsprojekt.model.domain;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Comparator;

/**
 * Created by Lystus on 01.02.2018.
 */

public class Project extends BaseDomain<Integer,Project> {
    private String projectname,client,description;
    private Date projectstart,projectend;
    private Timestamp added;

    public Project(String projectname, String client, String description, Date projectstart, Date projectend, Timestamp added) {
        setProjectname(projectname);
        setClient(client);
        setDescription(description);
        setProjectstart(projectstart);
        setProjectend(projectend);
        setAdded(added);
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getProjectstart() {
        return projectstart;
    }

    public void setProjectstart(Date projectstart) {
        this.projectstart = projectstart;
    }

    public Date getProjectend() {
        return projectend;
    }

    public void setProjectend(Date projectend) {
        this.projectend = projectend;
    }

    public Timestamp getAdded() {
        return added;
    }

    public void setAdded(Timestamp added) {
        this.added = added;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public int compareTo(@NonNull Project project) {
        return this.getId().compareTo(project.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;

        Project project = (Project) o;

        if (projectname != null ? !projectname.equals(project.projectname) : project.projectname != null)
            return false;
        if (client != null ? !client.equals(project.client) : project.client != null) return false;
        if (description != null ? !description.equals(project.description) : project.description != null)
            return false;
        if (projectstart != null ? !projectstart.equals(project.projectstart) : project.projectstart != null)
            return false;
        if (projectend != null ? !projectend.equals(project.projectend) : project.projectend != null)
            return false;
        return added != null ? added.equals(project.added) : project.added == null;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
