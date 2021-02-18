package com.gildedrose;

class GildedRose {
    Item[] items;
    public static final String BACKSTAGE_PASS_NAME = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_NAME = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE_NAME = "Aged Brie";
    public static final String CONJURED_NAME = "Conjured Mana Cake";


    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (!items[i].name.equals(AGED_BRIE_NAME)
                    && !items[i].name.equals(BACKSTAGE_PASS_NAME)) {
                if (items[i].quality > 0) {
                    if (!items[i].name.equals(SULFURAS_NAME)) {
                        items[i].quality = items[i].quality - 1;
                    }
                }
            } else {
                checkBrie(i);
            }

            if (!items[i].name.equals(SULFURAS_NAME)) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                sellinPassed(i);
            }
        }
    }

    private void sellinPassed(int i) {
        if (!items[i].name.equals(AGED_BRIE_NAME)) {
                if (!items[i].name.equals(BACKSTAGE_PASS_NAME)) {
                    if (items[i].quality > 0) {
                        if (!items[i].name.equals(SULFURAS_NAME)) {
                            items[i].quality = items[i].quality - 1;
                        }
                    }
                } else {
                    items[i].quality = items[i].quality - items[i].quality;
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;
                }
        }
    }

    private void checkBrie(int i) {
        if (items[i].quality < 50) {
            items[i].quality = items[i].quality + 1;

            if (items[i].name.equals(BACKSTAGE_PASS_NAME)) {
                checkBackstage(i);
            }
        }
    }

    private void checkBackstage(int i) {
        if (items[i].sellIn < 11) {
            if (items[i].quality < 50) {
                items[i].quality = items[i].quality + 1;
            }
        }

        if (items[i].sellIn < 6) {
            if (items[i].quality < 50) {
                items[i].quality = items[i].quality + 1;
            }
        }
    }
}