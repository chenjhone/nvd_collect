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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * <p>Java class for solsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="solsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sol">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="source" 
 *                 use="required" type="{http://nvd.nist.gov/feeds/cve/1.2}solsSourceType" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "solsType", propOrder = {"sol" })
public class SolsType
{
    
    @XmlElement(required = true)
    private SolsType.Sol sol;
    
    /**
     * Gets the value of the sol property.
     * 
     * @return
     *     possible object is
     *     {@link SolsType.Sol }
     *     
     */
    public SolsType.Sol getSol()
    {
        return sol;
    }
    
    /**
     * Sets the value of the sol property.
     * 
     * @param value
     *     allowed object is
     *     {@link SolsType.Sol }
     *     
     */
    public void setSol(SolsType.Sol value)
    {
        this.sol = value;
    }
    
    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="source" use="required" type="{http://nvd.nist.gov/feeds/cve/1.2}solsSourceType" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {"value" })
    public static class Sol
    {
        
        @XmlValue
        private String value;
        
        @XmlAttribute(name = "source", required = true)
        private SolsSourceType source;
        
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
         *     {@link SolsSourceType }
         *     
         */
        public SolsSourceType getSource()
        {
            return source;
        }
        
        /**
         * Sets the value of the source property.
         * 
         * @param sourceType sourceType
         *     allowed object is
         *     {@link SolsSourceType }
         *     
         */
        public void setSource(SolsSourceType sourceType)
        {
            this.source = sourceType;
        }
        
    }
    
}