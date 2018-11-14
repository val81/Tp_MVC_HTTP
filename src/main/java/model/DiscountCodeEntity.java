package model;

/**
 * Classe représentant la table DISCOUNT_CODE
 * @author bbardy
 */
public class DiscountCodeEntity {
    private char code;
    private float taux;

    /**
     * Constructeur
     * @param code la clé du code de promotion
     * @param taux le taux en pourcentage
     */
    public DiscountCodeEntity(char code, float taux){
        this.code = code;
        this.taux = taux;
    }
    
    /**
     * @return le code de la promotion
     */
    public char getCode(){
        return code;
    }
    
    /**
     * @return le taux de la promotion
     */
    public float getTaux(){
        return taux;
    }
    
}
