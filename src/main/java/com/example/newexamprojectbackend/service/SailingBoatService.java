package com.example.newexamprojectbackend.service;

import com.example.newexamprojectbackend.model.SailingBoat;
import com.example.newexamprojectbackend.model.SailingType;
import com.example.newexamprojectbackend.repository.SailingBoatRepo;
import com.example.newexamprojectbackend.repository.SailingTypeRepo;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Service
public class SailingBoatService {

  private final SailingBoatRepo boatRepo;
  private final SailingTypeRepo typeRepo;

  @Autowired
  public SailingBoatService(SailingBoatRepo boatRepo, SailingTypeRepo typeRepo) {
    this.boatRepo = boatRepo;
    this.typeRepo = typeRepo;
  }

  public ResponseEntity<SailingBoat> addSailingBoat(SailingBoat boat) {
    if (boatExists(boat)) {
      return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
    SailingBoat savedBoat = boatRepo.save(boat);
    return ResponseEntity.ok(savedBoat);
  }

  public List<SailingBoat> listAllSailingBoats() {
    return boatRepo.findAll();
  }

  public Optional<SailingBoat> findSailingBoatById(Long id) {
    return boatRepo.findById(id);
  }

  public ResponseEntity<SailingBoat> updateSailingBoat(SailingBoat boat, Long boatId) {
    if (boatExists(boatId)) {
      boat.setId(boatId);
      SailingBoat updatedBoat = boatRepo.save(boat);
      return ResponseEntity.ok(updatedBoat);
    }
    return ResponseEntity.notFound().build();
  }

  public ResponseEntity<SailingBoat> removeSailingBoat(Long boatId) {
    Optional<SailingBoat> boatOpt = boatRepo.findById(boatId);
    if (boatOpt.isPresent()) {
      SailingBoat boatToRemove = boatOpt.get();
      boatRepo.deleteById(boatId);
      return ResponseEntity.ok(boatToRemove);
    }
    return ResponseEntity.notFound().build();
  }

  public SailingBoat storeSailingBoat(SailingBoat boat) {
    return boatRepo.save(boat);
  }

  public ResponseEntity<SailingBoat> updateAllSailingBoats(List<SailingBoat> boats) {
    boats.stream()
        .filter(this::boatExists)
        .forEach(boatRepo::save);
    return ResponseEntity.ok().build();
  }

  public List<SailingType> listAllSailingTypes() {
    return typeRepo.findAll();
  }

  private boolean boatExists(SailingBoat boat) {
    return boatExists(boat.getId());
  }

  private boolean boatExists(Long id) {
    return id != null && boatRepo.existsById(id);
  }
}
