package com.example.osagie.nvsprojekt.model.repository;

import com.example.osagie.nvsprojekt.model.domain.Project;
import com.example.osagie.nvsprojekt.model.domain.Project;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lystus on 02.02.2018.
 */

public class ProjectRepository extends BaseRepository<Integer,Project> {
    @Override
    public int update(Connection con, Project entity) throws SQLException {
        String stmnt="UPDATE PROJECT set PROJECTNAME = ?, CLIENT= ?,DESCRIPTION=?,PROJECTSTART=?,PROJECTEND=?,ADDED=? where ID = ?";
        PreparedStatement preStmnt = con.prepareStatement(stmnt);
        preStmnt.setString(1,entity.getProjectname());
        preStmnt.setString(2,entity.getClient());
        preStmnt.setString(3,entity.getDescription());
        preStmnt.setDate(4,entity.getProjectstart());
        preStmnt.setDate(5,entity.getProjectend());
        preStmnt.setTimestamp(6,entity.getAdded());
        preStmnt.setInt(7,entity.getId());
        int res = preStmnt.executeUpdate();
        con.commit();
        preStmnt.close();
        return res;
    }

    @Override
    public int insert(Connection con, Project entity) throws SQLException {
        if(!exist(con,entity)) {
            String stmnt = "INSERT INTO PROJECT" +
                    "(PROJECTNAME,CLIENT,DESCRIPTION,PROJECTSTART,PROJECTEND,ADDED)" +
                    "VALUES (?,?,?,?,?,?)";
            PreparedStatement preStmnt = con.prepareStatement(stmnt);
            preStmnt.setString(1,entity.getProjectname());
            preStmnt.setString(2,entity.getClient());
            preStmnt.setString(3,entity.getDescription());
            preStmnt.setDate(4,entity.getProjectstart());
            preStmnt.setDate(5,entity.getProjectend());
            preStmnt.setTimestamp(6,entity.getAdded());
            int res = preStmnt.executeUpdate();
            con.commit();
            preStmnt.close();
            return res;
        }
        return 0;
    }
    public boolean exist(Connection con,Project entity)throws SQLException {
        String stmnt="SELECT ID FROM PROJECT WHERE PROJECTNAME=? && CLIENT=? && DESCRIPTION=?,PROJECTSTART=?,PROJECTEND=?,ADDED=?";
        PreparedStatement preStmnt = con.prepareStatement(stmnt);
        preStmnt.setString(1,entity.getProjectname());
        preStmnt.setString(2,entity.getClient());
        preStmnt.setString(3,entity.getDescription());
        preStmnt.setDate(4,entity.getProjectstart());
        preStmnt.setDate(5,entity.getProjectend());
        preStmnt.setTimestamp(6,entity.getAdded());
        ResultSet resultSet= preStmnt.executeQuery();
        while(resultSet.next()){
            int id=resultSet.getInt("ID");
            if(id!=0)
                return true;
        }
        preStmnt.close();
        return false;
    }
    @Override
    public Project findById(Connection con, Integer id) throws SQLException {
        String stmnt="SELECT ID,PROJECTNAME,CLIENT,DESCRIPTION,PROJECTSTART,PROJECTEND,ADDED FROM PROJECT WHERE ID=?";
        PreparedStatement preStmnt = con.prepareStatement(stmnt);
        preStmnt.setInt(1,id);
        ResultSet rs = preStmnt.executeQuery();
        Project p=null;
        while(rs.next()){
            p.setId(Integer.parseInt(rs.getString("ID")));
            p.setProjectname(rs.getString("PROJECTNAME"));
            p.setClient(rs.getString("CLIENT"));
            p.setDescription(rs.getString("DESCRIPTION"));
            p.setProjectstart(Date.valueOf(rs.getString("PROJECTSTART")));
            p.setProjectend(Date.valueOf(rs.getString("PROJECTEND")));
            p.setAdded(Timestamp.valueOf(rs.getString("ADDED")));
        }
        return p;
    }

    @Override
    public List<Project> findAll(Connection con) throws SQLException {
        String stmnt="SELECT ID,PROJECTNAME,CLIENT,DESCRIPTION,PROJECTSTART,PROJECTEND,ADDED FROM PROJECT";
        PreparedStatement preStmnt = con.prepareStatement(stmnt);
        ResultSet rs = preStmnt.executeQuery();
        List<Project> list=new ArrayList<>();
        while(rs.next()){
            Project p=null;
            p.setId(Integer.parseInt(rs.getString("ID")));
            p.setProjectname(rs.getString("PROJECTNAME"));
            p.setClient(rs.getString("CLIENT"));
            p.setDescription(rs.getString("DESCRIPTION"));
            p.setProjectstart(Date.valueOf(rs.getString("PROJECTSTART")));
            p.setProjectend(Date.valueOf(rs.getString("PROJECTEND")));
            p.setAdded(Timestamp.valueOf(rs.getString("ADDED")));
            list.add(p);
        }
        return list;
    }

    @Override
    public int deleteById(Connection con, Integer id) throws SQLException {
        String stmnt="DELETE FROM PROJECT WHERE ID=?";
        PreparedStatement preStmnt = con.prepareStatement(stmnt);
        preStmnt.setInt(1,id);
        int res = preStmnt.executeUpdate();
        con.commit();
        preStmnt.close();
        return res;
    }
}
