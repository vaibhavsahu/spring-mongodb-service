package com.vaibhav.example.springmongodbservice.document;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    private Gender gender;
    private int age;
    private String email;
    private int satisfaction;
}
