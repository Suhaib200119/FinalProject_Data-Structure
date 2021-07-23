
package finalproject;


public class Users {
    
    private String name;
    private String password;
    private boolean isAdmin;
    private boolean isActive;
    public Users(String name, String password, boolean isAdmin, boolean isActive) {
        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean IsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean IsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return  "{ name=" + name + ", password=" + password + ", isAdmin=" + isAdmin + ", isActive=" + isActive + '}';
    }
    
    
    
}
