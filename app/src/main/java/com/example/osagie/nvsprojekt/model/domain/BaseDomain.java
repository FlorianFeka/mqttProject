package com.example.osagie.nvsprojekt.model.domain;

/**
 * Created by Florian on 30.01.2018.
 */

public abstract class BaseDomain<PK_TYPE extends Number, DOMAIN_TYP extends BaseDomain<PK_TYPE, DOMAIN_TYP>> implements Comparable<DOMAIN_TYP>{
    protected PK_TYPE id;
    private Integer version=0;

    public PK_TYPE getId() {
        return id;
    }
    public void setId(PK_TYPE id){this.id=id;}
    public Integer getVersion() {
        return version;
    }

    public boolean isNew(){
        return id == null;
    }
}
