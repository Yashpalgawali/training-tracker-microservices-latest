package com.example.designation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.designation.entity.Designation;

@Repository("desigrepo")
public interface DesignationRepository extends JpaRepository<Designation, Long> {

	Optional<Designation> findByDesignation(String designation);

	@Query("UPDATE Designation d SET d.designation=:designation WHERE d.designationId=:desigId")
	@Modifying
	public int updateDesignation(Long desigId, String designation);
}
