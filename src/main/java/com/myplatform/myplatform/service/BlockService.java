package com.myplatform.myplatform.service;

import com.myplatform.myplatform.dto.BlockDto;
import com.myplatform.myplatform.model.Block;
import com.myplatform.myplatform.model.Page;
import com.myplatform.myplatform.repo.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockService {

    @Autowired
    private PageService pageService;

    @Autowired
    private BlockRepository blockRepository;

    public void updateBlock(Integer blockId, Integer pageId, Integer workspaceId, BlockDto blockDto) {
        Page page = pageService.getPageByFrontendId(pageId, workspaceId);
        Block block = page.getBlocks().get(blockId);
        block.setContent(block.getContent());
        block.setType(blockDto.getType());
        block.setTitle(blockDto.getTitle());
        blockRepository.save(block);
    }


    static BlockDto convertToDto(Block block) {
        BlockDto blockDto = new BlockDto();
        blockDto.setType(block.getType());
        blockDto.setContent(block.getContent());
        blockDto.setTitle(block.getTitle());
        blockDto.setFrontendId(block.getFrontendId()); // Новое поле
        return blockDto;
    }

    static Block convertToEntity(BlockDto blockDto, Page page) {
        Block block = new Block();
        block.setPage(page);
        block.setType(blockDto.getType());
        block.setContent(blockDto.getContent());
        block.setTitle(blockDto.getTitle());
        block.setFrontendId(blockDto.getFrontendId()); // Новое поле
        return block;
    }

}
