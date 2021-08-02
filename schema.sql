CREATE TABLE dnschecker.dnsserver (
        id INT PRIMARY KEY,
        serverIp STRING, 
        country STRING,
        countryShortCode STRING,
        location string
);
CREATE TABLE dnschecker.dnsrequest (
        id UUID PRIMARY KEY,
        recordType STRING, 
        value STRING,
        queryDate INT,
        dnsServerId INT
);