import java.util.ArrayList;
public class Patient extends User {
    private int age;
    private String gender;
    private String phone;
    private Doctor doctor;
    private ArrayList<Appointment> appointments;

    Patient(String ID, String name, String username, String password,int age, String gender, String phone){
        super(ID, name, username, password);
        this.age=age;
        this.gender=gender;
        this.phone=phone;
        this.doctor=null;
        this.appointments= new ArrayList<>();
    }

    public void setDoctor(Doctor doctor){
        this.doctor=doctor;
    }
    
    @Override
    public void displayInfo(){
        System.out.println("ID: " + ID);
        System.out.println("Name: " + name);
        System.out.println("Username: " + username);        
        System.out.println("Age: "+age);
        System.out.println("Gender: "+gender);
        System.out.println("Phone number: "+phone);
    }
}
