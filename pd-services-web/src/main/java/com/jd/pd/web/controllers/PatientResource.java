package com.jd.pd.web.controllers;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jd.pd.model.Patient;
import com.jd.pd.repository.PatientRepository;

@RestController
@RequestMapping("/patient")
public class PatientResource {

	@Autowired
	private PatientRepository patientRepo;

	@RequestMapping(method = RequestMethod.GET, value = "/{patient-id}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	Patient getByID(@PathVariable("patient-id") String patientId) {
		return patEntityToModelmapper.apply((patientRepo.findById(patientId)).get());
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	Collection<Patient> search(@RequestParam("q") String searchTerm) {
		return patientRepo.findAll().stream().map(patEntityToModelmapper).collect(Collectors.toList());
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	Patient create(@RequestBody Patient patient) {
		com.jd.pd.entity.Patient patientEntity = patModelToEntityMapper.apply(patient);
		return patEntityToModelmapper.apply(patientRepo.save(patientEntity));
	}

	private Function<Patient, com.jd.pd.entity.Patient> patModelToEntityMapper = (patientModel) -> {
		com.jd.pd.entity.Patient patientEntity = new com.jd.pd.entity.Patient();
		patientEntity.setPatientId(patientModel.getPatientId());
		patientEntity.setFirstName(patientModel.getFirstName());
		patientEntity.setLastname(patientModel.getLastName());
		patientEntity.setAge(patientModel.getAge());
		patientEntity.setEmail(patientModel.getEmail());
		return patientEntity;
	};

	private Function<com.jd.pd.entity.Patient, Patient> patEntityToModelmapper = (patientEntity) -> {
		Patient patientModel = new Patient();
		patientModel.setPatientId(patientEntity.getPatientId());
		patientModel.setFirstName(patientEntity.getFirstName());
		patientModel.setLastname(patientEntity.getLastname());
		patientModel.setAge(patientEntity.getAge());
		patientModel.setEmail(patientEntity.getEmail());
		return patientModel;
	};
}
