package com.vng.ewallet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card")
public class Card implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    @NotNull(message = "User can not be undefined")
//    private User user;

    @Column(name = "card_name")
    @NotBlank(message = "Card name can not be blank")
    private String cardName;

    @Column(name = "code")
    @NotBlank(message = "Code can not be blank")
    private String code;


}
