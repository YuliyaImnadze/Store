package com.example.store.entity2;

import com.example.store.entity.Company;

import java.math.BigDecimal;

public class RegistrationProduct  {


    private String name;

    private String description;

    //    @ManyToOne
//    @JoinColumn(name = "supplier_id")
    private Company supplier;

    private BigDecimal price;


//    не знаю как
//    private List<Keyword> keywords;


//    @Lob // не знаю как
//    @Enumerated(EnumType.STRING)
//    @Column(name = "characteristics", columnDefinition = "BLOB")
//    private Map<Characteristic, CharacteristicValues> characteristics;


}
