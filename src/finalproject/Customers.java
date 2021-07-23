
package finalproject;


public class Customers {
    private String name;
    private String id;
    private String Addres;
    private String problem;
    private int Status ;
    private int numberCard;
    private String nameEmployee;

    public Customers(String name, String id, String Addres, String problem, int Status ,String nameEmployee) {
        this.name = name;
        this.id = id;
        this.Addres = Addres;
        this.problem = problem;
        this.Status = Status;
        this.nameEmployee=nameEmployee;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public int getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(int numberCard) {
        this.numberCard = numberCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddres() {
        return Addres;
    }

    public void setAddres(String Addres) {
        this.Addres = Addres;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        String StatusString="";
        if(Status==1){
            StatusString="Solved";
        }else{
            StatusString="Not Solved";
        }
        return "Customers{" + "name=" + name + ", id=" + id + ", Addres=" + Addres + ", problem=" + problem + ", Status=" + StatusString + '}';
    }
    public String toString2() {
        String StatusString="";
        if(Status==1){
            StatusString="Solved";
        }else{
            StatusString="Not Solved";
        }
        return "Customers{" + "name=" + name + ", id=" + id + ", Addres=" + Addres + ", problem=" + problem + ", Status=" + StatusString + ", Served By=" + nameEmployee + '}';
    }
    
    
    
}
