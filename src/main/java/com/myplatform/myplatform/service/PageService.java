package com.myplatform.myplatform.service;

import com.myplatform.myplatform.dto.BlockDto;
import com.myplatform.myplatform.dto.PageDto;
import com.myplatform.myplatform.model.Block;
import com.myplatform.myplatform.model.Page;
import com.myplatform.myplatform.model.Workspace;
import com.myplatform.myplatform.repo.PageRepository;
import com.myplatform.myplatform.repo.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PageService {

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private WorkspaceRepository workspaceRepository;

    public PageDto getPageByIdAndWorkspace(Integer pageId, Integer workspaceId) {
        Optional<Page> pageOpt = pageRepository.findById(pageId);

        if (pageOpt.isEmpty() || !pageOpt.get().getWorkspace().getId().equals(workspaceId)) {
            throw new RuntimeException("Page not found");
        }

        Page page = pageOpt.get();
        return convertToDto(page);
    }

    public void updatePage(Integer pageId, Integer workspaceId, PageDto pageDto) {
        Optional<Page> pageOpt = pageRepository.findById(pageId);
        if (pageOpt.isEmpty() || !pageOpt.get().getWorkspace().getId().equals(workspaceId)) {
            throw new RuntimeException("Page not found");
        }

        Page page = pageOpt.get();
        page.setTitle(pageDto.getTitle());
        page.setContent("");

        if (pageDto.getPageBlocks() != null) {
            page.getBlocks().clear();
            page.getBlocks().addAll(pageDto.getPageBlocks().stream()
                    .map(blockDto -> {
                        Block block = new Block();
                        block.setPage(page);
                        block.setType(blockDto.getType());
                        block.setContent(blockDto.getContent());
                        return block;
                    }).collect(Collectors.toList()));
        }

        pageRepository.save(page);
    }

    public void deletePage(Integer pageId, Integer workspaceId) {
        Optional<Page> pageOpt = pageRepository.findById(pageId);
        if (pageOpt.isEmpty() || !pageOpt.get().getWorkspace().getId().equals(workspaceId)) {
            throw new RuntimeException("Page not found");
        }

        pageRepository.delete(pageOpt.get());
    }

    static PageDto convertToDto(Page page) {
        PageDto dto = new PageDto();
        dto.setTitle(page.getTitle());
        dto.setPageBlocks(page.getBlocks().stream()
                .map(block -> {
                    BlockDto blockDto = new BlockDto();
                    blockDto.setType(block.getType());
                    blockDto.setContent(block.getContent());
                    return blockDto;
                }).collect(Collectors.toList()));
        return dto;
    }
}
