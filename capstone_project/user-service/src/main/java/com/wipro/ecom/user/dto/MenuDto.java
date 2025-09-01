package com.wipro.ecom.user.dto;

import com.wipro.ecom.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuDto {

    private String label;
    private String route;
    private Set<String> rolesAllowed;
}
