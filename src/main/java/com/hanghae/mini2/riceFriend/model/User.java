package com.hanghae.mini2.riceFriend.model;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "user")
public class User extends Timestamp{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String nickname;

    public String email;

    public String password;

    public String gender;

    @Enumerated(EnumType.STRING)
    public Role role;


}
