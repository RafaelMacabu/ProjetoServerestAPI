package dev.serverest.stepdef;

import dev.serverest.services.BaseService;
import io.cucumber.java.pt.Entao;

public class CommonStepDef {
    private BaseService service = new BaseService();

    @Entao("o status code sera {int}")
    public void o_status_code_sera(Integer status) {
        service.action().
                assertStatus(status);
    }
}
