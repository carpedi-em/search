package com.kakaobank.search.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "search_popular")
public class SearchPopular {
    public static final SearchPopular EMPTY = new SearchPopular();
    @Id
    private String id;
    @Builder.Default
    private int hit = 1;

    public SearchPopular updateHit() {
        this.hit++;
        return this;
    }

    public boolean isEmpty() {
        return EMPTY.equals(this);
    }
}
