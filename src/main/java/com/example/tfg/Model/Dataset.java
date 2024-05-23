package com.example.tfg.Model;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Document(collection = "datasets")
@AllArgsConstructor
public class Dataset {

    @Id
    private String id;
    private String providerId;
    private String name;
    private String description;
    private String TermsOfUse;
    private BigDecimal price;
    private Date date;
    private String accessTerms;

    public Dataset() {
    }

    public Dataset(String providerId, String name, String description, String TermsOfUse, BigDecimal price, Date date, String accessTerms) {
        this.providerId = providerId;
        this.name = name;
        this.description = description;
        this.TermsOfUse=TermsOfUse;
        this.price = price;
        this.date = date;
        this.accessTerms = accessTerms;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTermsOfUse() {
        return TermsOfUse;
    }

    public void setTermsOfUse(String termsOfUse) {
        TermsOfUse = termsOfUse;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAccessTerms() {
        return accessTerms;
    }

    public void setAccessTerms(String accessTerms) {
        this.accessTerms = accessTerms;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dataset dataset = (Dataset) o;
        return Objects.equals(id, dataset.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
