package org.yesee.hinet_vcpe_provider.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yesee.hinet_vcpe_provider.model.bean.Wan;
import org.yesee.hinet_vcpe_provider.model.dao.WanDao;

import com.google.common.collect.Lists;

@Service
public class WanService {

	@Autowired
	private WanDao wanDao;

	public List<Wan> findAll() {
		return wanDao.findAll();
	}

	public List<Wan> findAllByMacAddress(String macAddress) {
		List<Wan> wanList = Lists.newArrayList();
		for (Wan wan : wanDao.findAll()) {
			if (wan.getMacAddress().equals(macAddress)) {
				wanList.add(wan);
			}
		}
		return wanList;
	}

	public List<Wan> findAllBymacAddressQuicker(String macAddress) {
		return wanDao.findAllByMacAddressQuicker(macAddress);
	}

	public List<Wan> findAllOrderByDefault(String macAddress) {
		if (findDefaultWan(macAddress).isPresent()) {
			Wan wan = findDefaultWan(macAddress).get();
			List<Wan> wanListOrderByDefault = new ArrayList<>();
			wanListOrderByDefault.add(wan);
			List<Wan> wanList = wanDao.findAllByMacAddressQuicker(macAddress);
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
		}else {
			return wanDao.findAllByMacAddressQuicker(macAddress);
		}

	}

	public Optional<Wan> findById(Integer id) {
		return wanDao.findById(id);
	}

	public Optional<Wan> findDefaultWan(String macAddress) {
		for (Wan wan : wanDao.findAllByMacAddressQuicker(macAddress)) {
			if (wan.getDefaultSetting().equals(Wan.DefaultSetting.Yes)) {
				return Optional.of(wan);
			}
		}
		return Optional.empty();
	}

	public Optional<Wan> findByWanName(String wanName, String macAddress) {
		return wanDao.findByWanName(wanName, macAddress);
	}

	public void deleteWanById(Wan wan) {
		wanDao.deleteWanById(wan);
	}

	public void addOrEditWan(Wan wan) {
		wanDao.addOrEditWan(wan);
	}

}
