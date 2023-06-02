package com.example.newexamprojectbackend.controller;

import com.example.newexamprojectbackend.model.SailingRace;
import com.example.newexamprojectbackend.service.SailingRaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for the SailingRace API.
 */
@RestController
@RequestMapping("/sailingraces")
@CrossOrigin("*")
public class SailingRaceController {

  // Sailing race service
  private final SailingRaceService raceService;

  /**
   * Constructor for dependency injection.
   *
   * @param raceService Service providing business logic for SailingRace operations.
   */
  public SailingRaceController(SailingRaceService raceService) {
    this.raceService = raceService;
  }

  /**
   * Endpoint for adding a new sailing race.
   *
   * @param race New sailing race to add.
   * @return Response entity containing the added sailing race.
   */
  @PostMapping
  public ResponseEntity<SailingRace> addSailingRace(@RequestBody SailingRace race) {
    return raceService.addSailingRace(race);
  }

  /**
   * Endpoint for listing all sailing races.
   *
   * @return List of all sailing races.
   */
  @GetMapping
  public List<SailingRace> listAllSailingRaces() {
    return raceService.listAllSailingRaces();
  }

  /**
   * Endpoint for finding a sailing race by ID.
   *
   * @param id ID of the sailing race to find.
   * @return Response entity containing the found sailing race.
   */
  @GetMapping("/{id}")
  public ResponseEntity<SailingRace> findSailingRaceById(@PathVariable Long id) {
    return raceService.findSailingRaceById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Endpoint for updating a sailing race.
   *
   * @param id ID of the sailing race to update.
   * @param race Updated sailing race data.
   * @return Response entity containing the updated sailing race.
   */
  @PutMapping("/{id}")
  public ResponseEntity<SailingRace> updateSailingRace(@PathVariable Long id, @RequestBody SailingRace race) {
    return raceService.updateSailingRace(race, id);
  }

  /**
   * Endpoint for removing a sailing race.
   *
   * @param id ID of the sailing race to remove.
   * @return Response entity.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> removeSailingRace(@PathVariable Long id) {
    return raceService.removeSailingRace(id);
  }
}
