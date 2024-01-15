package org.capisoft.securitybackend.mappers;

import org.capisoft.securitybackend.api.models.requests.TemplateRequest;
import org.capisoft.securitybackend.api.models.responses.TemplateResponse;
import org.capisoft.securitybackend.entities.Template;

public class TemplateMapper {

    public static Template templateFormTemplateRequest(TemplateRequest templateRequest) {
        return Template.builder()
                .name(templateRequest.getName())
                .build();
    }

    public static TemplateResponse templateResponseFromTemplate(Template template) {
        return TemplateResponse.builder()
                .id(template.getId())
                .name(template.getName())
                .createdAt(template.getCreatedAt())
                .build();
    }
}
