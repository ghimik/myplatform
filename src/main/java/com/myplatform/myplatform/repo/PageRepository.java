package com.myplatform.myplatform.repo;

import com.myplatform.myplatform.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PageRepository extends JpaRepository<Page, Integer> {

    Optional<Page> findByFrontendIdAndWorkspaceId(Integer frontendId, Integer workspaceId);


}
