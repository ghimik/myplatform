package com.myplatform.myplatform.service;

import com.myplatform.myplatform.dto.BlockDto;
import com.myplatform.myplatform.model.Block;
import com.myplatform.myplatform.model.Page;
import com.myplatform.myplatform.repo.BlockRepository;
import com.myplatform.myplatform.repo.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlockService {

    @Autowired
    private PageService pageService;

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private PageRepository pageRepository;

    public void updateBlock(Integer blockId, Integer pageId, Integer workspaceId, BlockDto blockDto) {
        Page page = pageService.getPageByFrontendId(pageId, workspaceId);
        Block block = page.getBlocks().stream().filter(
                block1 -> block1.getFrontendId().equals(blockId)
        ).findAny().orElseThrow(() -> new RuntimeException("Block not found"));

        block.setContent(blockDto.getContent());
        block.setType(blockDto.getType());
        block.setTitle(blockDto.getTitle());
        blockRepository.save(block);
    }

    @Transactional
    public void deleteBlock(Integer blockId, Integer pageId, Integer workspaceId) {
        Page page = pageService.getPageByFrontendId(pageId, workspaceId);

        if (page.getBlocks() != null) {
            Block block = page.getBlocks().stream().filter(
                    block1 -> block1.getFrontendId().equals(blockId)
            ).findAny().orElseThrow(() -> new RuntimeException("Block not found"));

            page.getBlocks().remove(block);
            blockRepository.delete(block);
            blockRepository.flush();
            pageRepository.saveAndFlush(page);
        }
        else {
           throw new RuntimeException("No blocks for page");
        }
    }

    public BlockDto createBlock(Integer pageId, Integer workspaceId, BlockDto blockDto) {
        Page page = pageService.getPageByFrontendId(pageId, workspaceId);

        // Конвертация DTO в сущность
        Block block = BlockService.convertToEntity(blockDto, page);

        // Сохранение блока
        Block savedBlock = blockRepository.save(block);

        // Конвертация сущности в DTO
        return BlockService.convertToDto(savedBlock);
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
        var frontid = blockDto.getFrontendId();
        if (frontid == null)
            frontid = -1;

        Block block = new Block();
        block.setPage(page);
        block.setType(blockDto.getType());
        block.setContent(blockDto.getContent());
        block.setTitle(blockDto.getTitle());
        block.setFrontendId(frontid); // Новое поле
        return block;
    }

}
