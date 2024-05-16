package com.eva.SuperTraders.domain.entity;


import jakarta.persistence.*;


@Entity
@Table(name = "portfolio_items", schema = "super_traders")
public class PortfolioItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "share_id", nullable = false)
    private Share share;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "purchase_price", nullable = false)
    private double purchasePrice;

    // Constructors
    public PortfolioItem(Share share, int quantity, double price) {}

    public PortfolioItem(User user, Share share, int quantity, double purchasePrice) {
        this.user = user;
        this.share = share;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
    }

    // Getter and Setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Share getShare() {
        return share;
    }

    public void setShare(Share share) {
        this.share = share;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}
