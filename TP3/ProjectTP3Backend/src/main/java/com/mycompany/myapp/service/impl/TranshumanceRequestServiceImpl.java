package com.mycompany.myapp.service.impl;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mycompany.myapp.domain.TranshumanceRequest;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.domain.enumeration.TranshumanceRequestState;
import com.mycompany.myapp.repository.TranshumanceRequestRepository;
import com.mycompany.myapp.service.*;
import com.mycompany.myapp.service.dto.*;
import com.mycompany.myapp.service.mapper.TranshumanceRequestMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    private JavaMailSender javaMailSender;

    @Autowired
    public ApiaryService apiaryService;

    @Autowired
    public HiveService hiveService;
    @Autowired
    public UserService userService;

    @Autowired
    public BeekeeperService beekeeperService;
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
        if(transhumanceRequestDTO.getBeekeperId() != 0){
            Optional<BeekeeperDTO> bee = beekeeperService.findOne(Long.valueOf(transhumanceRequestDTO.getBeekeperId()));
            if (bee.isPresent())
                transhumanceRequestDTO.setBeekeeper(bee.get());
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
    public void sendAuthorizationRequestToPortal(long id, TranshumanceRequestDTO transhumanceRequest) throws IOException, InterruptedException {
        String postMapping = portalEndpoint + "api/transhumanceApproval/" + id;
        HttpClient client = HttpClient.newHttpClient();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String requestBody = objectMapper.writeValueAsString(transhumanceRequest);
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(postMapping))
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
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
                Optional<ApiaryDTO> apiaryDestination = apiaryService.findOne(Long.valueOf(transhumanceJudged.getApiaryId()));
                List<HiveDTO> hives =  new ArrayList<>(transhumanceJudged.getHives());

                for(HiveDTO hive : hives){
                    HiveDTO hiveToChange = hiveService.findOne(hive.getId()).get();
                    hiveToChange.setApiary(apiaryDestination.get());
                    hiveService.update(hiveToChange);
                }
                transhumanceJudged.setState(TranshumanceRequestState.APPROVED);
           }else{
                transhumanceJudged.setState(TranshumanceRequestState.NOT_APPROVED);
            }
            update(transhumanceJudged);
            sendEmail(transhumanceJudged.getState() == TranshumanceRequestState.APPROVED, transhumanceJudged.getBeekeeper().getId(), transhumanceJudged);
        }



    }

    public void sendEmail(boolean status, Long beekeeperId, TranshumanceRequestDTO trans ) {
        BeekeeperDTO bee = beekeeperService.findOne(beekeeperId).get();
        User user = userService.findOne(bee.getUser().getId()).get();
        SimpleMailMessage message = new SimpleMailMessage();
        String state = status ? "aprovado" : "rejeitado";
        message.setFrom("beesenderbzzz@hotmail.com");
        message.setTo(user.getEmail());
        message.setSubject("HapiBee - Transumance Request results");
        message.setText("O seu pedido de transumância  para transferir as colmeias:\n" +trans.getHives().toString() +"\n para o apiário:\n" + trans.getApiaryId() + "\n foi "+ state + ".");

        javaMailSender.send(message);
    }
}
