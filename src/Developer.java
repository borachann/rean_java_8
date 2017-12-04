import java.time.LocalDate;

/**
 * Copyright (c) GL Finance Plc. All rights reserved. (http://www.gl-f.com/)
 * Author: Chann Bora (b.chann@gl-f.com) on 12/4/2017.
 */
public class Developer {
  public String developerName;
  public String address;
  public LocalDate dateOfBirth;
  public int developerAge;
  
  public Developer(String developerName, String address, LocalDate dateOfBirth, int developerAge) {
    this.developerName = developerName;
    this.address = address;
    this.dateOfBirth = dateOfBirth;
    this.developerAge = developerAge;
  }
  
  public Developer() {
    this.developerName = "Bora";
    this.address = "Phnom Penh";
    this.dateOfBirth = LocalDate.now().minusYears(24);
    this.developerAge = 24;
  }
  
  public String getDeveloperName() {
    return developerName;
  }
  
  public void setDeveloperName(String developerName) {
    this.developerName = developerName;
  }
  
  public String getAddress() {
    return address;
  }
  
  public void setAddress(String address) {
    this.address = address;
  }
  
  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }
  
  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }
  
  public int getDeveloperAge() {
    return developerAge;
  }
  
  public void setDeveloperAge(int developerAge) {
    this.developerAge = developerAge;
  }
  
  @Override
  public String toString() {
    return "Developer{" +
            "developerName='" + developerName + '\'' +
            ", address='" + address + '\'' +
            ", dateOfBirth=" + dateOfBirth +
            ", developerAge=" + developerAge +
            '}';
  }
}
