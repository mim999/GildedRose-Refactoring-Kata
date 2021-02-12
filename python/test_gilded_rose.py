# -*- coding: utf-8 -*-
import unittest

from gilded_rose import Item, GildedRose


class GildedRoseTest(unittest.TestCase):
    def test_quality_degretation(self):
        items = [Item("foo", sell_in=5, quality=20)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(19, items[0].quality)

    def test_time_degretation(self):
        items = [Item("foo", sell_in=5, quality=20)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(4, items[0].sell_in)

    def test_sellInPassed_quality_degretation(self):
        items = [Item("foo", sell_in=0, quality=20)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(18, items[0].quality)

    def test_quality_negative(self):
        items = [Item("foo", sell_in=5, quality=0)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertFalse(0 > items[0].quality)

    def test_brie_quality_increase(self):
        items = [Item("Aged Brie", sell_in=2, quality=0)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(1, items[0].quality)

    def test_quality_max(self):
        items = [Item("Aged Brie", sell_in=2, quality=50)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertTrue(items[0].quality <= 50)

    def test_sulfuras(self):
        items = [Item("Sulfuras, Hand of Ragnaros", sell_in=0, quality=80)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(80, items[0].quality)
        self.assertEqual(0, items[0].sell_in)

    def test_backstage(self):
        items = [Item(name="Backstage passes to a TAFKAL80ETC concert",
                      sell_in=15, quality=20),
                 Item(name="Backstage passes to a TAFKAL80ETC concert",
                      sell_in=10, quality=20),
                 Item(name="Backstage passes to a TAFKAL80ETC concert",
                      sell_in=5, quality=20),
                 Item(name="Backstage passes to a TAFKAL80ETC concert",
                      sell_in=0, quality=20)
                 ]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(21, items[0].quality)
        self.assertEqual(22, items[1].quality)
        self.assertEqual(23, items[2].quality)
        self.assertEqual(0, items[3].quality)


if __name__ == '__main__':
    unittest.main()
