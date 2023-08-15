package com.example.store.entity2;

import com.example.store.entity.User;

public class RegistrationOrganization {

    private String name;
    private String description;
    private String logo;

    //    @ManyToOne
//    @JoinColumn(name = "owner_id") // тот же самый вопрос, что и у организации
    private User owner;

}
