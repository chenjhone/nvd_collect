package com.chenjh.common.nvd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.huawei.vulncenter.common.nvd package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory
{

    private static final QName ENTRY_QNAME = new QName("http://nvd.nist.gov/feeds/cve/1.2", "entry");


    /**
     * Create an instance of {@link VulnSoftType }
     *
     * @return VulnSoftType
     */
    public VulnSoftType createVulnSoftType()
    {
        return new VulnSoftType();
    }

    /**
     * Create an instance of {@link VulnSoftType.Prod }
     * @return VulnSoftType.Prod
     */
    public VulnSoftType.Prod createVulnSoftTypeProd()
    {
        return new VulnSoftType.Prod();
    }

    /**
     * Create an instance of {@link LossTypeType }
     *
     * @return LossTypeType
     */
    public LossTypeType createLossTypeType()
    {
        return new LossTypeType();
    }

    /**
     * Create an instance of {@link VulnType }
     *
     * @return VulnType
     */
    public VulnType createVulnType()
    {
        return new VulnType();
    }

    /**
     * Create an instance of {@link SolsType }
     *
     * @return SolsType
     */
    public SolsType createSolsType()
    {
        return new SolsType();
    }

    /**
     * Create an instance of {@link EntryType }
     *
     * @return EntryType
     */
    public EntryType createEntryType()
    {
        return new EntryType();
    }

    /**
     * Create an instance of {@link Nvd }
     *
     * @return Nvd
     */
    public Nvd createNvd()
    {
        return new Nvd();
    }

    /**
     * Create an instance of {@link RangeType }
     *
     * @return RangeType
     */
    public RangeType createRangeType()
    {
        return new RangeType();
    }

    /**
     * Create an instance of {@link ImpactType }
     *
     * @return ImpactType
     */
    public ImpactType createImpactType()
    {
        return new ImpactType();
    }

    /**
     * Create an instance of {@link DescriptType }
     *
     * @return DescriptType
     */
    public DescriptType createDescriptType()
    {
        return new DescriptType();
    }

    /**
     * Create an instance of
     *
     * @return RefType
     */
    public RefType createRefType()
    {
        return new RefType();
    }

    /**
     * Create an instance of {@link VulnSoftType.Prod.Vers }
     *
     * @return VulnSoftType.Prod.Vers
     */
    public VulnSoftType.Prod.Vers createVulnSoftTypeProdVers()
    {
        return new VulnSoftType.Prod.Vers();
    }

    /**
     * Create an instance of {@link LossTypeType.SecProt }
     *
     * @return LossTypeType.SecProt
     */
    public LossTypeType.SecProt createLossTypeTypeSecProt()
    {
        return new LossTypeType.SecProt();
    }

    /**
     * Create an instance of {@link VulnType.Input }
     *
     * @return VulnType.Input
     */
    public VulnType.Input createVulnTypeInput()
    {
        return new VulnType.Input();
    }

    /**
     * Create an instance of {@link SolsType.Sol }
     *
     * @return SolsType.Sol
     */
    public SolsType.Sol createSolsTypeSol()
    {
        return new SolsType.Sol();
    }

    /**
     * Create an instance of {@link EntryType.Desc }
     *
     * @return EntryType.Desc
     */
    public EntryType.Desc createEntryTypeDesc()
    {
        return new EntryType.Desc();
    }

    /**
     * Create an instance of {@link EntryType.Impacts }
     * @return EntryType.Impacts
     */
    public EntryType.Impacts createEntryTypeImpacts()
    {
        return new EntryType.Impacts();
    }

    /**
     * Create an instance of {@link EntryType.Refs }
     * @return EntryType.Refs
     */
    public EntryType.Refs createEntryTypeRefs()
    {
        return new EntryType.Refs();
    }

    /**
     * createEntry
     * @param entryBean entryBean
     * Create an instance of {@link JAXBElement }{@code <}{@link EntryType }{@code >}}
     * @return JAXBElement<EntryType>
     */
    @XmlElementDecl(namespace = "http://nvd.nist.gov/feeds/cve/1.2", name = "entry")
    public JAXBElement<EntryType> createEntry(EntryType entryBean)
    {
        return new JAXBElement<EntryType>(ENTRY_QNAME, EntryType.class, null, entryBean);
    }

}
