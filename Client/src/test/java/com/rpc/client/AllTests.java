package com.rpc.client;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ClientTestPositive.class, ClientTestNegative.class })
public class AllTests {

}
