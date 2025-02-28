package com.jordan.albano.verticalslice.shared;

import com.jordan.albano.verticalslice.domain.Lifecycle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface LifecycleRepository<T extends Lifecycle, ID> extends JpaRepository<T, ID> {
    Optional<T> findByIdAndEnabledIsTrue(ID id);

    boolean existsByIdAndEnabledIsTrue(ID id);

    Page<T> findAllByEnabledIsTrue(PageRequest page);
}
