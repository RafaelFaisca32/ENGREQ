package com.mycompany.myapp.service.impl;


import com.mycompany.myapp.domain.TranshumanceRequest;
import com.mycompany.myapp.domain.enumeration.TranshumanceRequestState;
import com.mycompany.myapp.repository.TranshumanceRequestRepository;
import com.mycompany.myapp.service.ApiaryService;
import com.mycompany.myapp.service.TranshumanceRequestService;
import com.mycompany.myapp.service.dto.ApiaryDTO;
import com.mycompany.myapp.service.dto.TranshumanceRequestDTO;
import com.mycompany.myapp.service.mapper.TranshumanceRequestMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.ZonedDateTime;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TranshumanceRequest}.
 */
@Service
@Transactional
public class TranshumanceRequestServiceImpl implements TranshumanceRequestService {

    @Value("${portal-endpoint:http://localhost:3000}")
    private String portalEndpoint;

    private final Logger log = LoggerFactory.getLogger(TranshumanceRequestServiceImpl.class);

    private final TranshumanceRequestRepository transhumanceRequestRepository;

    @Autowired
    public ApiaryService apiaryService;
    private final TranshumanceRequestMapper transhumanceRequestMapper;



    public TranshumanceRequestServiceImpl(
        TranshumanceRequestRepository transhumanceRequestRepository,
        TranshumanceRequestMapper transhumanceRequestMapper
    ) {
        this.transhumanceRequestRepository = transhumanceRequestRepository;
        this.transhumanceRequestMapper = transhumanceRequestMapper;
    }

    @Override
    public TranshumanceRequestDTO save(TranshumanceRequestDTO transhumanceRequestDTO) {
        log.debug("Request to save TranshumanceRequest : {}", transhumanceRequestDTO);
        transhumanceRequestDTO.setState(TranshumanceRequestState.PENDING);
        transhumanceRequestDTO.setRequestDate(ZonedDateTime.now());
        if(transhumanceRequestDTO.getApiaryId() != 0){
            Optional<ApiaryDTO> apiary = apiaryService.findOne(Long.valueOf(transhumanceRequestDTO.getApiaryId()));
            if (apiary.isPresent())
                transhumanceRequestDTO.setApiary(apiary.get());
        }
        TranshumanceRequest transhumanceRequest = transhumanceRequestMapper.toEntity(transhumanceRequestDTO);
        transhumanceRequest = transhumanceRequestRepository.save(transhumanceRequest);
        return transhumanceRequestMapper.toDto(transhumanceRequest);
    }

    @Override
    public TranshumanceRequestDTO update(TranshumanceRequestDTO transhumanceRequestDTO) {
        log.debug("Request to update TranshumanceRequest : {}", transhumanceRequestDTO);
        TranshumanceRequest transhumanceRequest = transhumanceRequestMapper.toEntity(transhumanceRequestDTO);
        transhumanceRequest = transhumanceRequestRepository.save(transhumanceRequest);
        return transhumanceRequestMapper.toDto(transhumanceRequest);
    }

    @Override
    public Optional<TranshumanceRequestDTO> partialUpdate(TranshumanceRequestDTO transhumanceRequestDTO) {
        log.debug("Request to partially update TranshumanceRequest : {}", transhumanceRequestDTO);

        return transhumanceRequestRepository
            .findById(transhumanceRequestDTO.getId())
            .map(existingTranshumanceRequest -> {
                transhumanceRequestMapper.partialUpdate(existingTranshumanceRequest, transhumanceRequestDTO);

                return existingTranshumanceRequest;
            })
            .map(transhumanceRequestRepository::save)
            .map(transhumanceRequestMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TranshumanceRequestDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TranshumanceRequests");
        return transhumanceRequestRepository.findAll(pageable).map(transhumanceRequestMapper::toDto);
    }

    public Page<TranshumanceRequestDTO> findAllWithEagerRelationships(Pageable pageable) {
        return transhumanceRequestRepository.findAllWithEagerRelationships(pageable).map(transhumanceRequestMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TranshumanceRequestDTO> findOne(Long id) {
        log.debug("Request to get TranshumanceRequest : {}", id);
        Optional<TranshumanceRequest> tR = transhumanceRequestRepository.findOneWithEagerRelationships(id);
        Optional<TranshumanceRequestDTO> result = tR.map(transhumanceRequestMapper::toDto);
        if(tR.isPresent() && tR.get().getApiary() != null){
            if (result.isPresent())
                result.get().setApiaryId(Math.toIntExact(tR.get().getApiary().getId()));
        }


        return result;
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TranshumanceRequest : {}", id);
        transhumanceRequestRepository.deleteById(id);
    }

    @Override
    public void sendAuthorizationRequestToPortal(long id) throws IOException, InterruptedException {
        String postMapping = portalEndpoint + "api/transhumanceApproval/" + id;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(postMapping))
            .POST(HttpRequest.BodyPublishers.ofString(""))
            .build();
        client.send(request,
            HttpResponse.BodyHandlers.ofString());

    }

    @Override
    public void approveOrRejectTranshumance(String result) {

        String[] values = result.split(":");
        Optional<TranshumanceRequestDTO> transhumance = findOne(Long.valueOf(values[0]));
        if(transhumance.isPresent()){
            TranshumanceRequestDTO transhumanceJudged = transhumance.get();
            boolean isApproved = Boolean.parseBoolean(values[1]);
            if(isApproved){
                transhumanceJudged.setState(TranshumanceRequestState.APPROVED);
            }else{
                transhumanceJudged.setState(TranshumanceRequestState.NOT_APPROVED);
            }
            update(transhumanceJudged);
        }



    }
}
