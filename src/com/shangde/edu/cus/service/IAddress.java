package com.shangde.edu.cus.service;

import java.util.List;

import com.shangde.edu.cus.condition.QueryAddressCondition;
import com.shangde.edu.cus.domain.Address;
import com.shangde.edu.cus.dto.AddressDTO;


public interface IAddress {
	 /**
     * 根据address对象添加首选地址
     * @param address
     * @return
     */
    public java.lang.Integer addAddress(Address address);

    /**
     * 根据用户id删除首选地址
     * @param id
     * @return
     */
    public void delAddressById(int id);


    /**
     * 根据用户id更新首选地址
     * @param address
     * @return
     */
    public void updateAddress(Address address);

    /**
     * 根据用户id查询首选地址
     * @param id
     * @return
     */
    public Address getAddressById(int id);
    
    /**
     * 根据学员ID得到地址
     */
    public List<Address> getAddressByCusId(int customerId);

    /**
     * 根据查询条件得到地址getAddressList
     * @param queryAddressCondition
     */
    public List<Address> getAddressList(QueryAddressCondition queryAddressCondition);

    /**
     * 根据用户id获取首选地址
     * @param loginUserId
     * @return
     */
	public Address getFirstAddressByCusId(int loginUserId);

	/**
	 * 根据条件获取地址DTO列表
	 * @param queryAddressCondition
	 * @return
	 */
	public List<AddressDTO> getAddressDTOList(
			QueryAddressCondition queryAddressCondition);

	/**
	 * 将用户的地址都改为非常用地址
	 * @param loginUserId
	 */
	public void updatesetAddrsCommonByCusId(int loginUserId);
}