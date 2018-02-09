package com.example.osagie.nvsprojekt.model.repository;

import com.example.osagie.nvsprojekt.model.domain.Project_member_type;
import com.example.osagie.nvsprojekt.model.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lystus on 01.02.2018.
 */

public class Project_member_typeRepository extends BaseRepository<Integer,Project_member_type> {
    @Override
    public int update(Connection con, Project_member_type entity) throws SQLException {
        String stmnt="UPDATE PROJECT_MEMBER_TYPE set TOKEN = ?, DESCRIPTION= ? where ID = ?";
        PreparedStatement preStmnt = con.prepareStatement(stmnt);
        preStmnt.setString(1,entity.getToken());
        preStmnt.setString(2,entity.getDescription());
        preStmnt.setInt(3,entity.getId());
        int res = preStmnt.executeUpdate();
        con.commit();
        preStmnt.close();
        return res;
    }

    @Override
    public int insert(Connection con, Project_member_type entity) throws SQLException {
        if(!exist(con,entity)) {
            String stmnt = "INSERT INTO PROJECT_MEMBER_TYPE" +
                    "(TOKEN,DESCRIPTION)" +
                    "VALUES (?,?)";
            PreparedStatement preStmnt = con.prepareStatement(stmnt);
            preStmnt.setString(1, entity.getToken());
            preStmnt.setString(2, entity.getDescription());
            int res = preStmnt.executeUpdate();
            con.commit();
            preStmnt.close();
            return res;
        }
        return 0;
    }
    public boolean exist(Connection con,Project_member_type entity)throws SQLException {
        String stmnt="SELECT ID FROM PROJECT_MEMBER_TYPE WHERE TOKEN=? && DESCRIPTION=?";
        PreparedStatement preStmnt = con.prepareStatement(stmnt);
        preStmnt.setString(1, entity.getToken());
        preStmnt.setString(2,entity.getDescription());
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
    public Project_member_type findById(Connection con, Integer id) throws SQLException {
        String stmnt="SELECT ID,TOKEN,DESCRIPTION FROM PROJECT_MEMBER_TYPE WHERE ID=?";
        PreparedStatement preStmnt = con.prepareStatement(stmnt);
        preStmnt.setInt(1,id);
        ResultSet rs = preStmnt.executeQuery();
        Project_member_type pmt=new Project_member_type("","");
        while(rs.next()){
            pmt.setId(Integer.parseInt(rs.getString("ID")));
            pmt.setToken(rs.getString("TOKEN"));
            pmt.setDescription(rs.getString("DESCRIPTION"));
        }
        return pmt;
    }

    @Override
    public List<Project_member_type> findAll(Connection con) throws SQLException {
        String stmnt="SELECT ID,TOKEN,DESCRIPTION FROM PROJECT_MEMBER_TYPE";
        PreparedStatement preStmnt = con.prepareStatement(stmnt);
        ResultSet rs = preStmnt.executeQuery();
        List<Project_member_type> list=new ArrayList<>();
        while(rs.next()){
            Project_member_type pmt=new Project_member_type("","");
            pmt.setId(Integer.parseInt(rs.getString("ID")));
            pmt.setToken(rs.getString("TOKEN"));
            pmt.setDescription(rs.getString("DESCRIPTION"));
            list.add(pmt);
        }
        return list;
    }

    @Override
    public int deleteById(Connection con, Integer id) throws SQLException {
        String stmnt="DELETE FROM PROJECT_MEMBER_TYPE WHERE ID=?";
        PreparedStatement preStmnt = con.prepareStatement(stmnt);
        preStmnt.setInt(1,id);
        int res = preStmnt.executeUpdate();
        con.commit();
        preStmnt.close();
        return res;
    }
}
