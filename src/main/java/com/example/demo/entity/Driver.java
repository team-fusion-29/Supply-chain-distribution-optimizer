package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "drivers")
public class Driver {

    // ==========================================
    // Primary Key
    // ==========================================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ==========================================
    // Driver Information
    // ==========================================

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Driver ID is required.") private String driverId;

    @Column(nullable = false)
    @NotBlank(message = "Driver name is required.") private String fullName;

    @Column(nullable = false, unique = true)
    @NotBlank @Email(message = "Enter a valid email address.") private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Licence number is required.") private String licenseNumber;

    @Column(nullable = false, unique = true)
    private String aadhaarNumber;

    private Integer age;

    private Integer experience;

    private String gender;

    // ==========================================
    // Address Details
    // ==========================================

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String pincode;
    // ==========================================
    // Driver Status
    // ==========================================

    @Column(nullable = false)
    private String status;

    // ==========================================
    // Assignment Details
    // ==========================================

    private String assignedVehicle;

    private String assignedShipment;

    private String assignedRoute;

    private String assignedWarehouse;

    // ==========================================
    // Emergency Contact
    // ==========================================

    private String emergencyContactName;

    private String emergencyContactNumber;

    // ==========================================
    // Employment Details
    // ==========================================

    private LocalDate joiningDate;

    private Double salary;

    // ==========================================
    // Audit Fields
    // ==========================================

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private String password;

    private String vehicleNumber;

    private String vehicleType;

    private boolean enabled = true;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.DRIVER;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // ==========================================
    // Constructors
    // ==========================================

    public Driver() {

    }

    public Driver(Long id, String driverId, String fullName,
                  String email, String phone,
                  String licenseNumber, String aadhaarNumber,
                  Integer age, Integer experience,
                  String gender, String address,
                  String city, String state,
                  String country, String pincode,
                  String status, String assignedVehicle,
                  String assignedShipment,
                  String assignedRoute,
                  String assignedWarehouse,
                  String emergencyContactName,
                  String emergencyContactNumber,
                  LocalDate joiningDate,
                  Double salary,
                  LocalDateTime createdDate,
                  LocalDateTime updatedDate) {

        this.id = id;
        this.driverId = driverId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.licenseNumber = licenseNumber;
        this.aadhaarNumber = aadhaarNumber;
        this.age = age;
        this.experience = experience;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
        this.status = status;
        this.assignedVehicle = assignedVehicle;
        this.assignedShipment = assignedShipment;
        this.assignedRoute = assignedRoute;
        this.assignedWarehouse = assignedWarehouse;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactNumber = emergencyContactNumber;
        this.joiningDate = joiningDate;
        this.salary = salary;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;

    }
    // ==========================================
    // Automatically Set Dates
    // ==========================================

    @PrePersist
    public void prePersist() {

        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

    }

    @PreUpdate
    public void preUpdate() {

        this.updatedDate = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

    }

    // ==========================================
    // Getters and Setters
    // ==========================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getAadhaarNumber() {
        return aadhaarNumber;
    }

    public void setAadhaarNumber(String aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignedVehicle() {
        return assignedVehicle;
    }

    public void setAssignedVehicle(String assignedVehicle) {
        this.assignedVehicle = assignedVehicle;
    }

    public String getAssignedShipment() {
        return assignedShipment;
    }

    public void setAssignedShipment(String assignedShipment) {
        this.assignedShipment = assignedShipment;
    }

    public String getAssignedRoute() {
        return assignedRoute;
    }

    public void setAssignedRoute(String assignedRoute) {
        this.assignedRoute = assignedRoute;
    }

    public String getAssignedWarehouse() {
        return assignedWarehouse;
    }

    public void setAssignedWarehouse(String assignedWarehouse) {
        this.assignedWarehouse = assignedWarehouse;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public void setEmergencyContactNumber(String emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // ==========================================
    // toString()
    // ==========================================

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", driverId='" + driverId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", status='" + status + '\'' +
                ", assignedVehicle='" + assignedVehicle + '\'' +
                ", assignedShipment='" + assignedShipment + '\'' +
                '}';
    }
    

}
