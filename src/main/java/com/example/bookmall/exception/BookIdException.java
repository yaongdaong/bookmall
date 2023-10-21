package com.example.bookmall.exception;

@SuppressWarnings("serial")
public class BookIdException extends RuntimeException{
    private String bookId;

    // public BookIdException(Integer bookId){
    //     this.bookId = bookId;
    // }

        public BookIdException(Integer bookId) {
            super("Book ID is invalid: " + bookId.toString());
        }

    public String getBookId(){
        return bookId;
    }
}
