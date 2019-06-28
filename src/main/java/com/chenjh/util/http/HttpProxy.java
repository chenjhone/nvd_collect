package com.chenjh.util.http;

/**
 * @author chenjh
 * @version V1.0
 * @since 2018年12月27日
 */
public class HttpProxy
{
    /**
     * 主机
     */
    private String host;
    
    /**
     * 端口号
     */
    private int port;
    
    private String user;
    
    private String password;
    
    private String workstation;
    
    private String domain;
    
    /**
     * 构造器
     */
    public HttpProxy()
    {
    }
    
    /**
     * HttpProxy构造器
     * @param ipAndPort ipAndPort
     */
    public HttpProxy(String ipAndPort)
    {
        this.host = ipAndPort.split(":")[0];
        this.port = Integer.parseInt(ipAndPort.split(":")[1]);
    }
    
    /**
     * HttpProxy构造器
     * @param host host
     * @param port port
     */
    public HttpProxy(String host, int port)
    {
        super();
        this.host = host;
        this.port = port;
    }
    
    /**
     * HttpProxy构造器
     * @param host host
     * @param port port
     * @param user user
     * @param password pwd
     */
    public HttpProxy(String host, int port, String user, String password)
    {
        super();
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
    }
    
    /**
     * HttpProxy构造器
     * @param host host
     * @param port port
     * @param user user
     * @param password pwd
     * @param domain domain
     */
    public HttpProxy(String host, int port, String user, String password, String domain)
    {
        super();
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.domain = domain;
    }
    
    public String getHost()
    {
        return host;
    }
    
    public void setHost(String host)
    {
        this.host = host;
    }
    
    public int getPort()
    {
        return port;
    }
    
    public void setPort(int port)
    {
        this.port = port;
    }
    
    public String getUser()
    {
        return user;
    }
    
    public void setUser(String user)
    {
        this.user = user;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getWorkstation()
    {
        return workstation;
    }
    
    public void setWorkstation(String workstation)
    {
        this.workstation = workstation;
    }
    
    public String getDomain()
    {
        return domain;
    }
    
    public void setDomain(String domain)
    {
        this.domain = domain;
    }
    
}
