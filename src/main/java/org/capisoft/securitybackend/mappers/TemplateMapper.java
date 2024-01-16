package org.capisoft.securitybackend.mappers;

import org.capisoft.securitybackend.api.models.requests.TemplateRequest;
import org.capisoft.securitybackend.api.models.responses.AcademicPeriodResponse;
import org.capisoft.securitybackend.api.models.responses.TemplateResponse;
import org.capisoft.securitybackend.entities.Template;

import java.util.List;

public class TemplateMapper {

    public static Template templateFormTemplateRequest(TemplateRequest templateRequest) {
        return Template.builder()
                .name(templateRequest.getName())
                .build();
    }

    public static TemplateResponse templateResponseFromTemplate(Template template) {
        List<AcademicPeriodResponse> academicPeriodResponses =
                template.getCareerAcademicPeriods().stream().map(
                        careerAcademicPeriod -> {
                            return AcademicPeriodResponse.builder()
                                    .id(careerAcademicPeriod.getAcademicPeriod().getId())
                                    .name(careerAcademicPeriod.getAcademicPeriod().getName())
                                    .build();
                        }
                ).toList();
        return TemplateResponse.builder()
                .id(template.getId())
                .name(template.getName())
                .createdAt(template.getCreatedAt())
                .folders(template.getFolders().stream().map(FolderMapper::folderResponseFromFolder).toList())
                .academicPeriods(academicPeriodResponses)
                .build();
    }
}
