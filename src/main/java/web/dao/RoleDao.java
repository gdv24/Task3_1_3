package web.dao;

import web.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    List<Role> getAllRoles ();
    Set<Role> getByIdRoles(List<Long> ids);
    Set<Role> getByNameRoles(String nameRoles);
}
