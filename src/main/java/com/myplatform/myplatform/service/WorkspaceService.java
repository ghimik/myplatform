package com.myplatform.myplatform.service;

import com.myplatform.myplatform.dto.WorkspaceDto;
import com.myplatform.myplatform.dto.WorkspacesDto;
import com.myplatform.myplatform.model.User;
import com.myplatform.myplatform.model.Workspace;
import com.myplatform.myplatform.repo.UserRepository;
import com.myplatform.myplatform.repo.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkspaceService {

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<WorkspaceDto> getWorkspacesByUserId(Integer userId) {
        List<Workspace> workspaces = workspaceRepository.findByOwnerId(userId);
        return workspaces.stream().map(WorkspaceService::convertToDto)
                .collect(Collectors.toList());
    }


    @Transactional
    public List<WorkspaceDto> getWorkspacesByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return getWorkspacesByUserId(user.getId());
    }

    @Transactional
    public WorkspaceDto getWorkspacesByWorkspaceId(Integer workspaceId) {
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow();
        return convertToDto(workspace);
    }

    @Transactional
    public WorkspacesDto getWorkspacesList(String username) {
        User user = userRepository.findByUsername(username);
        return WorkspacesDto.fromWorkspaceList(workspaceRepository.findByOwnerId(user.getId()));
    }

    @Transactional
    public WorkspaceDto getWorkspaceById(Integer workspaceId) {
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new RuntimeException("Workspace not found"));
        return convertToDto(workspace);
    }

    public void createWorkspace(String username, String name) {
        Workspace workspace = new Workspace();
        workspace.setName(name);
        User user = userRepository.findByUsername(username);
        workspace.setOwnerId(user.getId());
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
