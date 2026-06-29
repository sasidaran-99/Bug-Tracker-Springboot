package com.bugtracker.service;

import com.bugtracker.model.Bug;
import com.bugtracker.repository.BugRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import com.bugtracker.dto.BugRequestDTO;
import com.bugtracker.dto.BugResponseDTO;

@Service
public class BugService {
    private final BugRepository br;

    public BugService(BugRepository br){
        this.br=br;
    }
    public BugResponseDTO createbug(BugRequestDTO bugRequestDTO){

        // Convert Request DTO -> Entity
        Bug bug = new Bug();

        bug.setTitle(bugRequestDTO.getTitle());
        bug.setDescription(bugRequestDTO.getDescription());
        bug.setPriority(bugRequestDTO.getPriority());
        bug.setStatus(bugRequestDTO.getStatus());

        // Save Entity
        Bug savedBug = br.save(bug);

        // Convert Entity -> Response DTO
        BugResponseDTO response = new BugResponseDTO();

        response.setId(savedBug.getId());
        response.setTitle(savedBug.getTitle());
        response.setDescription(savedBug.getDescription());
        response.setPriority(savedBug.getPriority());
        response.setStatus(savedBug.getStatus());

        return response;
    }
    public List<Bug> getallbugs(){
        return br.findAll();
    }
    public String deletebug(Long id){
        if(!br.existsById(id)){
            return " Not found";
        }
        br.deleteById(id);
        return "Bug deleted successfully";
    }

    public Bug updatebug(Long id,Bug update){
        Bug existing=br.findById(id).orElse(null);
        if(existing!=null){
            existing.setTitle(update.getTitle());
            existing.setDescription(update.getDescription());
            existing.setPriority(update.getPriority());
            existing.setStatus(update.getStatus());

            return br.save(existing);

        }
        return null;
    }
    public Page<Bug> pagination(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return br.findAll(pageable);
    }



}
