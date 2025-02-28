package com.jordan.albano.verticalslice.features.presentations;

import com.jordan.albano.verticalslice.domain.PresentationEntity;
import com.jordan.albano.verticalslice.shared.LifecycleRepository;

import java.util.UUID;
public interface JpaPresentationRepository extends LifecycleRepository<PresentationEntity, UUID> {
}
