package com.njupt.swg.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.njupt.swg.dao.IKeywordDao;
import com.njupt.swg.model.Keyword;
import com.njupt.swg.model.Pager;

@Service("keywordService")
public class KeywordService implements IKeywordService {
	private IKeywordDao keywordDao;
	
	public IKeywordDao getKeywordDao() {
		return keywordDao;
	}
	@Inject
	public void setKeywordDao(IKeywordDao keywordDao) {
		this.keywordDao = keywordDao;
	}

	@Override
	public List<Keyword> getKeywordByTimes(int num) {
		List<Keyword> ks = keywordDao.findUseKeyword();
		List<Keyword> kks = new ArrayList<Keyword>();
		for(Keyword k:ks){
			if(k.getTimes() >= num)
				kks.add(k);
			else
				break;
		}
		return kks;
	}

	@Override
	public List<Keyword> getMaxTimesKeyword(int num) {
		List<Keyword> ks = keywordDao.findUseKeyword();
		List<Keyword> kks = new ArrayList<Keyword>();
		if(ks.size()<=num)
			return ks;
		for(int i=0; i<num; i++){
			kks.add(ks.get(i));
		}
		return kks;
	}

	@Override
	public void addOrUpdate(String name) {
		this.keywordDao.addOrUpdate(name);
	}

	@Override
	public List<Keyword> listChiefKeyword(int num) {
		return this.keywordDao.listChiefKeyword(num);
	}

	@Override
	public Pager<Keyword> findNoUserKeyword() {
		return this.keywordDao.findNoUserKeyword();
	}

	@Override
	public void clearNoUseKeyword() {
		this.keywordDao.clearNoUseKeyword();
	}

	@Override
	public List<Keyword> findUseKeyword() {
		return this.keywordDao.findUseKeyword();
	}

	@Override
	public List<Keyword> listKeywordByCon(String con) {
		return this.keywordDao.listKeywordByCon(con);
	}

}
