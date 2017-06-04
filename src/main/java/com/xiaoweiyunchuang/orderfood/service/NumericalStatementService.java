package com.xiaoweiyunchuang.orderfood.service;

import java.util.List;

import com.xiaoweiyunchuang.orderfood.domain.FinanceReport;

public interface NumericalStatementService {

	List<FinanceReport> selectFinanceReports(FinanceReport financeReport);

}
