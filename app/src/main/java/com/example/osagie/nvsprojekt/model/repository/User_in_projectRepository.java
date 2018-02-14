package com.example.osagie.nvsprojekt.model.repository;

import com.example.osagie.nvsprojekt.model.domain.User_in_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lystus on 01.02.2018.
 */

public class User_in_projectRepository  extends BaseRepository<Integer, User_in_project>  {
    @Override
    public int update(Connection con, User_in_project entity) throws SQLException {
        String stmnt="UPDATE USER_IN_PROJECT set USER = ?, PROJECT= ? where ID = ?";
        PreparedStatement preStmnt = con.prepareStatement(stmnt);
        preStmnt.setInt(1,entity.getUser());
        preStmnt.setInt(2,entity.getProject());
        preStmnt.setInt(3,entity.getProject_member_type());
        preStmnt.setInt(4,entity.getId());
        int res = preStmnt.executeUpdate();
        con.commit();
        preStmnt.close();
        return res;
    }

    @Override
    public int insert(Connection con, User_in_project entity) throws SQLException {
        if(!exist(con,entity)) {
            String stmnt = "INSERT INTO USER_IN_PROJECT" +
                    "(USER,PROJECT,PROJECT_MEMBER_TYP)" +
                    "VALUES (?,?,?)";
            PreparedStatement preStmnt = con.prepareStatement(stmnt);
            preStmnt.setInt(1, entity.getUser());
            preStmnt.setInt(2, entity.getProject());
            preStmnt.setInt(3, entity.getProject_member_type());
            int res = preStmnt.executeUpdate();
            con.commit();
            preStmnt.close();
            return res;
        }
        return 0;
    }
    public boolean exist(Connection con,User_in_project entity)throws SQLException {
        String stmnt="SELECT ID FROM USER_IN_PROJECT WHERE USER=? && PROJECT=? && PROJECT_MEMBER_TYP=?";
        PreparedStatement preStmnt = con.prepareStatement(stmnt);
        preStmnt.setInt(1, entity.getUser());
        preStmnt.setInt(2,entity.getProject());
        preStmnt.setInt(3,entity.getProject_member_type());
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
    public User_in_project findById(Connection con, Integer id) throws SQLException {
        String stmnt="SELECT ID,USER,PROJECT,PROJECT_MEMBER_TYP FROM USER_IN_PROJECT WHERE ID=?";
        PreparedStatement preStmnt = con.prepareStatement(stmnt);
        preStmnt.setInt(1,id);
        ResultSet rs = preStmnt.executeQuery();
        User_in_project uip=new User_in_project(0,0,0);
        while(rs.next()){
            uip.setId(Integer.parseInt(rs.getString("ID")));
            uip.setUser(Integer.parseInt(rs.getString("USER")));
            uip.setProject(Integer.parseInt(rs.getString("PROJECT")));
            uip.setProject_member_type(rs.getInt("PROJECT_MEMBER_TYP"));
        }
        return uip;
    }

    public ArrayList<Integer> findByProjectNoUserId(Connection con,int projectId, int userId) throws SQLException {
        String stmnt="SELECT ID,USER,PROJECT,PROJECT_MEMBER_TYP FROM USER_IN_PROJECT WHERE PROJECT=? AND USER<>?";
        PreparedStatement preStmnt = con.prepareStatement(stmnt);
        preStmnt.setInt(1,projectId);
        preStmnt.setInt(2,userId);
        ResultSet rs = preStmnt.executeQuery();
        ArrayList<Integer> list = new ArrayList<>();
        while(rs.next()){
            list.add(rs.getInt("USER"));
        }
        return list;
    }

    @Override
    public List<User_in_project> findAll(Connection con) throws SQLException {
        String stmnt="SELECT ID,USER,PROJECT,PROJECT_MEMBER_TYP FROM USER_IN_PROJECT";
        PreparedStatement preStmnt = con.prepareStatement(stmnt);
        ResultSet rs = preStmnt.executeQuery();
        List<User_in_project> list=new ArrayList<>();
        while(rs.next()){
            User_in_project uip=new User_in_project(0,0,0);
            uip.setId(Integer.parseInt(rs.getString("ID")));
            uip.setUser(Integer.parseInt(rs.getString("USER")));
            uip.setProject(Integer.parseInt(rs.getString("PROJECT")));
            uip.setProject_member_type(rs.getInt("PROJECT_MEMBER_TYP"));
            list.add(uip);
        }
        return list;
    }

    @Override
    public int deleteById(Connection con, Integer id) throws SQLException {
        String stmnt="DELETE FROM USER_IN_PROJECT WHERE ID=?";
        PreparedStatement preStmnt = con.prepareStatement(stmnt);
        preStmnt.setInt(1,id);
        int res = preStmnt.executeUpdate();
        con.commit();
        preStmnt.close();
        return res;
    }

    public ArrayList<Integer> getAllProjectByUser(Connection con,int user)throws SQLException {
        String stmnt="SELECT PROJECT FROM USER_IN_PROJECT WHERE USER=?";
        PreparedStatement preStmnt = con.prepareStatement(stmnt);
        preStmnt.setInt(1,user);
        ResultSet rs = preStmnt.executeQuery();
        ArrayList<Integer> list=new ArrayList<>();
        while(rs.next()){
            list.add(rs.getInt("PROJECT"));
        }
        return list;
    }
}
