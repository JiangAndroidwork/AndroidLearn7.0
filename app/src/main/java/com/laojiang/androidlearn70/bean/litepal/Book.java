package com.laojiang.androidlearn70.bean.litepal;

import org.litepal.crud.DataSupport;

/**
 * 类介绍（必填）：litepal 数据库 基类
 * Created by Jiang on 2017/2/13 11:07.
 */

public class Book extends DataSupport{
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", pages=" + pages +
                ", name='" + name + '\'' +
                '}';
    }

    private int id;
    private String author;
    private double price;
    private int pages;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
