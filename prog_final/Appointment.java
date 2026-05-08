public class Appointment {
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private String date;
    private String time;
    private String status;
    public Appointment() {
    
    }
    public Appointment(String appointmentId,String patientId,String doctorId,
                       String date,String time,String status) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;

        if (date == null || date.equals("")) {
            System.out.println("Appointment date cannot be empty.");
            this.date = "Unknown";
        }
        else {

            this.date = date;
        }
        if (time == null || time.equals("")) {
            System.out.println("Appointment time cannot be empty.");
            this.time = "Unknown";
        }
        else {

            this.time = time;
        }
        this.status = status;
    }
    public String getAppointmentId() {
        return appointmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public void setDate(String date) {

        if (date == null || date.equals("")) {

            System.out.println("Date cannot be empty.");
            return;
        }

        this.date = date;
    }

    public void setTime(String time) {

        if (time == null || time.equals("")) {

            System.out.println("Time cannot be empty.");
            return;
        }

        this.time = time;
    }

    public void setStatus(String status) {

        if (this.status != null &&
                this.status.equalsIgnoreCase("Cancelled") &&
                status.equalsIgnoreCase("Completed")) {

            System.out.println("Cancelled appointment cannot be completed.");
            return;
        }
        this.status = status;
    }
    
    public void displayAppointment() {

        System.out.println("===== Appointment =====");

        System.out.println("Appointment ID: " + appointmentId);
        System.out.println("Patient ID: " + patientId);
        System.out.println("Doctor ID: " + doctorId);

        System.out.println("Date: " + date);
        System.out.println("Time: " + time);

        System.out.println("Status: " + status);

    }

}