package com.wnc.gRPC.client_server.service;

import com.wnc.gRPC.ActorCrud;
import com.wnc.gRPC.ActorCrudServiceGrpc;
import com.wnc.gRPC.client_server.dto.ActorDTO;
import com.wnc.gRPC.gRPC_server.entity.Actor;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ClientActorService {
    private ActorCrudServiceGrpc.ActorCrudServiceBlockingStub stub;

    public ClientActorService() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", 9090).usePlaintext().build();
        this.stub = ActorCrudServiceGrpc.newBlockingStub(channel);
    }

    public List<Actor> getAllActors() {
        ActorCrud.ActorList actorList = stub.readAll(ActorCrud.Empty.newBuilder().build());
        return actorList.getActorList().stream()
                .map(actor -> new Actor(actor.getId(),
                        actor.getFirstName(),
                        actor.getLastName(),
                        actor.getLastUpdate())).toList();
    }

    public Actor getActorById(int id) {
        ActorCrud.Actor actor = stub.readActorById(ActorCrud.ActorID.newBuilder().setActorID(id).build());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss");
        return new Actor(actor.getId(),
                actor.getFirstName(),
                actor.getLastName(),
                actor.getLastUpdate());
    }

    public Actor updateActor(int id, ActorDTO actorProfile) {
        ActorCrud.Actor actor = stub.updateActor(ActorCrud.ActorUpdateRequest.newBuilder()
                .setActorID(id)
                .setFirstName(actorProfile.getFirstName())
                .setLastName(actorProfile.getLastName()).build());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss");
        return new Actor(actor.getId(),
                actor.getFirstName(),
                actor.getLastName(),
                LocalDateTime.now().format(formatter));
    }
}
