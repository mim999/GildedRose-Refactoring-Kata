# -*- coding: utf-8 -*-
import unittest

from gilded_rose import Item, GildedRose


class GildedRoseTest(unittest.TestCase):
    # tests if quality decreases with time on normal item
    def test_quality_degretation(self):
        items = [Item("foo", sell_in=5, quality=20)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(19, items[0].quality)

    # tests if time decreases
    def test_time_degretation(self):
        items = [Item("foo", sell_in=5, quality=20)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(4, items[0].sell_in)

    # tests if quality decrease double after sellin passed
    def test_sellInPassed_quality_degretation(self):
        items = [Item("foo", sell_in=0, quality=20)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(18, items[0].quality)

    # tests if quality can't be negative
    def test_quality_negative(self):
        items = [Item("foo", sell_in=5, quality=0)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertFalse(0 > items[0].quality)

    # tests if quality increased of brie with time
    def test_brie_quality_increase(self):
        items = [Item("Aged Brie", sell_in=2, quality=0)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(1, items[0].quality)

    # test if quality maxes out at 50
    def test_quality_max(self):
        items = [Item("Aged Brie", sell_in=2, quality=50)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertTrue(items[0].quality <= 50)

    # tests if sulfuras sellin stay the same
    def test_sulfuras_sellin(self):
        items = [Item("Sulfuras, Hand of Ragnaros", sell_in=0, quality=80)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(0, items[0].sell_in)

     # tests if sulfuras quality stay the same
    def test_sulfuras_quality(self):
        items = [Item("Sulfuras, Hand of Ragnaros", sell_in=0, quality=80)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(80, items[0].quality)

    # test backstage increase by one
    def test_backstage_increase_by_one(self):
        items = [Item(name="Backstage passes to a TAFKAL80ETC concert",
                      sell_in=15, quality=20)
                 ]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(21, items[0].quality)

    # test backstage increase by two
    def test_backstage_increase_by_two(self):
        items = [
            Item(name="Backstage passes to a TAFKAL80ETC concert",
                      sell_in=10, quality=20)
        ]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(22, items[0].quality)

    # test backstage increase by three
    def test_backstage_increase_by_three(self):
        items = [
            Item(name="Backstage passes to a TAFKAL80ETC concert",
                      sell_in=5, quality=20)
        ]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(23, items[0].quality)

    # test backstage quality to zero after sellin
    def test_backstage_quality_zero(self):
        items = [
            Item(name="Backstage passes to a TAFKAL80ETC concert",
                      sell_in=0, quality=20)
        ]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(0, items[0].quality)

    # test conjured loses quality twice as fast
    def test_conjured(self):
        items = [Item(name="Conjured Mana Cake", sell_in=3, quality=6)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(4, items[0].quality)


if __name__ == '__main__':
    unittest.main()
