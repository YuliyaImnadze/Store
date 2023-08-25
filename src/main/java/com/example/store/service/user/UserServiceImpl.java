package com.example.store.service.user;

import com.example.store.dto.user.UserDtoRequest;
import com.example.store.dto.user.UserDtoResponse;
import com.example.store.entity.ProductLine;
import com.example.store.entity.User;
import com.example.store.exception.PageSizeExceedsLimitException;
import com.example.store.mapper.UserMapper;
import com.example.store.repository.UserRepository;
import com.example.store.service.common.CommonServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "users")
public class UserServiceImpl extends CommonServiceImpl<User, UserDtoRequest, UserDtoResponse,
        UserRepository,
        UserMapper>
        implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository repository, UserMapper mapper,
                           UserMapper userMapper) {
        super(repository, mapper);
        this.userMapper = userMapper;
    }

//    @Override // пока не удаляю. дальше все равно буду переопределять, чтобы пароль захэш
//    @Transactional
//    public UserDtoResponse create(UserDtoRequest userDtoRequest) {
//        User user = mapper.toEntityFromRequest(userDtoRequest);
//        User saved = repository.save(user);
//        return mapper.toDtoResponseFromEntity(saved);
//    }


    @Override
    public Page<UserDtoResponse> findAll(Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        Page<User> users = repository.findAll(PageRequest.of(
                pageNumber,
                pageable.getPageSize(),
                Sort.by("username")));
//        pageable.getSortOr(Sort.by("username"))
        int totalPages = users.getTotalPages();
        if (pageNumber > totalPages) {
            throw new PageSizeExceedsLimitException("Invalid page number.There are only " + totalPages + " pages in the table");
        }
            return users.map(userMapper::toDtoResponseFromEntity);
    }

    @Override
    @Cacheable // ничего дальше не делала, потому что не знаю как - все переопределять?
    public Long totalCount() {
        return repository.count();
    }

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
