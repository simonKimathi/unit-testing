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
import jakarta.persistence.IdClass;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "orderdetails", indexes = {
        @Index(name = "productCode", columnList = "productCode")
})
@IdClass(OrderDetailID.class)
public class OrderDetail implements Serializable {


    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "orderNumber", nullable = false,foreignKey = @ForeignKey(name = "orderdetails_ibfk_1"))
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "orderNumber")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("orderNumber")
    @Setter(AccessLevel.NONE)
    private Order orderNumber;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productCode", nullable = false,foreignKey = @ForeignKey(name = "orderdetails_ibfk_2"))
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "productCode")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("productCode")
    @Setter(AccessLevel.NONE)
    private Product productCode;

    @NotNull
    @Column(name = "quantityOrdered", nullable = false)
    private Integer quantityOrdered;

    @NotNull
    @Column(name = "priceEach", nullable = false, precision = 10, scale = 2)
    private BigDecimal priceEach;

    @NotNull
    @Column(name = "orderLineNumber", nullable = false)
    private Short orderLineNumber;



    @JsonProperty("orderNumber")
    public void setOrderById(Integer orderNum) {
        orderNumber = Order.fromId(orderNum);

    }



    @JsonProperty("productCode")
    public void setProductByProductCode(String prdCode) {
        productCode = Product.fromProductCode(prdCode);

    }


}
