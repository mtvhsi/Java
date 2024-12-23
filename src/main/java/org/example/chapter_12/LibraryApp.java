package org.example.chapter_12;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;

class Book {
    private final String title;
    private final boolean canTakeHome;
    private final Semaphore semBook;
    private boolean otslez;

    public Book(String title, boolean canTakeHome){
        this.title = title;
        this.canTakeHome = canTakeHome;
        this.semBook = new Semaphore(1, true);
        this.otslez = false;
    }

    public String getTitle() {
        return title;
    }
    public boolean canTakeHome() {
        return canTakeHome;
    }

    public Semaphore getSemBook() {
        return semBook;
    }

    public boolean setOtslez(boolean otsl){
        this.otslez = otsl;
        return otsl;
    }

    public boolean otslez(){
        return otslez;
    }
}

class Library {
    private final List<Book> books;
    private final Semaphore semRead;

    public Library(List<Book> books, int n) {
        this.books = books;
        this.semRead = new Semaphore(n, true);
    }

    public void readInReadingRoom(Book b) throws InterruptedException {
        if (b.otslez()) {
            System.out.println(Thread.currentThread().getName() + " не может читать книгу " + b.getTitle() + " в читальном зале, она занята.");
            return;
        }

        semRead.acquire();
        try {
            b.getSemBook().acquire();
            try {
                System.out.println(Thread.currentThread().getName() + " читает книгу в читальном зале: " + b.getTitle());
                TimeUnit.SECONDS.sleep(1);
            } finally {
                b.getSemBook().release();
            }
        } finally {
            semRead.release();
        }
    }

    public void takeHome(Book b) throws InterruptedException {
        if (b.canTakeHome()) {
            if (!b.otslez()) {
                b.getSemBook().acquire();
                try {
                    b.setOtslez(true);
                    System.out.println(Thread.currentThread().getName() + " взял книгу на руки: " + b.getTitle());
                    TimeUnit.SECONDS.sleep(2);
                } finally {
                    b.getSemBook().release();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " не может взять книгу " + b.getTitle() + " на руки, она уже занята.");
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " не может взять книгу " + b.getTitle() + " на руки.");
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
        books.add(new Book("Книга 1", true));
        books.add(new Book("Книга 2",true));
        books.add(new Book("Книга 3",true));
        books.add(new Book("Книга 4", true));

        Library lib = new Library(books, 2);
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
