package com.example.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";
    private ImageView bookImage;
    private TextView textBookName, textAuthorName, textDescription, textPages;
    private Button btnAddToCurrentlyReading, btnAddToWantToRead, btnAddToAlreadyRead, btnAddToFavourite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

//        String longDescription = "A young woman named Aomame follows a taxi driver's enigmatic suggestion and begins to notice" +
//                "\n" +
//                "As Aomame's and Tengo's narratives converge over the course of this single year, we learn of the profound and" +
//                "\n" +
//                "A love story, a mytery, a fantasy, a novael of self-discovery, a dystopia to rival George Orwell's -1Q84";
//
       initViews();
//        //TODO: Get the data from recycler view in here
//        Book book = new Book(1, "1Q84", "Haruki Murakami", 1350, "https://images-na.ssl-images-amazon.com/images/I/41FdmYnaNuL._SX322_BO1,204,203,200_.jpg",
//                "a work of maddening brilliance", "Long description");

        Intent intent = getIntent();
        if (null != intent){
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (bookId != -1){
                 Book incomingBook = Utils.getInstance(this).getBookById(bookId);
                 if (null != incomingBook){
                     setData(incomingBook);

                     handleAlreadyRead(incomingBook);
                     handleWantToReadBooks(incomingBook);
                     handleCurrentlyReadingBooks(incomingBook);
                     handleFavouriteBooks(incomingBook);
                 }
            }
        }



    }

    private void handleFavouriteBooks(final Book book) {
        ArrayList<Book> favouriteBooks = Utils.getInstance(this).getFavouriteBooks();
        boolean existInFavouriteBooks = false;
        for(Book b: favouriteBooks){
            if(b.getId() == book.getId()){
                existInFavouriteBooks = true;
            }
        }
        if(existInFavouriteBooks){
            btnAddToFavourite.setEnabled(false);
        }else{
            btnAddToFavourite.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addToFavouriteBooks(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, FavouriteListActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Somethings wrong happend, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleCurrentlyReadingBooks(final Book book) {
        ArrayList<Book> currentlyReadingBooks = Utils.getInstance(this).getCurrentlyReadingBooks();
        boolean existInCurrentlyReadingBooks = false;
        for(Book b: currentlyReadingBooks){
            if(b.getId() == book.getId()){
                existInCurrentlyReadingBooks = true;
            }
        }
        if(existInCurrentlyReadingBooks){
            btnAddToCurrentlyReading.setEnabled(false);
        }else{
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addToCurrentlyReadingBooks(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Somethings wrong happend, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleWantToReadBooks(final Book book) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance(this).getWantToReadBooks();
        boolean existInWantToReadBooks = false;
        for(Book b: wantToReadBooks){
            if(b.getId() == book.getId()){
                existInWantToReadBooks = true;
            }
        }
        if(existInWantToReadBooks){
            btnAddToWantToRead.setEnabled(false);
        }else{
            btnAddToWantToRead.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addToWantToReadBooks(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, WantToReadActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Somethings wrong happend, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }

    /**
     * Enable and Disable button,
     * add the book to the already read book arraylist.
     * @param book
     */
    private void handleAlreadyRead(final Book book) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBooks();

        boolean exsitInAlreadyReadBooks = false;

        for(Book b: alreadyReadBooks){
            if(b.getId() == book.getId()){
                exsitInAlreadyReadBooks = true;
            }
        }
        if(exsitInAlreadyReadBooks){
            btnAddToAlreadyRead.setEnabled(false);
        }else{
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addToAlreadyRead(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Somethings wrong happend, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }



    private void setData(Book book) {
        textBookName.setText(book.getName());
        textAuthorName.setText(book.getAuthor());
        textPages.setText(String.valueOf(book.getPages()));
        textDescription.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap()
                .load(book.getImageUrl())
                .into(bookImage);

    }

    private void initViews() {
        bookImage = findViewById(R.id.bookImage);

        textBookName = findViewById(R.id.textBookName);
        textAuthorName = findViewById(R.id.textAuthorName);
        textDescription = findViewById(R.id.textDescription);
        textPages = findViewById(R.id.textPages);

        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyRead);
        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToWantToRead = findViewById(R.id.btnAddToWantToRead);
        btnAddToFavourite = findViewById(R.id.btnAddToFavourite);


    }
}