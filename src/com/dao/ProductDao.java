package com.dao;

import com.entity.Product;

import java.util.List;

public interface ProductDao {

    public boolean add(String type, String code, String name, String unit, String classification,
                       String norm, String cost_price, String purchase_price, String unit_price);

    public boolean delete(int id);

    public Product queryById(int id);

    public boolean update(int id, String name, String unit, String classification,
                          String norm, String cost_price, String purchase_price, String unit_price);

    public boolean reviewPass(int id);

    public boolean reviewFailed(int id);

    public List<Product> queryByPage(int pageNow);

    public List<Product> searchAndQueryByPage(int pageNow, String a, String b, String c);

    public int getPageCount();

    public int getSearchedPageCount(String a, String b, String c);
}
