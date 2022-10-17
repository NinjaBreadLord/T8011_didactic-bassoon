package com.nighthawk.spring_portfolio.mvc.songs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/songs")
public class SongsApiController {

    // Autowired enables Control to connect HTML and POJO Object to database easily for CRUD operations
    @Autowired
    private SongsJpaRepository repository;

    /*
    GET List of Songs
     */
    @GetMapping("/all")
    public ResponseEntity<List<Songs>> getSongs() {
        return new ResponseEntity<>( repository.findAllByOrderBySongAsc(), HttpStatus.OK);
    }
}
