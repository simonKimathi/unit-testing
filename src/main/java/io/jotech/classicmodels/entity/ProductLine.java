package io.jotech.classicmodels.entity;

import java.io.Serializable;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
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
@Table(name = "productlines")
public class ProductLine implements Serializable {

    @Id
    @Size(max = 50)
    @Column(name = "productLine", nullable = false, length = 50)
    @JsonbProperty(value = "productLine")
    private String id;

    @Size(max = 4000)
    @Column(name = "textDescription", length = 4000)
    private String textDescription;

    @Lob
    @Column(name = "htmlDescription")
    private String htmlDescription;

    @Column(name = "image")
    private byte[] image;


    public static ProductLine fromProductLine(String productLine) {

        return ProductLine.builder().id(productLine).build();
    }

}
