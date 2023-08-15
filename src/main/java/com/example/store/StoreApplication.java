package com.example.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }

}

// все хэш переделать
// все dto пересмотреть


// зачем нужен fetchType при Dto
// когда в репо писать квери
// а ты сразу понимаешь, когда сущности делаешь, где нужно ставить FetchType?
// как понять где зациклится? (потому что я только по факту ставлю @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL) - вот тут еще. взяло и все сохранилось без репозитория. вроде понятно, что так, но не понятно как предугадать
//    private Set<ProductLine> productList;
// точно правильно хэш код рассчитается. как понять, если неправильно? в каких случаях нужно исключать поля (кроме стэк оверф)
