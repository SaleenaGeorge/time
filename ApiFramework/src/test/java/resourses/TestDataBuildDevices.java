package resourses;

import pojo.RegisterDevice;

public class TestDataBuildDevices {
	
	public RegisterDevice registerDevicePayLoad(String access_id, String display_name, String station_type, String user_identifier, String user_identifiertype, String contact_list) {
		RegisterDevice r=new RegisterDevice();
		r.setAccess_id(access_id);
		r.setDisplay_name(display_name);
		r.setStation_type(station_type);
		r.setDevice_model("");
		r.setFirmware_version("1.0");
		r.setUser_identifier(user_identifier);
		r.setUser_identifier_type(user_identifiertype);
		r.setContact_list(contact_list);
		return r;
	}

}
