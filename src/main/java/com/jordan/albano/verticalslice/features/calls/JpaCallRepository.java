package com.jordan.albano.verticalslice.features.calls;


import com.jordan.albano.verticalslice.domain.CallEntity;
import com.jordan.albano.verticalslice.shared.LifecycleRepository;

import java.util.UUID;

public interface JpaCallRepository extends LifecycleRepository<CallEntity, UUID> {
}
