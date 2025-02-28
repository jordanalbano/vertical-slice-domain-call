package com.jordan.albano.verticalslice.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Embeddable
@AllArgsConstructor
public class PeriodEntity {
    private LocalDate startDate;
    private LocalDate endDate;
    public PeriodEntity(){

    }
}
