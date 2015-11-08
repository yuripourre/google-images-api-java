package br.com.etyllica.network.googleimages.models;

import java.util.List;

public class ResponseData {

	List<GsearchResult> results;

	public List<GsearchResult> getResults() {
		return results;
	}

	public void setResults(List<GsearchResult> results) {
		this.results = results;
	}
}
