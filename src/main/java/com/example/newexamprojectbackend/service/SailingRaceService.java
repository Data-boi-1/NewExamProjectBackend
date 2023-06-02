package com.example.newexamprojectbackend.service;

import com.example.newexamprojectbackend.model.SailingRace;
import com.example.newexamprojectbackend.repository.SailingRaceRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * Service providing business logic for SailingRace operations.
 */
@Service
public class SailingRaceService {

  // Sailing race repository
  private final SailingRaceRepo raceRepo;

  /**
   * Constructor for dependency injection.
   *
   * @param raceRepo Repository providing data operations for SailingRace entities.
   */
  public SailingRaceService(SailingRaceRepo raceRepo) {
    this.raceRepo = raceRepo;
  }

  /**
   * Adds a new sailing race.
   *
   * @param race New sailing race to add.
   * @return Response entity containing the added sailing race.
   */
  public ResponseEntity<SailingRace> addSailingRace(SailingRace race) {
    SailingRace newRace = raceRepo.save(race);
    return ResponseEntity.status(HttpStatus.CREATED).body(newRace);
  }

  /**
   * Lists all sailing races.
   *
   * @return List of all sailing races.
   */
  public List<SailingRace> listAllSailingRaces() {
    return raceRepo.findAll();
  }

  /**
   * Finds a sailing race by ID.
   *
   * @param id ID of the sailing race to find.
   * @return Optional containing the found sailing race, or empty if not found.
   */
  public Optional<SailingRace> findSailingRaceById(Long id) {
    return raceRepo.findById(id);
  }

  /**
   * Updates a sailing race.
   *
   * @param race Updated sailing race data.
   * @param id ID of the sailing race to update.
   * @return Response entity containing the updated sailing race.
   */
  public ResponseEntity<SailingRace> updateSailingRace(SailingRace race, Long id) {
    if (raceRepo.existsById(id)) {
      race.setId(id);
      SailingRace updatedRace = raceRepo.save(race);
      return ResponseEntity.ok(updatedRace);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  /**
   * Removes a sailing race.
   *
   * @param id ID of the sailing race to remove.
   * @return Response entity.
   */
  public ResponseEntity<Void> removeSailingRace(Long id) {
    if (raceRepo.existsById(id)) {
      raceRepo.deleteById(id);
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
