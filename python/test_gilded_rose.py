# -*- coding: utf-8 -*-
import unittest

from gilded_rose import Item, GildedRose


class GildedRoseTest(unittest.TestCase):
    def test_quality_degretation(self):
        items = [Item("foo", sell_in=5, quality=20)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(19, items[0].quality)


if __name__ == '__main__':
    unittest.main()
