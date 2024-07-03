package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageData<T> implements Serializable {

    private int page;

    private int total_pages;

    private long total;

    private List<T> rows;

}
