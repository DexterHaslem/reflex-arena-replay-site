import unittest
from replays import get_replays_in_dir


class ReplaysTestCase(unittest.TestCase):
    def setUp(self):
        self.test_dir = 'testreplays'

    def tearDown(self):
        pass

    def test_get_replays_in_dir(self):
        replays = get_replays_in_dir(self.test_dir)
        assert len(replays) == 1
        d = replays[0]
        assert d['time'] == 1468043981
        assert d['size'] == 184
        assert d['href'] == 'testreplays\\2R2H_Trampoline_09Jul2016_0059_0markers.rep'
        assert d['filename'] == '2R2H_Trampoline_09Jul2016_0059_0markers'



if __name__ == '__main__':
    unittest.main()
