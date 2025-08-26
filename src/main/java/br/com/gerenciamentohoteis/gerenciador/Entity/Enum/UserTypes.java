package br.com.gerenciamentohoteis.gerenciador.Entity.Enum;

public enum UserTypes {
    USER("USER"),
    ADMIN("ADMIN");

    private String role;

    UserTypes(String role) {
        this.role = role;
    }
    public String getRole(){
        return role;
    }
}
