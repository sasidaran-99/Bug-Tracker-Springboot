package com.bugtracker.Repository;
import com.bugtracker.model.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugRepository  extends JpaRepository<Bug,Long>  {
}
