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
import java.util.List;

@WebServlet(name = "ServletProductFindByPage", urlPatterns = "/hello")
public class ServletProductFindByPage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ServletProductFindByPage On");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        ProductService ps = new ProductServiceImp();
        int pageNow;
        if (request.getParameter("pageNow") == null) {
            pageNow = 1;
        } else {
            pageNow = Integer.parseInt(request.getParameter("pageNow"));
        }
        int pageCount = ps.getPageCount();
        List<Product> products = ps.queryByPage(pageNow);
        request.setAttribute("pageNow", pageNow);
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("products", products);
        if (products != null) {
            request.getRequestDispatcher("hello.jsp").forward(request, response);
        }
        System.out.println("ServletProductFindByPage Off");
    }
}
