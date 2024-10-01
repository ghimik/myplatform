package com.myplatform.myplatform.repo;

import com.myplatform.myplatform.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {

    List<Permission> findByPageId(Integer pageId);

}
