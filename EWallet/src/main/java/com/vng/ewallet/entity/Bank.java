package com.vng.ewallet.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

//    @ManyToOne()
//    @JoinColumn(name = "user_id")
//    private User user;

}
