package com.bingle;

import com.bingle.esports.dto.ESportsApiResponse;
import com.bingle.match.service.MatchService;
import com.bingle.team.service.TeamService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class OnBooting implements ApplicationListener<ApplicationReadyEvent> {

    private final TeamService teamService;
    private final MatchService matchService;
    private final ResourceLoader resourceLoader;

    @Autowired
    public OnBooting(TeamService teamService, MatchService matchService, ResourceLoader resourceLoader) {
        this.teamService = teamService;
        this.matchService = matchService;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Resource resource = resourceLoader.getResource("classpath:23.json");

        try {
            InputStream inputStream = resource.getInputStream();
            byte[] jsonData = inputStream.readAllBytes();

            ESportsApiResponse eSportsApiResponse = objectMapper.readValue(jsonData, ESportsApiResponse.class);

            teamService.addTeam(eSportsApiResponse.getContent().getTeams());
            matchService.addMatch(eSportsApiResponse.getContent().getMatches());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
