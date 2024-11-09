package com.myplatform.myplatform.service;

import com.myplatform.myplatform.dto.PermissionDto;
import com.myplatform.myplatform.model.Permission;
import com.myplatform.myplatform.repo.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public void createPermission(PermissionDto permissionDto) {
        Permission permission = new Permission();
        permission.setUserId(permissionDto.getUserId());
        permission.setWorkspaceId(permissionDto.getWorkspaceId());
        permission.setPermissionType(permissionDto.getPermissionType());

        permissionRepository.save(permission);
    }
}
