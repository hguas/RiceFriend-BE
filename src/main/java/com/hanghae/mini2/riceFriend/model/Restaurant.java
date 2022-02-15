package com.hanghae.mini2.riceFriend.model;


import com.hanghae.mini2.riceFriend.dto.request.MeetingRequestDto;
import com.hanghae.mini2.riceFriend.dto.response.MeetingResonseDto;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "restaurant")
@ApiModel(value = "RESTAURANT_TABLE", description = "음식점 TABLE")
public class Restaurant extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    // 양방향 매핑
    @OneToOne(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Meeting meeting;

    // 음식점 테이블 UPDATE
    public void updateRestaurant(MeetingRequestDto requestDto, Location location) {
        this.name = requestDto.getRestaurantName();
        this.imageUrl = requestDto.getRestaurantUrl();
        this.location = location;
    }
}