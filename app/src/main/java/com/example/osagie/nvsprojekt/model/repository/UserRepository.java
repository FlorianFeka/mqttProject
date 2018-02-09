package com.example.osagie.nvsprojekt.model.repository;

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

public class UserRepository extends BaseRepository <Integer,User>{
    @Override
    public int update(Connection con, User entity) throws SQLException {
        String stmnt="UPDATE USER set  USERNAME = ?, EMAIL= ? , PASSWORD= ? where ID = ?";
        PreparedStatement preStmnt = con.prepareStatement(stmnt);
        preStmnt.setString(1,entity.getUsername());
        preStmnt.setString(2,entity.getEmail());
        preStmnt.setString(3,entity.getPassword());
        preStmnt.setInt(4,entity.getId());
        int res = preStmnt.executeUpdate();
        con.commit();
        preStmnt.close();
        return res;
    }

    @Override
    public int insert(Connection con, User entity) throws SQLException {
        String stmnt = "INSERT INTO USER" +
                "(USERNAME,EMAIL,PASSWORD)" +
                "VALUES (?,?,?)";
        PreparedStatement preStmnt = con.prepareStatement(stmnt);
        preStmnt.setString(1, entity.getUsername());
        preStmnt.setString(2, entity.getEmail());
        preStmnt.setString(3, entity.getPassword());
        int res = preStmnt.executeUpdate();
        con.commit();
        preStmnt.close();
        return res;
    }
    public boolean exist(Connection con,User entity)throws SQLException {
        String stmnt="SELECT ID FROM USER WHERE USERNAME=? && EMAIL=? && PASSWORD=?";
        PreparedStatement preStmnt = con.prepareStatement(stmnt);
        preStmnt.setString(1, entity.getUsername());
        preStmnt.setString(2,entity.getEmail());
        preStmnt.setString(3,entity.getPassword());
        ResultSet resultSet= preStmnt.executeQuery();
        boolean next = resultSet.next();
        preStmnt.close();
        return next;
    }
    @Override
    public User findById(Connection con, Integer id) throws SQLException {
        String stmnt="SELECT ID,USERNAME,EMAIL,PASSWORD FROM USER WHERE ID=?";
        PreparedStatement preStmnt = con.prepareStatement(stmnt);
        preStmnt.setInt(1,id);
        ResultSet rs = preStmnt.executeQuery();
        User user=null;
        while(rs.next()){
            user.setId(Integer.parseInt(rs.getString("ID")));
            user.setUsername(rs.getString("USERNAME"));
            user.setEmail(rs.getString("EMAIL"));
            user.setPassword(rs.getString("PASSWORD"));
        }
        return user;
    }

    @Override
    public List<User> findAll(Connection con) throws SQLException {
        String stmnt="SELECT ID,USERNAME,EMAIL,PASSWORD FROM USER";
        PreparedStatement preStmnt = con.prepareStatement(stmnt);
        ResultSet rs = preStmnt.executeQuery();
        List<User> list=new ArrayList<>();
        while(rs.next()){
            int id=Integer.parseInt(rs.getString("ID"));
            String name=rs.getString("USERNAME");
            String email=rs.getString("EMAIL");
            String password=(rs.getString("PASSWORD"));
            User user=new User(name,email,password);
            user.setId(id);
            list.add(user);
        }
        return list;
    }

    @Override
    public int deleteById(Connection con, Integer id) throws SQLException {
        String stmnt="DELETE FROM USER WHERE ID=?";
        PreparedStatement preStmnt = con.prepareStatement(stmnt);
        preStmnt.setInt(1,id);
        int res = preStmnt.executeUpdate();
        con.commit();
        preStmnt.close();
        return res;
    }
    public User findByUsernameAndPassword(Connection con,String username,String password)throws SQLException{
        String stmnt="SELECT ID,USERNAME,EMAIL,PASSWORD FROM USER WHERE USERNAME=? && PASSWORD=?";
        PreparedStatement preStmnt = con.prepareStatement(stmnt);
        preStmnt.setString(1,username);
        preStmnt.setString(2,password);
        ResultSet rs = preStmnt.executeQuery();
        String r_username = "",r_email = "",r_password = "";
        int id = 0;
        User user=null;
        if(rs.next()){
            id = rs.getInt("ID");
            r_username = rs.getString("USERNAME");
            r_email = rs.getString("EMAIL");
            r_password = rs.getString("PASSWORD");
            user = new User(r_username,r_email,r_password);
            user.setId(id);
        }
        return user;
    }
    public int findByUsername(Connection con,String username)throws SQLException{
        String stmnt="SELECT ID,USERNAME,EMAIL,PASSWORD FROM USER WHERE USERNAME=?";
        PreparedStatement preStmnt = con.prepareStatement(stmnt);
        preStmnt.setString(1,username);
        ResultSet rs = preStmnt.executeQuery();
        String r_username = "",r_email = "",r_password = "";
        int id = 0;
        User user=null;
        while(rs.next()){
            id = rs.getInt("ID");
            r_username = rs.getString("USERNAME");
            r_email = rs.getString("EMAIL");
            r_password = rs.getString("PASSWORD");
        }
        user = new User(r_username,r_email,r_password);
        user.setId(id);
        return user.getId();
    }
}
