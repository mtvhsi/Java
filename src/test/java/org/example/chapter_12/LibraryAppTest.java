package org.example.chapter_12;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

class BookTest {
    @Test
    void testGetTitle() {
        Book book = new Book("Test Book");
        assertEquals("Test Book", book.getTitle());
    }
}

class LibraryTest {
    private Library library;
    private List<Book> books;

    @BeforeEach
    void setUp() {
        books = new ArrayList<>();
        books.add(new Book("Book 1"));
        books.add(new Book("Book 2"));
        library = new Library(books, 2, 2);
    }

    @Test
    void testLibraryInitialization() {
        assertNotNull(library);
    }

    @Test
    void testTakeHomeBook() throws InterruptedException {
        Book book = books.get(0);
        Semaphore semTake = new Semaphore(2);
        semTake.acquire();
        try {
            library.takeHome(book);
            assertEquals(1, semTake.availablePermits());
        } finally {
            semTake.release();
        }
    }

    @Test
    void testReadInReadingRoom() throws InterruptedException {
        Book book = books.get(0);
        Semaphore semRead = new Semaphore(2);
        semRead.acquire();
        try {
            library.readInReadingRoom(book);
            assertEquals(1, semRead.availablePermits());
        } finally {
            semRead.release();
        }
    }
}

