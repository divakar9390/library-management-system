package com.example.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.dto.BookResponseDto;
import com.example.model.Book;
import com.example.repository.BookRePository;
import com.example.util.IDGenerator;


@Service
public class BookService {

   public final BookRePository bookRepository;
   private final IDGenerator idGenerator;
   private final BookResponseDto bookResponseDto;

   BookService(BookRePository bookRepository, IDGenerator idGenerator, BookResponseDto bookResponseDto){
      this.idGenerator = idGenerator;
      this.bookRepository = bookRepository;
      this.bookResponseDto = bookResponseDto;}

   public Book save(Book book){

       String bookId;

          do {
          bookId = idGenerator.generatorBookId();
          } while (bookRepository.existsById(bookId));

          book.setBookId(bookId);
        return bookRepository.save(book);
   }
   public List<Book> saveAll(List<Book> books){
    books.forEach(book->{
            String bookId;
            do {
               bookId = idGenerator.generatorBookId();
            } while (bookRepository.existsById(bookId));
            book.setBookId(bookId);
    });
    return bookRepository.saveAll(books);
   }

   public List<Book> findall(){
        return bookRepository.findAll();
   }
   public Optional<BookResponseDto> findById(String BookId){
        return bookRepository.findById(BookId).map(book -> new BookResponseDto(
            book.getBookId(),
            book.getTitle(),
            book.getPrice(),
            book.getAvailability(),
            book.getLanguage()
        ));
   }

   public void deleteById(String BookId){
        bookRepository.deleteById(BookId);
   }
}
