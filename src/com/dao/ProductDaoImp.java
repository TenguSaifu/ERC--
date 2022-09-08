package com.dao;

import com.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImp implements ProductDao {

    PreparedStatement pst;
    ResultSet rs;

    @Override
    public boolean add(String type, String code, String name, String unit, String classification,
                       String norm, String cost_price, String purchase_price, String unit_price) {
        BaseDao Utils = new BaseDao();
        Connection conn = Utils.getConnection();
        boolean flag = false;
        String sql = "insert into Products(id, type, code, name, unit, classification, norm, " +
                "cost_price, purchase_price, unit_price) values(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setObject(1, type);
            pst.setObject(2, code);
            pst.setObject(3, name);
            pst.setObject(4, unit);
            pst.setObject(5, classification);
            pst.setObject(6, norm);
            pst.setObject(7, cost_price);
            pst.setObject(8, purchase_price);
            pst.setObject(9, unit_price);
            if (pst.executeUpdate() > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.release(conn, pst, rs);
        }
        return flag;
    }

    @Override
    public boolean delete(int id) {
        BaseDao Utils = new BaseDao();
        Connection conn = Utils.getConnection();
        boolean flag = false;
        String sql = "delete from Products where id = " + id + " and review != \"pass\"";
        try {
            pst = conn.prepareStatement(sql);
            int rs = pst.executeUpdate();
            if (rs > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.release(conn, pst, rs);
        }
        return flag;
    }

    @Override
    public Product queryById(int a) {
        BaseDao Utils = new BaseDao();
        Connection conn = Utils.getConnection();
        Product product = null;
        int id;
        String type, code, name, unit, classification, norm, cost_price, purchase_price, unit_price, review;
        String sql = "select * from Products where id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setObject(1, a);
            rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                type = rs.getString(2);
                code = rs.getString(3);
                name = rs.getString(4);
                unit = rs.getString(5);
                classification = rs.getString(6);
                norm = rs.getString(7);
                cost_price = rs.getString(8);
                purchase_price = rs.getString(9);
                unit_price = rs.getString(10);
                review = rs.getString(11);
                product = new Product(id, type, code, name, unit, classification, norm,
                        cost_price, purchase_price, unit_price, review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.release(conn, pst, rs);
        }
        return product;
    }

    @Override
    public boolean update(int id, String name, String unit, String classification,
                          String norm, String cost_price, String purchase_price, String unit_price) {
        BaseDao Utils = new BaseDao();
        Connection conn = Utils.getConnection();
        boolean flag = false;
        String sql = "update Products set name = ?, unit = ?, classification = ?, norm = ?, " +
                "cost_price = ?, purchase_price = ?, unit_price = ? where id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setObject(1, name);
            pst.setObject(2, unit);
            pst.setObject(3, classification);
            pst.setObject(4, norm);
            pst.setObject(5, cost_price);
            pst.setObject(6, purchase_price);
            pst.setObject(7, unit_price);
            pst.setObject(8, id);
            int rs = pst.executeUpdate();
            if (rs > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.release(conn, pst, rs);
        }
        return flag;
    }

    @Override
    public boolean reviewPass(int id) {
        BaseDao Utils = new BaseDao();
        Connection conn = Utils.getConnection();
        boolean flag = false;
        String sql = "update Products set review = ? where id = ? and review != ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setObject(1, "pass");
            pst.setObject(2, id);
            pst.setObject(3, "pass");
            int rs = pst.executeUpdate();
            if (rs > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.release(conn, pst, rs);
        }
        return flag;
    }

    @Override
    public boolean reviewFailed(int id) {
        BaseDao Utils = new BaseDao();
        Connection conn = Utils.getConnection();
        boolean flag = false;
        String sql = "update Products set review = ? where id = ? and review != ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setObject(1, "failed");
            pst.setObject(2, id);
            pst.setObject(3, "failed");
            int rs = pst.executeUpdate();
            if (rs > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.release(conn, pst, rs);
        }
        return flag;
    }

    @Override
    public List<Product> queryByPage(int pageNow) {
        BaseDao Utils = new BaseDao();
        Connection conn = Utils.getConnection();
        List<Product> products = new ArrayList<>();
        int pageCount = 10; //每页计数
        int id;
        String type, code, name, unit, classification, norm, cost_price, purchase_price, unit_price, review;
        String sql = "select * from Products order by id limit ?,?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setObject(1, (pageNow - 1) * pageCount);
            pst.setObject(2, pageNow * pageCount);
            rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                type = rs.getString(2);
                code = rs.getString(3);
                name = rs.getString(4);
                unit = rs.getString(5);
                classification = rs.getString(6);
                norm = rs.getString(7);
                cost_price = rs.getString(8);
                purchase_price = rs.getString(9);
                unit_price = rs.getString(10);
                review = rs.getString(11);
                products.add(new Product(id, type, code, name, unit, classification, norm,
                        cost_price, purchase_price, unit_price, review));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.release(conn, pst, rs);
        }
        return products;
    }

    @Override
    public List<Product> searchAndQueryByPage(int pageNow, String a, String b, String c) {
        BaseDao Utils = new BaseDao();
        Connection conn = Utils.getConnection();
        List<Product> products = new ArrayList<>();
        int pageCount = 10; //每页计数
        int id;
        String type, code, name, unit, classification, norm, cost_price, purchase_price, unit_price, review;
        String sql = "select * from Products where code like ? and name like ? and classification like ? order by id limit ?,?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setObject(1, "%" + a + "%");
            pst.setObject(2, "%" + b + "%");
            pst.setObject(3, "%" + c + "%");
            pst.setObject(4, (pageNow - 1) * pageCount);
            pst.setObject(5, pageNow * pageCount);
            rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                type = rs.getString(2);
                code = rs.getString(3);
                name = rs.getString(4);
                unit = rs.getString(5);
                classification = rs.getString(6);
                norm = rs.getString(7);
                cost_price = rs.getString(8);
                purchase_price = rs.getString(9);
                unit_price = rs.getString(10);
                review = rs.getString(11);
                products.add(new Product(id, type, code, name, unit, classification, norm,
                        cost_price, purchase_price, unit_price, review));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.release(conn, pst, rs);
        }
        return products;
    }

    @Override
    public int getPageCount() {
        BaseDao Utils = new BaseDao();
        Connection conn = Utils.getConnection();
        int rowCount = 0; //查询到的数据总数
        int pageCount = 1; //页码最大值
        String sql = "select count(*) from Products";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.first();
            rowCount = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.release(conn, pst, rs);
        }
        if (rowCount % 10 == 0) {
            pageCount = rowCount / 10;
        } else {
            pageCount = rowCount / 10 + 1;
        }
        return pageCount;
    }

    @Override
    public int getSearchedPageCount(String a, String b, String c) {
        BaseDao Utils = new BaseDao();
        Connection conn = Utils.getConnection();
        int rowCount = 0; //查询到的数据总数
        int pageCount = 1; //页码最大值
        String sql = "select count(*) from Products where code like ? and name like ? and classification like ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setObject(1, a + "%");
            pst.setObject(2, b + "%");
            pst.setObject(3, c + "%");
            rs = pst.executeQuery();
            rs.first();
            rowCount = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.release(conn, pst, rs);
        }
        if (rowCount % 10 == 0) {
            pageCount = rowCount / 10;
        } else {
            pageCount = rowCount / 10 + 1;
        }
        return pageCount;
    }
}