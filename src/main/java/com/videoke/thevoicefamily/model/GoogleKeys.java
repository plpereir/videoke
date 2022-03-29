package com.videoke.thevoicefamily.model;

import java.util.ArrayList;
import java.util.List;

public class GoogleKeys {
	private List<String> googleAPIKeys;
	private List<String> googleChannelKeys;
	
	

	public List<String> getGoogleAPIKeys() {
		return googleAPIKeys;
	}



	public void setGoogleAPIKeys(List<String> googleAPIKeys) {
		this.googleAPIKeys = googleAPIKeys;
	}



	public List<String> getGoogleChannelKeys() {
		return googleChannelKeys;
	}



	public void setGoogleChannelKeys(List<String> googleChannelKeys) {
		this.googleChannelKeys = googleChannelKeys;
	}



	public GoogleKeys(Boolean initialize)
	{
		if(initialize)
		{
			//add key
			List<String> keysList = new ArrayList<String>();
			keysList.add("AIzaSyBPrXRjwThgbfhEvZx46lytwtKnDCeM2Xw");
			keysList.add("AIzaSyDNVUxQbPK8PMM8tT0pVPizdNgLzNeV8qw");
			keysList.add("AIzaSyBKZK197aNmhoWF7RUq-AqXXizGHNjkW_E");
			keysList.add("AIzaSyDdUzy1BDYGR6k2ZGW_lbpbSKeLhcyBLOE");
			keysList.add("AIzaSyCGbM7CaXlGBC0ey3FhhOCXmGO29SfNGNA");
			keysList.add("AIzaSyBRVKKUKLn6-Ac505hkqSIt16kXxmWG_ko");
			setGoogleAPIKeys(keysList);
						
			//add keys from Youtube Channels
			List<String> channelList = new ArrayList<String>();
			channelList.add("UCkEUAI-G5YUSXOBcazgpnUA");
			channelList.add("UCRKCTVnBNVX9jDDNaFhPk1A");
			channelList.add("UCb87w2HwzMiTSWJpgQgXJFQ");
			channelList.add("UCOy5M5xosCqqPYNGRzISBpw");
			channelList.add("UCWPGipZ9-ktXUtvSac2209g");
			
			//https://www.youtube.com/channel/UCRKXh0dGLgIOc2lbzZG2pmg
			//https://www.youtube.com/channel/UCcC75odpvQGI63SflLmcx7Q
			//https://www.youtube.com/channel/UCBNZrPqmJYXQSUkUgIVgLng
			//https://www.youtube.com/channel/UCbWgVCxEt6mYu2GVOMtKd1A
			//https://www.youtube.com/channel/UCSk6hkasRGZJwNRa8JkBI2g
			//https://www.youtube.com/channel/UCs7Z6uI9Xg14SetotHlNpfA
			//https://www.youtube.com/channel/UCUVVt6U7Qyk5ifrFP55GQ2A
			//https://www.youtube.com/channel/UCbqcG1rdt9LMwOJN4PyGTKg
			//https://www.youtube.com/channel/UCKPUll_mEogOAlx58672Vww
			
			//https://www.youtube.com/channel/UCily2V-y7V1Mrj3JewFTYDQ (fazer)
			//https://www.youtube.com/channel/UCBNZrPqmJYXQSUkUgIVgLng (fazer)
			//https://www.youtube.com/channel/UC90F1AbHAWq2Y5qx5okFfXg (fazer)
			
			
			//https://www.youtube.com/channel/UCEseG660SA_TGc9o5VGcXSw
			//https://www.youtube.com/channel/UCS88MWJ-SwKfYNS_zDi4xuQ
			//https://www.youtube.com/channel/UC8ePOKxbr65St7MtpsEXpLQ
			//https://www.youtube.com/channel/UC7INlXBnLeTdyhfHZSPi1AA
			//https://www.youtube.com/channel/UCR1SYiLw-DJWh37dkZMpL1g
			//https://www.youtube.com/channel/UCGPtwSksVkbWmstoUPe-7_A
			//https://www.youtube.com/channel/UCQW7oQh5sZaImSgfAIIGk6g
			//https://www.youtube.com/channel/UCB6-xeXegbTYfYI14x1M8eg
			//https://www.youtube.com/channel/UCaN6zySB_oT-wwfPRPXlxHA
			//https://www.youtube.com/channel/UCEquUJQdEPDSGlcemADZ0dg
			//https://www.youtube.com/channel/UCB2PtfyIP4ix5j2z-FPYCOA
			//https://www.youtube.com/channel/UCHsHiuE_LppL223ya1Aozog
			//https://www.youtube.com/channel/UCn3pS35ceTh14ls1JGKYnMA
			//https://www.youtube.com/channel/UC_Er8G7fL0PtFWof4wiRM7g
			//https://www.youtube.com/channel/UC_3QzRFqeHifV_TEVpijv4w
			//https://www.youtube.com/channel/UCP6tTN2l2BtyJWVshHtmQfQ
			//https://www.youtube.com/channel/UC3_kiyweO1hZwKJSM2_DJng
			//https://www.youtube.com/channel/UCoT_YK9oIGH-tM8NF2nMY9g
			//https://www.youtube.com/channel/UC4sCMXkZbT1WY-D7V6vMBig
			//https://www.youtube.com/channel/UCGSAiZVn95fq1-5ZTKEmfEg
			//https://www.youtube.com/channel/UC_b_3FzxQFrrx3i379u5CPA
			//https://www.youtube.com/channel/UC8AbWF4gRAhbdRLtUuGI6Xg
			//https://www.youtube.com/channel/UCFJOrnVeCIlJu1GYzslYtEA
			//UCS88MWJ-SwKfYNS_zDi4xuQ
			//UCwTRjvjVge51X-ILJ4i22ew
			//UCQW7oQh5sZaImSgfAIIGk6g
			//UCUjCVq8rtiO5vr6U_i5iUXw
			//UC-SM2X-QPVX1S4VqeLlMbhA
			//https://www.youtube.com/channel/UCb87w2HwzMiTSWJpgQgXJFQ
			//https://www.youtube.com/user/
			//https://www.youtube.com/channel/UCN2nuJG7mkmfqsMrH-5clgg	
			//https://www.youtube.com/channel/
			//https://www.youtube.com/channel/
			//https://www.youtube.com/channel/UCqOXzoxMr_m5D2ACBJGaA1Q
			//https://www.googleapis.com/youtube/v3/channels?key={YOUR_API_KEY}&forUsername={USER_NAME}&part=id
			//https://www.youtube.com/channel/UC_b_3FzxQFrrx3i379u5CPA
			//https://www.youtube.com/channel/UCT9aHb_Z1BX5cPrxHcexmXQ
			//https://www.youtube.com/c/CristinaPaula_CrisKaraok%C3%AAShow
			//get id channel https://youtube.googleapis.com/youtube/v3/videos?id=B1yrNFMvBaw&key=AIzaSyDNVUxQbPK8PMM8tT0pVPizdNgLzNeV8qw&part=snippet%2CcontentDetails%2Cstatistics&

			setGoogleChannelKeys(channelList);
			
		}
	}
}
