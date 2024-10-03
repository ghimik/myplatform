package com.myplatform.myplatform.service;

import com.myplatform.myplatform.dto.WorkspaceDto;
import com.myplatform.myplatform.model.Workspace;
import com.myplatform.myplatform.repo.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<WorkspaceDto> getAllWorkspaces() {
        return workspaceRepository.findAll().stream().map(
                workspace -> {
                    WorkspaceDto workspaceDto = new WorkspaceDto();
                    workspaceDto.setName(workspace.getName());
                    workspaceDto.setOwnerId(workspace.getOwnerId());
                    return workspaceDto;
                }
        ).collect(Collectors.toUnmodifiableList());
    }
}
