/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mapstruct.example.dto;

import com.vividsolutions.jts.geom.MultiPolygon;

/**
 *
 * @author Sjaak Derksen
 */
public class Building {

    private MultiPolygon extent;
    private Address address;
    private BuildingType type;

    public MultiPolygon getExtent() {
        return extent;
    }

    public void setExtent(MultiPolygon extent) {
        this.extent = extent;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public BuildingType getType() {
        return type;
    }

    public void setType(BuildingType type) {
        this.type = type;
    }

}
