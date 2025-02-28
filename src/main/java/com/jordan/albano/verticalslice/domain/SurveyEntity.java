package com.jordan.albano.verticalslice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Entity
@Table(name = "surveys")
@Getter
@Setter
public class SurveyEntity extends Lifecycle {
    private String name;
    @ManyToMany
    @JoinTable(name = "calls_surveys",
            joinColumns = @JoinColumn(name = "calls_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "surveys_id", referencedColumnName = "id"))
    private List<Question> questions;
    @Enumerated(EnumType.STRING)
    private UserTypeObjective userType;
}
