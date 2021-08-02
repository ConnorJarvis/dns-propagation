package com.example.dnschecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.xbill.DNS.*;

import javax.persistence.Transient;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DNSQuery {

    @Autowired
    DNSServerRepository dnsServerRepository;

    private String dnsServerIp;

    private String value;

    private DNSRecordType dnsRecordType;

    private Resolver resolver;

    private Lookup lookup;

    private Record[] records;

    public DNSQuery(String dnsServerIp, String value, DNSRecordType dnsRecordType) throws UnknownHostException, TextParseException {
        this.dnsServerIp = dnsServerIp;
        this.value = value;
        this.dnsRecordType = dnsRecordType;
        resolver = new SimpleResolver(dnsServerIp);
        lookup = new Lookup(value, dnsRecordType.asDnsType());
        lookup.setResolver(resolver);

    }

    public List<String> doQuery() {
        records = lookup.run();
        if (records == null) {
            List<String> result = new ArrayList<>();
            return result;
        }
        switch (dnsRecordType){
            case A: return resolveAQuery();
            case AAAA: return resolveAAAAQuery();
            case TXT: return resolveTXTQuery();
            case CNAME: return resolveCNAMEQuery();
            case MX: return  resolveMXQuery();
            case NS: return resolveNSQuery();
            case PTR: return resolvePTRQuery();
            case SRV: return resolveSRVQuery();
        }

        List<String> result = new ArrayList<>();
        return result;
    }

    public List<String> resolveAQuery() {
        List<String> result = new ArrayList<>();

        for (Record record : records) {
            result.add(((ARecord) record).getAddress().getHostAddress());
        }

        return result;
    }

    public List<String> resolveAAAAQuery() {
        List<String> result = new ArrayList<>();

        for (Record record : records) {
            result.add(((AAAARecord) record).getAddress().getHostAddress());
        }

        return result;
    }

    public List<String> resolveCNAMEQuery() {
        List<String> result = new ArrayList<>();

        for (Record record : records) {
            result.add(((CNAMERecord) record).getTarget().toString(true));
        }
        return result;
    }

    public List<String> resolveMXQuery() {
        List<String> result = new ArrayList<>();

        for (Record record : records) {
            result.add(((MXRecord) record).getTarget().toString(true));
        }
        return result;
    }

    public List<String> resolveNSQuery() {
        List<String> result = new ArrayList<>();

        for (Record record : records) {
            result.add(((NSRecord) record).getTarget().toString(true));
        }
        return result;
    }

    public List<String> resolvePTRQuery() {
        List<String> result = new ArrayList<>();

        for (Record record : records) {
            result.add(((PTRRecord) record).getTarget().toString(true));
        }
        return result;
    }

    public List<String> resolveSRVQuery() {
        List<String> result = new ArrayList<>();

        for (Record record : records) {
            result.add(((SRVRecord) record).getTarget().toString(true));
        }
        return result;
    }

    public List<String> resolveTXTQuery() {
        List<String> result = new ArrayList<>();

        for (Record record : records) {
            for(String string: ((TXTRecord) record).getStrings()) {
                result.add(string);
            }
        }
        return result;
    }
}
