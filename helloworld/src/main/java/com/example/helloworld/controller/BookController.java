package com.example.helloworld.controller;

import com.example.helloworld.entity.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: weilong
 * @Date: 2020/3/4 10:19
 */

@RestController
@RequestMapping(value = "/api")
public class BookController {

    private List<Book> books = new ArrayList<>();

    @PostMapping(value = "/book")
    public ResponseEntity<List<Book>> addBook(@RequestBody Book book) {
        books.add(book);
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity deleteBookById(@PathVariable("id") int id) {
        books.remove(id);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/book")
    public ResponseEntity getBookByName(@RequestParam(value = "name") String name) {
        List<Book> results = books.stream().filter(book -> book.getName().equals(name)).collect(Collectors.toList());
        return ResponseEntity.ok(results);
    }

    @GetMapping("/books")
    public ResponseEntity getAllBook() {
        return ResponseEntity.ok(books);
    }
}