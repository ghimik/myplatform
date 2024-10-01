package com.myplatform.myplatform.service;

import com.myplatform.myplatform.model.Permission;
import com.myplatform.myplatform.repo.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public Permission setPermission(Integer userId, Integer pageId, String permissionType) {
        Permission permission = new Permission();
        permission.setUserId(userId);
        permission.setPageId(pageId);
        permission.setPermissionType(permissionType);
        return permissionRepository.save(permission);
    }

    public List<Permission> findPermissionsByPageId(Integer pageId) {
        return permissionRepository.findByPageId(pageId);
    }

    public void deletePermission(Integer id) {
        permissionRepository.deleteById(id);
    }
}
