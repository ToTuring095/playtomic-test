package com.playtomic.tests.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.playtomic.tests.wallet.entity.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long>{

	
}
