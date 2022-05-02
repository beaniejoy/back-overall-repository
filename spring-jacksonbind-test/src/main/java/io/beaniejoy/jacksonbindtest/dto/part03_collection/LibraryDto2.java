package io.beaniejoy.jacksonbindtest.dto.part03_collection;

import java.util.List;

public class LibraryDto2 {
    private String name;

    private List<Book> books;

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "LibraryDto2{" +
                "name='" + name + '\'' +
                ", books=" + books +
                '}';
    }
}
