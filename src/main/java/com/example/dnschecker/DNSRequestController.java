package com.example.dnschecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xbill.DNS.TextParseException;

import java.net.IDN;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DNSRequestController {

        @Autowired
        private DNSServerRepository dnsServerRepository;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/v1/dnsrequest")
    public List<String> dnsRequest(@RequestParam(value= "serverId") Long serverId, @RequestParam(value= "dnsRecordType") DNSRecordType dnsRecordType, @RequestParam(value= "value") String value) throws UnknownHostException, TextParseException {
        try {
            value = IDN.toASCII(value, IDN.ALLOW_UNASSIGNED);
        }catch (IllegalArgumentException iae) {
            List<String> result = new ArrayList<>();
            return result;
        }

        DNSServer dnsServer = dnsServerRepository.getById(serverId);
        String dnsServerIp = dnsServer.getServerIp();
        DNSQuery dnsQuery = new DNSQuery(dnsServerIp, value, dnsRecordType);
        List<String> response = dnsQuery.doQuery();
        return response;

    }
}
