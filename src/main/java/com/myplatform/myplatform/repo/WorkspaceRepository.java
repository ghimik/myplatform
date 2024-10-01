package com.myplatform.myplatform.repo;

import com.myplatform.myplatform.model.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Integer> {

    List<Workspace> findByOwnerId(Integer ownerId);

}
