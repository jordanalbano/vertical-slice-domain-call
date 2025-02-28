package com.jordan.albano.verticalslice.features.users.findall;

import com.jordan.albano.verticalslice.features.users.findall.dto.FindAllUserByPageDto;
import com.jordan.albano.verticalslice.shared.EvadocMapper;
import com.jordan.albano.verticalslice.shared.mediator.Handler;
import org.keycloak.admin.client.resource.GroupsResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Handler
@Service
public class FindAllUsersByPageHandler {
    private final GroupsResource groupsResource;
    private final String groupDefault;
    private final EvadocMapper mapper;

    FindAllUsersByPageHandler(
            GroupsResource defaultGroupId,
            @Value("${keycloak-evadoc-groups-id.default}") String groupDefault,
            EvadocMapper mapper) {
        this.groupsResource = defaultGroupId;
        this.groupDefault = groupDefault;
        this.mapper = mapper;
    }

    public PageImpl<FindAllUserByPageDto> findAllByPage(FindAllUserByPageQuery query) {
        var users = this.groupsResource.group(groupDefault).members().stream().map(mapper::toFindAllUserByPageDTO).toList();
        return new PageImpl<>(users);
    }
}