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

    private void updateSellinPassed(Item item) {
        if (!item.name.equals(SULFURAS_NAME)) {
            item.sellIn = item.sellIn - 1;
        }
    }

    private void updateQuality(Item item) {
        if (!item.name.equals(AGED_BRIE_NAME)
                && !item.name.equals(BACKSTAGE_PASS_NAME)) {
            if (item.quality > 0) {
                if (!item.name.equals(SULFURAS_NAME)) {
                    item.quality = item.quality - 1;
                }
            }
        } else {
            checkBrie(item);
        }
    }

    private void sellinPassed(Item item) {
        if (!item.name.equals(AGED_BRIE_NAME)) {
                if (!item.name.equals(BACKSTAGE_PASS_NAME)) {
                    if (item.quality > 0) {
                        if (!item.name.equals(SULFURAS_NAME)) {
                            item.quality = item.quality - 1;
                        }
                    }
                } else {
                    item.quality = item.quality - item.quality;
                }
            } else {
            checkMaxQuality(item);
        }
    }

    private void checkMaxQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality = item.quality + 1;
        }
    }

    private void checkBrie(Item item) {
        if (item.name.equals(BACKSTAGE_PASS_NAME)) {
            checkBackstage(item);
        }
        checkMaxQuality(item);
    }

    private void checkBackstage(Item item) {
        if (item.sellIn <= BACKSTAGE_PASS_DOUBLE_SELLIN) {
            checkMaxQuality(item);
        }

        if (item.sellIn <= BACKSTAGE_PASS_TRIPLE_SELLIN) {
            checkMaxQuality(item);
        }
    }
}