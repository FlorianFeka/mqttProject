package com.example.osagie.nvsprojekt.model.repository;

import domain.BaseDomain;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseRepository<PK_TYPE extends Number, DOMAIN_TYPE extends BaseDomain<PK_TYPE, DOMAIN_TYPE>> {
    public int save(Connection con, DOMAIN_TYPE entity) throws SQLException {
        if(entity.isNew()){
            return insert(con, entity);
        }else{
            return update(con, entity);
        }
    }

    public abstract int update(Connection con, DOMAIN_TYPE entity) throws SQLException;

    public abstract int insert(Connection con, DOMAIN_TYPE entity) throws SQLException;

    public abstract DOMAIN_TYPE findById(Connection con, PK_TYPE id) throws SQLException;

    public abstract List<DOMAIN_TYPE> findAll(Connection con) throws SQLException;

    public final int delete(Connection con, DOMAIN_TYPE entity) throws SQLException {
        return (entity.isNew() ? 1 : deleteById(con, entity.getId()));
    }

    public abstract int deleteById(Connection con, PK_TYPE entity) throws SQLException;
}
