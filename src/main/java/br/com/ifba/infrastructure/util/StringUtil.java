package br.com.ifba.infrastructure.util;

/**
 * Classe utilitária para manipulação e validação de Strings.
 * 
 * Contém métodos estáticos reutilizáveis para evitar repetição
 * de código nas regras de negócio.
 */
public class StringUtil {

    /**
     * Verifica se uma String é nula ou vazia.
     * 
     * @param str String a ser verificada
     * @return true se for null ou vazia (ou só espaços), false caso contrário
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Verifica se a String possui tamanho mínimo.
     * 
     * @param str String a ser verificada
     * @param min tamanho mínimo esperado
     * @return true se atender ao tamanho mínimo, false caso contrário
     */
    public static boolean hasMinLength(String str, int min) {
        return str != null && str.trim().length() >= min;
    }

    /**
     * Verifica se a String não ultrapassa o tamanho máximo.
     * 
     * @param str String a ser verificada
     * @param max tamanho máximo permitido
     * @return true se estiver dentro do limite, false caso contrário
     */
    public static boolean hasMaxLength(String str, int max) {
        return str != null && str.trim().length() <= max;
    }

    /**
     * Remove espaços em branco do início e fim da String.
     * 
     * @param str String a ser tratada
     * @return String sem espaços nas extremidades ou null
     */
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }
}