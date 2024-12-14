package org.example.chapter_12;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;

class Book {
    private final String title;

    public Book(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

class Library {
    private final List<Book> books;
    private final Semaphore semRead;
    private final Semaphore semTake;

    public Library(List<Book> books, int n, int m) {
        this.books = books;
        this.semRead = new Semaphore(n, true);
        this.semTake = new Semaphore(m, true);
    }

    public void readInReadingRoom(Book b) throws InterruptedException {
        semRead.acquire();
        try {
            System.out.println(Thread.currentThread().getName() + " читает книгу в читальном зале: " + b.getTitle());
            TimeUnit.SECONDS.sleep(1);
        } finally {
            semRead.release();
        }
    }

    public void takeHome(Book b) throws InterruptedException {
        semTake.acquire();
        try {
            System.out.println(Thread.currentThread().getName() + " взял книгу на руки: " + b.getTitle());
            TimeUnit.SECONDS.sleep(2);
        } finally {
            semTake.release();
        }
    }
}

class User implements Runnable {
    private final Library lib;
    private final List<Book> booksToRead;

    public User(Library lib, List<Book> booksToRead) {
        this.lib = lib;
        this.booksToRead = booksToRead;
    }

    @Override
    public void run() {
        try {
            for (Book b : booksToRead) {
                if (Math.random() < 0.5) {
                    lib.readInReadingRoom(b);
                } else {
                    lib.takeHome(b);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class LibraryApp {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Книга 1"));
        books.add(new Book("Книга 2"));
        books.add(new Book("Книга 3"));
        books.add(new Book("Книга 4"));

        Library lib = new Library(books, 2, 2);
        ExecutorService exec = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 4; i++) {
            List<Book> userBooks = new ArrayList<>();
            userBooks.add(books.get((int) (Math.random() * books.size())));
            userBooks.add(books.get((int) (Math.random() * books.size())));
            exec.execute(new User(lib, userBooks));
        }

        exec.shutdown();
        try {
            exec.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
