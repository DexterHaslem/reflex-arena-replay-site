from flask import jsonify
from datetime import datetime


# TODO: replay binary parsing

def replay(mapname, players, scores, time, size, href=''):
    return {
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
        replays_resp.append(replay("cpm3a", "joe,schmo", [1, 5], datetime.now(), 1024))

    return replays_resp


def get_replays_json(root_dir):
    return jsonify(get_replays(root_dir))
