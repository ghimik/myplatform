package com.myplatform.myplatform.dto;

import com.myplatform.myplatform.model.Workspace;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WorkspacesDto {

    private List<WorkspaceBrief> workspaces;

    public List<WorkspaceBrief> getWorkspaces() {
        return workspaces;
    }

    public void setWorkspaces(List<WorkspaceBrief> workspaces) {
        this.workspaces = workspaces;
    }

    public static WorkspacesDto fromWorkspaceList(List<Workspace> workspaces) {
        WorkspacesDto dto = new WorkspacesDto();
        dto.setWorkspaces(workspaces.stream().map(
                workspace -> {
                    WorkspaceBrief brief = new WorkspaceBrief();
                    brief.setWorkspaceTitle(workspace.getName());
                    brief.setWorkspaceId(workspace.getOwnerId());
                    return brief;
                })
                .collect(Collectors.toList()));
        return dto;
    }

    public static class WorkspaceBrief {
        private Integer workspaceId;

        private String workspaceTitle;

        public Integer getWorkspaceId() {
            return workspaceId;
        }

        public void setWorkspaceId(Integer workspaceId) {
            this.workspaceId = workspaceId;
        }

        public String getWorkspaceTitle() {
            return workspaceTitle;
        }

        public void setWorkspaceTitle(String workspaceTitle) {
            this.workspaceTitle = workspaceTitle;
        }
    }

}
