package com.eva.SuperTraders.domain.dto;

import com.eva.SuperTraders.domain.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionHistoryDto {

    private Long id;

    private User user;

    private String type;

    private int quantity;

    private double price;

    private Date timestamp;

    private Date createDate;

    private Long createUserId;

    private Date updateDate;

    private Long updateUserId;

    private Long deleteUserId;

    private Date deleteDate;

    @JsonIgnore
    private Boolean isDeleted;
}
