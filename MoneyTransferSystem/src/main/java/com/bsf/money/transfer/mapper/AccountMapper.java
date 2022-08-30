package com.bsf.money.transfer.mapper;

import com.bsf.money.transfer.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(source = "accountNumber", target = "accountNumber")
//    @Mapping(source = "balance", target = "balance")
    Account toModel(com.bsf.money.transfer.entities.Account account);
}
