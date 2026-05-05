public class Appointment {
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private String date;
    private String time;
    private String status;


    public Appointment(String appointmentId, String patientId, String doctorId, String date, String time,
            String status) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
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
//=====================================================    
public static Appointment createAppointment(String id, String patientId, String doctorId,
    String date, String time) {

    if (doctorId == null || doctorId.isEmpty()) {
        System.out.println("Patient must be assigned to a doctor first");
        return null;
    }

    if (date == null || date.isEmpty()) {
        System.out.println("Date can't be empty");
        return null;
    }

    if (time == null || time.isEmpty()) {
        System.out.println("Time can't be empty");
        return null;
    }

    return new Appointment(id, patientId, doctorId, date, time, "confirmed");
}
    // ================================
    
    public static boolean isDoctorAvailable(Appointment[] arr_Doc_avb,
    String doctorId, String date, String time) {

    for (int i = 0; i < arr_Doc_avb.length; i++) {

        Appointment a = arr_Doc_avb[i];
        if (a == null) continue;

        if (a.getDoctorId().equals(doctorId)
                && a.getDate().equals(date)
                && a.getTime().equals(time)
                && !a.getStatus().equals("cancelled")) {

            return false;
        }
    }

    return true;
}
//========================================
public void updateStatus(String newStatus) {
    String formattedNewStatus = newStatus.toLowerCase();
    String currentStatus = this.status.toLowerCase();
    if (currentStatus.equals("cancelled") && formattedNewStatus.equals("completed")) {
        System.out.println("Error: A cancelled appointment cannot be marked as completed.");
        
        return; 
    }

    
    this.status = newStatus;
    System.out.println("Status updated successfully to: " + newStatus);
}

public String toFileString() {
    return appointmentId + "," + patientId + "," + doctorId + ","
            + date + "," + time + "," + status;
}
}
