package com.wellsfargo.counselor.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Portfolio {
    @Id
    @GeneratedValue
    private long portfolioId;

    @OneToOne
    @JoinColumn(name = "clientId", nullable = false, unique = true)
    private Client client;

    @Column(nullable = false)
    private LocalDate creationDate;

    @OneToMany(mappedBy = "portfolio")
    private List<Security> securities = new ArrayList<>();

    protected Portfolio() {

    }

    public Portfolio(Client client, LocalDate creationDate) {
        this.client = client;
        this.creationDate = creationDate;
    }

    public Long getPortfolioId() {
        return portfolioId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public List<Security> getSecurities() {
        return securities;
    }

    public void addSecurity(Security security) {
        securities.add(security);
        security.setPortfolio(this);
    }

    public void removeSecurity(Security security) {
        securities.remove(security);
        security.setPortfolio(null);
    }
}
