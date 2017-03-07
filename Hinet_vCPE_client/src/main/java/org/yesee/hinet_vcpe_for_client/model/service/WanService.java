package org.yesee.hinet_vcpe_for_client.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yesee.hinet_vcpe_for_client.model.bean.Wan;
import org.yesee.hinet_vcpe_for_client.model.dao.WanDao;

@Service
public class WanService {

	@Autowired
	private WanDao wanDao;

	public List<Wan> findAll() {
		return wanDao.findAll();
	}

	public List<Wan> findAllWithDefaultWanFirst() {
		
		if (findDefaultWan().isPresent()) {
			Wan wan = findDefaultWan().get();
			List<Wan> wanListOrderByDefault = new ArrayList<>();
			wanListOrderByDefault.add(wan);
			List<Wan> wanList = wanDao.findAll();
			ListIterator<Wan> it = wanList.listIterator();
			while (it.hasNext()) {
				if (it.next().getId() == wan.getId()) {
					it.remove();
				}
			}
			while (it.hasPrevious()) {
				it.previous();
			}
			while (it.hasNext()) {
				wanListOrderByDefault.add(it.next());
			}

			return wanListOrderByDefault;
		} else {
			return wanDao.findAll();
			
		}
	}

	public Optional<Wan> findById(Integer id) {
		return wanDao.findById(id);
	}

	public Optional<Wan> findDefaultWan() {
		for (Wan wan : wanDao.findAll()) {
			if (wan.getDefaultSetting().equals(Wan.DefaultSetting.Yes)) {
				return Optional.of(wan);
			}
		}
		return Optional.empty();
	}

	public Optional<Wan> findByWanName(String wanName) {
		return wanDao.findByWanName(wanName);
	}

	public void deleteWanById(Wan wan) {
		wanDao.deleteWanById(wan);
	}

	public void addOrEditWan(Wan wan) {
		wanDao.addOrEditWan(wan);
	}

}
