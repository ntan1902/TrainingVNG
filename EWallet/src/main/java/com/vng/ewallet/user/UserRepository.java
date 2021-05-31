package com.vng.ewallet.user;

import com.vng.ewallet.dto.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("SELECT new com.vng.ewallet.dto.BankUser(u.userName, b.bankName, b.code ) " +
//            "FROM User u JOIN u.banks b")
//    List<BankUser> findBanks(Long id);
}
