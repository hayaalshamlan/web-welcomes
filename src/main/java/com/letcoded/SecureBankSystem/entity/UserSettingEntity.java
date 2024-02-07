package com.letcoded.SecureBankSystem.entity;
import javax.persistence.*;

@Entity
@Table(name="user_settings")

public class UserSettingEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;

    private boolean receiveNewsLetter;

    private String preferredLanguage;


    @OneToOne
    @JoinColumn(name = "user_id")
    private  UserEntity user;


}
