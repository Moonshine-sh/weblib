package com.WebLib.repos;

import com.WebLib.domain.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepo extends CrudRepository<Genre, Integer> {
}
