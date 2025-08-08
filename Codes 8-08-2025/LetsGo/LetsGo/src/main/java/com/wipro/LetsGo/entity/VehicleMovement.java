package com.wipro.LetsGo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name="vehicle_movement")
public class VehicleMovement {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="vehId")
	private int vehId;
	@Column(name="lat")
	private double lat;
	@Column(name="longitude")
	private double longitude;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getVehId() {
		return vehId;
	}
	public void setVehId(int vehId) {
		this.vehId = vehId;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public VehicleMovement(Long id, int vehId, double lat, double longitude) {
		super();
		this.id = id;
		this.vehId = vehId;
		this.lat = lat;
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "VehicleMovement [id=" + id + ", vehId=" + vehId + ", lat=" + lat + ", longitude=" + longitude + "]";
	}
}
