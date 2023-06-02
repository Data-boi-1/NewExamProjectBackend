package com.example.newexamprojectbackend.repository;
import com.example.newexamprojectbackend.model.SailingType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SailingTypeRepo extends JpaRepository<SailingType, Long> {
  SailingType findByName(String s);
}
