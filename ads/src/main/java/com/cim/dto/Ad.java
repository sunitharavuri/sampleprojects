/**
 * 
 */
package com.cim.dto;

import java.sql.Timestamp;

/**
 * @author Sunitha Ravuri
 *
 */
public class Ad {
	/**
	 * unique_string_representing_partne
	 */
	private String partnerId;
	/**
	 * int_representing_campaign_duration_in_seconds_from_now
	 */
	private int activeSeconds;
	/**
	 * content_to_display_as_ad
	 */
	private String adContent;

	private Timestamp createdOn;

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public int getActiveSeconds() {
		return activeSeconds;
	}

	public void setActiveSeconds(int activeSeconds) {
		this.activeSeconds = activeSeconds;
	}

	public String getAdContent() {
		return adContent;
	}

	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

}
