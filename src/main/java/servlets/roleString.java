package servlets;

public class roleString {
    public roleString(String role, String notrole) {
        this.role = role;
        this.notrole = notrole;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNotrole() {
        return notrole;
    }

    public void setNotrole(String notrole) {
        this.notrole = notrole;
    }

    public String role;
    public String notrole;
}