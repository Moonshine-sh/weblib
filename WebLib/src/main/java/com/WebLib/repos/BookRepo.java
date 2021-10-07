package com.WebLib.repos;

import com.WebLib.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepo  extends CrudRepository<Book, Integer> {
}
