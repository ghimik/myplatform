package com.myplatform.myplatform.service;

import com.myplatform.myplatform.model.Block;
import com.myplatform.myplatform.repo.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockService {

    @Autowired
    private BlockRepository blockRepository;

    public Block createBlock(Integer pageId, String type, String content) {
        Block block = new Block();
        block.setPageId(pageId);
        block.setType(type);
        block.setContent(content);
        return blockRepository.save(block);
    }

    public List<Block> findBlocksByPageId(Integer pageId) {
        return blockRepository.findByPageId(pageId);
    }

    public void deleteBlock(Integer id) {
        blockRepository.deleteById(id);
    }
}
