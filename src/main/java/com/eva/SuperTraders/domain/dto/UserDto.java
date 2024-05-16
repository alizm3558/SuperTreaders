package com.eva.SuperTraders.domain.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String username;

    private String password;

    private Date createDate;

    private Long createUserId;

    private Date updateDate;

    private Long updateUserId;

    private Long deleteUserId;

    private Date deleteDate;

    private Boolean isDeleted;
}
