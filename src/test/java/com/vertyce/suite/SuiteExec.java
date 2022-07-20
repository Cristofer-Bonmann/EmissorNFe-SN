package com.vertyce.suite;

import com.vertyce.nfe.GeradorDestTest;
import com.vertyce.nfe.GeradorEmitTest;
import com.vertyce.nfe.GeradorIdeTest;
import com.vertyce.nfe.GeradorInfNFeTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        GeradorInfNFeTest.class,
        GeradorIdeTest.class,
        GeradorEmitTest.class,
        GeradorDestTest.class,
})
public class SuiteExec {
}
