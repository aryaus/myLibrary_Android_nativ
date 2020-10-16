package com.example.mylib;

// this class will be implemented with singleton pattern

import java.util.ArrayList;

public class Utils {
    private static Utils instance;
    private static ArrayList<Book> allBooks;
   private static ArrayList<Book> alreadyReadBooks;
   private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> favouriteBooks;


    private Utils() {
        if (null == allBooks){
            allBooks = new ArrayList<>();
            initData();
        }
        if(null == alreadyReadBooks){
            alreadyReadBooks = new ArrayList<>();
        }
        if(null == wantToReadBooks){
            wantToReadBooks = new ArrayList<>();
        }
        if(null == currentlyReadingBooks){
            currentlyReadingBooks = new ArrayList<>();
        }
        if(null == favouriteBooks){
            favouriteBooks = new ArrayList<>();
        }
    }

    private void initData() {
        //TODO: add initial data

        allBooks.add(new Book(1, "1Q84", "Haruki Murakami", 1350, "https://images-na.ssl-images-amazon.com/images/I/41FdmYnaNuL._SX322_BO1,204,203,200_.jpg",
                "a work of maddening brilliance", "Long description"));
        allBooks.add(new Book(2, "The Myth of Sisyphus", "Albert Camus", 250, "https://images-na.ssl-images-amazon.com/images/I/61hdhYRwDsL.jpg",
                "One of the most influential works of this century, this is a crucial exposition of existentialist thought", "Long description"));
    }


    // the synchronized keyword make your method thread safe and synchronized make
    // them to call the methods one by one and not at the same time
    public static Utils getInstance(){
        if (null == instance) {
            instance = new Utils();
        }
        return instance;

    }

    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Book> getFavouriteBooks() {
        return favouriteBooks;
    }
    public Book getBookById(int id){
        for (Book b: allBooks){
            if (b.getId() == id){
                return b;
            }
        }
        return null;
    }

    public boolean addToAlreadyRead(Book book){
        return alreadyReadBooks.add(book);
    }

    public boolean addToWantToReadBooks(Book book){
        return wantToReadBooks.add(book);
    }
    public boolean addToCurrentlyReadingBooks(Book book){
        return currentlyReadingBooks.add(book);
    }
    public boolean addToFavouriteBooks(Book book){
        return favouriteBooks.add(book);
    }
    public boolean removeFromAlreadyRead(Book book){
        return alreadyReadBooks.remove(book);
    }
    public boolean removeFromWantToRead(Book book){
        return wantToReadBooks.remove(book);
    }
    public boolean removeFromCurrentlyReading(Book book){
        return currentlyReadingBooks.remove(book);
    }
    public boolean removeFromFavourites(Book book){
        return favouriteBooks.remove(book);
    }

}
