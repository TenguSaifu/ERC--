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

@WebServlet(name = "ServletProductUpdate", urlPatterns = "/product_update")
public class ServletProductUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ServletProductUpdate On");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        ProductService ps = new ProductServiceImp();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String unit = request.getParameter("unit");
        String classification = request.getParameter("classification");
        String norm = request.getParameter("norm");
        String cost_price = request.getParameter("cost_price");
        String purchase_price = request.getParameter("purchase_price");
        String unit_price = request.getParameter("unit_price");
        response.setContentType("text/html;charset=gb2312");
        PrintWriter out = response.getWriter();
        if (name.equals("") || unit.equals("") || classification.equals("") || norm.equals("")) {
            System.out.println("必要值为空，修改失败！");
            out.print("<script>alert('必要值为空，修改失败！');window.location.href='hello'</script>");
            out.flush();
            out.close();
        } else {
            if (ps.update(id, name, unit, classification, norm, cost_price, purchase_price, unit_price)) {
                int pageNow = 1;
                int pageCount = ps.getPageCount();
                List<Product> products = ps.queryByPage(pageNow);
                request.setAttribute("users", products);
                request.setAttribute("pageNow", pageNow);
                request.setAttribute("pageCount", pageCount);
                request.getRequestDispatcher("/hello").forward(request, response);
            } else {
                System.out.println("修改产品失败，请检查输入字段是否有误！");
            }
        }
        System.out.println("ServletProductUpdate Off");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
