package com.jd.pd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jd.pd.entity.Patient;

@Repository
@Transactional
public interface PatientRepository extends JpaRepository<Patient, String> {

}
