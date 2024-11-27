package com.wnc.gRPC.gRPC_server.repository;

import com.wnc.gRPC.gRPC_server.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {
}
