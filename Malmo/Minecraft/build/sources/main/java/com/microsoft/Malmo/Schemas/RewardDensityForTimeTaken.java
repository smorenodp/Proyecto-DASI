//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.04.16 at 06:20:13 PM CEST 
//


package com.microsoft.Malmo.Schemas;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RewardDensityForTimeTaken.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RewardDensityForTimeTaken">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PER_TICK"/>
 *     &lt;enumeration value="PER_TICK_ACCUMULATED"/>
 *     &lt;enumeration value="MISSION_END"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RewardDensityForTimeTaken")
@XmlEnum
public enum RewardDensityForTimeTaken {

    PER_TICK,
    PER_TICK_ACCUMULATED,
    MISSION_END;

    public String value() {
        return name();
    }

    public static RewardDensityForTimeTaken fromValue(String v) {
        return valueOf(v);
    }

}