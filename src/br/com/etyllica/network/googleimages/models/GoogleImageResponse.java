package br.com.etyllica.network.googleimages.models;

import com.google.gson.annotations.SerializedName;

public class GoogleImageResponse {

	@SerializedName("responseData")
	ResponseData responseData;
	
	@SerializedName("responseStatus")
	int responseStatus;

	public ResponseData getResponseData() {
		return responseData;
	}

	public void setResponseData(ResponseData responseData) {
		this.responseData = responseData;
	}

	public int getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(int responseStatus) {
		this.responseStatus = responseStatus;
	}
	
}
