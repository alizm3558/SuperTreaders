package com.eva.SuperTraders.domain.dto;

import com.eva.SuperTraders.domain.entity.User;
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

    private String type; // AL/SAT

    private int quantity;

    private double price;

    private Date timestamp;

    private Date createDate;

    private Long createUserId;

    private Date updateDate;

    private Long updateUserId;

    private Long deleteUserId;

    private Date deleteDate;

    private Boolean isDeleted;
}
