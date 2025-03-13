package com.abhiyb.Quote_Word.service;

import com.abhiyb.Quote_Word.model.Quote;
import com.abhiyb.Quote_Word.model.Word;
import com.abhiyb.Quote_Word.repo.quoteRepo;
import com.abhiyb.Quote_Word.repo.wordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Random;

@Service
public class randomService {
     @Autowired
     quoteRepo qrepo;

     @Autowired
     wordRepo wrepo;


    public Quote getRandomQuote() {
        List<Quote>quotes=qrepo.findAll();
        if(quotes.isEmpty())return null;
        int randomIndex=new Random().nextInt(quotes.size());
        return quotes.get(randomIndex);
    }

    public Word getRandomWord() {
        List<Word>words=wrepo.findAll();
        if(words.isEmpty())return null;
        int randomWord=new Random().nextInt(words.size());
        return words.get(randomWord);
    }

    public String addQuote(Quote addquote) {
        qrepo.save(addquote);
        return "added quote";
    }


    public String addWord(Word addword) {
        wrepo.save(addword);
        return "added word";
    }

    public String deleteQuote(int id) {
        qrepo.deleteById(id);
        return "quote deleted successfully";
    }

    public String deleteWord(int id) {
        wrepo.deleteById(id);
        return "word deleted successfully";
    }

    public List<Quote> getAllQuote() {
        return qrepo.findAll();
    }

    public List<Word> getAllWord() {
        return wrepo.findAll();
    }

    public List<Word> searchWord(String query)
    {
        return wrepo.findByWordStartingWithIgnoreCase(query);
    }

    public List<Quote> getAllQuotesPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Quote> quotePage = qrepo.findAll(pageable);
        return quotePage.getContent();
    }

    public List<Word> getAllWordsPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Word> wordPage = wrepo.findAll(pageable);
        return wordPage.getContent();
    }

    public List<Word> filterWord(String sortBy, String order) {
        if ("alphabet".equalsIgnoreCase(sortBy)) {
            return order.equalsIgnoreCase("desc")
                    ? wrepo.findAll(Sort.by(Sort.Direction.DESC, "word"))
                    : wrepo.findAll(Sort.by(Sort.Direction.ASC, "word"));
        } else { // Default: Sort by time
            return order.equalsIgnoreCase("desc")
                    ? wrepo.findAll(Sort.by(Sort.Direction.DESC, "id"))
                    : wrepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
        }
    }

    public List<Quote> filterQuote(String order) {
        return order.equalsIgnoreCase("desc")
                ? qrepo.findAll(Sort.by(Sort.Direction.DESC, "id"))
                : qrepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
}
