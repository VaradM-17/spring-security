package com.ss.spring_security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ss.spring_security.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(String name);
}
