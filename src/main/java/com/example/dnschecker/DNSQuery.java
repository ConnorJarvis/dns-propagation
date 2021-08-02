package com.example.dnschecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.xbill.DNS.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class DNSQuery {

    @Autowired
    DNSServerRepository dnsServerRepository;

    private String dnsServerIp;

    private String value;

    private DNSRecordType dnsRecordType;


    public DNSQuery(String dnsServerIp, String value, DNSRecordType dnsRecordType) throws UnknownHostException, TextParseException {
        this.dnsServerIp = dnsServerIp;
        this.value = value;
        this.dnsRecordType = dnsRecordType;
    }

    public List<String> doQuery() throws UnknownHostException, TextParseException {
        Resolver resolver = new SimpleResolver(InetAddress.getByName(dnsServerIp));
        Lookup lookup = new Lookup(value, dnsRecordType.asDnsType());
        resolver.setTimeout(Duration.ofSeconds(10));
        resolver.setTCP(true);
        lookup.setResolver(resolver);
        lookup.setNdots(0);

        Record[] records = lookup.run();
        if (records == null) {
            List<String> result = new ArrayList<>();
            return result;
        }
        switch (dnsRecordType){
            case A: return resolveAQuery(records);
            case AAAA: return resolveAAAAQuery(records);
            case TXT: return resolveTXTQuery(records);
            case CNAME: return resolveCNAMEQuery(records);
            case MX: return  resolveMXQuery(records);
            case NS: return resolveNSQuery(records);
            case PTR: return resolvePTRQuery(records);
            case SRV: return resolveSRVQuery(records);
        }

        List<String> result = new ArrayList<>();
        return result;
    }

    public List<String> resolveAQuery(Record[] records) {
        List<String> result = new ArrayList<>();

        for (Record record : records) {
            result.add(((ARecord) record).getAddress().getHostAddress());
        }

        return result;
    }

    public List<String> resolveAAAAQuery(Record[] records) {
        List<String> result = new ArrayList<>();

        for (Record record : records) {
            result.add(((AAAARecord) record).getAddress().getHostAddress());
        }

        return result;
    }

    public List<String> resolveCNAMEQuery(Record[] records) {
        List<String> result = new ArrayList<>();

        for (Record record : records) {
            result.add(((CNAMERecord) record).getTarget().toString(true));
        }
        return result;
    }

    public List<String> resolveMXQuery(Record[] records) {
        List<String> result = new ArrayList<>();

        for (Record record : records) {
            result.add(((MXRecord) record).getTarget().toString(true));
        }
        return result;
    }

    public List<String> resolveNSQuery(Record[] records) {
        List<String> result = new ArrayList<>();

        for (Record record : records) {
            result.add(((NSRecord) record).getTarget().toString(true));
        }
        return result;
    }

    public List<String> resolvePTRQuery(Record[] records) {
        List<String> result = new ArrayList<>();

        for (Record record : records) {
            result.add(((PTRRecord) record).getTarget().toString(true));
        }
        return result;
    }

    public List<String> resolveSRVQuery(Record[] records) {
        List<String> result = new ArrayList<>();

        for (Record record : records) {
            result.add(((SRVRecord) record).getTarget().toString(true));
        }
        return result;
    }

    public List<String> resolveTXTQuery(Record[] records) {
        List<String> result = new ArrayList<>();

        for (Record record : records) {
            for(String string: ((TXTRecord) record).getStrings()) {
                result.add(string);
            }
        }
        return result;
    }
}
