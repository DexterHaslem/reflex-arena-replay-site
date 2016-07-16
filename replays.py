from flask import jsonify
from datetime import datetime
from os import listdir
from os.path import isfile, join, splitext, getsize, getmtime
from math import floor

REPLAY_EXT = "rep"


# TODO: replay binary parsing

def get_replays_in_dir(root):
    ret = []
    files = [f for f in listdir(root) if isfile(join(root, f))]
    for f in files:
        filename, ext = splitext(f)
        fullpath = join(root, f)
        ret.append({
            "filename": filename,
            "size": floor(getsize(fullpath) / 1024),
            "time": floor(getmtime(fullpath)),
            "href": fullpath
        })
    return ret


def replay(filename, mapname, players, scores, time, size, href=''):
    return {
        "filename": filename,
        "map": mapname,
        "players": players,
        "scores": scores,
        "time": time,
        "size": size,
        "href": href
    }


def get_replays(root_dir):
    replays_resp = []

    # test
    for x in range(10):
        replays_resp.append(replay("foo.rep", "cpm3a", "joe,schmo", [1, 5], datetime.now(), 1024, 'foo.rep'))

    return replays_resp


def get_replays_json(root_dir):
    return jsonify(get_replays(root_dir))
