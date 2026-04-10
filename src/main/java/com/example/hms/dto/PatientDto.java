package com.example.hms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {
    private Long id;
    private String name;
    private Integer age;
    private String gender;
    private String disease;
    private String address;
    private String phoneNumber;
}
