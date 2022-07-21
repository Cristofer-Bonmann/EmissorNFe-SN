package com.vertyce.suite;

import com.vertyce.certificado.CertificadoTest;
import com.vertyce.nfe.*;
import com.vertyce.nfe.icms.GeradorICMS00Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CertificadoTest.class,
        GeradorInfNFeTest.class,
        GeradorIdeTest.class,
        GeradorEmitTest.class,
        GeradorDestTest.class,
        GeradorDetTest.class,
        GeradorProdTest.class,
        GeradorImpostoTest.class,
        GeradorICMSTest.class,
        GeradorICMS00Test.class,
})
public class SuiteExec {
}
