package com.jordan.albano.verticalslice.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EXTENSION")
public class ExtensionEntity extends ProjectEntity {

}
