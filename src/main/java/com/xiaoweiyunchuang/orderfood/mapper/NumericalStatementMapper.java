package com.xiaoweiyunchuang.orderfood.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiaoweiyunchuang.orderfood.domain.FinanceReport;

@Mapper
public interface NumericalStatementMapper {

	List<FinanceReport> selectFinanceReports(FinanceReport financeReport);

}