package com.chenjh.common.nvd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for rangeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rangeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="local" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="local_network" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="network" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="user_init" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rangeType", propOrder = {"local", "localNetwork", "network", "userInit" })
public class RangeType
{
    
    private Object local;
    
    @XmlElement(name = "local_network")
    private Object localNetwork;
    
    private Object network;
    
    @XmlElement(name = "user_init")
    private Object userInit;
    
    /**
     * Gets the value of the local property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getLocal()
    {
        return local;
    }
    
    /**
     * Sets the value of the local property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setLocal(Object value)
    {
        this.local = value;
    }
    
    /**
     * Gets the value of the localNetwork property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getLocalNetwork()
    {
        return localNetwork;
    }
    
    /**
     * Sets the value of the localNetwork property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setLocalNetwork(Object value)
    {
        this.localNetwork = value;
    }
    
    /**
     * Gets the value of the network property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getNetwork()
    {
        return network;
    }
    
    /**
     * Sets the value of the network property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setNetwork(Object value)
    {
        this.network = value;
    }
    
    /**
     * Gets the value of the userInit property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getUserInit()
    {
        return userInit;
    }
    
    /**
     * Sets the value of the userInit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setUserInit(Object value)
    {
        this.userInit = value;
    }
    
}
