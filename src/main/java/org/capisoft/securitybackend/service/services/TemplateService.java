package org.capisoft.securitybackend.service.services;

import jakarta.transaction.Transactional;
import org.apache.coyote.Response;
import org.capisoft.securitybackend.api.models.requests.TemplateRequest;
import org.capisoft.securitybackend.api.models.responses.TemplateResponse;
import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.capisoft.securitybackend.common.CustomResponseBuilder;
import org.capisoft.securitybackend.entities.Career;
import org.capisoft.securitybackend.entities.CareerAcademicPeriod;
import org.capisoft.securitybackend.entities.Template;
import org.capisoft.securitybackend.mappers.TemplateMapper;
import org.capisoft.securitybackend.repositories.CareerAcademicPeriodRepository;
import org.capisoft.securitybackend.repositories.CareerRepository;
import org.capisoft.securitybackend.repositories.TemplateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class TemplateService {

    private final TemplateRepository templateRepository;
    private final CareerAcademicPeriodRepository careerAcademicPeriodRepository;

    private final CareerRepository careerRepository;

    private final CustomResponseBuilder responseBuilder;
    public TemplateService(TemplateRepository templateRepository, CareerAcademicPeriodRepository careerAcademicPeriodRepository, CareerRepository careerRepository, CustomResponseBuilder responseBuilder) {
        this.templateRepository = templateRepository;
        this.careerAcademicPeriodRepository = careerAcademicPeriodRepository;
        this.careerRepository = careerRepository;
        this.responseBuilder = responseBuilder;
    }


    public ResponseEntity<CustomAPIResponse<?>> save (TemplateRequest request){
        CareerAcademicPeriod careerAcademicPeriod = careerAcademicPeriodRepository
                .findCareerAcademicPeriodByAcademicPeriod_IdAndCareer_Id(request.getAcademicPeriod(), request.getCareer())
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada."));

        Template template = TemplateMapper.templateFormTemplateRequest(request);
        template.setCareerAcademicPeriods(new HashSet<>(List.of(careerAcademicPeriod)));
        careerAcademicPeriod.setTemplates(new HashSet<>(List.of(template)));
        templateRepository.save(template);
        return responseBuilder.buildResponse(HttpStatus.CREATED, "Template creado exitosamente!");

    }

    public ResponseEntity<CustomAPIResponse<?>> findByCareer(Long id){
        Career career = careerRepository.findById(id).orElseThrow(() -> new RuntimeException("Carrera no encontrada."));
        List<Template> templates = templateRepository.findAllByCareerAcademicPeriodsIn(career.getCareerAcademicPeriods());
        List<TemplateResponse> responses = templates.stream().map(TemplateMapper::templateResponseFromTemplate).toList();
        return responseBuilder.buildResponse(HttpStatus.OK, "Templates encontrados exitosamente!", responses);
    }
}
