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

    public void updateQuality() {
        for (Item item: items) {
            UpdateSingleItem(item);
        }
    }

    private void UpdateSingleItem(Item item) {
        updateQuality(item);

        updateSellin(item);

        checkSellinPassed(item);
    }

    private void checkSellinPassed(Item item) {
        if (item.sellIn < 0) {
            updateSellinPassed(item);
        }
    }

    private void updateSellin(Item item) {
        if (!item.name.equals(SULFURAS_NAME)) {
            item.sellIn = item.sellIn - 1;
        }
    }

    private void updateQuality(Item item) {
        if (item.name.equals(AGED_BRIE_NAME)
                || item.name.equals(BACKSTAGE_PASS_NAME)) {
                    checkBrie(item);
                }
        else if (item.quality > 0) {
            if (!item.name.equals(SULFURAS_NAME)) {
                decreaseQuality(item);
            }
        }
    }

    private void increaseQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality = item.quality + 1;
        }
    }

    private void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    private void updateSellinPassed(Item item) {
        if (item.name.equals(AGED_BRIE_NAME)) {
            increaseQuality(item);
        }
        else if (item.name.equals(BACKSTAGE_PASS_NAME)) {
            item.quality = 0;
        }
        else if (!item.name.equals(SULFURAS_NAME)) {
            decreaseQuality(item);
        }
    }

    private void checkBrie(Item item) {
        if (item.name.equals(BACKSTAGE_PASS_NAME)) {
            checkBackstage(item);
        }
        increaseQuality(item);
    }

    private void checkBackstage(Item item) {
        if (item.sellIn <= BACKSTAGE_PASS_DOUBLE_SELLIN) {
            increaseQuality(item);
        }

        if (item.sellIn <= BACKSTAGE_PASS_TRIPLE_SELLIN) {
            increaseQuality(item);
        }
    }
}