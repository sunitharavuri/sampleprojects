/**
 * 
 */
package com.cim.api;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cim.dto.Ad;
import com.cim.service.CampaignService;

/**
 * @author Sunitha Ravuri
 *
 */
@RestController
public class CampaignResource {
	@Resource
	private CampaignService campaignService;

	@PostMapping("/ad")
	public ResponseEntity<?> addAd(@RequestBody Ad adRequest) {

		try {
			String adResponse = campaignService.saveAdByPartner(adRequest);
			if (null != adResponse)
				return new ResponseEntity<String>(adResponse, HttpStatus.OK);
			else
				return new ResponseEntity<String>("Unable to add ad for partner, please try after some time.",
						HttpStatus.NOT_ACCEPTABLE);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

	}

	@GetMapping("ad/{partnerId}")
	public ResponseEntity<?> getAd(@PathVariable String partnerId) {

		try {
			Ad adResponse = campaignService.getAd(partnerId);
			System.out.println("--->"+adResponse);
			if (null != adResponse)
				return new ResponseEntity<Ad>(adResponse, HttpStatus.OK);
			else
				return new ResponseEntity<String>("Unable to find ad for partner, please try after some time.",
						HttpStatus.NOT_ACCEPTABLE);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

	}
	
	@GetMapping("/test")
	public String testt() {
		return "SUCCESSFULLY LAUNCHED APPLICATION";
	}
}
