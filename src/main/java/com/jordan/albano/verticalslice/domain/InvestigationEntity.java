package com.jordan.albano.verticalslice.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("INVESTIGATION")
public class InvestigationEntity extends ProjectEntity {
}
