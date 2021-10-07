package com.WebLib.repos;

import com.WebLib.domain.Intertable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IntertableRepo extends CrudRepository<Intertable, Integer> {
    List<Intertable> findAllByReaderID(Integer readerid);
    List<Intertable> findAllByBookID(Integer bookid);
}
