package com.Library.Library.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Library.Library.Model.Book;

public interface BookRepo extends JpaRepository<Book, Long> {

}
