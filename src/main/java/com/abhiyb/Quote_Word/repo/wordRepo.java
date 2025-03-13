package com.abhiyb.Quote_Word.repo;

import com.abhiyb.Quote_Word.model.Word;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface wordRepo extends JpaRepository<Word,Integer> {
    List<Word> findByWordStartingWithIgnoreCase(String query);
    List<Word>findAll(Sort sort);

}
