package com.Kelly.TrackByDaylight.service;

//import com.Kelly.TrackByDaylight.repository.MatchRepository;

import com.Kelly.TrackByDaylight.model.Match;
import com.Kelly.TrackByDaylight.repository.MatchRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.connector.Request;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class RequestService {

    private final MatchRepository matchRepository;
//
//    public List<Request> getAllMatches(){
//
//        return matchRepository.findAll();
//    }

//    public Match getMatchByKiller(String killer){
//        return matchRepository.find
//    }

    public void createMatch(Match match){
        match.setCreatedAt(Instant.now());
        matchRepository.save(match);

    }


}
