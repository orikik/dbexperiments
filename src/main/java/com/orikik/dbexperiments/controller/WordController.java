package com.orikik.dbexperiments.controller;

import com.orikik.dbexperiments.dto.WordDto;
import com.orikik.dbexperiments.service.WordService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class WordController {
    @Resource(name = "wordServiceDao")
    private WordService wordService;

    @GetMapping("/word/all")
    public List<WordDto> allWords() {
        return wordService.getWords();
    }

    @GetMapping("/word/{id}")
    public WordDto wordById(@PathVariable Long id) {
        return wordService.getWordById(id);
    }

    @PostMapping("/word/create")
    public WordDto createWord(@RequestBody WordDto wordDto) {
        return wordService.createNewWord(wordDto);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/word/{id}")
    public void deleteWord(@PathVariable Long id) {
        wordService.deleteWordById(id);
    }

    @PutMapping("/word/update")
    public WordDto updateWord(@RequestBody WordDto wordDto) {
        return wordService.updateWordById(wordDto);
    }
}
