package com.example.userapp.util;

import com.example.userapp.model.entity.SectorItem;

import java.util.List;

public class SectorItemTestUtil {

    public static List<SectorItem> getSectorItems() {

        SectorItem parentAll = createEntity("Manufacturing", false, null);
        SectorItem parent = createEntity("Food and Beverage", false, parentAll);
        SectorItem child = createEntity("Beverages", true, parent);

        return List.of(
                parentAll,
                parent,
                child
        );

    }

    public static SectorItem createEntity(String name, boolean isLeaf, SectorItem parent) {

        SectorItem sectorItem = new SectorItem();
        sectorItem.setName(name);
        sectorItem.setLeaf(isLeaf);
        sectorItem.setParent(parent);

        return sectorItem;
    }
}
