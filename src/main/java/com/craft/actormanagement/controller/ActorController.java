package com.craft.actormanagement.controller;

import com.craft.actormanagement.model.Actor;
import com.craft.actormanagement.service.ActorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Actor createActor(@Valid @RequestBody Actor actor) {
        return actorService.createActor(actor);
    }

    @GetMapping
    public List<Actor> getAllActors(@RequestParam(required = false) String name) {
        if (name != null && !name.isBlank()) {
            return actorService.searchByName(name);
        }
        return actorService.getAllActors();
    }

    @GetMapping("/{id}")
    public Actor getActorById(@PathVariable Long id) {
        return actorService.getActorById(id);
    }

    @PutMapping("/{id}")
    public Actor updateActor(@PathVariable Long id, @Valid @RequestBody Actor actor) {
        return actorService.updateActor(id, actor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteActor(@PathVariable Long id) {
        actorService.deleteActor(id);
    }
}