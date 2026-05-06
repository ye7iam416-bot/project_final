public class Doctor extends User {
    String specialization;
    String department;
    String phonenumber;
    String status;
    Patient[] assignedPatients = new Patient[100];// array ثابت
   Appointment[] appointments = new Appointment[100];
    int patientCount = 0;
    int appointmentCount = 0;
    
    public Doctor(String ID, String name, String username, String password, String specialization, String department,String phonenumber,
         String status,Patient[] assignedPatients,Appointment[] appointments, int patientCount, int appointmentCount) {
        super(ID, name, username, password);
        this.specialization = specialization;
        this.department = department;
        this.phonenumber =phonenumber;
         this.status = status;
        this.patientCount = patientCount;
        this.appointmentCount = appointmentCount;
    }

    public void setStatus(String s){
        this.status = s;
    }

    public String getStatus(){
        return status;
    }
    @Override
       public void displayInfo() {
        super.displayInfo();
        System.out.println("specialization"+specialization);
        System.out.println("department"+department);
        System.out.println("phonenumber"+phonenumber);   
    }

   public  void addpatient(Patient p){
    if (patientCount<assignedPatients.length) {
     assignedPatients[patientCount++]=p;  
        }
  else{
    System.out.println("Patient list is full");
    }
}
public void viewAssignedPatients() {
    if (patientCount == 0) {
        System.out.println("No patients assigned to Dr. " + name);
    } else {
        System.out.println("Assigned Patients for Dr. " + name + " :");
        for (int i = 0; i < patientCount; i++) {
            System.out.println("Patient " + (i + 1));
             assignedPatients[i].displayInfo();
            System.out.println("-------------------");
        }
        System.out.println("Total patients: " + patientCount);
    }
}

  public void addAppointment(Appointment a) {
        if (appointmentCount < appointments.length) {
            appointments[appointmentCount++] = a;
        } else {
            System.out.println("Appointment list is full");
        }
    }

    public void viewAppointments() {
        if (appointmentCount == 0) {
            System.out.println("No appointments");
            return;
        }

        for (int i = 0; i < appointmentCount; i++) {
            Appointment a = appointments[i];
            System.out.println("ID: " + a.getAppointmentId());
            System.out.println("Patient ID: " + a.getPatientId());
            System.out.println("Date: " + a.getDate());
            System.out.println("Time: " + a.getTime());
            System.out.println("Status: " + a.getStatus());
            System.out.println("-------------------");
        }
    }
     public void updateAppointmentStatus(int index, String newStatus) {
        if (index < 0 || index >= appointmentCount) {
            System.out.println("Invalid appointment");
            return;
        }

        appointments[index].updateStatus(newStatus);
    }












   



   
    
} 
