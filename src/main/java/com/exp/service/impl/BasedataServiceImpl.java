package com.exp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.base.BaseDaoImpl;
import com.exp.entities.Basedata;
import com.exp.service.BasedataService;
@Service
@Transactional
public class BasedataServiceImpl extends BaseDaoImpl<Basedata> implements
		BasedataService {

}
