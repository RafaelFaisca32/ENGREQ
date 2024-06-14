package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.AnnualInventoryDeclaration;
import com.mycompany.myapp.domain.enumeration.AnnualInventoyDeclarationState;
import com.mycompany.myapp.domain.enumeration.TranshumanceRequestState;
import com.mycompany.myapp.repository.AnnualInventoryDeclarationRepository;
import com.mycompany.myapp.service.AnnualInventoryDeclarationService;
import com.mycompany.myapp.service.dto.AnnualInventoryDeclarationDTO;
import com.mycompany.myapp.service.dto.TranshumanceRequestDTO;
import com.mycompany.myapp.service.mapper.AnnualInventoryDeclarationMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * Service Implementation for managing {@link AnnualInventoryDeclaration}.
 */
@Service
@Transactional
public class AnnualInventoryDeclarationServiceImpl implements AnnualInventoryDeclarationService {

    @Value("${portal-endpoint:http://localhost:3000}")
    private String portalEndpoint;

    private final Logger log = LoggerFactory.getLogger(AnnualInventoryDeclarationServiceImpl.class);

    private final AnnualInventoryDeclarationRepository annualInventoryDeclarationRepository;

    private final AnnualInventoryDeclarationMapper annualInventoryDeclarationMapper;

    public AnnualInventoryDeclarationServiceImpl(
        AnnualInventoryDeclarationRepository annualInventoryDeclarationRepository,
        AnnualInventoryDeclarationMapper annualInventoryDeclarationMapper
    ) {
        this.annualInventoryDeclarationRepository = annualInventoryDeclarationRepository;
        this.annualInventoryDeclarationMapper = annualInventoryDeclarationMapper;
    }

    @Override
    public AnnualInventoryDeclarationDTO save(AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO) {
        log.debug("Request to save AnnualInventoryDeclaration : {}", annualInventoryDeclarationDTO);
        AnnualInventoryDeclaration annualInventoryDeclaration = annualInventoryDeclarationMapper.toEntity(annualInventoryDeclarationDTO);
        annualInventoryDeclaration = annualInventoryDeclarationRepository.save(annualInventoryDeclaration);
        return annualInventoryDeclarationMapper.toDto(annualInventoryDeclaration);
    }

    @Override
    public AnnualInventoryDeclarationDTO update(AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO) {
        log.debug("Request to update AnnualInventoryDeclaration : {}", annualInventoryDeclarationDTO);
        AnnualInventoryDeclaration annualInventoryDeclaration = annualInventoryDeclarationMapper.toEntity(annualInventoryDeclarationDTO);
        annualInventoryDeclaration = annualInventoryDeclarationRepository.save(annualInventoryDeclaration);
        return annualInventoryDeclarationMapper.toDto(annualInventoryDeclaration);
    }

    @Override
    public Optional<AnnualInventoryDeclarationDTO> partialUpdate(AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO) {
        log.debug("Request to partially update AnnualInventoryDeclaration : {}", annualInventoryDeclarationDTO);

        return annualInventoryDeclarationRepository
            .findById(annualInventoryDeclarationDTO.getId())
            .map(existingAnnualInventoryDeclaration -> {
                annualInventoryDeclarationMapper.partialUpdate(existingAnnualInventoryDeclaration, annualInventoryDeclarationDTO);

                return existingAnnualInventoryDeclaration;
            })
            .map(annualInventoryDeclarationRepository::save)
            .map(annualInventoryDeclarationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AnnualInventoryDeclarationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AnnualInventoryDeclarations");
        return annualInventoryDeclarationRepository.findAll(pageable).map(annualInventoryDeclarationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AnnualInventoryDeclarationDTO> findOne(Long id) {
        log.debug("Request to get AnnualInventoryDeclaration : {}", id);
        return annualInventoryDeclarationRepository.findById(id).map(annualInventoryDeclarationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AnnualInventoryDeclaration : {}", id);
        annualInventoryDeclarationRepository.deleteById(id);
    }

    @Override
    public void sendAuthorizationRequestToPortal(long id) throws IOException, InterruptedException {
        String postMapping = portalEndpoint + "api/annualInventoryDeclarationApproval/" + id;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(postMapping))
            .POST(HttpRequest.BodyPublishers.ofString(""))
            .build();
        client.send(request,
            HttpResponse.BodyHandlers.ofString());

    }

    @Override
    public void approveOrRejectAnnualInventoryDeclarationApproval(String result) {

        String[] values = result.split(":");
        Optional<AnnualInventoryDeclarationDTO> annualInventoryDeclarationDTO = findOne(Long.valueOf(values[0]));
        if(annualInventoryDeclarationDTO.isPresent()){
            AnnualInventoryDeclarationDTO declarationJudged = annualInventoryDeclarationDTO.get();
            boolean isApproved = Boolean.parseBoolean(values[1]);
            if(isApproved && LocalDate.now().getYear() != annualInventoryDeclarationDTO.get().getRevisionDate().getYear()){
                declarationJudged.setAnnualInventoryDeclarationState(AnnualInventoyDeclarationState.APPROVED);
            }else{
                declarationJudged.setAnnualInventoryDeclarationState(AnnualInventoyDeclarationState.DECLINED);
            }
            declarationJudged.setRevisionDate(LocalDate.now());
            declarationJudged.setRevisionLocation(values[2]);
            declarationJudged.setRevisorSignature(values[3]);
            declarationJudged.setRevisorName(values[4]);
            update(declarationJudged);
        }


    }
}
