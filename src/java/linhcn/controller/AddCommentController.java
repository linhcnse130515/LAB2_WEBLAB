/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhcn.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import linhcn.comment.CommentDAO;
import linhcn.comment.CommentDTO;

/**
 *
 * @author nguye
 */
@WebServlet(name = "AddCommentController", urlPatterns = {"/AddCommentController"})
public class AddCommentController extends HttpServlet {

    private final String ERROR = "error";
    private final String FAIL = "loginHtml";
    private final String SUCCESS = "articleDetailAction";

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
        String url = ERROR;
        try {
            HttpSession session = request.getSession(true);
            AccountDTO user = (AccountDTO) session.getAttribute("USER");
            if (user != null) {
                String comment = request.getParameter("txtComment");
                int articleId = Integer.parseInt(request.getParameter("txtArticleId"));
                ArticleDAO articleDAO = new ArticleDAO();
                ArticleDTO article = articleDAO.getById(articleId);
                if (null != article) {
                    CommentDAO commentDAO = new CommentDAO();
                    Date date = new Date(System.currentTimeMillis());
                    String email = user.getEmail();

                    CommentDTO commentDTO = new CommentDTO(null, comment, email, date, articleId);
                    boolean result = commentDAO.createActicle(commentDTO);
                    if (result) {
                        url = SUCCESS;
                        request.setAttribute("MESSAGE", "Com created, waiting for approval");
                    }
                }
            } else {
                url = FAIL;
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
