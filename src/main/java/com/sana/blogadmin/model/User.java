package com.sana.blogadmin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address = new Address();
    private Geo geo = new Geo();
    private String phone;
    private String website;
    private Company company = new Company();
}
