package com.vng.ewallet.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    @NotBlank(message = "Name can not be blank")
    private String userName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(targetEntity = Bank.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Bank> banks;

    @OneToOne(targetEntity = Card.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private Card card;

}