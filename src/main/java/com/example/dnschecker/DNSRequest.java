package com.example.dnschecker;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "dnsrequest")
public class DNSRequest {

    @Id
    @Column(name="id")
    private String requestId;

    @Enumerated(EnumType.STRING)
    @Column(name="recordtype")
    private DNSRecordType dnsRecordType;

    @Column(name="value")
    private String value;

    @Column(name="querydate")
    private Long queryDate;

    @Column(name="dnsserverid")
    private Long dnsServerId;




    public DNSRequest(DNSRecordType dnsRecordType, String value, Long dnsServerId){
        this.requestId = UUID.randomUUID().toString();
        this.dnsRecordType = dnsRecordType;
        this.value = value;
        this.queryDate = System.currentTimeMillis() / 1000L;
        this.dnsServerId = dnsServerId;
    }

    public String getRequestId(){
        return requestId;
    }

    public DNSRecordType getDnsRecordType(){
        return dnsRecordType;
    }

    public String getValue() {
        return value;
    }

    public Long getQueryDate() {
        return queryDate;
    }

    public Long getDnsServerId() {
        return dnsServerId;
    }


}
