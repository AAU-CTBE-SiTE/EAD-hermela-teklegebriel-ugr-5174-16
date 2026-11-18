package com.craft.actormanagement.repo;

import com.craft.actormanagement.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepo extends JpaRepository<Actor, Long> {

    List<Actor> findByNameContainingIgnoreCase(String name);
}