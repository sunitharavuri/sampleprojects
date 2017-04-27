/**
 * 
 */
package com.cim.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cim.dto.Ad;

/**
 * @author Sunitha Ravuri
 *
 */
@Component
public class CampaignDAO {

	private Map<String, List<Ad>> campaignDataSource;

	public Boolean pushCampaign(Ad campaign) {
		List<Ad> adList = null;
		if (null == campaignDataSource.get(campaign.getPartnerId())) {
			adList = new ArrayList<Ad>();
			adList.add(campaign);
		} else {
			adList = campaignDataSource.get(campaign.getPartnerId());
			adList.add(campaign);
		}
		this.campaignDataSource.put(campaign.getPartnerId(), adList);
		return Boolean.TRUE;
	}

	public List<Ad> getAd(String partnerId) {
		return this.campaignDataSource.get(partnerId);
	}
}
