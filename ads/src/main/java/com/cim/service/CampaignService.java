/**
 * 
 */
package com.cim.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cim.dao.CampaignDAO;
import com.cim.dto.Ad;

/**
 * @author Sunitha Ravuri
 *
 */
@Component
public class CampaignService {

	@Resource
	private CampaignDAO campaignDAO;

	private String successResponse = "Ad successfully added for partner %s.";
	private String failureResponse = "Existing campaign is active, cannot add another ad for this partner %s";
	private final Long timeInSecondsTill1900 = 2208988800L;

	public String saveAdByPartner(Ad adRequest) {
		Boolean isSuccess = Boolean.FALSE;
		if (null == adRequest.getPartnerId()) {
			throw new RuntimeException("INVALID REQUEST");
		} else {
			List<Ad> adList = campaignDAO.getAd(adRequest.getPartnerId());
			if (null != adList && !adList.isEmpty()) {
				boolean isAvailable = Boolean.FALSE;
				for (Ad ad : adList) {
					Long leftSeconds = System.currentTimeMillis()
							- (timeInSecondsTill1900 + ad.getCreatedOn().getTime() + ad.getActiveSeconds());
					if (leftSeconds > 0) {
						isAvailable = Boolean.TRUE;
						break;
					}
				}
				if (!isAvailable) {
					isSuccess = campaignDAO.pushCampaign(adRequest);
				}
			} else {
				isSuccess = campaignDAO.pushCampaign(adRequest);
			}

		}
		return String.format((isSuccess ? successResponse : failureResponse), adRequest.getPartnerId());
	}

	public Ad getAd(String partnerId) {

		if (null == partnerId) {
			throw new RuntimeException("INVALID PARTNER ID");
		} else {
			List<Ad> adList = campaignDAO.getAd(partnerId);
			if (null != adList && !adList.isEmpty()) {
				for (Ad ad : adList) {
					Long currentTime = System.currentTimeMillis();
					Long leftSeconds = currentTime - (ad.getCreatedOn().getTime() + ad.getActiveSeconds());
					if (leftSeconds < 0) {
						return ad;
					}
				}

			}
		}
		return null;
	}
}
