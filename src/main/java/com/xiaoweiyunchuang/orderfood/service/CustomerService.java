package com.xiaoweiyunchuang.orderfood.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.xiaoweiyunchuang.microcloud.common.CheckUtil;
import com.xiaoweiyunchuang.microcloud.common.DateUtil;
import com.xiaoweiyunchuang.microcloud.common.IDGenerate;
import com.xiaoweiyunchuang.orderfood.config.SMSConfig;
import com.xiaoweiyunchuang.orderfood.domain.Customer;
import com.xiaoweiyunchuang.orderfood.domain.SMSBean;
import com.xiaoweiyunchuang.orderfood.domain.SMSCallBackBean;
import com.xiaoweiyunchuang.orderfood.mapper.CustomerMapper;

@Service
public class CustomerService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private static int vertifacationCode = 0;
	@Autowired
	private CustomerMapper mapper;
	
	@Autowired
	RedisTemplate<Object, Object> redisTemplate;
	@Resource(name = "redisTemplate")
	ValueOperations<Object, Object> valOps;

	/**
	 * 新增会员
	 * @param customer
	 */
	@Transactional
	public void saveCustomer(Customer customer) {
		customer.setCustomerId(""+IDGenerate.getId());
		customer.setCreateBy(customer.getPhoneNum());
		customer.setCreateDate(DateUtil.now());
		customer.setUpdateBy(customer.getPhoneNum());
		customer.setUpdateDate(DateUtil.now());
		customer.setDelFlag("N");
		mapper.saveCustomer(customer);
	}
	
	/**
	 * 查询个人信息
	 * @param customerId
	 * @return
	 */
	@Transactional
	public Customer findCustomerById(String customerId) {
		Customer customer = mapper.findCustomerById(customerId);
		return customer;
	}

	/**
	 * 验证该用户是否存在
	 * 
	 * @param name
	 * @return
	 */
	public boolean isCustomerrExist(String name) {
		List<Customer> customers = mapper.findCustomerByName(name);

		for (Customer customer : customers) {
			if (customer.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;

	}
	
	/**
	 * 校验验证码是否有效
	 * @param phoneNum
	 * @param validateCode
	 * @param time
	 * @return
	 */
	public boolean checkValidateCode(String phoneNum, String validateCode, String time){
		SMSBean bean = (SMSBean) valOps.get(phoneNum);
		if(!CheckUtil.isLongTime(time) || !CheckUtil.isValidateCode(validateCode)){
			return false;
		}else{
			long lTime = Long.valueOf(time);
			long waitTime = lTime - bean.getCreateTime();
			if(waitTime < SMSBean.WAIT_TIME && bean.getPhoneNum().equals(phoneNum) && bean.getCode() == Integer.valueOf(vertifacationCode)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 根据用户ID查找该用户是否存在
	 * @param customerId
	 * @return
	 */
	public boolean isCustomerExist(String customerId){
		Customer customer = mapper.findCustomerById(customerId);
		if(customer != null){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断该电话号码是否存在
	 * @param customer
	 * @return
	 */
	public boolean isPhoneNumExist(Customer customer){
		if(customer != null){
			String phoneNum = customer.getPhoneNum();
			if(CheckUtil.isMobileNO(phoneNum)){
				List<Customer> customers = mapper.findCustomerInfo(customer);
				for(Customer cus: customers){
					if(cus.getPhoneNum().equalsIgnoreCase(phoneNum)){
						//然后对比验证码是否一致
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 发送短信
	 * 
	 * @param phone
	 * @return
	 */
	public boolean isSendSMSSuccess(String phone) {
		vertifacationCode = (int) (Math.random() * 900000 + 100000);
		TaobaoClient client = new DefaultTaobaoClient(SMSConfig.BASE_URL, SMSConfig.API_Key, SMSConfig.SECRET);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
//		req.setExtend("123456");
		req.setSmsType("normal");
		req.setSmsFreeSignName("小微云创");
		req.setSmsParamString("{\"code\":\""+vertifacationCode+"\",\"product\":\""+SMSConfig.SIGN_NAME+"\"}");
		req.setRecNum(phone);
		req.setSmsTemplateCode(SMSConfig.MODEL_ID);
		try {
			AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
			logger.info(rsp.getBody());
			SMSCallBackBean bean = (SMSCallBackBean) JSONObject.parseObject(rsp.getBody(), SMSCallBackBean.class);
			SMSCallBackBean.AlibabaSMSBean bean2 = null;
			SMSCallBackBean.AlibabaSMSBean.ResultBean bean3 = null;
			if (bean != null)
				bean2 = bean.getAlibaba_aliqin_fc_sms_num_send_response();
			else
				return false;
			if (bean2 != null)
				bean3 = bean2.getResult();
			else
				return false;
			if (bean3 != null && bean3.isSuccess()){
				//返回成功后将值存入map
				SMSBean smsbean = new SMSBean();
				smsbean.setPhoneNum(phone);
				smsbean.setCreateTime(System.currentTimeMillis());
				smsbean.setCode(vertifacationCode);
				
				valOps.set(phone, smsbean);
				return true;
			}
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 完善个人资料
	 * @param customer
	 */
	@Transactional
	public void updataCustomerInfo(Customer customer){
		if(customer != null){
			mapper.updateCustomer(customer);
		}
	}
}
