package com.bingle;

import com.bingle.esports.dto.ESportsApiResponse;
import com.bingle.match.service.MatchService;
import com.bingle.team.service.TeamService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
public class OnBooting {

    private final TeamService teamService;
    private final MatchService matchService;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady(ApplicationReadyEvent event) {
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ClassPathResource resource = new ClassPathResource("23.json");

        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(resource.getURI()));

            ESportsApiResponse eSportsApiResponse = objectMapper.readValue(jsonData, ESportsApiResponse.class);

            teamService.addTeam(eSportsApiResponse.getContent().getTeams());
            matchService.addMatch(eSportsApiResponse.getContent().getMatches());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
