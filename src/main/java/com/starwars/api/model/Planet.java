package com.starwars.api.model;

import org.springframework.data.annotation.Id;

public class Planet {

    @Id
    private String id;

    private String name;
    private String climate;
    private String terrain;
    private int filmsAppearancesCount;

    public Planet() {

    }
    
    public Planet(String id, String name, String climate, String terrain, int filmsAppearancesCount) {
        this.id = id;
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
        this.filmsAppearancesCount = filmsAppearancesCount;
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((climate == null) ? 0 : climate.hashCode());
		result = prime * result + filmsAppearancesCount;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((terrain == null) ? 0 : terrain.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planet other = (Planet) obj;
		if (climate == null) {
			if (other.climate != null)
				return false;
		} else if (!climate.equals(other.climate))
			return false;
		if (filmsAppearancesCount != other.filmsAppearancesCount)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (terrain == null) {
			if (other.terrain != null)
				return false;
		} else if (!terrain.equals(other.terrain))
			return false;
		return true;
	}

	public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimate() {
        return this.climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getTerrain() {
        return this.terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public int getFilmsAppearancesCount() {
        return filmsAppearancesCount;
    }

    public void setFilmsAppearancesCount(int filmsAppearancesCount) {
        this.filmsAppearancesCount = filmsAppearancesCount;
    }
}