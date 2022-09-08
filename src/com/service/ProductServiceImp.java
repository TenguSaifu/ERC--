package com.service;

import com.dao.ProductDao;
import com.dao.ProductDaoImp;
import com.entity.Product;

import java.util.List;

public class ProductServiceImp implements ProductService {
    ProductDao productDao = new ProductDaoImp();

    @Override
    public boolean add(String type, String code, String name, String unit, String classification,
                       String norm, String cost_price, String purchase_price, String unit_price) {
        return productDao.add(type, code, name, unit, classification, norm, cost_price, purchase_price, unit_price);
    }

    @Override
    public boolean delete(int id) {
        return productDao.delete(id);
    }

    @Override
    public Product queryById(int id) {
        return productDao.queryById(id);
    }

    @Override
    public boolean update(int id, String name, String unit, String classification,
                          String norm, String cost_price, String purchase_price, String unit_price) {
        return productDao.update(id, name, unit, classification, norm, cost_price, purchase_price, unit_price);
    }

    @Override
    public boolean reviewPass(int id) {
        return productDao.reviewPass(id);
    }

    @Override
    public boolean reviewFailed(int id) {
        return productDao.reviewFailed(id);
    }

    @Override
    public List<Product> queryByPage(int pageNow) {
        return productDao.queryByPage(pageNow);
    }

    @Override
    public List<Product> searchAndQueryByPage(int pageNow, String a, String b, String c) {
        return productDao.searchAndQueryByPage(pageNow, a, b, c);
    }

    @Override
    public int getPageCount() {
        return productDao.getPageCount();
    }

    @Override
    public int getSearchedPageCount(String a, String b, String c) {
        return productDao.getSearchedPageCount(a, b, c);
    }
}
