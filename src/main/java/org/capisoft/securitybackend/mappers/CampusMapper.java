package org.capisoft.securitybackend.mappers;

import org.capisoft.securitybackend.api.models.requests.CampusRequest;
import org.capisoft.securitybackend.api.models.responses.CampusResponse;
import org.capisoft.securitybackend.entities.Campus;

public class CampusMapper {

    public static Campus campusFromCampusRequest(CampusRequest request){
        return Campus.builder().name(request.getName()).build();
    }

    public static CampusResponse campusResponseFromCampus(Campus campus){
        return CampusResponse.builder()
                .id(campus.getId())
                .name(campus.getName()).build();
    }

}
