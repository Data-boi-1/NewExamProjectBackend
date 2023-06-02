package com.example.newexamprojectbackend.config;

import com.example.newexamprojectbackend.model.SailingBoat;
import com.example.newexamprojectbackend.model.SailingRace;
import com.example.newexamprojectbackend.model.SailingType;
import com.example.newexamprojectbackend.repository.SailingBoatRepo;
import com.example.newexamprojectbackend.repository.SailingRaceRepo;
import com.example.newexamprojectbackend.repository.SailingTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;


@Component
public class InitData implements CommandLineRunner {

  private final SailingBoatRepo sailingBoatRepo;
  private final SailingRaceRepo sailingRaceRepo;
  private final SailingTypeRepo sailingTypeRepo;


  @Autowired
  public InitData(SailingBoatRepo sailingBoatRepo, SailingRaceRepo sailingRaceRepo, SailingTypeRepo sailingTypeRepo) {
    this.sailingBoatRepo = sailingBoatRepo;
    this.sailingRaceRepo = sailingRaceRepo;
    this.sailingTypeRepo = sailingTypeRepo;
  }

  // Creates different types of sailing boats
  private void createDifferentSizedBoats() {
    SailingType longSailingBoat = new SailingType();
    longSailingBoat.setName("40ft+");
    sailingTypeRepo.save(longSailingBoat);

    SailingType mediumSailingBoat = new SailingType();
    mediumSailingBoat.setName("25-40ft");
    sailingTypeRepo.save(mediumSailingBoat);

    SailingType shortSailingBoat = new SailingType();
    shortSailingBoat.setName("below-25ft");
    sailingTypeRepo.save(shortSailingBoat);
  }

  // Creates sailing boats and associates them with their respective types
  private void createSailingBoats() {
    SailingType longSailingBoat = sailingTypeRepo.findByName("40ft+"); //type of boat
    SailingType mediumSailingBoat = sailingTypeRepo.findByName("25-40ft"); //type of boat
    SailingType shortSailingBoat = sailingTypeRepo.findByName("below-25ft"); //type of boat

    SailingBoat sailingBoat1 = new SailingBoat();
    sailingBoat1.setName("Short Sailing boat (Sailing boat 1)");
    sailingBoat1.setSailingType(longSailingBoat);
    sailingBoat1.setPoints(0);
    sailingBoatRepo.save(sailingBoat1);

    SailingBoat sailingBoat2 = new SailingBoat();
    sailingBoat2.setName("Medium sized sailing boat (Sailing boat 2)");
    sailingBoat2.setSailingType(mediumSailingBoat);
    sailingBoat2.setPoints(0);
    sailingBoatRepo.save(sailingBoat2);

    SailingBoat sailingBoat3 = new SailingBoat();
    sailingBoat3.setName("Largest sailing boat (Sailing boat 3");
    sailingBoat3.setSailingType(shortSailingBoat);
    sailingBoat3.setPoints(0);
    sailingBoatRepo.save(sailingBoat3);
  }

  // Creates sailing races on Wednesdays between May and October
  private void createSailingRaces() {
    LocalDate startDate = LocalDate.of(2023, 5, 1);
    LocalDate endDate = LocalDate.of(2023, 10, 1);

    LocalDate sailRaceDate = startDate.with(DayOfWeek.WEDNESDAY);
    int sailRaceCount = 0;

    while (!sailRaceDate.isAfter(endDate)) {
      SailingRace sailingRace = new SailingRace();
      sailingRace.setDate(sailRaceDate);
      sailingRaceRepo.save(sailingRace);
      sailRaceCount++;
      sailRaceDate = sailRaceDate.plusWeeks(1);
    }

    int numRaces = sailRaceCount * 3; // 3 times the number of Wednesdays
    System.out.println("Created " + numRaces + " races.");
  }




  @Override
  public void run(String... args) throws Exception {
    createSailingRaces();
    createDifferentSizedBoats();
    createSailingBoats();
  }

}
