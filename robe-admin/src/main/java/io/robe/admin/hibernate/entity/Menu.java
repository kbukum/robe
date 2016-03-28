package io.robe.admin.hibernate.entity;

import io.robe.hibernate.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table
public class Menu extends BaseEntity {


    @Column(length = 50, nullable = false)
    private String code;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(name = "itemIndex")
    private int index;

    @Column(length = 32)
    private String parentOid;

    @Transient
    private List<Menu> items = new LinkedList<Menu>();


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Menu> getItems() {
        return items;
    }

    public void setItems(List<Menu> items) {
        this.items = items;
    }

    public String getParentOid() {
        return parentOid;
    }

    public void setParentOid(String parentOid) {
        this.parentOid = parentOid;
    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
