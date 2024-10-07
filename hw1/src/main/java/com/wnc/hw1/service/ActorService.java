package com.wnc.hw1.service;

import com.wnc.hw1.dto.ActorDTO;
import com.wnc.hw1.model.Actor;
import com.wnc.hw1.repository.ActorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ActorService {
    private final ActorRepository actorRepository;

    // requirement 1: View all
    public List<Actor> getAllActor(){
        return actorRepository.findAll();
    }

    // requirement 2: View single actor
    public Actor getSingleActor(Long id){
        return actorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Actor not found with id: " + id));
    }

    // requirement 3
    public Actor createNewActor(ActorDTO actorDTO){
        Actor newActor = new Actor();
        newActor.setFirst_name(actorDTO.getFirst_name());
        newActor.setLast_name(actorDTO.getLast_name());

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        newActor.setLastUpdate(now.format(formatter));
        actorRepository.save(newActor);
        return newActor;
    }

    // requirement 4
    public boolean deleteActor(Long id){
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Actor not found with id: " + id));
        if(actor != null) {
            actorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // requirement 5
    public Actor updateActor(Long actorId, ActorDTO actorDTO) {
        // Find the actor by ID or throw an exception if not found
        Actor actor = actorRepository.findById(actorId)
                .orElseThrow(() -> new EntityNotFoundException("Actor not found with id: " + actorId));

        // Update the actor details
        if(actorDTO.getFirst_name() != null && !actorDTO.getFirst_name().isEmpty()){
            actor.setFirst_name(actorDTO.getFirst_name());
        }

        if(actorDTO.getLast_name() != null && !actorDTO.getLast_name().isEmpty()) {
            actor.setLast_name(actorDTO.getLast_name());
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        actor.setLastUpdate(now.format(formatter));
        // Save the updated actor back to the repository
        return actorRepository.save(actor);
    }

}
