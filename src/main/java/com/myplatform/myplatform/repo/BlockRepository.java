package com.myplatform.myplatform.repo;

import com.myplatform.myplatform.model.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlockRepository extends JpaRepository<Block, Integer> {

    List<Block> findByPageId(Integer pageId);

}
