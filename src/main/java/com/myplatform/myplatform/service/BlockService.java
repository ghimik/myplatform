package com.myplatform.myplatform.service;

import com.myplatform.myplatform.dto.BlockDto;
import com.myplatform.myplatform.model.Block;
import com.myplatform.myplatform.repo.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    public BlockDto getBlockById(Integer id) {
        Block block = blockRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Block not found"));
        BlockDto blockDto = new BlockDto();
        blockDto.setContent(block.getContent());
        blockDto.setType(block.getType());
        blockDto.setPageId(block.getPageId());
        return blockDto;
    }

    public Block updateBlock(Integer id, String content) {
        Block block = blockRepository.findById(id).orElseThrow();
        block.setContent(content);
        return blockRepository.save(block);
    }


}
