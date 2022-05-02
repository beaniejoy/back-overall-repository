package io.beaniejoy.jacksonbindtest.dto.part03_collection;

import java.util.List;

public class LibraryDto {
    private String name;

    private List<String> books;

    public String getName() {
        return name;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "LibraryDto{" +
                "name='" + name + '\'' +
                ", books=" + books +
                '}';
    }
}
