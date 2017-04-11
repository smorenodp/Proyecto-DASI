//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.04.11 at 05:03:56 PM CEST 
//


package com.microsoft.Malmo.Schemas;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ShapeTypes.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ShapeTypes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="north_south"/>
 *     &lt;enumeration value="east_west"/>
 *     &lt;enumeration value="north_east"/>
 *     &lt;enumeration value="north_west"/>
 *     &lt;enumeration value="south_east"/>
 *     &lt;enumeration value="south_west"/>
 *     &lt;enumeration value="ascending_east"/>
 *     &lt;enumeration value="ascending_west"/>
 *     &lt;enumeration value="ascending_north"/>
 *     &lt;enumeration value="ascending_south"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ShapeTypes")
@XmlEnum
public enum ShapeTypes {

    @XmlEnumValue("north_south")
    NORTH_SOUTH("north_south"),
    @XmlEnumValue("east_west")
    EAST_WEST("east_west"),
    @XmlEnumValue("north_east")
    NORTH_EAST("north_east"),
    @XmlEnumValue("north_west")
    NORTH_WEST("north_west"),
    @XmlEnumValue("south_east")
    SOUTH_EAST("south_east"),
    @XmlEnumValue("south_west")
    SOUTH_WEST("south_west"),
    @XmlEnumValue("ascending_east")
    ASCENDING_EAST("ascending_east"),
    @XmlEnumValue("ascending_west")
    ASCENDING_WEST("ascending_west"),
    @XmlEnumValue("ascending_north")
    ASCENDING_NORTH("ascending_north"),
    @XmlEnumValue("ascending_south")
    ASCENDING_SOUTH("ascending_south");
    private final String value;

    ShapeTypes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ShapeTypes fromValue(String v) {
        for (ShapeTypes c: ShapeTypes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
