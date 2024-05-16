package com.eva.SuperTraders.domain.dto;

import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShareDto {

    private Long id;

    private String symbol;

    private String name;

    private double price;

    private Long totalQuantity;

    private int remainingQuantity;

    private Date createDate;

    private Long createUserId;

    private Date updateDate;

    private Long updateUserId;

    private Long deleteUserId;

    private Date deleteDate;

    private Boolean isDeleted;
}
