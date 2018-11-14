/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO;
import model.DAOException;
import model.DataSourceFactory;
import model.DiscountCodeEntity;

/**
 *
 * @author pedago
 */

public class DiscountCodeController extends HttpServlet {

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
        try{
            DAO dao = new DAO(DataSourceFactory.getDataSource());
            
            String action = request.getParameter("action");
                        
            if(action != null){
                char code = request.getParameter("code").charAt(0);
                String messageConfirmation = null;
                
                switch (action) {
                    case "DELETE":{
                            try{
                                dao.sup(code);
                                messageConfirmation = "Le code "+code+" a bien été supprimé de la table";
                            }catch(DAOException ex){
                                messageConfirmation = "ERREUR: Le code "+code+" ne peut pas être supprimé: il est utilisé"; 
                            }
                        }
                        
                        break;
                    
                    
                    case "ADD":{
                        double taux = Double.parseDouble(request.getParameter("taux"));
                        try{
                            dao.ajout(code, taux);
                            messageConfirmation = "Le code "+code+" a bien été ajouté à la table";
                        }catch(DAOException ex){
                            messageConfirmation = "ERREUR: Le code "+code+" est déjà présent dans la table";
                        }
                        break;
                    }
                    
           
                    default:
                        break;
                }
                
                
               
                request.setAttribute("confirmationAction", messageConfirmation);
                
            }
               
            
            List<DiscountCodeEntity> fullTable = dao.Liste();
            List<Character> usedDiscountCodes = dao.usedDiscountCodes();

            request.setAttribute("usedCodes", usedDiscountCodes);
            request.setAttribute("fullTable", fullTable);
            request.getRequestDispatcher("views/view.jsp").forward(request, response);
            
        }catch(IOException | NumberFormatException | SQLException | ServletException | DAOException ex){
            request.setAttribute("confirmationAction", ex.getMessage());
            request.getRequestDispatcher("views/view.jsp").forward(request, response);
            Logger.getLogger("servlet").log(Level.SEVERE, "Erreur de traitement", ex);
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
