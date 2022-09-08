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
import java.util.List;

@WebServlet(name = "ServletProductReviewFailed", urlPatterns = "/review_failed")
public class ServletProductReviewFailed extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ServletProductReviewFailed On");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        ProductService ps = new ProductServiceImp();
        String ids = request.getParameter("ids");
        System.out.println("ids = " + ids);
        ids = ids.substring(1, ids.length() - 2);
        String[] id = ids.split(",");
        response.setContentType("text/html;charset=gb2312");
        PrintWriter out = response.getWriter();
        out.print("0");
        for (int i = 0; i < id.length; i++) {
            id[i] = id[i].replace("\"", "");
            System.out.println("id:" + id[i]);
            if (ps.reviewFailed(Integer.parseInt(id[i]))) {
                System.out.println("审核不通过");
            } else {
                System.out.println("此条审核操作失败");
                out.print("1");
                break;
            }
        }
        out.flush();
        out.close();
        int pageNow = 1;
        int pageCount = ps.getPageCount();
        List<Product> products = ps.queryByPage(pageNow);
        request.setAttribute("pageNow", pageNow);
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("products", products);
        request.getRequestDispatcher("hello.jsp").forward(request, response);
        System.out.println("ServletProductReviewFailed Off");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

