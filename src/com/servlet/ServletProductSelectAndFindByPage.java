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

@WebServlet(name = "ServletProductSelectAndFindByPage", urlPatterns = "/product_select")
public class ServletProductSelectAndFindByPage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ServletProductSelectAndFindByPage On");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        ProductService ps = new ProductServiceImp();
        int pageNow;
        if (request.getParameter("pageNow") == null) {
            pageNow = 1;
        } else {
            pageNow = Integer.parseInt(request.getParameter("pageNow"));
        }
        System.out.println("pageNow=" + pageNow);
        String a = request.getParameter("a");
        String b = request.getParameter("b");
        String c = request.getParameter("c");
        System.out.println("a:" + a);
        System.out.println("b:" + b);
        System.out.println("c:" + c);
        int pageCount = ps.getSearchedPageCount(a, b, c);
        System.out.println("selectedPageCount:" + pageCount);
        List<Product> products = ps.searchAndQueryByPage(pageNow, a, b, c);
        request.setAttribute("pageNow", pageNow);
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("products", products);
        request.setAttribute("pp", "product_select");
        request.setAttribute("a", a);
        request.setAttribute("b", b);
        request.setAttribute("c", c);
        if (products != null) {
            request.getRequestDispatcher("hello.jsp").forward(request, response);
        }
        System.out.println("ServletProductSelectAndFindByPage Off");
    }
}
