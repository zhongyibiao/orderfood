package com.xiaoweiyunchuang.orderfood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoweiyunchuang.orderfood.domain.FinanceReport;
import com.xiaoweiyunchuang.orderfood.mapper.NumericalStatementMapper;

@Service
public class NumericalStatementServiceImpl implements NumericalStatementService {

	@Autowired
	NumericalStatementMapper numericalStatementMapper;

	@Override
	public List<FinanceReport> selectFinanceReports(FinanceReport financeReport) {
		return numericalStatementMapper.selectFinanceReports(financeReport);
	}

}
