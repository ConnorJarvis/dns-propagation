package com.example.dnschecker;

import javax.persistence.*;

@Entity
@Table(name = "dnsserver")
public class DNSServer {
    @Id
    @Column(name="id")
    private Long id;

    @Column(name="serverIp")
    private String serverIp;

    @Column(name="country")
    private String country;

    @Column(name="countryShortCode")
    private String countryShortCode;

    @Column(name="location")
    private String location;

    public Long getId(){
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getServerIp(){
        return serverIp;
    }
    public String getCountryShortCode(){
        return countryShortCode;
    }

    public String getLocation(){
        return location;
    }
}
