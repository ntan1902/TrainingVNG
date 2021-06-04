package com.vng.ewallet.entity;

import com.vng.ewallet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("SELECT new com.vng.ewallet.dto.BankUser(u.userName, b.bankName, b.code ) " +
//            "FROM User u JOIN u.banks b")
//    List<BankUser> findBanks(Long id);
}
