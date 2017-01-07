package br.com.unicsul.dto;

import java.util.List;

public class NaveDTO {
	
	private Integer id;
	
	private String name;
	
	private String model;
	
	private String manufacturer;
	
	private String cost_in_credits;
	
	private String length;
	
	private String max_atmosphering_speed;

	private String crew;
	
	private String passengers;
	
	private String cargo_capacity;
	
	private String consumables;
	
	private String hyperdrive_rating;
	
	private String MGLT;
	
	private String starship_class;
	
	private List<String> pilots;
	
	private List<String> films;
	
	private String created;
	
	private String edited;
	
	private String url;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getModel() {
		return model;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public String getCost_in_credits() {
		return cost_in_credits;
	}

	public String getLength() {
		return length;
	}

	public String getMax_atmosphering_speed() {
		return max_atmosphering_speed;
	}

	public String getCrew() {
		return crew;
	}

	public String getPassengers() {
		return passengers;
	}

	public String getCargo_capacity() {
		return cargo_capacity;
	}

	public String getConsumables() {
		return consumables;
	}

	public String getHyperdrive_rating() {
		return hyperdrive_rating;
	}

	public String getMGLT() {
		return MGLT;
	}

	public String getStarship_class() {
		return starship_class;
	}

	public List<String> getPilots() {
		return pilots;
	}

	public List<String> getFilms() {
		return films;
	}

	public String getCreated() {
		return created;
	}

	public String getEdited() {
		return edited;
	}

	public String getUrl() {
		return url;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public void setCost_in_credits(String cost_in_credits) {
		this.cost_in_credits = cost_in_credits;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public void setMax_atmosphering_speed(String max_atmosphering_speed) {
		this.max_atmosphering_speed = max_atmosphering_speed;
	}

	public void setCrew(String crew) {
		this.crew = crew;
	}

	public void setPassengers(String passengers) {
		this.passengers = passengers;
	}

	public void setCargo_capacity(String cargo_capacity) {
		this.cargo_capacity = cargo_capacity;
	}

	public void setConsumables(String consumables) {
		this.consumables = consumables;
	}

	public void setHyperdrive_rating(String hyperdrive_rating) {
		this.hyperdrive_rating = hyperdrive_rating;
	}

	public void setMGLT(String mGLT) {
		MGLT = mGLT;
	}

	public void setStarship_class(String starship_class) {
		this.starship_class = starship_class;
	}

	public void setPilots(List<String> pilots) {
		this.pilots = pilots;
	}

	public void setFilms(List<String> films) {
		this.films = films;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public void setEdited(String edited) {
		this.edited = edited;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
