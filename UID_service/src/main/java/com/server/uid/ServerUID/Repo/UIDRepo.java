package com.server.uid.ServerUID.Repo;

import com.server.uid.ServerUID.Model.UID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UIDRepo extends JpaRepository<UID,Integer> {
}
