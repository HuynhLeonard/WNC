package com.wnc.gRPC.service;

import com.wnc.gRPC.ActorCrud;
import com.wnc.gRPC.ActorCrudServiceGrpc;
import com.wnc.gRPC.entity.Actor;
import com.wnc.gRPC.repository.ActorRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@GrpcService
public class actorService extends ActorCrudServiceGrpc.ActorCrudServiceImplBase {
    @Autowired
    private ActorRepository actorRepository;

    @Override
    public void readAll(ActorCrud.Empty request, StreamObserver<ActorCrud.ActorList> responseObserver) {
        List<Actor> actors = actorRepository.findAll();
        List<ActorCrud.Actor> actorProto = actors.stream()
                .map(actor -> ActorCrud.Actor.newBuilder()
                        .setId(actor.getId())
                        .setFirstName(actor.getFirstName())
                        .setLastName(actor.getLastName())
                        .setLastUpdate(actor.getLastUpdate().toString()).build()).toList();
        ActorCrud.ActorList response = ActorCrud.ActorList.newBuilder()
                .addAllActor(actorProto).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void readActorById(ActorCrud.ActorID request, StreamObserver<ActorCrud.Actor> responseObserver) {
        int actorID = request.getActorID();

        Optional<Actor> actorOptional = actorRepository.findById(actorID);

        Actor actor = actorOptional.orElseThrow(() -> new RuntimeException("Actor not found"));

        ActorCrud.Actor response = ActorCrud.Actor.newBuilder()
                .setId(actor.getId())
                .setFirstName(actor.getFirstName())
                .setLastName(actor.getLastName())
                .setLastUpdate(actor.getLastUpdate().toString())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void updateActor(ActorCrud.ActorUpdateRequest request, StreamObserver<ActorCrud.Actor> responseObserver) {
        int actorID = request.getActorID();
        if (actorID <= 0) {
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Invalid Actor ID").asRuntimeException());
            return;
        }

        Optional<Actor> existingActorOptional = actorRepository.findById(actorID);

        if (existingActorOptional.isEmpty()) {
            responseObserver.onError(Status.NOT_FOUND.withDescription("Actor not found").asRuntimeException());
            return;
        }

        Actor existingActor = existingActorOptional.get();

        if (request.getFirstName() != null && !request.getFirstName().isEmpty()) {
            existingActor.setFirstName(request.getFirstName());
        }

        if (request.getLastName() != null && !request.getLastName().isEmpty()) {
            existingActor.setLastName(request.getLastName());
        }

        existingActor.setLastUpdate(LocalDateTime.now());

        Actor updatedActor = actorRepository.save(existingActor);
        ActorCrud.Actor response = ActorCrud.Actor.newBuilder()
                .setId(updatedActor.getId())
                .setFirstName(updatedActor.getFirstName())
                .setLastName(updatedActor.getLastName())
                .setLastUpdate(updatedActor.getLastUpdate().toString())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
