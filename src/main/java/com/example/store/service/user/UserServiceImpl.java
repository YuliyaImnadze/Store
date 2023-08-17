package com.example.store.service.user;

import com.example.store.dto.user.UserDtoRequest;
import com.example.store.dto.user.UserDtoResponse;
import com.example.store.entity.ProductLine;
import com.example.store.entity.User;
import com.example.store.mapper.UserMapper;
import com.example.store.repository.CompanyRepository;
import com.example.store.repository.UserRepository;
import com.example.store.service.common.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class UserServiceImpl extends CommonServiceImpl<User, UserDtoRequest, UserDtoResponse,
        UserRepository,
        UserMapper>
        implements UserService {

    public UserServiceImpl(UserRepository repository, UserMapper mapper) {
        super(repository, mapper);
    }

//    @Override // пока не удаляю. дальше все равно буду переопределять, чтобы пароль захэш
//    @Transactional
//    public UserDtoResponse create(UserDtoRequest userDtoRequest) {
//        User user = mapper.toEntityFromRequest(userDtoRequest);
//        User saved = repository.save(user);
//        return mapper.toDtoResponseFromEntity(saved);
//    }



    @Override
    public void addMoneyToSeller(ProductLine productLine) {
        User ownerCompany = productLine.getProduct().getSupplier().getOwner();
        BigDecimal totalSum = productLine.getTotalSum();
        ownerCompany.setBalance(ownerCompany.getBalance().add(totalSum));
    }


    @Override
    public boolean wasTheProductPurchased(UUID buyerId, UUID productId) {
        return repository.existsProductIdByUserId(buyerId, productId);
    }


}
