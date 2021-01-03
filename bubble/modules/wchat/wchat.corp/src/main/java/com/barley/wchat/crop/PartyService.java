package com.barley.wchat.crop;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpUser;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;

/**
 * 成员管理
 * 
 * @author peculiar.1@163.com
 * @version $ID: PartyService.java, V1.0.0 2020年12月29日 下午12:26:15 $
 */
public class PartyService {
	public static void main(String[] args) {
		Integer agentId = 1000002;
		// config
		WxCpDefaultConfigImpl config = new WxCpDefaultConfigImpl();
		config.setCorpId("wwea11285cb9dba5b5"); // 设置微信企业号的appid
		// 应用测试
		config.setCorpSecret("NZ5ZXOP3aGpYQ8sAcBEJAEJtdvvh6QtaFnTghuhAP6c"); // 设置微信企业号的app corpSecret
		config.setAgentId(1000002); // 设置微信企业号应用ID
		config.setToken(
				"SEjcOYZu5mdCbJYZbPbVu7FApar1pQAgHRuFZ-DdXx_UlCpMi1Ewh4plgH5Y_cAJuYSneplTUeE9QIbIlLIuUOpaxgxxmst-83fcY45zawH4QqBYJf1rFVCg-l4zdUhjFEl3Yz6OEW4CR2Vdz-4eKVRK0U4HaiyIFQJDqR_8AwUGR9VshUkxk6LM615oTHyGll75_m0caJ-MSVg7yq2w2Q"); // 设置微信企业号应用的token
		config.setAesKey("..."); // 设置微信企业号应用的EncodingAESKey

		// service
		WxCpServiceImpl wxCpService = new WxCpServiceImpl();
		wxCpService.setWxCpConfigStorage(config);

		String userId = "ZhuYuanYu";
		WxCpMessage message = WxCpMessage.TEXT().agentId(agentId).toUser(userId).content("企业信息测试消息,请不要回复").build();
		try {
			wxCpService.getMessageService().send(message);
		} catch (WxErrorException e) {
			e.printStackTrace();
		}

		try {
			WxCpUser wxCpUser = wxCpService.getUserService().getById(userId);
			System.out.println(wxCpUser.getName());
			System.out.println(wxCpUser.getTelephone());
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
	}
}
