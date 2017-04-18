//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.04.16 at 06:20:13 PM CEST 
//


package com.microsoft.Malmo.Schemas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * 
 *             An entity that can be placed using the DrawingDecorator.
 *         
 * 
 * <p>Java class for SpawnableTypes simple type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;simpleType name="SpawnableTypes">
 *   &lt;union memberTypes=" {http://ProjectMalmo.microsoft.com}EntityTypes {http://ProjectMalmo.microsoft.com}NonLivingEntityTypes">
 *   &lt;/union>
 * &lt;/simpleType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpawnableTypes", propOrder = {
    "value"
})
public class SpawnableTypes {

    @XmlValue
    protected String value;

    /**
     * 
     *             An entity that can be placed using the DrawingDecorator.
     *         
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

}