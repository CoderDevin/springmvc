package com.devin.dao.Impl;

import com.devin.dao.UserDao;
import com.devin.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<User> findUsers() {
        String sql = "select * from sys_user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    @Override
    public Long saveUser(User user) {
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String sql = " insert into sys_user values(?,?,?,?,?) ";
                PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setObject(1,null);
                preparedStatement.setString(2,user.getUsername());
                preparedStatement.setString(3,user.getEmail());
                preparedStatement.setString(4,user.getPassword());
                preparedStatement.setString(5,user.getPhoneNum());
                return preparedStatement;
            }
        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        template.update(preparedStatementCreator,generatedKeyHolder);
        long userId = generatedKeyHolder.getKey().longValue();
        return userId;
    }

    @Override
    public void saveUserIdAndRoleId(Long[] roleId, Long userId) {
        String sql = "insert into sys_user_role values(?,?)";
        for(Long roleIdEach:roleId){
            template.update(sql,userId,roleIdEach);
        }

    }

    @Override
    public void deleteUser(Long id) {
        String sql = " delete from sys_user_role where userId=? ";
        template.update(sql,id);

        String sql2 = "delete from sys_user where id=? ";
        template.update(sql2,id);
    }
}
