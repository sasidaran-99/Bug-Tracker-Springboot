package com.bugtracker.service;

import com.bugtracker.model.Bug;
import com.bugtracker.repository.BugRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import com.bugtracker.dto.BugRequestDTO;
import com.bugtracker.dto.BugResponseDTO;
import com.bugtracker.exception.BugNotFoundException;

@Service
public class BugService {
    private final BugRepository br;

    public BugService(BugRepository br) {
        this.br = br;
    }

    private BugResponseDTO mapToDTO(Bug bug) {
        BugResponseDTO response = new BugResponseDTO();
        response.setId(bug.getId());
        response.setTitle(bug.getTitle());
        response.setDescription(bug.getDescription());
        response.setPriority(bug.getPriority());
        response.setStatus(bug.getStatus());
        return response;
    }

    public BugResponseDTO createbug(BugRequestDTO bugRequestDTO) {
        Bug bug = new Bug();
        bug.setTitle(bugRequestDTO.getTitle());
        bug.setDescription(bugRequestDTO.getDescription());
        bug.setPriority(bugRequestDTO.getPriority());
        bug.setStatus(bugRequestDTO.getStatus());
        
        Bug savedBug = br.save(bug);
        return mapToDTO(savedBug);
    }

    public List<BugResponseDTO> getallbugs() {
        return br.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public BugResponseDTO getbugbyid(Long id) {
        Bug bug = br.findById(id).orElseThrow(() -> new BugNotFoundException("Bug not found with id " + id));
        return mapToDTO(bug);
    }

    public String deletebug(Long id) {
        Bug bug = br.findById(id).orElseThrow(() -> new BugNotFoundException("Bug not found with id " + id));
        br.delete(bug);
        return "Bug deleted successfully";
    }

    public BugResponseDTO updatebug(Long id, BugRequestDTO update) {
        Bug existing = br.findById(id).orElseThrow(() -> new BugNotFoundException("Bug not found with id " + id));
        
        existing.setTitle(update.getTitle());
        existing.setDescription(update.getDescription());
        existing.setPriority(update.getPriority());
        existing.setStatus(update.getStatus());
        
        return mapToDTO(br.save(existing));
    }

    public Page<BugResponseDTO> pagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return br.findAll(pageable).map(this::mapToDTO);
    }
}
