package com.axel.cv.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "educations")
@Data
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String institution;
    private String degree;
    private String field;
    private String startYear;
    private String endYear;
    private int displayOrder;
}
