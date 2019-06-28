//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.09.24 at 10:07:51 AM GMT+08:00 
//

package com.chenjh.common.nvd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * <p>Java class for impactType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="impactType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="source" use="required" type="{http://nvd.nist.gov/feeds/cve/1.2}impactSourceType" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "impactType", propOrder = {"value" })
public class ImpactType
{
    
    @XmlValue
    private String value;
    
    @XmlAttribute(name = "source", required = true)
    private ImpactSourceType source;
    
    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue()
    {
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
    public void setValue(String value)
    {
        this.value = value;
    }
    
    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link ImpactSourceType }
     *     
     */
    public ImpactSourceType getSource()
    {
        return source;
    }
    
    /**
     * Sets the value of the source property.
     * 
     * @param sourceType sourceType
     *     allowed object is
     *     {@link ImpactSourceType }
     *     
     */
    public void setSource(ImpactSourceType sourceType)
    {
        this.source = sourceType;
    }
    
}