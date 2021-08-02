package com.example.dnschecker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DNSServerRepository extends JpaRepository<DNSServer, Long>, JpaSpecificationExecutor<DNSServer> {
    @Query(value = "select serverIp from DNSServer where id=?1")
    String getServerIp(Long id);

    @Query(value = "select country from DNSServer where id=?1")
    String getCountry(Long id);

    @Query(value = "select * from DNSServer", nativeQuery = true)
    DNSServer[] getAll();
}
