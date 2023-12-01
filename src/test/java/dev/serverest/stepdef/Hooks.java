package dev.serverest.stepdef;

import dev.serverest.utils.ResetUtils;
import dev.serverest.utils.ScenarioUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    @Before(order = 0)
    public void before(Scenario scenario) {
        ScenarioUtils.add(scenario);
    }

    @After(order = 0)
    public void after() {
        ScenarioUtils.remove();
    }

    @After(order = 9999)
    public void setBearerTokenToNull(){
        ResetUtils.reset();
    }
}
