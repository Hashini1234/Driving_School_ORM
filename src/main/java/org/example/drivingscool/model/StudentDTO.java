package org.example.drivingscool.model;

public class StudentDTO {
    private int studentId;

    private String name;
    private String email;
    private String phone;
    private String address;
    private double registerFee;
    private String registrationDate;

    public StudentDTO() {

    }

    public StudentDTO(int studentId, String name, String email, String phone, String address, double registerFee, String registrationDate) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.registerFee = registerFee;
        this.registrationDate = registrationDate;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRegisterFee(double registerFee) {
        this.registerFee = registerFee;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public double getRegisterFee() {
        return registerFee;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

}