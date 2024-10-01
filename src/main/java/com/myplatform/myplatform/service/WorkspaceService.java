package com.myplatform.myplatform.service;

import com.myplatform.myplatform.model.Workspace;
import com.myplatform.myplatform.repo.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkspaceService {

    @Autowired
    private WorkspaceRepository workspaceRepository;

    public Workspace createWorkspace(Integer ownerId, String name) {
        Workspace workspace = new Workspace();
        workspace.setOwnerId(ownerId);
        workspace.setName(name);
        return workspaceRepository.save(workspace);
    }

    public List<Workspace> findWorkspacesByOwnerId(Integer ownerId) {
        return workspaceRepository.findByOwnerId(ownerId);
    }
}
