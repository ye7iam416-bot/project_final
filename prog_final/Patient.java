public class Patient extends User {

    private int age;
    private String gender;
    private Doctor assignedDoctor;
    private Appointment[] appointments = new Appointment[100];
    private int appointmentCount = 0;

    public Patient() {

    }

    public Patient(String id,String name,String username,String password,String phone,int age,
    String gender) {
        super(id, name, username, password, phone);
        setAge(age);
        setGender(gender);
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Doctor getAssignedDoctor() {
        return assignedDoctor;
    }

    public Appointment[] getAppointments() {
        return appointments;
    }

    public int getAppointmentCount() {
        return appointmentCount;
    }

    public void setAge(int age) {

        if (age <= 0 || age >= 120) {

            System.out.println("Invalid age.");
            return;
        }

        this.age = age;
    }

    public void setGender(String gender) {

        if (gender == null || gender.trim().equals("")) {

            System.out.println("Gender cannot be empty.");
            return;
        }

        this.gender = gender;
    }

    public void setAssignedDoctor(Doctor assignedDoctor) {

        if (assignedDoctor == null) {

            System.out.println("Invalid doctor.");
            return;
        }

        this.assignedDoctor = assignedDoctor;
    }

    @Override
    public void displayInfo() {
        System.out.println("Patient ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Username: " + username);
        System.out.println("Phone: " + phone);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        if (assignedDoctor != null) {
            System.out.println("Assigned Doctor: "
                    + assignedDoctor.getName());
        } else {
            System.out.println("Assigned Doctor: None");
        }
    }

    public void viewDoctor() {
        if (assignedDoctor == null) {
            System.out.println("No doctor assigned.");
            return;
        }
        System.out.println("====> Assigned Doctor <====");
        assignedDoctor.displayInfo();
        System.out.println("-------------------");
    }

    public boolean addAppointment(Appointment appointment) {
        if (appointment == null) {
            System.out.println("Invalid appointment.");
            return false;
        }

        for (int i = 0; i < appointmentCount; i++) {
            if (appointments[i].getAppointmentId()
                    .equals(appointment.getAppointmentId())) {
                System.out.println("Appointment already exists.");
                return false;
            }
        }

        if (appointmentCount >= appointments.length) {
            System.out.println("Appointment list is full.");
            return false;
        }
        appointments[appointmentCount++] = appointment;
        return true;
    }

    public int bookAppointment(Appointment[] allAppointments,int allAppointmentCount,
    String appointmentId,String patientId,String doctorId,String date,String time,
    String status) {

        if (assignedDoctor == null) {
            System.out.println("Patient must be assigned to a doctor first.");
            return allAppointmentCount;
        }
        if (!doctorId.equals(assignedDoctor.getId())) {
            System.out.println("You can only book with assigned doctor.");
            return allAppointmentCount;
        }

        Appointment appointment = new Appointment(appointmentId,patientId,doctorId,date,
        time,status);

        if (!addAppointment(appointment)) {

            return allAppointmentCount;
        }
        assignedDoctor.addAppointment(appointment);
        allAppointments[allAppointmentCount++] = appointment;
        System.out.println("Appointment booked successfully.");
        System.out.println("-------------------");
        return allAppointmentCount;
    }
    public void viewAppointments() {
        System.out.println("====> My Appointments <====");
        if (appointmentCount == 0) {
            System.out.println("No appointments.");
            return;
        }
        for (int i = 0; i < appointmentCount; i++) {
            appointments[i].displayAppointment();
            System.out.println("-------------------");
        }
    }
    public void cancelAppointment(String appointmentId) {
        for (int i = 0; i < appointmentCount; i++) {
            if (appointments[i].getAppointmentId()
                    .equals(appointmentId)) {
                if (appointments[i].getStatus()
                        .equalsIgnoreCase("Completed")) {
                    System.out.println("Completed appointment cannot be cancelled.");
                    return;
                }
                appointments[i].setStatus("Cancelled");
                System.out.println("Appointment cancelled successfully.");
                return;
            }
        }
        System.out.println("Appointment not found.");
    }
}