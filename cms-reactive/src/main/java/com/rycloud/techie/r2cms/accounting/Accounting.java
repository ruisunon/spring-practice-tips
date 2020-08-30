package com.rycloud.techie.r2cms.accounting;

import javax.persistence.Column;
    import javax.persistence.Entity;
    import javax.persistence.GeneratedValue;
    import javax.persistence.GenerationType;
    import javax.persistence.Id;
    import javax.persistence.Table;

@Entity
@Table(name = "Accountings")
public class Accounting {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private long id;
  @Column(name = "first_name")
  private String fName;
  @Column(name = "last_name")
  private String lName;
  @Column(name = "specialization")
  private String specialization;

  public Accounting(String fName, String lName, String specialization) {
    this.fName = fName;
    this.lName = lName;
    this.specialization = specialization;
  }

  Accounting() {
  }

  public long getId() {
    return id;
  }

  public String getfName() {
    return fName;
  }

  public String getlName() {
    return lName;
  }

  public String getSpecialization() {
    return specialization;
  }

}