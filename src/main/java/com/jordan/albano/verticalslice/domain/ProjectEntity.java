package com.jordan.albano.verticalslice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "projects")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
public class ProjectEntity extends Lifecycle {
    @Column(nullable = false)
    private String name;
}
