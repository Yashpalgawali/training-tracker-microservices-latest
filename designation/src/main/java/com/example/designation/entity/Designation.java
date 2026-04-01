package com.example.designation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Designation extends BaseEntity{

	@Id
	@SequenceGenerator(name="desig_seq",allocationSize = 1,initialValue = 1)
	@GeneratedValue(generator = "desig_seq" ,strategy = GenerationType.SEQUENCE)
	Long designationId;
	
	String designation;
}
