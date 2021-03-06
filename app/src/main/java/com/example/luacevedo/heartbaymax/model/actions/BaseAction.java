package com.example.luacevedo.heartbaymax.model.actions;

import com.example.luacevedo.heartbaymax.model.patient.PatientAttribute;

public abstract class BaseAction<T> {

    protected String attributeRoot;

    public String getAttributeRoot() {
        return attributeRoot;
    }

    public void setAttributeRoot(String attributeRoot) {
        this.attributeRoot = attributeRoot;
    }

    public abstract void execute(PatientAttribute<T> attribute);

}
