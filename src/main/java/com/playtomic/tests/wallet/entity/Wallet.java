package com.playtomic.tests.wallet.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


@Entity
@Data
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @Column
    private BigDecimal balance;
    
}
