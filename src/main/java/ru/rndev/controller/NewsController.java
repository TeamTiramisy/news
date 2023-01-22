package ru.rndev.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.rndev.dto.NewsCreateDto;
import ru.rndev.dto.NewsDto;
import ru.rndev.dto.NewsFilter;
import ru.rndev.service.NewsService;

import java.util.List;

@RestController
@RequestMapping("/v1/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    public ResponseEntity<List<NewsDto>> findAll(NewsFilter newsFilter, Pageable pageable){
        return new ResponseEntity<>(newsService.findAll(newsFilter, pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDto> findById(@PathVariable Integer id){
        return new ResponseEntity<>(newsService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<NewsDto> save(@RequestBody NewsCreateDto newsCreateDto,
                                        @AuthenticationPrincipal UserDetails userDetails){
        return new ResponseEntity<>(newsService.save(newsCreateDto, userDetails), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<NewsDto> update(@PathVariable Integer id,
                                          @RequestBody NewsCreateDto newsCreateDto,
                                          @AuthenticationPrincipal UserDetails userDetails){
        return new ResponseEntity<>(newsService.update(id, newsCreateDto, userDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id,
                                    @AuthenticationPrincipal UserDetails userDetails) {
        newsService.delete(id, userDetails);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
