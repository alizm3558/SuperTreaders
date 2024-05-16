package com.eva.SuperTraders.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;


import java.util.Date;

@Entity
@Table(name = "shares", schema = "super_traders")
@Data
@DynamicUpdate
public class Share {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "symbol", unique = true, nullable = false)
    private String symbol;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "total_share_quantity", nullable = false) // Toplam hisse adeti
    private Long totalQuantity;

    @Column(name = "remaining_quantity", nullable = false) // Mevcut hisse adeti
    private int remainingQuantity;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", updatable = false, nullable = false)
    private Date createDate;

    @Column(name = "create_user_id")
    private Long createUserId;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date", insertable = false)
    private Date updateDate;

    @Column(name = "update_user_id")
    private Long updateUserId;

    @Column(name = "delete_user_id")
    private Long deleteUserId;

    @Column(name = "delete_date", insertable = false)
    private Date deleteDate;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

}
