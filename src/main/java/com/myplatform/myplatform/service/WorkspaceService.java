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

    public List<WorkspaceDto> getWorkspacesByUserId(Integer userId) {
        List<Workspace> workspaces = workspaceRepository.findByOwnerId(userId);
        return workspaces.stream().map(WorkspaceService::convertToDto)
                .collect(Collectors.toList());
    }

    public WorkspaceDto getWorkspaceById(Integer workspaceId) {
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new RuntimeException("Workspace not found"));
        return convertToDto(workspace);
    }

    public void createWorkspace(Integer ownerId, String name) {
        Workspace workspace = new Workspace();
        workspace.setOwnerId(ownerId);
        workspace.setName(name);
        workspaceRepository.save(workspace);
    }

    static WorkspaceDto convertToDto(Workspace workspace) {
        WorkspaceDto dto = new WorkspaceDto();
        dto.setWorkspaceId(workspace.getId());
        dto.setOwnerId(workspace.getOwnerId());
        dto.setName(workspace.getName());
        dto.setPages(workspace.getPages().stream()
                .map(PageService::convertToDto)
                .collect(Collectors.toList()));
        return dto;
    }
}
