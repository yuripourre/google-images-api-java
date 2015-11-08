package br.com.etyllica.network.googleimages.models;

import com.google.gson.annotations.SerializedName;

public class GsearchResult {

	@SerializedName("width")
	int width;
	
	@SerializedName("height")
	int height;
	
	@SerializedName("imageId")
	String imageId;
	
	@SerializedName("tbWidth")
	int tbWidth;
	
	@SerializedName("tbHeight")
	int tbHeight;
	
	@SerializedName("unescapedUrl")
	String url;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public int getTbWidth() {
		return tbWidth;
	}

	public void setTbWidth(int tbWidth) {
		this.tbWidth = tbWidth;
	}

	public int getTbHeight() {
		return tbHeight;
	}

	public void setTbHeight(int tbHeight) {
		this.tbHeight = tbHeight;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
