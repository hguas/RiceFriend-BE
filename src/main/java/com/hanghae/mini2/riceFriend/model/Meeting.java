package com.hanghae.mini2.riceFriend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "meeting")
public class Meeting extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String title;

    public String content;

    public LocalDateTime date;

    public int limitMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restuarant_id")
    public Restaurant restaurant;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"meeting"})
    @OrderBy("id desc")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "meeting")
    @JsonIgnoreProperties({"meeting"})
    private List<MeetingUser> meetingUsers = new ArrayList<>();
}
