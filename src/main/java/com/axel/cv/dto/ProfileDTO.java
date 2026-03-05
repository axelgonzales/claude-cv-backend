package com.axel.cv.dto;

import lombok.Data;

@Data
public class ProfileDTO {
    private Long id;
    private String name;
    private String title;
    private String summary;
    private String email;
    private String phone;
    private String linkedin;
    private String github;
    private String location;
}
