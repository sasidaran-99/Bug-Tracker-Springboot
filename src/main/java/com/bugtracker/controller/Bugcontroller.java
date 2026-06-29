package com.bugtracker.controller;

import com.bugtracker.dto.BugRequestDTO;
import com.bugtracker.dto.BugResponseDTO;
import com.bugtracker.model.Bug;
import com.bugtracker.service.BugService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/bugs")
public class Bugcontroller {
    private BugService bs;
    public Bugcontroller(BugService bs){
        this.bs=bs;
    }
    @PostMapping
    public BugResponseDTO createbug(@Valid @RequestBody BugRequestDTO bugRequestDTO){
        return bs.createbug(bugRequestDTO);
    }
    @GetMapping
    public List<Bug> getallbugs(){
        return bs.getallbugs();
    }
    @DeleteMapping("/{id}")
    public String deletebug(@PathVariable Long id){
        return bs.deletebug(id);
    }
    @PutMapping("/{id}")
    public Bug updatebug(@PathVariable Long id,@Valid @RequestBody Bug bug){
         return bs.updatebug(id,bug);
    }
    @GetMapping("/page")
    public Page<Bug> pagination(@RequestParam int page, @RequestParam int size){
        return bs.pagination(page,size);
    }


}
