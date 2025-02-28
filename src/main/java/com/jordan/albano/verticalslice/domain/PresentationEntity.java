package com.jordan.albano.verticalslice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;

@Entity
@Table(name = "presentations")
@Getter
@Setter
public class PresentationEntity extends Lifecycle {
    @Column(nullable = false)
    private String userId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "call_to_apply_id")
    private CallEntity callToApply;
    @OneToMany(mappedBy = "presentation")
    private Set<Answer> answers;
    private PresentationState state;
    @ManyToMany
    @JoinTable(name = "presentations_projects",
            joinColumns = @JoinColumn(name = "presentation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "projects_id", referencedColumnName = "id"))
    private Set<ExtensionEntity> extensions;

    @ManyToMany
    @JoinTable(name = "presentations_projects",
            joinColumns = @JoinColumn(name = "presentation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "projects_id", referencedColumnName = "id"))
    private Set<InvestigationEntity> investigations;

    public PresentationEntity() {
        this.callToApply = new CallEntity();
    }
}
