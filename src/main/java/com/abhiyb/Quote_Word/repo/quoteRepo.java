package com.abhiyb.Quote_Word.repo;

import com.abhiyb.Quote_Word.model.Quote;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface quoteRepo extends JpaRepository<Quote,Integer> {
    List<Quote>findAll(Sort sort);
}
