public class Doctor extends User {
    private String specialization;
    private String department;
    private Patient[] patients = new Patient[100];
    private int patientCount = 0;
    private Appointment[] appointments = new Appointment[100];
    private int appointmentCount = 0;

    public Doctor(String id, String name, String username,
                  String password, String phone,
                  String specialization, String department) {
        super(id, name, username, password, phone);
        this.specialization = specialization;
        this.department = department;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getDepartment() {
        return department;
    }

    public Appointment[] getAppointments() {
        return appointments;
    }

    public int getAppointmentCount() {
        return appointmentCount;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void displayInfo() {
        System.out.println("Doctor ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Username: " + username);
        System.out.println("Phone: " + phone);
        System.out.println("Specialization: " + specialization);
        System.out.println("Department: " + department);
    }

public void addPatient(Patient patient) {
    if (patient == null) {
        return;
    }
    if (patientCount >= patients.length) {
        System.out.println("Patients list full.");
        return;
    }
    patients[patientCount++] = patient;
}

public void addAppointment(Appointment appointment) {

    if (appointment == null) return;

    if (appointmentCount >= appointments.length) {

        System.out.println("Appointments full.");
        return;
    }

    for (int i = 0; i < appointmentCount; i++) {

        if (appointments[i].getDate()
                .equals(appointment.getDate())
                &&
            appointments[i].getTime()
                .equals(appointment.getTime())
                &&
            !appointments[i].getStatus()
                    .equalsIgnoreCase("Cancelled")) {

            System.out.println("Doctor already has appointment at this time.");
            return;
        }
    }

    appointments[appointmentCount++] = appointment;
}

    public void viewPatients() {

    System.out.println("===== My Patients =====");
    if (patientCount == 0) {
        System.out.println("No assigned patients.");
        return;
    }
    for (int i = 0; i < patientCount; i++) {
        patients[i].displayInfo();
        System.out.println("-------------------");
    }
}

   public void viewAppointments() {
    System.out.println("===== My Appointments =====");
    if (appointmentCount == 0) {
        System.out.println("No appointments.");
        return;
    }

    for (int i = 0; i < appointmentCount; i++) {
        appointments[i].displayAppointment();
        System.out.println("-------------------");
    }
}
    public void updateAppointmentStatus(String appointmentId,String newStatus) {
        for (int i = 0; i < appointmentCount; i++) {
            if (appointments[i].getAppointmentId()
                    .equals(appointmentId)) {
                if (appointments[i].getStatus()
                        .equalsIgnoreCase("Cancelled")
                        && newStatus.equalsIgnoreCase("Completed")) {
                    System.out.println("Cancelled appointment cannot be completed.");
                    return;
                }
                if (appointments[i].getStatus()
                        .equalsIgnoreCase("Completed")
                        && newStatus.equalsIgnoreCase("Cancelled")) {

                    System.out.println("Completed appointment cannot be cancelled.");
                    return;
                }

                appointments[i].setStatus(newStatus);

                System.out.println("Appointment status updated successfully.");

                return;
            }
        }

        System.out.println("Appointment not found.");
    }
}