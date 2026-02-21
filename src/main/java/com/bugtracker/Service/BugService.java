package com.bugtracker.Service;

import com.bugtracker.model.Bug;
import com.bugtracker.Repository.BugRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

@Service
public class BugService {
    private final BugRepository br;

    public BugService(BugRepository br){
        this.br=br;
    }
    public Bug createbug(Bug bug){
        return br.save(bug);
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
