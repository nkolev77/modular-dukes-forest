/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.forest.persistence.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

import com.forest.model.Category;
import com.forest.model.Product;

/**
 *
 * @author markito
 */
@Entity
@Table(name = "CATEGORY")
@NamedQueries({
    @NamedQuery(name = "Category.findAll", query = "SELECT c FROM CategoryEntity c"),
    @NamedQuery(name = "Category.findById", query = "SELECT c FROM CategoryEntity c WHERE c.id = :id"),
    @NamedQuery(name = "Category.findByName", query = "SELECT c FROM CategoryEntity c WHERE c.name = :name"),
    @NamedQuery(name = "Category.findByTags", query = "SELECT c FROM CategoryEntity c WHERE c.tags = :tags")})
public class CategoryEntity extends Category {
    
    private static final long serialVersionUID = -5400424750505982222L;

    public CategoryEntity() {
    }

    public CategoryEntity(Integer id) {
        this.id = id;
    }

    public CategoryEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic(optional = false)
    @Size(min=3, max=45, message="{category.name}")
    @Column(name = "NAME", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Size(min=3, max=45, message="{category.tags}")
    @Column(name = "TAGS", length = 45)
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
    
    @XmlTransient
    @OneToMany(targetEntity=ProductEntity.class, cascade = CascadeType.ALL, mappedBy = "category")
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoryEntity)) {
            return false;
        }
        CategoryEntity other = (CategoryEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getName() + " [ID: " + id + "]";
    }

}
