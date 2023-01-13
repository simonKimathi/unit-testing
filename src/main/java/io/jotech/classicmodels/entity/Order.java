package io.jotech.classicmodels.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.time.LocalDate;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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
@Table(name = "orders", indexes = {
        @Index(name = "customerNumber", columnList = "customerNumber")
})
public class Order implements Serializable {

    @Id
    @Column(name = "orderNumber", nullable = false)
    @JsonProperty(value = "orderNumber")
    private Integer orderNumber;

    @NotNull
    @Column(name = "orderDate", nullable = false)
    private LocalDate orderDate;

    @NotNull
    @Column(name = "requiredDate", nullable = false)
    private LocalDate requiredDate;

    @Column(name = "shippedDate")
    @JsonbDateFormat
    private LocalDate shippedDate;

    @Size(max = 15)
    @NotNull
    @Column(name = "status", nullable = false, length = 15)
    private String status;

    @Lob
    @Column(name = "comments")
    private String comments;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerNumber", nullable = false,foreignKey = @ForeignKey(name = "orders_ibfk_1"))
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "customerNumber")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("customerNumber")
    @Setter(AccessLevel.NONE)
    private Customer customerNumber;




    @JsonProperty("customerNumber")
    public void setCustomerByCustomerNumber(Integer customerNum) {
        customerNumber = Customer.fromCustomerNumber(customerNum);

    }

    public static Order fromId(Integer orderNumber) {

        return Order.builder().orderNumber(orderNumber).build();
    }

}
