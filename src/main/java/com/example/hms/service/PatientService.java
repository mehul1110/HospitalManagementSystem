package com.example.hms.service;

import com.example.hms.dto.PatientDto;
import com.example.hms.entity.Patient;
import com.example.hms.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public void createPatient(PatientDto patientDto) {
        Patient patient = new Patient();
        patient.setName(patientDto.getName());
        patient.setAge(patientDto.getAge());
        patient.setGender(patientDto.getGender());
        patient.setDisease(patientDto.getDisease());
        patient.setAddress(patientDto.getAddress());
        patient.setPhoneNumber(patientDto.getPhoneNumber());

        patientRepository.save(patient);
    }

    public List<PatientDto> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public PatientDto getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return mapToDto(patient);
    }

    public void updatePatient(Long id, PatientDto patientDto) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        if(patientDto.getName() != null) patient.setName(patientDto.getName());
        if(patientDto.getAge() != null) patient.setAge(patientDto.getAge());
        if(patientDto.getGender() != null) patient.setGender(patientDto.getGender());
        if(patientDto.getDisease() != null) patient.setDisease(patientDto.getDisease());
        if(patientDto.getAddress() != null) patient.setAddress(patientDto.getAddress());
        if(patientDto.getPhoneNumber() != null) patient.setPhoneNumber(patientDto.getPhoneNumber());

        patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    private PatientDto mapToDto(Patient patient) {
        return new PatientDto(
                patient.getId(),
                patient.getName(),
                patient.getAge(),
                patient.getGender(),
                patient.getDisease(),
                patient.getAddress(),
                patient.getPhoneNumber()
        );
    }
}
