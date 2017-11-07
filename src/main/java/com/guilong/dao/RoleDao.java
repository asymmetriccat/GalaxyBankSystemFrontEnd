package com.guilong.dao;

import org.springframework.data.repository.CrudRepository;

import com.guilong.domain.security.Role;

public interface RoleDao extends CrudRepository<Role, Integer>{
   Role findByName(String name);
}
