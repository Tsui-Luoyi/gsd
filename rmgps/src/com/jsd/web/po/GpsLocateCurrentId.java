package com.jsd.web.po;

/**
 * GpsLocateCurrentId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class GpsLocateCurrentId implements java.io.Serializable {

	// Fields

	private Integer id;
	private String vno;

	// Constructors

	/** default constructor */
	public GpsLocateCurrentId() {
	}

	/** full constructor */
	public GpsLocateCurrentId(Integer id, String vno) {
		this.id = id;
		this.vno = vno;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVno() {
		return this.vno;
	}

	public void setVno(String vno) {
		this.vno = vno;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GpsLocateCurrentId))
			return false;
		GpsLocateCurrentId castOther = (GpsLocateCurrentId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getVno() == castOther.getVno()) || (this.getVno() != null
						&& castOther.getVno() != null && this.getVno().equals(
						castOther.getVno())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getVno() == null ? 0 : this.getVno().hashCode());
		return result;
	}

}