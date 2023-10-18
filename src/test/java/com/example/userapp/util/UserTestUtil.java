package com.example.userapp.util;

import com.example.userapp.model.entity.SectorItem;
import com.example.userapp.model.entity.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserTestUtil {

    private final SectorItemTestUtil sectorItemTestUtil;

    public static User getUser(List<SectorItem> sectorItems) {

        return createEntity("David", sectorItems, true);
    }

    private static User createEntity(String name, List<SectorItem> sectorItems, boolean agreedToTerms) {

        User user = new User();

        user.setName(name);
        user.setSectorItems(sectorItems);
        user.setAgreedToTerms(agreedToTerms);
        return user;
    }
}
