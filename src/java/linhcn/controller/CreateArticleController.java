/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhcn.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import linhcn.account.AccountDTO;
import linhcn.article.ArticleDAO;
import linhcn.article.ArticleDTO;

/**
 *
 * @author nguye
 */
@WebServlet(name = "CreateArticleController", urlPatterns = {"/CreateArticleController"})
public class CreateArticleController extends HttpServlet {

    private final String STATUS = "new";
    private final String FAIL = "loginHtml";
    private final String SUCCESS = "createArticlePage";

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
            HttpSession session = request.getSession(true);
            AccountDTO user = (AccountDTO) session.getAttribute("USER");
            if (user != null) {
                String title = request.getParameter("txtTitle");
                String sortDescription = request.getParameter("txtDescription");
                String content = request.getParameter("txtContent");

                ArticleDAO acticleDao = new ArticleDAO();

                String author = user.getName();
                Date postDate = new Date(System.currentTimeMillis());
                String email = user.getEmail();

                ArticleDTO newArticle = new ArticleDTO(null, title, sortDescription, author, postDate, STATUS, email, content);
                boolean result = acticleDao.createActicle(newArticle);
                if (result) {
                    url = SUCCESS;
                    request.setAttribute("MESSAGE", "Acticle created, waiting for approval");
                } else {
                    url = SUCCESS;
                    request.setAttribute("MESSAGE", "Create faild");
                }
            }
        } catch (SQLException | NamingException e) {
            log("Exception at CreateArticleController" + e.toString());
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
