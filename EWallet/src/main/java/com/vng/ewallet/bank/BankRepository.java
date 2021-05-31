package com.vng.ewallet.bank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    @Query(value = "SELECT * FROM bank b WHERE b.code = ?1", nativeQuery = true)
    Bank findBankByCode(String code);
}
