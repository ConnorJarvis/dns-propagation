package com.example.dnschecker;

import org.xbill.DNS.ARecord;
import org.xbill.DNS.Record;
import org.xbill.DNS.Type;

public enum DNSRecordType {
    A {
        @Override
        public int asDnsType(){
            return Type.A;
        }
    },
    AAAA {
        @Override
        public int asDnsType(){
            return Type.AAAA;
        }
    },
    CNAME {
        @Override
        public int asDnsType(){
            return Type.CNAME;
        }
    },
    MX {
        @Override
        public int asDnsType(){
            return Type.MX;
        }
    },
    NS {
        @Override
        public int asDnsType(){
            return Type.NS;
        }
    },
    PTR {
        @Override
        public int asDnsType(){
            return Type.PTR;
        }
    },
    SRV {
        @Override
        public int asDnsType(){
            return Type.SRV;
        }
    },
    TXT {
        @Override
        public int asDnsType(){
            return Type.TXT;
        }
    };
    public int asDnsType(){
        return Type.A;
    }
}