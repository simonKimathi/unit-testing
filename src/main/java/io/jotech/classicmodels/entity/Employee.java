package io.jotech.classicmodels.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;

import static jakarta.persistence.FetchType.LAZY;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "employees", indexes = {
        @Index(name = "officeCode", columnList = "officeCode"),
        @Index(name = "reportsTo", columnList = "reportsTo")
})
public class Employee implements Serializable {
    @Id
    @Column(name = "employeeNumber", nullable = false)
    @JsonProperty(value = "employeeNumber")
    private Integer employeeNumber;

    @Size(max = 50)
    @NotNull
    @Column(name = "lastName", nullable = false, length = 50)
    private String lastName;

    @Size(max = 50)
    @NotNull
    @Column(name = "firstName", nullable = false, length = 50)
    private String firstName;

    @Size(max = 10)
    @NotNull
    @Column(name = "extension", nullable = false, length = 10)
    private String extension;

    @Size(max = 100)
    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    private String email;


    @NotNull
    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "officeCode", nullable = false,foreignKey = @ForeignKey(name = "employees_ibfk_2"))
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "officeCode")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("officeCode")
    @Setter(AccessLevel.NONE)
    private Office officeCode;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "reportsTo",foreignKey = @ForeignKey(name = "employees_ibfk_1"))
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "employeeNumber")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("reportsTo")
    @Setter(AccessLevel.NONE)
    private Employee reportsTo;


    @Size(max = 50)
    @NotNull
    @Column(name = "jobTitle", nullable = false, length = 50)
    private String jobTitle;



    @JsonProperty("officeCode")
    public void setOfficeByOfficeCode(String officeCod) {
        officeCode = Office.fromOfficeCode(officeCod);

    }

    @JsonProperty("reportsTo")
    public void setReportsToByEmployeeNumber(Integer employeeNumber) {
        reportsTo = Employee.fromEmployeeNumber(employeeNumber);

    }

    public static Employee fromEmployeeNumber(Integer employeeNumber) {

        return Employee.builder().employeeNumber(employeeNumber).build();
    }

}
