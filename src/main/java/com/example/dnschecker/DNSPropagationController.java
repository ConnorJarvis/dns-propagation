package com.example.dnschecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DNSPropagationController {

    @Autowired
    private DNSServerRepository dnsServerRepository;


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("dnsRecordTypes", DNSRecordType.values());
        model.addAttribute("dnsServers", dnsServerRepository.getAll());
        return "index";
    }

}
