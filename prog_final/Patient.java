public class Patient extends User {
    private int age;
    private String gender;
    private String assignedDoctor;
    private String phone;
    private Appointment[] appointments = new Appointment[10];
    private int appointmentCount = 0;
    

    
    public Patient(String ID, String name, String username, String password, int age, String gender,
            String assignedDoctor, String phone, Appointment[] appointments, int appointmentCount) {
        super(ID, name, username, password);
        this.age = age;
        this.gender = gender;
        this.assignedDoctor = assignedDoctor;
        this.phone = phone;
        this.appointments = appointments;
        this.appointmentCount = appointmentCount;
    }

    public int getAge(){
        return age;
    }

    public String getgender(){
        return gender;
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

    public void viewDoctor(){
        if (assignedDoctor!=null) {
            System.out.println("Assigned Dotor: "+assignedDoctor);
        }else{
            System.out.println("No Doctor assigned");
        }
    }

    public void viewAppointment(){
        if (appointmentCount==0) {
            System.out.println("No appointment");
        }else{
            for(int i=0;i<appointmentCount;i++){
                Appointment a = appointments[i];
                System.out.println("ID: "+a.getAppointmentId());
                System.out.println("Date: "+a.getDate());
                System.out.println("Time: "+a.getTime()+"\nStatus: "+a.getStatus());
                
            }
        }
    }

    public void bookAppointment(String appointmentId,String date,String time){
        
        if (assignedDoctor==null) {
            System.out.println("No doctor assigned");
            return;
        }

        if (appointmentCount==appointments.length) {
            System.out.println("List is full");
            return;
        }

        if (!Appointment.isDoctorAvailable(appointments, appointmentId, date, time)) {
            System.out.println("Doctor not available at this time");
            return;
        }

        Appointment ap = Appointment.createAppointment(appointmentId, time, appointmentId, date, time);

        if (ap!=null) {
            appointments[appointmentCount] = ap;
            appointmentCount++;
            System.out.println("Appointment booked");
        }
    }

    public void cancelAppointment(int index){
        if (index<0 || index>appointmentCount) {
            System.out.println("Invalid appointment");
            return;
        }
        appointments[index].updateStatus("Cancelled");
        System.out.println("Appontment Cancelled");
    }



}
