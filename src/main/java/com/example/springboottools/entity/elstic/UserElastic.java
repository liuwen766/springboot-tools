package com.example.springboottools.entity.elstic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserElastic {
    String id;
    String name;
    String address;
    int age;
}
