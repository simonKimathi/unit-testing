package io.jotech.classicmodels.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "customers", indexes = {
        @Index(name = "salesRepEmployeeNumber", columnList = "salesRepEmployeeNumber")
})
public class Customer implements Serializable {

    @Id
    @Column(name = "customerNumber", nullable = false)
    @JsonProperty(value = "customerNumber")
    private Integer customerNumber;

    @Size(max = 50)
    @NotNull
    @Column(name = "customerName", nullable = false, length = 50)
    private String customerName;

    @Size(max = 50)
    @NotNull
    @Column(name = "contactLastName", nullable = false, length = 50)
    private String contactLastName;

    @Size(max = 50)
    @NotNull
    @Column(name = "contactFirstName", nullable = false, length = 50)
    private String contactFirstName;

    @Size(max = 50)
    @NotNull
    @Column(name = "phone", nullable = false, length = 50)
    private String phone;

    @Size(max = 50)
    @NotNull
    @Column(name = "addressLine1", nullable = false, length = 50)
    private String addressLine1;

    @Size(max = 50)
    @Column(name = "addressLine2", length = 50)
    private String addressLine2;

    @Size(max = 50)
    @NotNull
    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Size(max = 50)
    @Column(name = "state", length = 50)
    private String state;

    @Size(max = 15)
    @Column(name = "postalCode", length = 15)
    private String postalCode;

    @Size(max = 50)
    @NotNull
    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salesRepEmployeeNumber",foreignKey = @ForeignKey(name = "customers_ibfk_1"))
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "employeeNumber")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("salesRepEmployeeNumber")
    @Setter(AccessLevel.NONE)
    private Employee salesRepEmployeeNumber;

    @Column(name = "creditLimit", precision = 10, scale = 2)
    private BigDecimal creditLimit;

    @JsonProperty("salesRepEmployeeNumber")
    public void setSalesRepEmployeeNumberByEmployeeNumber(Integer employeeNumber) {
        salesRepEmployeeNumber = Employee.fromEmployeeNumber(employeeNumber);

    }


    public static Customer fromCustomerNumber(Integer customerNumber) {

        return Customer.builder().customerNumber(customerNumber).build();
    }




}
