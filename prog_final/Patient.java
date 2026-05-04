public class Patient extends User {
     int age;
    String gender;
    String assignedDoctor;
    String phone;
    String[] appointments = new String[100];
    int appointmentCount = 0;

    
    public Patient(String ID, String name, String username, String password, int age, String gender,
            String assignedDoctor, String phone, String[] appointments, int appointmentCount) {
        super(ID, name, username, password);
        this.age = age;
        this.gender = gender;
        this.assignedDoctor = assignedDoctor;
        this.phone = phone;
        this.appointments = appointments;
        this.appointmentCount = appointmentCount;
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
