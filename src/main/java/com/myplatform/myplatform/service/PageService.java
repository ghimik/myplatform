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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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
        page.setContent(Objects.requireNonNullElse(pageDto.getContent(), ""));

        if (pageDto.getPageBlocks() != null) {
            List<Block> blocks = page.getBlocks();
            for (Block block : blocks) {
                for (BlockDto blockDto : pageDto.getPageBlocks())
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
        Page page = getPageByFrontendId(pageId, workspaceId);

        if (Objects.isNull(page)) {
            throw new RuntimeException("Page not found");
        }

        pageRepository.delete(page);
    }

    @Transactional
    public void addPage(Workspace workspace, PageDto pageDto) {

        Page page = new Page();
        page.setTitle(pageDto.getTitle());
        page.setContent(pageDto.getContent());
        page.setFrontendId(Objects.requireNonNullElse(pageDto.getFrontendId(), 0));
        page.setWorkspace(workspace);

        if (pageDto.getPageBlocks() != null) {
            if (page.getBlocks() == null) {
                page.setBlocks(pageDto.getPageBlocks().stream()
                        .map(blockDto -> BlockService.convertToEntity(blockDto, page))
                        .collect(Collectors.toList()));
            } else {
                page.getBlocks().addAll(pageDto.getPageBlocks().stream()
                        .map(blockDto -> BlockService.convertToEntity(blockDto, page))
                        .collect(Collectors.toList()));
            }
        }

        workspace.getPages().add(page);
        pageRepository.saveAndFlush(page);
        workspaceRepository.saveAndFlush(workspace);

    }

    @Transactional
    public void addPages(Integer workspaceId, PagesDto pagesDto) {
        Optional<Workspace> workspaceOpt = workspaceRepository.findById(workspaceId);
        if (workspaceOpt.isEmpty()) {
            throw new RuntimeException("Workspace not found");
        }

        Workspace workspace = workspaceOpt.get();
        pagesDto.getPages().forEach(page -> addPage(workspace, page));
    }


    static PageDto convertToDto(Page page) {
        PageDto dto = new PageDto();
        dto.setTitle(page.getTitle());
        dto.setFrontendId(page.getFrontendId());
        dto.setContent(page.getContent());
        dto.setPageBlocks(page.getBlocks().stream()
                .map(block -> {
                    BlockDto blockDto = new BlockDto();
                    blockDto.setType(block.getType());
                    blockDto.setContent(block.getContent());
                    blockDto.setFrontendId(block.getFrontendId());
                    blockDto.setTitle(block.getTitle());
                    return blockDto;
                }).collect(Collectors.toList()));
        return dto;
    }


}
