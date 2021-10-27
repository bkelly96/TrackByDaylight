package com.Kelly.TrackByDaylight.controller;

import com.Kelly.TrackByDaylight.model.Match;
import com.Kelly.TrackByDaylight.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.*;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping
public class SystemRequestController {

    private final RequestService requestService;

//    @GetMapping
//    public ResponseEntity<List<Match>> getAllRequests(){
//        return new ResponseEntity<List<Match>>(requestService.getAllMatches(), OK);
//    }

    //Create another get method since this one isn't working.

    @PostMapping("/match")
    public ResponseEntity createMatch(@RequestBody Match match){
        requestService.createMatch(match);
        return new ResponseEntity(CREATED);
    }

}
