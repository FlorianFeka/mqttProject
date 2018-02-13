package com.example.osagie.nvsprojekt.model.repository;

import com.example.osagie.nvsprojekt.model.domain.Project;
import com.example.osagie.nvsprojekt.model.domain.Project;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            String stmnt = "INSERT INTO PROJECT" +
                    "(PROJECTNAME,CLIENT,DESCRIPTION,PROJECTSTART,PROJECTEND)" +
                    "VALUES (?,?,?,?,?)";
            PreparedStatement preStmnt = con.prepareStatement(stmnt, Statement.RETURN_GENERATED_KEYS);
            preStmnt.setString(1,entity.getProjectname());
            preStmnt.setString(2,entity.getClient());
            preStmnt.setString(3,entity.getDescription());
            preStmnt.setDate(4,entity.getProjectstart());
            preStmnt.setDate(5,entity.getProjectend());
            int res = preStmnt.executeUpdate();
            ResultSet rs=preStmnt.getGeneratedKeys();
            int id=-1;
            if (rs.next()) {
                id=rs.getInt(1);
            }
            rs.close();
            con.commit();
            preStmnt.close();
            return id;
    }
    public boolean checkname(Connection con,ArrayList<Integer> list,String name)throws SQLException {
        List<Project> projects=new ArrayList<>();
        for(int i:list){
            projects.add(findById(con,i));
        }
        for(Project p:projects){
            if(p.getProjectname().equals(name))
                return true;
        }
        return false;
    }
    @Override
    public Project findById(Connection con, Integer id) throws SQLException {
        String stmnt="SELECT ID,PROJECTNAME,CLIENT,DESCRIPTION,PROJECTSTART,PROJECTEND FROM PROJECT WHERE ID=?";
        PreparedStatement preStmnt = con.prepareStatement(stmnt);
        preStmnt.setInt(1,id);
        ResultSet rs = preStmnt.executeQuery();
        Project p=new Project("","","",null,null);
        while(rs.next()){
            p.setId(Integer.parseInt(rs.getString("ID")));
            p.setProjectname(rs.getString("PROJECTNAME"));
            p.setClient(rs.getString("CLIENT"));
            p.setDescription(rs.getString("DESCRIPTION"));
            p.setProjectstart(Date.valueOf(rs.getString("PROJECTSTART")));
            p.setProjectend(Date.valueOf(rs.getString("PROJECTEND")));
        }
        return p;
    }

    @Override
    public List<Project> findAll(Connection con) throws SQLException {
        String stmnt="SELECT ID,PROJECTNAME,CLIENT,DESCRIPTION,PROJECTSTART,PROJECTEND FROM PROJECT";
        PreparedStatement preStmnt = con.prepareStatement(stmnt);
        ResultSet rs = preStmnt.executeQuery();
        List<Project> list=new ArrayList<>();
        while(rs.next()){
            Project p=new Project("","","",null,null);
            p.setId(Integer.parseInt(rs.getString("ID")));
            p.setProjectname(rs.getString("PROJECTNAME"));
            p.setClient(rs.getString("CLIENT"));
            p.setDescription(rs.getString("DESCRIPTION"));
            p.setProjectstart(Date.valueOf(rs.getString("PROJECTSTART")));
            p.setProjectend(Date.valueOf(rs.getString("PROJECTEND")));
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
