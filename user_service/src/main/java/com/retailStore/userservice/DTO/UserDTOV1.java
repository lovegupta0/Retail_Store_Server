package com.retailStore.userservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTOV1 {
    private String uid;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
}
