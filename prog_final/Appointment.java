public class Appointment {

    private String appointmentId;
    private String patientId;
    private String doctorId;
    private String date;
    private String time;
    private String status;

    public Appointment() {

    }

    public Appointment(String appointmentId, String patientId,String doctorId,String date,
String time, String status) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        setDate(date);
        setTime(time);
        setStatus(status);
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
        if (date == null || date.trim().equals("")) {
           System.out.println("Appointment date cannot be empty.");
            return;
        }
      this.date = date;
    }

    public void setTime(String time) {
        if (time == null || time.trim().equals("")) {
            System.out.println("Appointment time cannot be empty.");
            return;
        }
        this.time = time;
    }
    public void setStatus(String status) {
        if (!(status.equalsIgnoreCase("Confirmed") ||
                status.equalsIgnoreCase("Completed") ||
                status.equalsIgnoreCase("Cancelled"))) {

            System.out.println("Invalid appointment status.");
            return;
        }

        if (this.status != null &&
                this.status.equalsIgnoreCase("Cancelled") &&
                status.equalsIgnoreCase("Completed")) {

            System.out.println("Cancelled appointment cannot be completed.");
            return;
        }
        this.status = status;
    }
    public void displayAppointment() {
        System.out.println("Appointment ID: " + appointmentId);
        System.out.println("Patient ID: " + patientId);
        System.out.println("Doctor ID: " + doctorId);
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.println("Status: " + status);
    }
}