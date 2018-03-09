package com.goshine.core.properties;

public class ImageCodeProperties extends SmsCodeProperties {

	private int width = 70;
	private int height = 30;

	public ImageCodeProperties() {
		setLength(4);
	}
	
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
}
