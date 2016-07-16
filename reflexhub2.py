from flask import Flask, render_template
from replays import get_replays, get_replays_json

app = Flask(__name__)

# on actual server
# replay_dir = "C:\\steamcmd\\reflex_ds\\replays"
replay_dir = 'testreplays'

@app.route('/')
def index():
    return file_list()

@app.route('/parsed')
def parsed():
    return render_template('parsed.html', replays=get_replays(replay_dir))

@app.route('/fileList')
def file_list():
    return render_template('filelist.html', replays=get_replays(replay_dir))

@app.route('/about')
def about():
    return render_template('about.html')

@app.route('/getReplays')
def get_replays_rest():
    replay_json = get_replays_json(replay_dir)
    return replay_json


if __name__ == '__main__':
    app.run(debug=True)
