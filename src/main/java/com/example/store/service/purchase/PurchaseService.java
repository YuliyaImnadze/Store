package com.example.store.service.purchase;

import com.example.store.dto.purchase.PurchaseDtoRequest;
import com.example.store.dto.purchase.PurchaseDtoResponse;
import com.example.store.entity.ProductLine;
import com.example.store.entity.Purchase;
import com.example.store.service.common.CommonService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface PurchaseService extends CommonService<Purchase, PurchaseDtoRequest,PurchaseDtoResponse> {

    PurchaseDtoResponse create(PurchaseDtoRequest purchaseDtoRequest);

    List<PurchaseDtoResponse> findAllPurchaseByBuyer(Authentication authentication);


}
