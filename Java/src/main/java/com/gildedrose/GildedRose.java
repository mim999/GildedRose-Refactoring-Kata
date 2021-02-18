package com.gildedrose;

class GildedRose {
    Item[] items;
    public static final String BACKSTAGE_PASS_NAME = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_NAME = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE_NAME = "Aged Brie";
    public static final String CONJURED_NAME = "Conjured Mana Cake";

    public static final int MAX_QUALITY = 50;
    public static final int BACKSTAGE_PASS_DOUBLE_SELLIN = 10;
    public static final int BACKSTAGE_PASS_TRIPLE_SELLIN = 5;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    // runs through all items
    public void updateQuality() {
        for (Item item: items) {
            UpdateSingleItem(item);
        }
    }

    // Updates sellin and then quality
    private void UpdateSingleItem(Item item) {
        updateSellin(item);
        if (item.sellIn < 0) {
            updateQualityExpired(item);
        }
        else {
            updateQuality(item);
        }
    }

    // sellin update
    private void updateSellin(Item item) {
        if (!item.name.equals(SULFURAS_NAME)) {
            item.sellIn = item.sellIn - 1;
        }
    }

    // quality update for non expired
    private void updateQuality(Item item) {
        // brie increases in quality
        if(item.name.equals(AGED_BRIE_NAME)){
            increaseQuality(item, 1);
        }

        // backstage increase quality in three stages
        else if(item.name.equals(BACKSTAGE_PASS_NAME)) {
            if (item.sellIn <= BACKSTAGE_PASS_TRIPLE_SELLIN) {
                increaseQuality(item,3);
            }
            else if (item.sellIn <= BACKSTAGE_PASS_DOUBLE_SELLIN) {
                increaseQuality(item,2);
            }
            else {
                increaseQuality(item, 1);
            }
        }

        // conjured decreases twice the speed
        else if(item.name.equals(CONJURED_NAME)){
            decreaseQuality(item,2);
        }

        // sulfuras stays the same
        else if (item.name.equals(SULFURAS_NAME)) {
                return;
            }

        // all else decreases by 1
        else{
            decreaseQuality(item,1);
        }
    }

    // updates quality of items with sellin < 0
    private void updateQualityExpired(Item item) {
        // brie increases
        if (item.name.equals(AGED_BRIE_NAME)) {
            increaseQuality(item, 1);
        }
        // backstage loses value
        else if (item.name.equals(BACKSTAGE_PASS_NAME)) {
            item.quality = 0;
        }

        // sulfuras stays the same
        else if (item.name.equals(SULFURAS_NAME)) {
            return;
        }

        // all else loses twice as fast
        else{
            decreaseQuality(item, 2);
        }
    }

    // Increase items quality by value, can't be over max
    private void increaseQuality(Item item, int value) {
        if (item.quality < MAX_QUALITY) {
            item.quality = item.quality + value;
        }
    }

    // Decrease items quality by value, can't be below zero
    private void decreaseQuality(Item item, int value) {
        if (item.quality > 0) {
            item.quality = item.quality - value;
        }
    }


}