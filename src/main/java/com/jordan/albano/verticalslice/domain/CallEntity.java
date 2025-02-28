package com.jordan.albano.verticalslice.domain;

import
        jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "calls")
@Getter
@Setter
public class CallEntity extends Lifecycle {
    private String name;
    private int academicYear;
    @Embedded
    private PeriodEntity period;
    @Enumerated(EnumType.STRING)
    private CallState state;
    @ManyToMany
    @JoinTable(name = "calls_surveys",
            joinColumns = @JoinColumn(name = "calls_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "surveys_id", referencedColumnName = "id"))
    private Set<SurveyEntity> surveys;

    public CallEntity(UUID id) {
        this.id = id;
    }

    public CallEntity() {

    }

    public CallEntity(String uuid) {
        this.id = UUID.fromString(uuid);

    }
}
