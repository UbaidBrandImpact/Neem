package com.example.bim.neem.Service;

import org.json.JSONException;

public interface IServiceInvokerCallback {
	void onRequestCompleted(String response, String mode) throws JSONException;
}
