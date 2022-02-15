package com.fitr.bntu.bookwebservice.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {
    private int pageNumber;
    private int numberOfElementsPerPage;

    public Pagination() {
    }

    public Pagination(int pageNumber, int numberOfElementsPerPage) {
        this.pageNumber = pageNumber;
        this.numberOfElementsPerPage = numberOfElementsPerPage;
    }
}
