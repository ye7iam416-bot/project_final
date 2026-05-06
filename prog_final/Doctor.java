public class Doctor extends User {
    String specialization;
    String department;
    String phonenumber;
    String[] assignedPatients = new String[100]; // array ثابت
    String[] appointments = new String[100];
    int patientCount = 0;
    int appointmentCount = 0;
    
    public Doctor(String ID, String name, String username, String password, String specialization, String department,String phonenumber,
            String[] assignedPatients, String[] appointments, int patientCount, int appointmentCount) {
        super(ID, name, username, password);
        this.specialization = specialization;
        this.department = department;
        this.phonenumber =phonenumber;
        this.assignedPatients = assignedPatients;
        this.appointments = appointments;
        this.patientCount = patientCount;
        this.appointmentCount = appointmentCount;
    }
    @Override
public void displayInfo() {
        System.out.println("ID: " + ID);
        System.out.println("Name: " + name);
        System.out.println("Username: " + username);
        System.out.println("specialization"+specialization);
        System.out.println("department"+department);
        System.out.println("phonenumber"+phonenumber);   
    }

    public void viewAppointment(){
    if (appointmentCount==0) {
     System.out.println("No Appointment for Dr."+ name);   
    return;
    }
    System.out.println("Dr."+ name +"is Appointment:");
    for(int i=0; i< appointmentCount; i++){
    System.out.println((i+1)+"."+appointments[i]);
    }
  
   }
public void viewAssignedPatients() {
    if (patientCount == 0) {
        System.out.println("No patients assigned to Dr. " + name);
    } else {
        System.out.println("Assigned Patients for Dr. " + name + " :");
        for (int i = 0; i < patientCount; i++) {
            System.out.println((i + 1) + ". Patient ID: " + assignedPatients[i]);
        }
        System.out.println("Total patients: " + patientCount);
    }
}
    



   
    
} 
