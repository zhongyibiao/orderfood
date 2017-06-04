package com.xiaoweiyunchuang.orderfood.domain;

import java.util.List;

public class SMSCallBackBean {
	private AlibabaSMSBean alibaba_aliqin_fc_sms_num_send_response;

	public class AlibabaSMSBean {
		private String request_id;
		private ResultBean result;

		public class ResultBean {
			private String err_code;
			private String model;
			private boolean success;

			public String getErr_code() {
				return err_code;
			}

			public void setErr_code(String err_code) {
				this.err_code = err_code;
			}

			public String getModel() {
				return model;
			}

			public void setModel(String model) {
				this.model = model;
			}

			public boolean isSuccess() {
				return success;
			}

			public void setSuccess(boolean success) {
				this.success = success;
			}

		}

		public String getRequest_id() {
			return request_id;
		}

		public void setRequest_id(String request_id) {
			this.request_id = request_id;
		}

		public ResultBean getResult() {
			return result;
		}

		public void setResult(ResultBean result) {
			this.result = result;
		}

	}

	public AlibabaSMSBean getAlibaba_aliqin_fc_sms_num_send_response() {
		return alibaba_aliqin_fc_sms_num_send_response;
	}

	public void setAlibaba_aliqin_fc_sms_num_send_response(AlibabaSMSBean alibaba_aliqin_fc_sms_num_send_response) {
		this.alibaba_aliqin_fc_sms_num_send_response = alibaba_aliqin_fc_sms_num_send_response;
	}

}
