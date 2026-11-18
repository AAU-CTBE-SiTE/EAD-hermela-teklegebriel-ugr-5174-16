package com.craft.actormanagement.service;

import com.craft.actormanagement.model.Actor;
import com.craft.actormanagement.repo.ActorRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ActorService {

    private final ActorRepo actorRepo;

    public ActorService(ActorRepo actorRepo) {
        this.actorRepo = actorRepo;
    }

    public Actor createActor(Actor actor) {
        actor.setId(null);
        return actorRepo.save(actor);
    }

    public List<Actor> getAllActors() {
        return actorRepo.findAll();
    }

    public Actor getActorById(Long id) {
        return actorRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Actor with id " + id + " not found"));
    }

    public List<Actor> searchByName(String name) {
        return actorRepo.findByNameContainingIgnoreCase(name);
    }

    public Actor updateActor(Long id, Actor updated) {
        Actor existing = getActorById(id);
        existing.setName(updated.getName());
        existing.setAge(updated.getAge());
        existing.setGender(updated.getGender());
        existing.setNationality(updated.getNationality());
        return actorRepo.save(existing);
    }

    public void deleteActor(Long id) {
        Actor existing = getActorById(id);
        actorRepo.delete(existing);
    }
}