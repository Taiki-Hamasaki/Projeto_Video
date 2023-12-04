/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Conexao;

/**
 *
 * @author taiki-hamasaki
 */
public class Errors {
    public static String getStackTraceFormatted(Exception e) {
        String retorno = e.getMessage() + "\n";
        for(int i = 0;i<e.getStackTrace().length;i++) {
            if(i %2 == 0) {
                retorno += e.getStackTrace()[i] + ", ";
            } else {
                retorno += e.getStackTrace()[i] + "\n";
            }
        }
        return retorno;
    }
}
