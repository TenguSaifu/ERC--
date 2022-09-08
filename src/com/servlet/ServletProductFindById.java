package com.servlet;

import com.entity.Product;
import com.service.ProductService;
import com.service.ProductServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletProductFindById", urlPatterns = "/product_findById")
public class ServletProductFindById extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ServletProductFindById On");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        ProductService ps = new ProductServiceImp();
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = ps.queryById(id);
        PrintWriter out = response.getWriter();
        out.print(product);
        out.flush();
        out.close();
        System.out.println("ServletProductFindById Off");
    }
}
