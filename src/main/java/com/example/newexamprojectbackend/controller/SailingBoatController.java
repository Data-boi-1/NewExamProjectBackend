package com.example.newexamprojectbackend.controller;

import com.example.newexamprojectbackend.model.SailingBoat;
import com.example.newexamprojectbackend.model.SailingRace;
import com.example.newexamprojectbackend.model.SailingType;
import com.example.newexamprojectbackend.service.SailingBoatService;
import com.example.newexamprojectbackend.service.SailingRaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for the SailingBoat API.
 */
@RestController
@RequestMapping("/sailingboats")
@CrossOrigin("*")
public class SailingBoatController {

  // Sailing boat service
  private final SailingBoatService boatService;
  private final SailingRaceService raceService;

  /**
   * Constructor for dependency injection.
   *
   * @param boatService Service providing business logic for SailingBoat operations.
   */
  public SailingBoatController(SailingBoatService boatService, SailingRaceService raceService){
    this.boatService = boatService;
    this.raceService = raceService;
  }

  /**
   * Endpoint for adding a new sailing boat.
   *
   * @param boat New sailing boat to add.
   * @return Response entity containing the added sailing boat.
   */
  @PostMapping
  public ResponseEntity<SailingBoat> addSailingBoat(@RequestBody SailingBoat boat) {
    return boatService.addSailingBoat(boat);
  }

  /**
   * Endpoint for listing all sailing boats.
   *
   * @return List of all sailing boats.
   */
  @GetMapping
  public List<SailingBoat> listAllSailingBoats() {
    return boatService.listAllSailingBoats();
  }

  /**
   * Endpoint for finding a sailing boat by ID.
   *
   * @param id ID of the sailing boat to find.
   * @return Response entity containing the found sailing boat.
   */
  @GetMapping("/{id}")
  public ResponseEntity<SailingBoat> findSailingBoatById(@PathVariable Long id) {
    return boatService.findSailingBoatById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Endpoint for updating a sailing boat.
   *
   * @param id ID of the sailing boat to update.
   * @param boat Updated sailing boat data.
   * @return Response entity containing the updated sailing boat.
   */
  @PutMapping("/{id}")
  public ResponseEntity<SailingBoat> updateSailingBoat(@PathVariable Long id, @RequestBody SailingBoat boat) {
    return boatService.updateSailingBoat(boat, id);
  }

  /**
   * Endpoint for removing a sailing boat.
   *
   * @param id ID of the sailing boat to remove.
   * @return Response entity containing the removed sailing boat.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<SailingBoat> removeSailingBoat(@PathVariable Long id) {
    return boatService.removeSailingBoat(id);
  }

  /**
   * Endpoint for listing all sailing types.
   *
   * @return List of all sailing types.
   */
  @GetMapping("/sailingtypes")
  public List<SailingType> listAllSailingTypes() {
    return boatService.listAllSailingTypes();
  }
  @GetMapping("/sailingraces")
  public List<SailingRace> listAllSailingRaces() {
    return raceService.listAllSailingRaces();
  }
}
