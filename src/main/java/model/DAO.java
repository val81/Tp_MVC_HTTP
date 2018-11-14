package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class DAO {

	protected final DataSource myDataSource;

	/**
	 * @param dataSource la source de données à utiliser
	 */
	public DAO(DataSource dataSource) {
		this.myDataSource = dataSource;
	}


	/**
	 * Détruit un enregistrement dans la table DISCOUNT_CODE
	 * @param discountID la clé du code de promotion à détruire
	 * @return le nombre d'enregistrements détruits (1 ou 0 si pas trouvé)
	 * @throws DAOException
	 */
	public int sup(char discountID) throws DAOException {
		String sqlQuery = "DELETE FROM Discount_Code WHERE Discount_Code = ?";
                
		try (   Connection connection = myDataSource.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sqlQuery)
                ) {
			stmt.setString(1, String.valueOf(discountID));
			
			return stmt.executeUpdate();

		}  catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new DAOException(ex.getMessage());
		}
	}
        
        
        
        /**
         * Ajoute un enregistrement dans la table DISCOUNT_CODE
         * @param discountID la clé du code de promotion à ajouter
         * @param taux le taux de réduction en pourcentage
         * @return le nombre d'enregistrements ajoutés (1 ou 0 si pas trouvé)
         * @throws DAOException 
         */
	public int ajout(char discountID, double taux) throws DAOException {
            String sqlQuery = "INSERT INTO Discount_Code VALUES (?, ?)";
            
            try (   Connection connection = myDataSource.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sqlQuery)
            ) {

                    stmt.setString(1, String.valueOf(discountID));
                    stmt.setDouble(2, taux);

                    return stmt.executeUpdate();

            }  catch (SQLException ex) {
                    Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
                    throw new DAOException(ex.getMessage());
            }
	}
        
        /**
         * @return la liste des codes déjà utilisés
         * @throws DAOException 
         */
        public List<Character> usedDiscountCodes() throws DAOException{
            ArrayList<Character> table = new ArrayList<>();
            String sqlQuery = "SELECT DISTINCT Discount_Code FROM Product_Code";
            try (   Connection connection = myDataSource.getConnection();
                    Statement stmt = connection.createStatement();
            ) {
                    ResultSet rs = stmt.executeQuery(sqlQuery);
                    while(rs.next()){
                        char code = rs.getString("DISCOUNT_CODE").charAt(0);
                        table.add(code);
                    }

            }  catch (SQLException ex) {
                    Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
                    throw new DAOException(ex.getMessage());
            }
            
            return table;
        } 
        
        /**
         * @return la table Discount_Code sous forme de liste
         * @throws DAOException 
         */
        public List<DiscountCodeEntity> Liste() throws DAOException{
            ArrayList<DiscountCodeEntity> table = new ArrayList<>();
            String sqlQuery = "SELECT * FROM Discount_Code";
            try (   Connection connection = myDataSource.getConnection();
                    Statement stmt = connection.createStatement();
            ) {
                    ResultSet rs = stmt.executeQuery(sqlQuery);
                    while(rs.next()){
                        char code = rs.getString("DISCOUNT_CODE").charAt(0);
                        float taux = rs.getFloat("RATE");
                        table.add(new DiscountCodeEntity(code, taux));
                    }

            }  catch (SQLException ex) {
                    Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
                    throw new DAOException(ex.getMessage());
            }
            
            return table;
        }
        
        /**
         * Modifie un enregistrement de la table Discount_Code
         * @param discountID
         * @param taux
         * @return le nombre d'enregistrements ajoutés (1 ou 0 si pas trouvé)
         * @throws DAOException 
         */
       
}
