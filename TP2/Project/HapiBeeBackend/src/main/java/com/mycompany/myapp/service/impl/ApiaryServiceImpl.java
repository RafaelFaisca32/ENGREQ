package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Apiary;
import com.mycompany.myapp.domain.enumeration.ApiaryState;
import com.mycompany.myapp.domain.enumeration.TranshumanceRequestState;
import com.mycompany.myapp.repository.ApiaryRepository;
import com.mycompany.myapp.service.ApiaryService;
import com.mycompany.myapp.service.dto.ApiaryDTO;
import com.mycompany.myapp.service.dto.TranshumanceRequestDTO;
import com.mycompany.myapp.service.mapper.ApiaryMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Apiary}.
 */
@Service
@Transactional
public class ApiaryServiceImpl implements ApiaryService {

    @Value("${portal-endpoint:http://localhost:3000}")
    private String portalEndpoint;

    private final Logger log = LoggerFactory.getLogger(ApiaryServiceImpl.class);

    private final ApiaryRepository apiaryRepository;

    private final ApiaryMapper apiaryMapper;

    public ApiaryServiceImpl(ApiaryRepository apiaryRepository, ApiaryMapper apiaryMapper) {
        this.apiaryRepository = apiaryRepository;
        this.apiaryMapper = apiaryMapper;
    }

    @Override
    public ApiaryDTO save(ApiaryDTO apiaryDTO) {
        log.debug("Request to save Apiary : {}", apiaryDTO);
        Apiary apiary = apiaryMapper.toEntity(apiaryDTO);
        apiary = apiaryRepository.save(apiary);
        return apiaryMapper.toDto(apiary);
    }

    @Override
    public ApiaryDTO update(ApiaryDTO apiaryDTO) {
        log.debug("Request to update Apiary : {}", apiaryDTO);
        Apiary apiary = apiaryMapper.toEntity(apiaryDTO);
        apiary = apiaryRepository.save(apiary);
        return apiaryMapper.toDto(apiary);
    }

    @Override
    public Optional<ApiaryDTO> partialUpdate(ApiaryDTO apiaryDTO) {
        log.debug("Request to partially update Apiary : {}", apiaryDTO);

        return apiaryRepository
            .findById(apiaryDTO.getId())
            .map(existingApiary -> {
                apiaryMapper.partialUpdate(existingApiary, apiaryDTO);

                return existingApiary;
            })
            .map(apiaryRepository::save)
            .map(apiaryMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ApiaryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Apiaries");
        return apiaryRepository.findAll(pageable).map(apiaryMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ApiaryDTO> findOne(Long id) {
        log.debug("Request to get Apiary : {}", id);
        return apiaryRepository.findById(id).map(apiaryMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Apiary : {}", id);
        apiaryRepository.deleteById(id);
    }

    @Override
    public void sendAuthorizationRequestToPortal(long id) throws IOException, InterruptedException {
        String postMapping = portalEndpoint + "api/apiary/" + id;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(postMapping))
            .POST(HttpRequest.BodyPublishers.ofString(""))
            .build();
        client.send(request,
            HttpResponse.BodyHandlers.ofString());
    }

    @Override
    public void approveOrRejectApiary(String result) {
        String[] values = result.split(":");
        Optional<ApiaryDTO> apiary = findOne(Long.valueOf(values[0]));
        if(apiary.isPresent()){
            ApiaryDTO apiaryJudged = apiary.get();
            boolean isApproved = Boolean.parseBoolean(values[1]);
            if(isApproved){
                apiaryJudged.setState(ApiaryState.APPROVED);
            }else{
                apiaryJudged.setState(ApiaryState.NOT_APPROVED);
            }
            update(apiaryJudged);
        }
    }
}
