package com.eva.SuperTraders.domain.dto;

import com.eva.SuperTraders.domain.entity.PortfolioItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String username;

    private String password;

    private Set<PortfolioItem> portfolio = new HashSet<>();
}
