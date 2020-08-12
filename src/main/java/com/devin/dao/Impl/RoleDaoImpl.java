package com.devin.dao.Impl;

import com.devin.dao.RoleDao;
import com.devin.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public class RoleDaoImpl implements RoleDao {

    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Role> findRoles() {
        String sql = "select * from sys_role";
        List<Role> res = template.query(sql, new BeanPropertyRowMapper<Role>(Role.class));
        return res;
    }

    @Override
    public void addRole(Role role) {
        String sql = "insert sys_role values(?,?,?)";
        template.update(sql,null,role.getRoleName(),role.getRoleDesc());
    }

    @Override
    public void deleteRole(Role role) {
        String sql = "delete from sys_role where id=?";
        template.update(sql,role.getId());
    }

    @Override
    public List<Role> findRoleById(Long id) {
        String sql = "SELECT r.`roleName` FROM sys_user u,sys_user_role ur,sys_role r WHERE r.`id`=ur.`roleId` AND u.`id`=ur.`userId` AND u.id=?;";
        List<Role> roles = template.query(sql, new BeanPropertyRowMapper<Role>(Role.class), id);
        return roles;
    }
}
