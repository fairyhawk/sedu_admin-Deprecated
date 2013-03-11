package com.shangde.edu.cus.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.cus.condition.QueryAddressCondition;
import com.shangde.edu.cus.domain.Address;
import com.shangde.edu.cus.dto.AddressDTO;

@SuppressWarnings("unchecked")
public class AddressImpl extends BaseService implements IAddress{
    public java.lang.Integer addAddress(Address address) {
return simpleDao.createEntity("Address_NS.createAddress",address);
    }

    public void delAddressById(int id){
        simpleDao.deleteEntity("Address_NS.deleteAddressById",id);
    }

    public void updateAddress(Address address) {
        simpleDao.updateEntity("Address_NS.updateAddress",address);
    }

    public Address getAddressById(int id) {
        return simpleDao.getEntity("Address_NS.getAddressById",id);
    }

    public List<Address> getAddressList(QueryAddressCondition queryAddressCondition) {
        return simpleDao.getForList("Address_NS.getAddressList",queryAddressCondition);
    }

	public Address getFirstAddressByCusId(int loginUserId) {
		return simpleDao.getEntity("Address_NS.getFirstAddressByCusId", loginUserId);
	}

	public List<AddressDTO> getAddressDTOList(
			QueryAddressCondition queryAddressCondition) {
        return simpleDao.getForList("Address_NS.getAddressDTOList",queryAddressCondition);
	}

	public void updatesetAddrsCommonByCusId(int loginUserId) {
		simpleDao.updateEntity("Address_NS.setAddrsCommonByCusId", loginUserId);
	}

	public List<Address> getAddressByCusId(int customerId) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("Address_NS.getAddressByCusId", customerId);
	}
}
