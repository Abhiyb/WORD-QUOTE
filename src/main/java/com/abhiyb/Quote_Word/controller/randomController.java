package com.abhiyb.Quote_Word.controller;

import com.abhiyb.Quote_Word.model.Quote;
import com.abhiyb.Quote_Word.model.Word;
import com.abhiyb.Quote_Word.service.randomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/")
@RestController
public class randomController {
  @Autowired
   private randomService service;

//get random quote and word
   @GetMapping("quote")
    public Quote getRandomQuote()
   {
       return service.getRandomQuote();
   }

   @GetMapping("word")
    public Word getRandomWord()
   {
       return service.getRandomWord();
   }

   // get all quote and word by page
   @GetMapping("allquote/paged")
   public List<Quote> getAllQuotesPaged(@RequestParam int page,@RequestParam int size)
   {
       return service.getAllQuotesPaged(page,size);
   }
   @GetMapping("allword/paged")
   public List<Word> getAllWordsPaged(@RequestParam int page, @RequestParam int size){
       return service.getAllWordsPaged(page,size);
   }

   // add
   @PostMapping("addquote")
    public String addQuote(@RequestBody Quote addquote)
   {
       return service.addQuote(addquote);
   }
    @PostMapping("addword")
    public String addWord(@RequestBody Word addword)
    {
        return service.addWord(addword);
    }

    //delete
    @DeleteMapping("deletequote/{id}")
    public String deleteQuote(@PathVariable int id)
    {
        return service.deleteQuote(id);
    }

    @DeleteMapping("deleteword/{id}")
    public String deleteWord(@PathVariable int id)
    {
        return service.deleteWord(id);
    }

  //searching
    @GetMapping("word/search")
    public List<Word> searchWord(String query)
    {
        return service.searchWord(query);
    }

    //filter by time and
    @GetMapping("word/filter")
    public List<Word> filterWord(
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "asc")String order
    )
    {
        return service.filterWord(sortBy,order);
    }

    @GetMapping("quote/filter")
    public List<Quote> filterQuote(
            @RequestParam(defaultValue = "asc")String order
    )
    {
        return service.filterQuote(order);
    }
}