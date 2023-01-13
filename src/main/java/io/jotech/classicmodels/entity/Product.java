package io.jotech.classicmodels.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "products", indexes = {
        @Index(name = "productLine", columnList = "productLine")
})

public class Product implements Serializable {

    @Id
    @Column(name = "productCode", nullable = false, updatable = false)
    private String productCode;

    @NotNull
    @Size(max = 70)
    @Column(length = 70, nullable = false)
    private String productName;


    @OneToOne(optional = false)
    @JoinColumn(name = "productLine",foreignKey = @ForeignKey(name = "products_ibfk_1"))
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("productLine")
    @Setter(AccessLevel.NONE)
    private ProductLine productLine;

    @NotNull
    @Size(max = 10)
    @Column(length = 10, nullable = false)
    private String productScale;


    @NotNull
    @Size(max = 50)
    @Column(length = 50, nullable = false)
    private String productVendor;

    @NotNull
    @Column(nullable = false, columnDefinition = "text")
    private String productDescription;

    @NotNull
    @Column(nullable = false, columnDefinition = "smallint")
    private Integer quantityInStock;

    @NotNull
    @Column(nullable = false)
    BigDecimal buyPrice;

    @NotNull
    @Column(nullable = false,name = "MSRP")
    BigDecimal mSRP;


    @JsonProperty("productLine")
    public void setProductLineByLine(String line) {
        productLine = ProductLine.fromProductLine(line);

    }

    public static Product fromProductCode(String productCode) {

        return Product.builder().productCode(productCode).build();
    }
}
