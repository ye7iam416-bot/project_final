public class Doctor extends User {
    String specialization;
    String department;
    String[] assignedPatients = new String[100]; // array ثابت
    String[] appointments = new String[100];
    int patientCount = 0;
    int appointmentCount = 0;
    
    public Doctor(String ID, String name, String username, String password, String specialization, String department,
            String[] assignedPatients, String[] appointments, int patientCount, int appointmentCount) {
        super(ID, name, username, password);
        this.specialization = specialization;
        this.department = department;
        this.assignedPatients = assignedPatients;
        this.appointments = appointments;
        this.patientCount = patientCount;
        this.appointmentCount = appointmentCount;
    }

    
    
} 
