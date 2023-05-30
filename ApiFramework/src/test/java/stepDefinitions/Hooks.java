package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeleteDevice")
	public void beforeScenario() throws IOException {
		StepDefinition m=new StepDefinition();
		if(StepDefinition.device_id==null) {
				m.register_device_pay_load_with("device1", "device1", "extron", "saleena_mgeorge", "LOGIN", "saleena.george@yuja.com");
				m.user_calls_with_request("RegisterDeviceAPI", "POST");
				m.verify_device_id_created_maps_to_using("device1", "GetDeviceAPI");
		}

}}
