package stepDefinations;

import java.io.IOException;

public class Hooks {

	
	public void beforeScenario() throws IOException {
		StepDefination m=new StepDefination();
		if(StepDefination.place_ID==null) {
		m.add_place_payload_with_payload("gurav", "french", "ostia");
		m.user_calla_with_http_request("addPlaceAPI", "POST");
		m.verify_place_id_created_maps_to_using("gurav", "getPlaceAPI");
		
		
		}
		
	}
}
