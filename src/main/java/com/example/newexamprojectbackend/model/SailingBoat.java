package com.example.newexamprojectbackend.model;

import jakarta.persistence.*;

@Entity
public class SailingBoat {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private int points;
  private String name;

  @ManyToOne
  @JoinColumn(name = "Sailing_boat_type_id")
  private SailingType sailingType;

  @ManyToOne
  @JoinColumn(name = "Sailing_race_id")
  private SailingRace sailingRace;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getPoints() {
    return points;
  }

  public void setPoints(int points) {
    this.points = points;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public SailingType getSailingType() {
    return sailingType;
  }

  public void setSailingType(SailingType sailingType) {
    this.sailingType = sailingType;
  }

  public SailingRace getSailingRace() {
    return sailingRace;
  }

  public void setSailingRace(SailingRace sailingRace) {
    this.sailingRace = sailingRace;
  }
}