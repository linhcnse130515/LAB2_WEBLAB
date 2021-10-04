/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhcn.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import linhcn.article.ArticleDAO;
import linhcn.article.ArticleDTO;

/**
 *
 * @author nguye
 */
public class HomeController extends HttpServlet {

    private final static String SUCCESS = "homePage";
    private final static String FAIL = "loginHtml";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = FAIL;
        try {

            HttpSession session = request.getSession();

            String txtSearch = request.getParameter("txtSearch");
            String search = "";
            if (txtSearch != null) {
                search = txtSearch;
            }
            String txtPage = request.getParameter("page");
            int pageNum = 1;
            if (txtPage != null) {
                pageNum = Integer.parseInt(txtPage);
            }

            ArticleDAO dao = new ArticleDAO();
            int pages = dao.getTotalPageSearch(search);
            List<ArticleDTO> list = dao.getAllActicleActive(search, pageNum);

            request.setAttribute("SEARCH", search);
            session.setAttribute("LIST", list);
            request.setAttribute("PAGES", pages);

            url = SUCCESS;

        } catch (NumberFormatException | SQLException | NamingException e) {
            log("Exception at SearchController" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
