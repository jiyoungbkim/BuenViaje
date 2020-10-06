package edu.iot.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TourSpot {
	private long tourSpotId;
	private long routeId;
	private String title;
	private String brief;
	
	public TourSpot(String title) {
		this.title = title;
	}
}
