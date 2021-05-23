package com.vng.ewallet.bank;

import com.vng.ewallet.user.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bank")
public class Bank implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bank_name")
    @NotBlank(message = "Bank name can not be blank")
    private String bankName;

    @Column(name = "code", unique = true)
    @NotBlank(message = "Bank code can not be blank")
    private String code;

    @Column(name = "holder_name")
    @NotBlank(message = "Holder name can not be blank")
    private String holderName;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @NotNull(message = "User id can not be undefined")
    private User user;

}
