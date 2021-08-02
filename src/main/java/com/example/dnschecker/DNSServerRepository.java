package com.example.dnschecker;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DNSServerRepository extends JpaRepository<DNSServer, Long>, JpaSpecificationExecutor<DNSServer> {
    @Cacheable("dnsserver")
    @Query(value = "select serverIp from DNSServer where id=?1")
    String getServerIp(Long id);

    @Cacheable("dnsserver")
    @Query(value = "select country from DNSServer where id=?1")
    String getCountry(Long id);

    @Cacheable("dnsserver")
    @Override
    @Query(value = "select * from DNSServer where id=?1")
    DNSServer getById(Long id);

    @Cacheable("dnsserver")
    @Query(value = "select * from DNSServer", nativeQuery = true)
    DNSServer[] getAll();
}
