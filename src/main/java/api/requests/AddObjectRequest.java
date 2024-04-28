package api.requests;

import lombok.Data;

@Data
public class AddObjectRequest {
	
	private String name;
	
	private AddObjectRequestData data;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public AddObjectRequestData getData() {
		return data;
	}
	
	public void setData(AddObjectRequestData data) {
		this.data = data;
	}

}
