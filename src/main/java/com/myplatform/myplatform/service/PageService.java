package com.myplatform.myplatform.service;

import com.myplatform.myplatform.dto.BlockDto;
import com.myplatform.myplatform.dto.PageDto;
import com.myplatform.myplatform.dto.PagesDto;
import com.myplatform.myplatform.model.Block;
import com.myplatform.myplatform.model.Page;
import com.myplatform.myplatform.model.Workspace;
import com.myplatform.myplatform.repo.PageRepository;
import com.myplatform.myplatform.repo.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PageService {

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private WorkspaceRepository workspaceRepository;


    public Page getPageByFrontendId(Integer frontendId, Integer workspaceId) {
        Optional<Page> pageOpt = pageRepository.findByFrontendIdAndWorkspaceId(frontendId, workspaceId);

        if (pageOpt.isEmpty()) {
            throw new RuntimeException("Page not found");
        }

        return pageOpt.get();
    }


    public PageDto getPageByIdAndWorkspace(Integer pageId, Integer workspaceId) {
        Optional<Page> pageOpt = pageRepository.findById(pageId);

        if (pageOpt.isEmpty() || !pageOpt.get().getWorkspace().getId().equals(workspaceId)) {
            throw new RuntimeException("Page not found");
        }

        Page page = pageOpt.get();
        return convertToDto(page);
    }

    public void updatePage(Integer pageId, Integer workspaceId, PageDto pageDto) {


        Page page = getPageByFrontendId(pageId, workspaceId);
        page.setTitle(pageDto.getTitle());
        page.setContent("");

        if (pageDto.getPageBlocks() != null) {
            List<Block> blocks = page.getBlocks();
            for (Block block : blocks) {
                for (BlockDto blockDto: pageDto.getPageBlocks())
                    if (block.getFrontendId().equals(blockDto.getFrontendId())) {
                        block.setContent(blockDto.getContent());
                        block.setTitle(blockDto.getTitle());
                        block.setType(blockDto.getTitle());
                    }
            }
            page.setBlocks(blocks);
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

    public void addPage(Integer workspaceId, PageDto pageDto) {
        Optional<Workspace> workspaceOpt = workspaceRepository.findById(workspaceId);
        if (workspaceOpt.isEmpty()) {
            throw new RuntimeException("Workspace not found");
        }

        Workspace workspace = workspaceOpt.get();
        Page page = new Page();
        page.setTitle(pageDto.getTitle());
        page.setContent("");

        if (pageDto.getPageBlocks() != null) {
            page.getBlocks().addAll(pageDto.getPageBlocks().stream()
                    .map(blockDto -> BlockService.convertToEntity(blockDto, page))
                    .collect(Collectors.toList()));
        }

        workspace.getPages().add(page);
        pageRepository.save(page);
        workspaceRepository.save(workspace);
    }

    public void addPages(Integer workspaceId, PagesDto pagesDto) {
        pagesDto.getPages().forEach(page -> addPage(workspaceId, page));
    }


    static PageDto convertToDto(Page page) {
        PageDto dto = new PageDto();
        dto.setTitle(page.getTitle());
        dto.setFrontendId(page.getFrontendId());
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
